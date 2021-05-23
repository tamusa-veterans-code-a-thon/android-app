package com.example.veterans_code_a_thon_android.models;

import java.util.List;

public class Test {
    List<String> categories;

    public Test(){}

    public Test (List<String> categories) {
        this.categories = categories;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
