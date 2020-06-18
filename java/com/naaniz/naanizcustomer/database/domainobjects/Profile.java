package com.naaniz.naanizcustomer.database.domainobjects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_profile")
public class Profile {

    @NonNull
    @PrimaryKey
    public String UID;

    public String name;
    public String email;
    public String dob;
    public String language;
    public String foodpref;
    public String state;
}
