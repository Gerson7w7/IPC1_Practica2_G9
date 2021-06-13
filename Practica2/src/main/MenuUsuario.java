package main;

import static main.Management.alumnos;
import static main.Management.cursos;
import static main.Management.profesores;
import static main.Management.notas;

public class MenuUsuario {
    
    public MenuUsuario(){

    }
    
    public void menuReportes(){
        Management.reportes.menuReportes(alumnos, profesores, cursos, notas);       
    }

}
