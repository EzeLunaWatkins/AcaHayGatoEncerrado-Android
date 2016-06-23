package model;

public abstract class MinSeleccionable {

    private int id = this.hashCode();

    private String nombre;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
}