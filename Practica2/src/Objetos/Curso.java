package Objetos;

public class Curso {

    private int id;
    private int codigo;
    private String nombre;
    private Alumno[] alumnos;
    //siempre con un contador
    private int calumnos;
    private Profesor profe;

    //notas
    private double[] notas;
    private int cnotas;

    public Curso(int id, int codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.alumnos = new Alumno[200];
        this.calumnos = 0;
        //agregamos al profe
        this.profe = null;
        //-------------
        this.notas = new double[15];
        this.cnotas = 0;
    }

    //Bueno, este es un metodo para que segun el codgio del curso
    //se vayan llenado sus alumnos
    public void AsignarAlumnos(Alumno ingresoAlumno) {
        getAlumnos()[getCalumnos()] = ingresoAlumno;
        setCalumnos(getCalumnos() + 1);
    }

    public void PonerNotas(double nota, int idAlumno) {
        for (int i = 0; i < getCalumnos(); i++) {
            if (getAlumnos()[i].getId() == idAlumno) {
                notas[cnotas] = nota;
                cnotas++;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumno[] alumnos) {
        this.alumnos = alumnos;
    }

    public int getCalumnos() {
        return calumnos;
    }

    public void setCalumnos(int calumnos) {
        this.calumnos = calumnos;
    }

    public Profesor getProfe() {
        return profe;
    }

    public void setProfe(Profesor profe) {
        this.profe = profe;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public int getCnotas() {
        return cnotas;
    }

    public void setCnotas(int cnotas) {
        this.cnotas = cnotas;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
