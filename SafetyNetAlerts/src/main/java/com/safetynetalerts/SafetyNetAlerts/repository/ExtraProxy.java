package com.safetynetalerts.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetynetalerts.SafetyNetAlerts.model.Firestation;
import com.safetynetalerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynetalerts.SafetyNetAlerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class ExtraProxy implements IExtraProxy {
    
    private static final Logger logger = LogManager.getLogger(ExtraProxy.class);
    
    @Autowired
    IRecoveredData recoveredData;
    
    
    public String endpoint1ToJSon(String station) {
        logger.debug("Chosing data for endpoint1");
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        List<Person> personPerGivenFirestation = new ArrayList<>();
        HashMap<String, Integer> childrenAndAdults = new HashMap<>();
        int countChildren = 0;
        int countAdults = 0;
        int age = -1;
        for (Firestation f : firestationList) {
            
            if (f.getStation().equals(station)) {
                for (Person p : personList) {
                    if (f.getAddress().equals(p.getAddress())) {
                        personPerGivenFirestation.add(p);
                        for (MedicalRecord m : medicalRecordList) {
                            if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
                                try {
                                    
                                    LocalDate actualDate = LocalDate.now();
                                    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                    LocalDate birthdateDateFormat = LocalDate.parse(m.getBirthdate(), pattern);
                                    age = Period.between(birthdateDateFormat, actualDate).getYears();
                                    if (age <= 18) {
                                        countChildren++;
                                    } else {
                                        countAdults++;
                                    }
                                    childrenAndAdults.put("children", countChildren);
                                    childrenAndAdults.put("adults", countAdults);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                
                            }
                        }
                    }
                }
            }
        }
        if (age == -1) {
            logger.error("Station number not valid");
        }
        String jsonString = "";
        try {
            logger.debug("Create exit String for endpoint1");
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode personDataNode = mapper.createArrayNode();
            for (Person p : personPerGivenFirestation) {
                ObjectNode personUniqueDataNode = mapper.createObjectNode();
                personUniqueDataNode.put("firstName", p.getFirstName());
                personUniqueDataNode.put("lastName", p.getLastName());
                personUniqueDataNode.put("address", p.getAddress());
                personUniqueDataNode.put("phone", p.getPhone());
                personDataNode.add(personUniqueDataNode);
            }
            
            ObjectNode childrenAndAdultsDataNode = mapper.createObjectNode();
            childrenAndAdultsDataNode.put("children", countChildren);
            childrenAndAdultsDataNode.put("adults", countAdults);
            
            ObjectNode stationCompletNode = mapper.createObjectNode();
            stationCompletNode.put("station", station);
            stationCompletNode.set("persons", personDataNode);
            stationCompletNode.set("childrenAndAdults", childrenAndAdultsDataNode);
            
            jsonString = mapper.writeValueAsString(stationCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 1 is "+jsonString);
        return jsonString;
    }
    
    public String endpoint2ToJSon(String address) {
        logger.debug("Chosing data for endpoint2");
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        
        List<Person> childrenListPerAddress = new ArrayList<>();
        List<Person> adultsListPerAddress = new ArrayList<>();
        List<Integer> childrenAgesPerAddress = new ArrayList<>();
        int loggerIndex=-1;
        for (Person p : personList) {
            if (address.equals(p.getAddress())) {
                loggerIndex=1;
                for (MedicalRecord m : medicalRecordList) {
                    if (p.getFirstName().equals(m.getFirstName()) && p.getLastName().equals(m.getLastName())) {
                        LocalDate actualDate = LocalDate.now();
                        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate birthdateDateFormat = LocalDate.parse(m.getBirthdate(), pattern);
                        int age = Period.between(birthdateDateFormat, actualDate).getYears();
                        if (age <= 18) {
                            childrenListPerAddress.add(p);
                            childrenAgesPerAddress.add(age);
                        } else {
                            adultsListPerAddress.add(p);
                        }
                    }
                }
            }
        }
        if(loggerIndex==-1){
            logger.error("Address not valid");
        }
        
        String jsonString = "";
        try {
            logger.debug("Create String for endpoint2");
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode otherPersonsDataNode = mapper.createArrayNode();
            for (Person p : adultsListPerAddress) {
                ObjectNode otherPersonsUniqueDataNode = mapper.createObjectNode();
                otherPersonsUniqueDataNode.put("firstName", p.getFirstName());
                otherPersonsUniqueDataNode.put("lastName", p.getLastName());
                otherPersonsDataNode.add(otherPersonsUniqueDataNode);
            }
            ArrayNode childrenDataNode = mapper.createArrayNode();
            for (Person p : childrenListPerAddress) {
                ObjectNode childrenUniqueDataNode = mapper.createObjectNode();
                childrenUniqueDataNode.put("firstName", p.getFirstName());
                childrenUniqueDataNode.put("lastName", p.getLastName());
                childrenUniqueDataNode.put("age", childrenAgesPerAddress.get(childrenListPerAddress.indexOf(p)));
                childrenDataNode.add(childrenUniqueDataNode);
            }
            ObjectNode childrenCompletNode = mapper.createObjectNode();
            childrenCompletNode.put("address", address);
            childrenCompletNode.set("childrenData", childrenDataNode);
            childrenCompletNode.set("otherPersonsPerAddress", otherPersonsDataNode);
            jsonString = mapper.writeValueAsString(childrenCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 2 is "+jsonString);
        return jsonString;
    }
    public String endpoint3ToJSon(String station) {
        logger.debug("Chosing data for endpoint3");
        String jSonStringEndpoint1 = endpoint1ToJSon(station);
        String jsonString = "";
        List<String> phoneNumbersPerGivenFirestation = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("Create exit String for endpoint3");
            JsonNode root = mapper.readTree(jSonStringEndpoint1);
            JsonNode personNode = root.path("persons");
            for (JsonNode n : personNode) {
                String phone = n.path("phone").asText();
                phoneNumbersPerGivenFirestation.add(phone);
            }
            ArrayNode phoneNumbersDataNode = mapper.createArrayNode();
            for (String phoneNo : phoneNumbersPerGivenFirestation) {
                phoneNumbersDataNode.add(phoneNo);
            }
            ObjectNode phonesCompletNode = mapper.createObjectNode();
            phonesCompletNode.put("station", station);
            phonesCompletNode.set("phoneNumbers", phoneNumbersDataNode);
            jsonString = mapper.writeValueAsString(phonesCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 3 is "+jsonString);
        return jsonString;
    }
    
    public String endpoint4ToJSon(String address) {
        logger.debug("Chosing data for endpoint4");
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordsList = recoveredData.getMedicalrecords();
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Integer> ages = new ArrayList<>();
        List<Person> personsPerAddress = new ArrayList<>();
        String firestationPerAddress = null;
        int loggerIndex = -1;
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address)) {
                firestationPerAddress = firestation.getStation();
                loggerIndex = 1;
            }
        }
        for (Person p : personList) {
            if (p.getAddress().equals(address)) {
                personsPerAddress.add(p);
                for (MedicalRecord m : medicalRecordsList) {
                    if (p.getFirstName().equals(m.getFirstName()) && p.getLastName().equals(m.getLastName())) {
                        LocalDate actualDate = LocalDate.now();
                        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate birthdateDateFormat = LocalDate.parse(m.getBirthdate(), pattern);
                        ages.add(Period.between(birthdateDateFormat, actualDate).getYears());
                        loggerIndex = 1;
                    }
                }
            }
        }
        if (loggerIndex == -1) {
            logger.error("Address not valid");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("Create exit String for endpoint4");
            ArrayNode personsDataNode = mapper.createArrayNode();
            for (Person p : personsPerAddress) {
                ObjectNode personsUniqueDataNode = mapper.createObjectNode();
                personsUniqueDataNode.put("lastName", p.getLastName());
                personsUniqueDataNode.put("phone", p.getPhone());
                personsUniqueDataNode.put("age", ages.get(personList.indexOf(p)));
                ArrayNode medicationsArrayDataNode = mapper.createArrayNode();
                for (String medication : medicalRecordsList.get(personList.indexOf(p)).getMedications()) {
                    medicationsArrayDataNode.add(medication);
                }
                personsUniqueDataNode.put("medications",
                        medicationsArrayDataNode);
                ArrayNode allergiesArrayDataNode = mapper.createArrayNode();
                for (String allergie : medicalRecordsList.get(personList.indexOf(p)).getAllergies()) {
                    allergiesArrayDataNode.add(allergie);
                }
                personsUniqueDataNode.put("allergies",
                        allergiesArrayDataNode);
                personsDataNode.add(personsUniqueDataNode);
            }
            ObjectNode completNode = mapper.createObjectNode();
            completNode.put("address", address);
            completNode.put("station", firestationPerAddress);
            completNode.set("personsData", personsDataNode);
            jsonString = mapper.writeValueAsString(completNode);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
    
        logger.info("Exit data for endpoint 4 is "+jsonString);
        return jsonString;
    }
    
    public String endpoint5ToJSon(String station) {
        logger.debug("Chosing data for endpoint5");
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordsList = recoveredData.getMedicalrecords();
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Integer> ages = new ArrayList<>();
        List<Person> personsPerFirestation = new ArrayList<>();
        List<MedicalRecord> medicalRecordsPerFirestation = new ArrayList<>();
        List<String> addressesPerFirestation = new ArrayList<>();
        int loggerIndex = -1;
        for (Firestation firestation : firestationList) {
            if (firestation.getStation().equals(station)) {
                loggerIndex = 1;
                for (Person person : personList) {
                    if (firestation.getAddress().equals(person.getAddress())) {
                        if (!addressesPerFirestation.contains(person.getAddress())) {
                            addressesPerFirestation.add(person.getAddress());
                        }
                        personsPerFirestation.add(person);
                        for (MedicalRecord medicalRecord : medicalRecordsList) {
                            if (person.getFirstName().equals(medicalRecord.getFirstName()) && person.getLastName().equals(medicalRecord.getLastName())) {
                                medicalRecordsPerFirestation.add(medicalRecord);
                                LocalDate actualDate = LocalDate.now();
                                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                LocalDate birthdateDateFormat = LocalDate.parse(medicalRecord.getBirthdate(), pattern);
                                ages.add(Period.between(birthdateDateFormat, actualDate).getYears());
                            }
                        }
                    }
                }
            }
        }
        if (loggerIndex == -1) {
            logger.error("Station number not valid");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("Create exit String for endpoint5");
            ArrayNode personAddressDataNode = mapper.createArrayNode();
            for (String address : addressesPerFirestation) {
                ObjectNode personAddressUniqueDataNode = mapper.createObjectNode();
                personAddressUniqueDataNode.put("address", address);
                
                ArrayNode personsNode = mapper.createArrayNode();
                ObjectNode personsUniqueDataNode = mapper.createObjectNode();
                for (Person p : personsPerFirestation) {
                    
                    if (p.getAddress().equals(address)) {
                        
                        personsUniqueDataNode.put("lastName", p.getLastName());
                        personsUniqueDataNode.put("phone", p.getPhone());
                        personsUniqueDataNode.put("age", ages.get(personsPerFirestation.indexOf(p)));
                        
                        ArrayNode medicationsArrayDataNode = mapper.createArrayNode();
                        for (String medication : medicalRecordsPerFirestation.get(personsPerFirestation.indexOf(p)).getMedications()) {
                            medicationsArrayDataNode.add(medication);
                        }
                        personsUniqueDataNode.put("medications",
                                medicationsArrayDataNode);
                        ArrayNode allergiesArrayDataNode = mapper.createArrayNode();
                        for (String allergie : medicalRecordsPerFirestation.get(personsPerFirestation.indexOf(p)).getAllergies()) {
                            allergiesArrayDataNode.add(allergie);
                        }
                        personsUniqueDataNode.put("allergies",
                                allergiesArrayDataNode);
                        personsNode.add(personsUniqueDataNode);
                    }
                    personAddressUniqueDataNode.put("personsPerAddress", personsNode);
                }
                personAddressDataNode.add(personAddressUniqueDataNode);
            }
            ObjectNode stationCompletNode = mapper.createObjectNode();
            stationCompletNode.put("station", station);
            stationCompletNode.set("addressesAndPersons", personAddressDataNode);
            jsonString = mapper.writeValueAsString(stationCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 5 is "+jsonString);
        
        return jsonString;
    }
    
    public String endpoint6ToJSon() {
        logger.debug("Chosing data for endpoint6");
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordsList = recoveredData.getMedicalrecords();
        List<Integer> ages = new ArrayList<>();
        
        for (MedicalRecord m : medicalRecordsList) {
            LocalDate actualDate = LocalDate.now();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate birthdateDateFormat = LocalDate.parse(m.getBirthdate(), pattern);
            ages.add(Period.between(birthdateDateFormat, actualDate).getYears());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("Create exit String for endpoint6");
            ArrayNode personsDataNode = mapper.createArrayNode();
            for (Person p : personList) {
                ObjectNode personsUniqueDataNode = mapper.createObjectNode();
                personsUniqueDataNode.put("lastName", p.getLastName());
                personsUniqueDataNode.put("address", p.getAddress());
                personsUniqueDataNode.put("age", ages.get(personList.indexOf(p)));
                personsUniqueDataNode.put("email", p.getEmail());
                
                ArrayNode medicationsArrayDataNode = mapper.createArrayNode();
                for (String medication : medicalRecordsList.get(personList.indexOf(p)).getMedications()) {
                    medicationsArrayDataNode.add(medication);
                }
                personsUniqueDataNode.put("medications",
                        medicationsArrayDataNode);
                ArrayNode allergiesArrayDataNode = mapper.createArrayNode();
                for (String allergie : medicalRecordsList.get(personList.indexOf(p)).getAllergies()) {
                    allergiesArrayDataNode.add(allergie);
                }
                personsUniqueDataNode.put("allergies",
                        allergiesArrayDataNode.toString());
                personsDataNode.add(personsUniqueDataNode);
            }
            
            jsonString = mapper.writeValueAsString(personsDataNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 6 is "+jsonString);
        return jsonString;
    }
    
    public String endpoint7ToJSon(String city) {
        logger.debug("Chosing data for endpoint7");
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<String> emailPerCity = new ArrayList<>();
        int loggerIndex = -1;
        for (Person p : personList) {
            if (p.getCity().equals(city)) {
                emailPerCity.add(p.getEmail());
                loggerIndex = 1;
            }
        }
        if (loggerIndex == -1) {
            logger.error("City not valid");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("Create exit String for endpoint7");
            ArrayNode emailsDataNode = mapper.createArrayNode();
            for (String email : emailPerCity) {
                emailsDataNode.add(email);
            }
            ObjectNode emailCompletNode = mapper.createObjectNode();
            emailCompletNode.put("city", city);
            emailCompletNode.set("emails", emailsDataNode);
            jsonString = mapper.writeValueAsString(emailCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
        logger.info("Exit data for endpoint 7 is "+jsonString);
        return jsonString;
    }
    
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}
