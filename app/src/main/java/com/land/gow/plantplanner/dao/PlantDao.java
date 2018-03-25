package com.land.gow.plantplanner.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.land.gow.plantplanner.model.Plant;

import java.util.List;

/**
 * Created by becky on 2018-03-22.
 */

@Dao
public interface PlantDao {

    @Query("SELECT * FROM plant")
    List<Plant> getAll();

    @Insert
    void insertAll(Plant... plants);

    @Delete
    void delete(Plant plant);
}