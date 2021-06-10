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
    
    //Simplemente es un metodo para mostrar a los cursos
    public void MostrarCursosProfe() {
        System.out.println("Mi nombre es: " + nombre);
      
        if (ccursos==0) {
            System.out.println("No tengo cursos asignados");
        }else{
            
        
            System.out.println("Imparto un total de " + ccursos + " cursos, siendo estos:");
            for (int i = 0; i < ccursos; i++) {
                System.out.println("Nombre: " + cursos[i].getNombre() + " Id: " + cursos[i].getId());

            }
            System.out.println("-------------------------------------------------------------");
        }   
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
