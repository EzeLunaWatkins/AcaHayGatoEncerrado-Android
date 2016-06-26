package model;

public class MinLaberinto extends MinSeleccionable {

    private String descripcion;
    private Boolean disponibilidad;
    private Boolean enJuego;

    public MinLaberinto() {

        this.disponibilidad = false;
        this.enJuego = false;
    }

    public MinLaberinto(String nombre) {

            if (this.esUnLaberintoValido(nombre)) {
                this.setNombre(nombre);
                this.disponibilidad = false;
                this.enJuego = false;
            }
    }

    public boolean esUnLaberintoValido(String nombreLaberinto) {
        return !nombreLaberinto.isEmpty();
    }

    public Boolean estaDisponibleOEnJuego() { return estaDisponible()||estaEnJuego();}

    public Boolean estaDisponible() { return this.disponibilidad;}

    public Boolean estaEnJuego() { return this.enJuego;}

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
