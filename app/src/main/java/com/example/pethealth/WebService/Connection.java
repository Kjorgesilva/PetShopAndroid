package com.example.pethealth.WebService;

public class Connection {

    private static String url = "http://192.168.1.21:8080/PetHealth/rest/";

    //192.168.1.25 notebook;
    //192.168.1.21 pc casa;

    public static String getUrl() {
        return url;
    }
}