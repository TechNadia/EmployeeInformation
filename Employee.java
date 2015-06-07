package com.example.nadiaakter.employeeinformation;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Nadia Akter on 5/17/2015.
 */
public class Employee implements Parcelable{

    private int id;
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String address;
    private String jobTitle;

    public Employee(Parcel source) {
        /*Log.d("=============", "=="+ source.readInt());
        Log.d("=============", source.readString());
        Log.d("=============", source.readString());
        Log.d("=============", source.readString());
        Log.d("=============", source.readString());
        Log.d("=============", source.readString());
        Log.d("=============", source.readString());*/

        id = source.readInt();
        empId = source.readString();
        firstName = source.readString();
        lastName = source.readString();
        email = source.readString();
        contact = source.readString();
        address = source.readString();
        jobTitle = source.readString();
    }
    public Employee(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return getFirstName()+ " " + getLastName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(empId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(contact);
        dest.writeString(address);
        dest.writeString(jobTitle);
    }

    public static final Creator<Employee> CREATOR
            = new Parcelable.Creator<Employee>(){
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

}
