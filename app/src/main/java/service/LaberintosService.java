package service;

import java.util.List;

import model.MinLaberinto;
import retrofit.Callback;
import retrofit.http.GET;

public interface LaberintosService {

    @GET("/laberintos")
    void getLaberintos(Callback<List<MinLaberinto>> callback);

    @GET("/laberintos/{LaberintoId}")
    void getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<MinLaberinto> callback);
}