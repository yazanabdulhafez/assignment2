package com.brightMinds.assignment2.Models;

import javax.persistence.*;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long employmentId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private long extensionNumber;
    private String employmentType;

    public Employee(){

    }


    public Employee(long employmentId,String firstName, String middleName,
                    String lastName, String mobileNumber,
                    String email, long extensionNumber, String employmentType) {
        this.employmentId=employmentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.extensionNumber = extensionNumber;
        this.employmentType = employmentType;
    }

    public long getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(long employmentId) {
        this.employmentId = employmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(long extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employmentId=" + employmentId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", extensionNumber=" + extensionNumber +
                ", employmentType='" + employmentType + '\'' +
                '}';
    }
}
