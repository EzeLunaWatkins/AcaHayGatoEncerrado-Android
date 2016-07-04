package model;

public class MinLaberinto extends MinSeleccionable {

    private String descripcion;
    private Boolean disponibilidad = false;
    private Boolean enJuego = false;

    public Boolean estaDisponibleOEnJuego() { return estaDisponible()||estaEnJuego();}

    public Boolean estaDisponible() { return this.disponibilidad;}

    public Boolean estaEnJuego() { return this.enJuego;}

    public String getDescripcion() {
        return this.descripcion;
    }
}