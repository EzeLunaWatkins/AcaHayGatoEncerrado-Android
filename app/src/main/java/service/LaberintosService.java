package service;

import java.util.List;

import model.MinInventario;
import model.MinItem;
import model.MinLaberinto;
import retrofit.Callback;
import retrofit.http.GET;

public interface LaberintosService {

    @GET("/laberintos")
    List<MinLaberinto> getLaberintos(Callback<List<MinLaberinto>> callback);

    @GET("/laberintos/{LaberintoId}")
    MinLaberinto getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<MinLaberinto> callback);

    @GET("/inventario")
    List<MinItem> getInventario(Callback<List<MinItem>> callback);

    @GET("/tirar/{ItemId}")
    void tirarItem(@retrofit.http.Path("ItemId") int id, Callback<MinItem> callback);
}