package com.bars.topilskiyanton.imedsister.Patient;

import java.util.Date;

/**
 * Created by Topilskiy Anton on 21.02.2018.
 */

public class ItemPatient {
    private String surname,
            name,
            secname,
            adress,
            gender,
            diagnosis,
            status,
            information;

    private Integer procedures,
            doctor,
            docObservers,
            history;

    private Date dateOfBirth;

    public ItemPatient(String surname, String name, String secname, String gender, String dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.secname = secname;
        this.gender = gender;
       // this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getSecname() {
        return secname;
    }

    public String getAdress() {
        return adress;
    }

    public String getGender() {
        return gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getStatus() {
        return status;
    }

    public String getInformation() {
        return information;
    }

    public Integer getProcedures() {
        return procedures;
    }

    public Integer getDoctor() {
        return doctor;
    }

    public Integer getDocObservers() {
        return docObservers;
    }

    public Integer getHistory() {
        return history;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setProcedures(Integer procedures) {
        this.procedures = procedures;
    }

    public void setDoctor(Integer doctor) {
        this.doctor = doctor;
    }

    public void setDocObservers(Integer docObservers) {
        this.docObservers = docObservers;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
