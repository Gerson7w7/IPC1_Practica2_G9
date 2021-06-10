package Objetos;

public class Alumno extends Persona {

    private int carné;
    private Curso[] cursos;
    //el contador respectivo
    private int ccursos;

    public Alumno(int carné, int id, String nombre, String fechaNac, String genero) {
        super(id, nombre, fechaNac, genero);
        this.carné = carné;
        this.cursos = new Curso[15];
        this.ccursos = 0;
    }

    //Metodo para que al alumno se le llenen sus cursos
    public void AsignarCursos(Curso agregarCurso) {
        getCursos()[getCcursos()] = agregarCurso;
        ccursos++;
    }

    public int getCarné() {
        return carné;
    }

    public void setCarné(int carné) {
        this.carné = carné;
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }

    public int getCcursos() {
        return ccursos;
    }

    public void setCcursos(int ccursos) {
        this.ccursos = ccursos;
    }

    @Override
    public String toString() {
        return "Almuno{" + super.toString() + "carne=" + carné + '}';
    }
}
