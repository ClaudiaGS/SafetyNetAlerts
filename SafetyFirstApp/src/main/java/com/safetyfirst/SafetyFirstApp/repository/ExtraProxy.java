package com.safetyfirst.SafetyFirstApp.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetyfirst.SafetyFirstApp.model.Firestation;
import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import com.safetyfirst.SafetyFirstApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ExtraProxy implements IExtraProxy{
    
    @Autowired
    IRecoveredData recoveredData;
    
    
    public String endpoint1ToJSon(String station) {
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        
        //persons per firestation
        List<Person> personPerGivenFirestation = new ArrayList<>();
        
        HashMap<String, Integer> childrenAndAdults = new HashMap<>();
        int countChildren = 0;
        int countAdults = 0;
        
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
                                    int age = Period.between(birthdateDateFormat, actualDate).getYears();
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
        String jsonString = "";
        try {
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
        }
        return jsonString;
    }
    
    public String endpoint2ToJSon(String address) {
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        
        List<Person> childrenListPerAddress = new ArrayList<>();
        List<Person> adultsListPerAddress = new ArrayList<>();
        List<Integer> childrenAgesPerAddress = new ArrayList<>();
        
        for (Person p : personList) {
            if (address.equals(p.getAddress())) {
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
        
        String jsonString = "";
        try {
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
        }
        return jsonString;
    }
    
    public String endpoint3ToJSon(String station) {
        
        String jSonStringEndpoint1 = endpoint1ToJSon(station);
        String jsonString = "";
        List<String> phoneNumbersPerGivenFirestation = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
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
        }
        return jsonString;
    }
    
    public String endpoint4ToJSon(String address) {
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordsList = recoveredData.getMedicalrecords();
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Integer> ages = new ArrayList<>();
        List<Person> personsPerAddress = new ArrayList<>();
        String firestationPerAddress = null;
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equals(address)) {
                firestationPerAddress = firestation.getStation();
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
                    }
                }
            }
        }
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode personsDataNode = mapper.createArrayNode();
            for (Person p : personsPerAddress) {
                ObjectNode personsUniqueDataNode = mapper.createObjectNode();
                personsUniqueDataNode.put("lastName", p.getLastName());
                personsUniqueDataNode.put("phone", p.getPhone());
                personsUniqueDataNode.put("age", ages.get(personList.indexOf(p)));
                // personsUniqueDataNode.put("station", firestationPerAddress);
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
            completNode.put("station",firestationPerAddress);
            completNode.set("personsData", personsDataNode);
            jsonString = mapper.writeValueAsString(completNode);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        return jsonString;
    }
    
    public String endpoint5ToJSon(String station) {
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<MedicalRecord> medicalRecordsList = recoveredData.getMedicalrecords();
        List<Firestation> firestationList = recoveredData.getFirestations();
        List<Integer> ages = new ArrayList<>();
        List<Person> personsPerFirestation = new ArrayList<>();
        List<MedicalRecord> medicalRecordsPerFirestation = new ArrayList<>();
        List<String> addressesPerFirestation = new ArrayList<>();
        for (Firestation firestation : firestationList) {
            if (firestation.getStation().equals(station)) {
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            
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
                    personAddressUniqueDataNode.put("personsPerAddress",personsNode);
                }
                personAddressDataNode.add(personAddressUniqueDataNode);
            }
            ObjectNode stationCompletNode = mapper.createObjectNode();
            stationCompletNode.put("station", station);
            stationCompletNode.set("addressesAndPersons",personAddressDataNode);
            jsonString = mapper.writeValueAsString(stationCompletNode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        return jsonString;
    }
    
    public String endpoint6ToJSon() {
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
        }
        
        
        return jsonString;
    }
    
    public String endpoint7ToJSon(String city) {
        String jsonString = "";
        List<Person> personList = recoveredData.getPersons();
        List<String> emailPerCity = new ArrayList<>();
        for (Person p : personList) {
            if (p.getCity().equals(city)) {
                emailPerCity.add(p.getEmail());
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
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
        }
        return jsonString;
    }
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}
