package com.land.gow.plantplanner.model;

/**
 * Created by becky on 2018-03-18.
 */

public class RepeatFrequency {
    public enum Type {
        NONE, DAILY,WEEKLY, MONTHLY, ANUALLY, WEEKDAY
    }

    private Type type;
}
