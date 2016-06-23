package model;

public class MinJugador {

    private Integer id = this.hashCode();

    private String nombre;

    private MinInventario inventario;

    public void jugarLaberinto(MinLaberinto laberinto) {

        this.inventario = new MinInventario();
        laberinto.inicializarPartida(this);
    }

    public void quitar(final Integer idItem) {
        this.inventario.quitar(idItem);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MinInventario getInventario() {
        return this.inventario;
    }

    public void setInventario(MinInventario inventario) {
        this.inventario = inventario;
    }
}
