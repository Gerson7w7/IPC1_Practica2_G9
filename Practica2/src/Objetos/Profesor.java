
package Objetos;

public class Profesor extends Persona {
    private int regPersonal;
    private String fContratacion;

    public Profesor(int regPersonal, String fContratacion, int id, String nombre, String fechaNac, String genero) {
        super(id, nombre, fechaNac, genero);
        this.regPersonal = regPersonal;
        this.fContratacion = fContratacion;
    }

    public int getRegPersonal() {
        return regPersonal;
    }

    public void setRegPersonal(int regPersonal) {
        this.regPersonal = regPersonal;
    }

    public String getfContratacion() {
        return fContratacion;
    }

    public void setfContratacion(String fContratacion) {
        this.fContratacion = fContratacion;
    }

    @Override
    public String toString() {
        return "Profesor{" + super.toString() + "regPersonal=" + regPersonal + ", fContratacion=" + fContratacion + '}';
    }
}
