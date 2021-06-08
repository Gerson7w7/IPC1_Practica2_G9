package main;

import java.io.File;
import java.util.Scanner;
import Objetos.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Management {

    public Alumno[] alumnos;
    public Profesor[] profesores;
    public Curso[] cursos;
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
        System.out.println("|    3.Cargar Cursos                                                                       |");
        System.out.println("|                               Ingrese su opción                                          |");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void menu() {
        String datos = "";
        while (true) {
            mHeader();
            int opt = Integer.parseInt(scanner.nextLine());
            switch (opt) {
                case 1:
                    System.out.println("========================= CARGAR ALUMNOS ========================");
                    datos = cargaDatos();
                    asignacionAlumnos(datos);
                    break;
                case 2:
                    System.out.println("========================= CARGAR PROFESORES ========================");
                    datos = cargaDatos();
                    asignacionProfesores(datos);
                    break;
                case 3:
                    System.out.println("========================= CARGAR PROFESORES ========================");
                    datos = cargaDatos();
                    asignacionCursos(datos);
                    break;
                default:
                    System.out.println("Opcion no válida");
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

    private void asignacionAlumnos(String content) {
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

            alumnos[i-1] = new Alumno(id, carnet, nombre, fNacimiento, genero);
        }
    }
    
    private void asignacionProfesores(String content) {
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

            profesores[i-1] = new Profesor(regPersonal, fContratacion, id, nombre, fNacimiento, genero);
        }
    }
    
    private void asignacionCursos(String content) {
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

            cursos[i-1] = new Curso(id, codigo, nombre);
        }
    }
}
