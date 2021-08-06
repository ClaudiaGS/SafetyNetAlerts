package com.safetyfirst.SafetyFirstApp.repository;

import com.safetyfirst.SafetyFirstApp.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsProxy implements IMedicalRecordsProxy {
    @Autowired
    IRecoveredData recoveredData;
    
    @Override
    public List<MedicalRecord> readMedicalRecords() {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        System.out.println("medical records list is " + medicalRecords);
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        MedicalRecord medicalRecordToDelete = null;
        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
                medicalRecordToDelete = m;
            }
        }
        medicalRecords.remove(medicalRecordToDelete);
        return medicalRecords;
    }
    
    @Override
    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, List<String> newMedications, List<String> newAllergies) {
        List<MedicalRecord> medicalRecordList = recoveredData.getMedicalrecords();
        for (MedicalRecord m : medicalRecordList) {
            List<String>medications=m.getMedications();
            List<String>allergies=m.getAllergies();
            if (m.getFirstName() .equals(firstName) && m.getLastName().equals(lastName)) {
                // if (!newMedications.isEmpty()) {
                
                for (String newMedication : newMedications) {
                    medications.add(newMedication);
                }
//                } else {
//                    m.setMedications(null);
//                }
//                if (!newAllergies.isEmpty()) {
                for (String newAllergie : newAllergies) {
                    allergies.add(newAllergie);
                }
//                } else {
//                    m.setAllergies(null);
//                }
            }
            
        }
        return medicalRecordList;
    }
    
    //    @Override
//    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, HashMap<String, String> params) {
//        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
//        for (MedicalRecord m : medicalRecords) {
//            if (m.getFirstName().equals(firstName) && m.getLastName().equals(lastName)) {
//                for (Map.Entry<String, String> entry : params.entrySet()) {
//                    switch (entry.getKey()) {
//                        case "birthdate":
//                            m.setBirthdate(entry.getValue());
//                            break;
//                        case "medications":
//                            try {
//                                ObjectMapper mapper = new ObjectMapper();
//                                List<String> newMedications = mapper.readValue(entry.getValue(), new TypeReference<List<String>>() {
//                                });
//                                System.out.println("newMedications"+newMedications);
//                                if (newMedications != null) {
//                                    for (String medication : newMedications) {
//                                        m.getMedications().add(medication);
//                                    }
//                                } else {
//                                    m.setMedications(null);
//                                }
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        case "allergies":
//                            try {
//                                ObjectMapper mapper = new ObjectMapper();
//                                List<String> newAllergies = mapper.readValue(entry.getValue(), new TypeReference<List<String>>() {
//                                });
//                                if (newAllergies != null) {
//                                    for (String allergie : newAllergies) {
//                                        m.getAllergies().add(allergie);
//                                    }
//                                } else {
//                                    m.setAllergies(null);
//                                }
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());
//                            }
//                            break;
//                        default:
//                            System.out.println("Trying to modify unexisting parameter");
//                            break;
//                    }
//                }
//            }
//        }
//        return medicalRecords;
//
//    }
//
    @Override
    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = recoveredData.getMedicalrecords();
        medicalRecords.add(medicalRecord);
        return medicalRecords;
    }
    
    @Override
    public void setRecoveredData(IRecoveredData recoveredData) {
        this.recoveredData = recoveredData;
    }
}