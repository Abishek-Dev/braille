package com.example.braille;

import android.icu.util.Measure;

public class GetHistory {

    // variables for storing our image and name.
    private String Message;
    private String Date;
    private String Time;

    public GetHistory() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    public GetHistory(String Message, String Time,String Date) {
        this.Message = Message;
        this.Time = Time;
        this.Date = Date;
    }

    // getter and setter methods
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

}
