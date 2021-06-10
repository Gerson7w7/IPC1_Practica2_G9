package main;

import static main.Management.alumnos;
import static main.Management.cursos;
import static main.Management.profesores;

public class MenuUsuario {
    
    public MenuUsuario(){

    }
    
    public void menuReportes(){
        Management.reportes.menuReportes(alumnos, profesores, cursos);       
    }

}
