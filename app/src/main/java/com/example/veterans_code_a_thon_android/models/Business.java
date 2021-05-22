package com.example.veterans_code_a_thon_android.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Business {
    String email;//Business email
    String site;//Business website
    String address1;//Address 1
    String address2;//Address 2
    String city;//City location
    String territory;//State/territory location
    int fax;//Business fax num
    int phoneNum;//Business phone num
    int established;//Year established
    //Verification date and expiration
    boolean isDisabledOwned;
    boolean isWomanOwned;
    boolean isMinorityOwned;

    public Business (JSONObject jsonObject) throws JSONException {
        //Set model values based on json
    }


}
