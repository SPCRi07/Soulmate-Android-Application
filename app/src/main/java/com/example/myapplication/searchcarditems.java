package com.example.myapplication;

public class searchcarditems {

    private String Name;
    private String imageurl;
    private String profileid;
    private String age;
        searchcarditems(String name, String imageurl, String profileid, String age){
            this.Name=name;
            this.imageurl=imageurl;
            this.profileid=profileid;
            this.age=age;
        }

    public String getName() {
            return Name;
    }

    public String getImage() {
        return imageurl;
    }

    public String getProfileid() {
        return profileid;
    }

    public String getAge() {
        return age;
    }
}
