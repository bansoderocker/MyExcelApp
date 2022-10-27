package com.project.myexcelapp.Model;

import java.util.List;

public class Party {

    String SrNo,PName,PContact,PAddress,PageNumber;


    public Party( String PName, String PContact, String PAddress, String pageNumber){
        this.PName = PName;
        this.PContact = PContact;
        this.PAddress = PAddress;
        PageNumber = pageNumber;
    }

    public Party() {

    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPContact() {
        return PContact;
    }

    public void setPContact(String PContact) {
        this.PContact = PContact;
    }

    public String getPAddress() {
        return PAddress;
    }

    public void setPAddress(String PAddress) {
        this.PAddress = PAddress;
    }

    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String pageNumber) {
        PageNumber = pageNumber;
    }
}
