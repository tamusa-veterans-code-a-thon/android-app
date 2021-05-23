package com.example.veterans_code_a_thon_android.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Business {
    int duns;
    String address1;
    String address2;
    String categories;
    String city;
    boolean disabled;
    String email;
    int established;
    String expiration;
    String fax;
    boolean minority;
    String name;
    String phone;
    String state;
    String url;
    String verified;
    boolean woman;

    public boolean isWoman() {
        return woman;
    }

    public void setWoman(boolean woman) {
        this.woman = woman;
    }

    public boolean isMinority() {
        return minority;
    }

    public void setMinority(boolean minority) {
        this.minority = minority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstablished() {
        return established;
    }

    public void setEstablished(int established) {
        this.established = established;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public int getDuns() {
        return duns;
    }

    public void setDuns(int duns) {
        this.duns = duns;
    }

    public Business () {}

//    public Bussiness () {
//
//    }
}
