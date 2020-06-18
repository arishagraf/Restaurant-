package com.naaniz.naanizcustomer.ui.main.fragments.profile;

import com.naaniz.naanizcustomer.R;

public class ProfileHeader extends ProfileItem {
    private String name;
    private String ph_no;

    ProfileHeader(String name, String ph_no){
        super.type = 1;
        super.navigateAction =R.id.action_profileFragment2_to_editProfile;
        this.name = name;
        this.ph_no = ph_no;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
