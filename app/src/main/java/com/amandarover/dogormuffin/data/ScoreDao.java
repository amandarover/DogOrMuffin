package com.amandarover.dogormuffin.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.amandarover.dogormuffin.data.Score;

import java.util.List;

@Dao
public interface ScoreDao {
    @Insert
    void insert(Score score);

    @Query("SELECT * FROM Score ORDER BY points DESC")
    List<Score> fetchAll();

    @Query("SELECT * FROM Score ORDER BY points DESC LIMIT 1")
    Score fetchHighest();
}
