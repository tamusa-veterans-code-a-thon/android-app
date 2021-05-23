package com.example.veterans_code_a_thon_android.models;

import java.util.ArrayList;
import java.util.List;

public class Test {
    String categories;
    String email;

    public Test(){}

    public Test (String categories, String email) {
        this.categories = categories;
        this.email = email;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
