package com.project.myexcelapp.Model;

public class ModelClass {

    String Name, Address, Phone;

    public ModelClass(String name, String address, String phone) {
        Name = name;
        Address = address;
        Phone = phone;
    }
    public ModelClass(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
