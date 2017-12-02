package com.amandarover.dogormuffin.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Score implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public int points = 0;
    public String userName;

    public String toString() {
        return userName + "\t" + points;
    }
}
