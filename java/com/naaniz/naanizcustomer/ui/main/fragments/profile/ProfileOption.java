package com.naaniz.naanizcustomer.ui.main.fragments.profile;

public class ProfileOption extends ProfileItem {
    private String option_label;
    public ProfileOption(String option_label, int navigateAction) {
        super.type = 2;
        super.navigateAction = navigateAction;
        this.option_label = option_label;
    }

    public String getOption_label() {
        return option_label;
    }

    public void setOption_label(String option_label) {
        this.option_label = option_label;
    }
}
