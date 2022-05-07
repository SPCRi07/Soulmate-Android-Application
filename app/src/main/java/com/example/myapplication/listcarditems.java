package com.example.myapplication;

import android.graphics.Bitmap;

public class listcarditems {

    private String Name;
    private String imageurl;
    private String profileid;
    private String profileidrestricted;

        listcarditems(String name, String imageurl, String profile){
            this.Name=name;
            this.imageurl=imageurl;
            this.profileid=profile;
        }
    public String getName() {
            return Name;
    }

    public String getImage() {
        return imageurl;
    }

    public String getProfileidrestricted() {
        return profileidrestricted;
    }

    public String getProfileid() {
        return profileid;
    }
}
