package com.naaniz.naanizcustomer.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.naaniz.naanizcustomer.database.domainobjects.Profile;

@Dao
public interface DataDao {

    @Insert
    void insertProfile(Profile profile);

    @Query("SELECT * FROM my_profile")
    Profile getMyProfile();

}
