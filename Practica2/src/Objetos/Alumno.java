
package Objetos;

public class Alumno extends Persona  {
    private int carné;

    public Alumno(int carné, int id, String nombre, String fechaNac, String genero) {
        super(id, nombre, fechaNac, genero);
        this.carné = carné;
    }

    public int getCarné() {
        return carné;
    }

    public void setCarné(int carné) {
        this.carné = carné;
    }

    @Override
    public String toString() {
        return "Almuno{" + super.toString() + "carne=" + carné + '}';
    }    
}
