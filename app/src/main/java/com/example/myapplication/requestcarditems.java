package com.example.myapplication;

public class requestcarditems {

    private String Name;
    private String imageurl;
    private String profileid;
    private String age;
    private String email;
        requestcarditems(String name, String imageurl, String profileid, String age,String email){
            this.Name=name;
            this.imageurl=imageurl;
            this.profileid=profileid;
            this.age=age;
            this.email=email;
        }

    public String getEmail() {
        return email;
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
