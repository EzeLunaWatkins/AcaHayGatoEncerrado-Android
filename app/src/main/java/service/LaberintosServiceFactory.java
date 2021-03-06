package service;

import retrofit.RestAdapter;

public class LaberintosServiceFactory {

    static public String SERVER_IP = "10.9.0.185";
    static public String API_URL = "http://"+ SERVER_IP +":9777";

    static public LaberintosService createLaberintosService(){

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        return restAdapter.create(LaberintosService.class);
    }
}