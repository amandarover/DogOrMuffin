package com.amandarover.dogormuffin.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Score {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public int points;
}
