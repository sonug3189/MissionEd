package com.missionedappdev.missoned.utility;

import android.app.Application;

public class StudentAPI extends Application
{
    private String Username,UserID;
    private static StudentAPI instance;

    public static StudentAPI getInstance() {
        if(instance==null) {
            instance=new StudentAPI();
        }
        return instance;
    }

    public StudentAPI() {  /* default constructor needed for firebase */ }

    public String getUserID() {
        return UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
}
