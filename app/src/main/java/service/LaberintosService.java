package service;

import java.util.List;

import model.MinInventario;
import model.MinItem;
import model.MinLaberinto;
import retrofit.Callback;
import retrofit.http.GET;

public interface LaberintosService {

    @GET("/laberintos")
    void getLaberintos(Callback<List<MinLaberinto>> callback);

    @GET("/laberintos/{LaberintoId}")
    void getLaberinto(@retrofit.http.Path("LaberintoId") String id, Callback<MinLaberinto> callback);

    @GET("/inventario")
    void getInventario(Callback<List<MinInventario>> callback);

    @GET("/tirar/{ItemId}")
    void tirarItem(@retrofit.http.Path("ItemId") String id, Callback<MinItem> callback);
}