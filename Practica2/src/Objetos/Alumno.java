package Objetos;

public class Alumno extends Persona {

    private int carné;
    private Curso[] cursos;
    //el contador respectivo
    private int ccursos;
    //notas
    private double[] notas;
    private int cnotas;

    public Alumno(int id, int carné, String nombre, String fechaNac, String genero) {
        super(id, nombre, fechaNac, genero);
        this.carné = carné;
        this.cursos = new Curso[15];
        this.ccursos = 0;
        this.notas = new double[15];
        this.cnotas = 0;
    }

    //Metodo para que al alumno se le llenen sus cursos
    public void AsignarCursos(Curso agregarCurso) {
        getCursos()[getCcursos()] = agregarCurso;
        ccursos++;
    }
    
//    public void PonerNotas(double nota, int idCurso) {
//        for (int i = 0; i < getCcursos(); i++) {
//            if (getCursos()[i].getId() == idCurso) {
//                notas[cnotas] = nota;
//                cnotas++;
//            }
//        }
//    }
    
    //Simplemente es un metodo para mostrar a los cursos
    public void MostrarCursos() {
        System.out.println("Mi nombre es: " + nombre);
        if (ccursos == 0) {
            System.out.println("No tengo cursos asignados");
        } else {

            System.out.println("Tengo estos cursos asignados" + getCcursos() + ", siendo estos:");
            for (int i = 0; i < getCcursos(); i++) {
                System.out.println("Nombre: " + getCursos()[i].getNombre() + " Id: " + getCursos()[i].getId());

                System.out.println("-------------------------------------------------------------");

            }
        }
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
