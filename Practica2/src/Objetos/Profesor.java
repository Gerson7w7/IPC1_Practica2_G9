package Objetos;

public class Profesor extends Persona {

    private int regPersonal;
    private String fContratacion;
    //Metamosle todos los cursos que puedan venir al profe xd
    private Curso[] cursos;
    //el contador respectivo
    private int ccursos;

    public Profesor(int regPersonal, String fContratacion, int id, String nombre, String fechaNac, String genero) {
        super(id, nombre, fechaNac, genero);
        this.regPersonal = regPersonal;
        this.fContratacion = fContratacion;
        //agregando al constructor el arreglo y el contador
        this.cursos = new Curso[15];
        this.ccursos = 0;
    }

    public void AsignarCursosProfe(Curso agregarCurso) {
        cursos[ccursos] = agregarCurso;
        ccursos++;
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
        return "Profesor{" + super.toString() + "regPersonal=" + regPersonal + ", fContratacion=" + fContratacion + '}';
    }
}
