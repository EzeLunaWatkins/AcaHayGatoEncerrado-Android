package service;

import retrofit.RestAdapter;

public class LaberintosServiceFactory {

    static public String SERVER_IP = "181.44.153.29";
    static public String API_URL = "http://" + SERVER_IP + ":9777";

    static public LaberintosService createLaberintosService(){

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API_URL).build();
        LaberintosService laberintosService = restAdapter.create(LaberintosService.class);
        return laberintosService;
    }
}
