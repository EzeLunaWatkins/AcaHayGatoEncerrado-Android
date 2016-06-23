package model;

public class MinLaberinto extends MinSeleccionable {

    private String descripcion;

    private Boolean disponibilidad;

    public MinLaberinto() {
        this.disponibilidad = false;
    }

    public MinLaberinto(String nombre) {

            if (this.esUnLaberintoValido(nombre)) {
                this.setNombre(nombre);
                this.disponibilidad = false;
            }
    }

    public boolean esUnLaberintoValido(String nombreLaberinto) {
        return !nombreLaberinto.isEmpty();
    }

    public Boolean estaDisponible() {
        return this.disponibilidad;
    }

    public void inicializarPartida(MinJugador jugador) {
        this.disponibilidad = false;
    }

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
