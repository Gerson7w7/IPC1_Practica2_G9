package main;

import java.io.File;
import java.util.Scanner;
import Objetos.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Management {

    static int cAlumnos = 0;
    static int cProfesores = 0;
    static int cCursos = 0;
    public static Alumno[] alumnos;
    public static Profesor[] profesores;
    public static Curso[] cursos;
    public static Scanner scanner = new Scanner(System.in);

    public Management() {
        alumnos = new Alumno[100];
        profesores = new Profesor[20];
        cursos = new Curso[15];
    }

    private void mHeader() {
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("|                                   BIENVENIDOS                                            |");
        System.out.println("|  Ingrese la opcion deseada                                                               |");
        System.out.println("|    1.Cargar Alumnos                                     2.Cargar Profesores              |");
        System.out.println("|    3.Cargar Cursos                                      4.Asignación de alumnos          |");
        System.out.println("|    5.Asignación de profesores                           6.Carga de notas                 |");
        System.out.println("|    7.Agregar usuario al sistema                         8.Reportes                       |");
        System.out.println("|    9.Cerrar sesión                                                                       |");
        System.out.println("|                               Ingrese su opción                                          |");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void menu() {
        String datos = "";
        while (true) {
            try {
                mHeader();
                int opt = Integer.parseInt(scanner.nextLine());
                switch (opt) {
                    case 1:
                        System.out.println("========================= CARGAR ALUMNOS ========================");
                        datos = cargaDatos();
                        cargaAlumnos(datos);
                        break;
                    case 2:
                        System.out.println("========================= CARGAR PROFESORES ========================");
                        datos = cargaDatos();
                        cargaProfesores(datos);
                        break;
                    case 3:
                        System.out.println("========================= CARGAR CURSOS ========================");
                        datos = cargaDatos();
                        cargaCursos(datos);
                        break;
                    case 4:
                        System.out.println("========================= ASIGNACIÓN ALUMNOS ========================");
                        datos = cargaDatos();
                        asignacionAlumnos(datos);
                        break;
                    case 5:
                        System.out.println("========================= ASIGNACIÓN PROFESORES ========================");
                        datos = cargaDatos();
                        asignacionProfesores(datos);
                        break;
                    case 6:
                        System.out.println("========================= CARGA DE NOTAS ========================");
                        datos = cargaDatos();
                        cargaNotas(datos);
                        break;
                    case 7:
                        System.out.println("========================= AGREGAR USUARIO AL SISTEMA ========================");
                        //HACER
                        break;
                    case 8:
                        System.out.println("========================= REPORTES ========================");
                        Reportes reportes = new Reportes();
                        reportes.menuReportes();
                        break;
                    case 9:
                        //cerrar seción
                        break;
                    default:
                        System.out.println("Opcion no válida");
                }               
            } catch (NumberFormatException e) {
                System.out.println("Intente solo con números.");
            }
        }

    }

    private String cargaDatos() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            System.out.println("Ingrese el nombre del archivo: ");
            String ruta = scanner.nextLine();
            //Leyendo el csv
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //Guardando los datos del csv
            String content = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceAll(" ", "");
                content += linea + "\n";
            }
            return content;
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private void cargaAlumnos(String content) {
        try {
            //Partiendo cada dato por medio de punto y coma (;)
            String filas[] = content.split("\n");
            int cantidadDatos = filas.length - 1;
            String[] columnas = filas[0].split(";");
            //asignando cada dato a un atributo de la clase correspondiente
            for (int i = 1; i < filas.length; i++) {
                columnas = filas[i].split(";");
                int id = Integer.parseInt(columnas[0]);
                int carnet = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];
                String fNacimiento = columnas[3];
                String genero = columnas[4];

                alumnos[i - 1] = new Alumno(id, carnet, nombre, fNacimiento, genero);
                cAlumnos++;
            }
            System.out.println("Los alumnos han sido cargado con éxito :D");
        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        }
    }

    private void cargaProfesores(String content) {
        try {
            //Partiendo cada dato por medio de punto y coma (;)
            String filas[] = content.split("\n");
            int cantidadDatos = filas.length - 1;
            String[] columnas = filas[0].split(";");
            //asignando cada dato a un atributo de la clase correspondiente
            for (int i = 1; i < filas.length; i++) {
                columnas = filas[i].split(";");
                int id = Integer.parseInt(columnas[0]);
                int regPersonal = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];
                String fNacimiento = columnas[3];
                String fContratacion = columnas[4];
                String genero = columnas[5];

                profesores[i - 1] = new Profesor(regPersonal, fContratacion, id, nombre, fNacimiento, genero);
                cProfesores++;
            }
            System.out.println("Los profesores han sido cargado con éxito :D");
        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        }
    }

    private void cargaCursos(String content) {
        try {
            //Partiendo cada dato por medio de punto y coma (;)
            String filas[] = content.split("\n");
            int cantidadDatos = filas.length - 1;
            String[] columnas = filas[0].split(";");
            //asignando cada dato a un atributo de la clase correspondiente
            for (int i = 1; i < filas.length; i++) {
                columnas = filas[i].split(";");
                int id = Integer.parseInt(columnas[0]);
                int codigo = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];

                cursos[i - 1] = new Curso(id, codigo, nombre);
                cCursos++;
            }
            System.out.println("Los cursos han sido cargado con éxito :D");
        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        }
    }

    private void asignacionAlumnos(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        int cantidadDatos = filas.length - 1;
        String[] columnas = filas[0].split(";");
        //asignando cada dato a un atributo de la clase correspondiente
        for (int i = 1; i < filas.length; i++) {
            columnas = filas[i].split(";");
            int idAlumno = Integer.parseInt(columnas[0]);
            int idCurso = Integer.parseInt(columnas[1]);
            int posicionAlumno = 0;
            for (int j = 0; j < cAlumnos; j++) {
                if (alumnos[j].getId() == idAlumno) {
                    posicionAlumno = j;
                }
            }
            int posicionCurso = 0;
            for (int j = 0; j < cCursos; j++) {
                if (cursos[j].getId() == idCurso) {
                    posicionCurso = j;
                }
            }
            //Vale, con esto estamos metiendo los alumnos al curso
            cursos[posicionCurso].AsignarAlumnos(alumnos[posicionAlumno]);
            alumnos[posicionAlumno].AsignarCursos(cursos[posicionCurso]);

        }
        System.out.println("Los alumnos han sido asignados con éxito :D");
    }
    
    private void asignacionProfesores(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        int cantidadDatos = filas.length - 1;
        String[] columnas = filas[0].split(";");
        //asignando cada dato a un atributo de la clase correspondiente
        for (int i = 1; i < filas.length; i++) {
            columnas = filas[i].split(";");
            int idProfesor = Integer.parseInt(columnas[0]);
            int idCurso = Integer.parseInt(columnas[1]);

            int posicionProfesor = 0;
            for (int j = 0; j < cProfesores; j++) {
                if (profesores[j].getId() == idProfesor) {
                    posicionProfesor = j;
                }
            }
            int posicionCurso = 0;
            for (int j = 0; j < cCursos; j++) {
                if (cursos[j].getId() == idCurso) {
                    posicionCurso = j;
                }
            }
                cursos[posicionCurso].setProfe(profesores[posicionProfesor]);
                profesores[posicionProfesor].AsignarCursosProfe(cursos[posicionCurso]);
        }              
        System.out.println("Los profesores han sido asignados con éxito :D");
    }
    
    private void cargaNotas(String content) {
        try {
            //Partiendo cada dato por medio de punto y coma (;)
            String filas[] = content.split("\n");
            int cantidadDatos = filas.length - 1;
            String[] columnas = filas[0].split(";");
            //asignando cada dato a un atributo de la clase correspondiente
            for (int i = 1; i < filas.length; i++) {
                columnas = filas[i].split(";");
                int idAlumno = Integer.parseInt(columnas[0]);
                int idCurso = Integer.parseInt(columnas[1]);
                double nota = Double.parseDouble(columnas[2]);

                int posicionCurso = 0;
                for (int j = 0; j < cAlumnos; j++) {
                    if (alumnos[j].getId() == idCurso) {
                        posicionCurso = j;
                    }
                }
                cursos[posicionCurso].PonerNotas(nota, idAlumno);
            }
            System.out.println("Las notas han sido cargadas con éxito :D");
        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la carga de archivos");
        }
    }
}
