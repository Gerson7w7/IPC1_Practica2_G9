package Objetos;

public class Persona {
    protected int id;
    protected String nombre;
    protected String fechaNac;
    protected String genero;
    protected String hola;

    public Persona(int id, String nombre, String fechaNac, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.genero = genero;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
}
