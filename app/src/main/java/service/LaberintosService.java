package service;

import java.util.List;

import model.MinInventario;
import model.MinItem;
import model.MinLaberinto;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface LaberintosService {

    @GET("/laberintos")
    void getLaberintos(Callback<List<MinLaberinto>> callback);

    @GET("/laberintos/{laberintoId}")
    void getLaberinto(@Path("laberintoId") String laberintoId, Callback<MinLaberinto> callback);

    @GET("/inventario")
    void getInventario(Callback<List<MinItem>> callback);

    @GET("/tirar/{ItemId}")
    void tirarItem(@retrofit.http.Path("ItemId") int id, Callback<MinItem> callback);
}