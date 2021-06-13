package main;

import java.io.File;
import java.util.Scanner;
import Objetos.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Management {

    public static Usuario usuario1 = null, usuario2 = null, usuario3 = null, usuario4 = null, usuario5 = null;
    public static Alumno[] alumnos;
    public static Profesor[] profesores;
    public static Curso[] cursos;
    public static Nota[] notas;
    public static Reportes reportes = new Reportes();
    public static Scanner scanner = new Scanner(System.in);
    public static LocalDateTime fechaHoraActuales = LocalDateTime.now();

    public Management() {
        alumnos = new Alumno[100];
        profesores = new Profesor[20];
        cursos = new Curso[15];
        notas = new Nota[200];
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
        String datos;
        boolean cerrarSesion = true;
        while (cerrarSesion) {
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
                        registroUsarios();
                        break;
                    case 8:
                        System.out.println("========================= REPORTES ========================");
                        reportes.menuReportes(alumnos, profesores, cursos, notas);
                        break;
                    case 9:
                        cerrarSesion = false;
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
        } catch (FileNotFoundException e) {
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
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        String[] columnas = filas[0].split(",");
        //asignando cada dato a un atributo de la clase correspondiente       
        for (int i = 1; i < filas.length; i++) {
            try {
                columnas = filas[i].split(",");
                int id = Integer.parseInt(columnas[0]);
                int carnet = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];
                String fNacimiento = columnas[3];
                String genero = columnas[4];
                if (genero.equals("M") || genero.equals("F")) {
                    for (int j = 0; j < alumnos.length; j++) {
                        if (alumnos[j] == null) {
                            alumnos[j] = new Alumno(id, carnet, nombre, fNacimiento, genero);
                            for (int k = 0; k < alumnos.length - 1; k++) {
                                for (int l = k + 1; l < alumnos.length; l++) {
                                    if (alumnos[k] != null) {
                                        if (alumnos[l] != null) {
                                            if (alumnos[k].getId() == alumnos[l].getId()) {
                                                String log = "Fecha: " + fechaHoraActuales
                                                        + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                                addToEndFile("Logs.log", log);
                                                System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                                alumnos[l] = null;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            for (int k = 0; k < alumnos.length - 1; k++) {
                                for (int l = k + 1; l < alumnos.length; l++) {
                                    if (alumnos[k] != null) {
                                        if (alumnos[l] != null) {
                                            if (alumnos[k].getCarné() == alumnos[l].getCarné()) {
                                                String log = "Fecha: " + fechaHoraActuales
                                                        + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                                addToEndFile("Logs.log", log);
                                                System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                                alumnos[l] = null;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                } else {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió un género inexistente en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nNo se ha definido un género: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            } catch (NumberFormatException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            }
        }
        System.out.println("Los alumnos han sido cargado con éxito :D");
    }

    private void cargaProfesores(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        String[] columnas = filas[0].split(",");
        //asignando cada dato a un atributo de la clase correspondiente
        for (int i = 1; i < filas.length; i++) {
            try {
                columnas = filas[i].split(",");
                int id = Integer.parseInt(columnas[0]);
                int regPersonal = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];
                String fNacimiento = columnas[3];
                String fContratacion = columnas[4];
                String genero = columnas[5];

                if (genero.equals("M") || genero.equals("F")) {
                    for (int j = 0; j < profesores.length; j++) {
                        if (profesores[j] == null) {
                            profesores[j] = new Profesor(regPersonal, fContratacion, id, nombre, fNacimiento, genero);
                            for (int k = 0; k < profesores.length - 1; k++) {
                                for (int l = k + 1; l < profesores.length; l++) {
                                    if (profesores[k] != null) {
                                        if (profesores[l] != null) {
                                            if (profesores[k].getId() == profesores[l].getId()) {
                                                String log = "Fecha: " + fechaHoraActuales
                                                        + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                                addToEndFile("Logs.log", log);
                                                System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                                profesores[l] = null;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            for (int k = 0; k < profesores.length - 1; k++) {
                                for (int l = k + 1; l < profesores.length; l++) {
                                    if (profesores[k] != null) {
                                        if (profesores[l] != null) {
                                            if (profesores[k].getRegPersonal() == profesores[l].getRegPersonal()) {
                                                String log = "Fecha: " + fechaHoraActuales
                                                        + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                                addToEndFile("Logs.log", log);
                                                System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                                profesores[l] = null;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                } else {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió un género inexistente en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nNo se ha definido un género: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            } catch (NumberFormatException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            }
        }
        System.out.println("Los profesores han sido cargado con éxito :D");
    }

    private void cargaCursos(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        String[] columnas = filas[0].split(",");
        //asignando cada dato a un atributo de la clase correspondiente
        for (int i = 1; i < filas.length; i++) {
            try {
                columnas = filas[i].split(",");
                int id = Integer.parseInt(columnas[0]);
                int codigo = Integer.parseInt(columnas[1]);
                String nombre = columnas[2];

                for (int j = 0; j < cursos.length; j++) {
                    if (cursos[j] == null) {
                        cursos[j] = new Curso(id, codigo, nombre);
                        for (int k = 0; k < cursos.length - 1; k++) {
                            for (int l = k + 1; l < cursos.length; l++) {
                                if (cursos[k] != null) {
                                    if (cursos[l] != null) {
                                        if (cursos[k].getId() == cursos[l].getId()) {
                                            String log = "Fecha: " + fechaHoraActuales
                                                    + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                            addToEndFile("Logs.log", log);
                                            System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                            cursos[l] = null;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        for (int k = 0; k < cursos.length - 1; k++) {
                            for (int l = k + 1; l < cursos.length; l++) {
                                if (cursos[k] != null) {
                                    if (cursos[l] != null) {
                                        if (cursos[k].getCodigo() == cursos[l].getCodigo()) {
                                            String log = "Fecha: " + fechaHoraActuales
                                                    + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                            addToEndFile("Logs.log", log);
                                            System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                            cursos[l] = null;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            }
        }
        System.out.println("Los cursos han sido cargado con éxito :D");
    }

    private void asignacionAlumnos(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        String[] columnas = filas[0].split(",");
        //asignando cada dato a un atributo de la clase correspondiente
        if (filas.length <= 200) {
            for (int i = 1; i < filas.length; i++) {
                try {
                    columnas = filas[i].split(",");
                    int idAlumno = Integer.parseInt(columnas[0]);
                    int idCurso = Integer.parseInt(columnas[1]);
                    int posicionAlumno = -1;
                    for (int j = 0; j < alumnos.length; j++) {
                        if (alumnos[j] != null) {
                            if (alumnos[j].getId() == idAlumno) {
                                posicionAlumno = j;
                            }
                        }
                    }
                    int posicionCurso = -1;
                    for (int j = 0; j < cursos.length; j++) {
                        if (cursos[j] != null) {
                            if (cursos[j].getId() == idCurso) {
                                posicionCurso = j;
                            }
                        }
                    }
                    if (posicionAlumno >= 0) {
                        if (posicionCurso >= 0) {
                            //Vale, con esto estamos metiendo los alumnos al curso
                            cursos[posicionCurso].AsignarAlumnos(alumnos[posicionAlumno]);
                            alumnos[posicionAlumno].AsignarCursos(cursos[posicionCurso]);
                        }
                    }
                } catch (NumberFormatException e) {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
                }
            }
        } else {
            for (int i = 1; i < 201; i++) {
                try {
                    columnas = filas[i].split(",");
                    int idAlumno = Integer.parseInt(columnas[0]);
                    int idCurso = Integer.parseInt(columnas[1]);
                    int posicionAlumno = -1;
                    for (int j = 0; j < alumnos.length; j++) {
                        if (alumnos[j] != null) {
                            if (alumnos[j].getId() == idAlumno) {
                                posicionAlumno = j;
                            }
                        }
                    }
                    int posicionCurso = -1;
                    for (int j = 0; j < cursos.length; j++) {
                        if (cursos[j] != null) {
                            if (cursos[j].getId() == idCurso) {
                                posicionCurso = j;
                            }
                        }
                    }
                    if (posicionAlumno >= 0) {
                        if (posicionCurso >= 0) {
                            //Vale, con esto estamos metiendo los alumnos al curso
                            cursos[posicionCurso].AsignarAlumnos(alumnos[posicionAlumno]);
                            alumnos[posicionAlumno].AsignarCursos(cursos[posicionCurso]);
                        }
                    }
                } catch (NumberFormatException e) {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
                }
            }
        }
        System.out.println("Los alumnos han sido asignados con éxito :D");
    }

    private void asignacionProfesores(String content) {
        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        String[] columnas = filas[0].split(",");
        //asignando cada dato a un atributo de la clase correspondiente
        if (filas.length <= 30) {
            for (int i = 1; i < filas.length; i++) {
                try {
                    columnas = filas[i].split(",");
                    int idProfesor = Integer.parseInt(columnas[0]);
                    int idCurso = Integer.parseInt(columnas[1]);

                    int posicionProfesor = -1;
                    for (int j = 0; j < profesores.length; j++) {
                        if (profesores[j] != null) {
                            if (profesores[j].getId() == idProfesor) {
                                posicionProfesor = j;
                            }
                        }
                    }
                    int posicionCurso = -1;
                    for (int j = 0; j < cursos.length; j++) {
                        if (cursos[j] != null) {
                            if (cursos[j].getId() == idCurso) {
                                posicionCurso = j;
                            }
                        }
                    }
                    if (posicionProfesor >= 0) {
                        if (posicionCurso >= 0) {
                            cursos[posicionCurso].setProfe(profesores[posicionProfesor]);
                            profesores[posicionProfesor].AsignarCursosProfe(cursos[posicionCurso]);
                        }
                    }
                } catch (NumberFormatException e) {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
                }
            }
        } else {
            for (int i = 1; i < 31; i++) {
                try {
                    columnas = filas[i].split(",");
                    int idProfesor = Integer.parseInt(columnas[0]);
                    int idCurso = Integer.parseInt(columnas[1]);

                    int posicionProfesor = -1;
                    for (int j = 0; j < profesores.length; j++) {
                        if (profesores[j] != null) {
                            if (profesores[j].getId() == idProfesor) {
                                posicionProfesor = j;
                            }
                        }
                    }
                    int posicionCurso = -1;
                    for (int j = 0; j < cursos.length; j++) {
                        if (cursos[j] != null) {
                            if (cursos[j].getId() == idCurso) {
                                posicionCurso = j;
                            }
                        }
                    }
                    if (posicionProfesor >= 0) {
                        if (posicionCurso >= 0) {
                            cursos[posicionCurso].setProfe(profesores[posicionProfesor]);
                            profesores[posicionProfesor].AsignarCursosProfe(cursos[posicionCurso]);
                        }
                    }
                } catch (NumberFormatException e) {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
                }
            }
        }
        System.out.println("Los profesores han sido asignados con éxito :D");
    }

    private void cargaNotas(String content) {

        //Partiendo cada dato por medio de punto y coma (;)
        String filas[] = content.split("\n");
        int cantidadDatos = filas.length - 1;
        String[] columnas = filas[0].split(";");
        //asignando cada dato a un atributo de la clase correspondiente
        for (int i = 1; i < filas.length; i++) {
            try {
                columnas = filas[i].split(",");
                int idAlumno = Integer.parseInt(columnas[0]);
                int idCurso = Integer.parseInt(columnas[1]);
                double nota = Double.parseDouble(columnas[2]);
                if (nota >= 0 || nota <= 100) {
                    for (int j = 0; j < notas.length; j++) {
                        if (notas[j] == null) {
                            notas[j] = new Nota(idAlumno, idCurso, nota);
                            for (int k = 0; k < notas.length - 1; k++) {
                                for (int l = k + 1; l < notas.length; l++) {
                                    if (notas[k] != null) {
                                        if (notas[l] != null) {
                                            if (notas[k].getIdAlumno() == notas[l].getIdAlumno() && notas[k].getIdCurso() == notas[l].getIdCurso()) {
                                                String log = "Fecha: " + fechaHoraActuales
                                                        + "\r\nEsta línea se ha cargado anteriormente: " + (l + 1) + "\r\n\r\n";
                                                addToEndFile("Logs.log", log);
                                                System.out.println("Hubo errores en la carga de archivos, revisar el log.");
                                                notas[l] = null;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                } else {
                    String log = "Fecha: " + fechaHoraActuales
                            + "\r\nSe ingresó una nota fura de rango en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                    addToEndFile("Logs.log", log);
                    System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
                }
            } catch (NumberFormatException e) {
                String log = "Fecha: " + fechaHoraActuales
                        + "\r\nSe escribió una letra donde debería ir un número en la línea: " + i + ") " + filas[i] + "\r\n\r\n";
                addToEndFile("Logs.log", log);
                System.out.println("Hubo errores en la carga de archivos, revisar el log. ");
            }
        }
        System.out.println("Las notas han sido cargadas con éxito :D");
    }

    private void registroUsarios() {
        Console console = System.console();

        //-------------PARA CONSOLA---------------------
        System.out.println("Ingrese el nombre de usuario");
        String usuario = console.readLine();
        System.out.println("Ingrese la contraseña");
        char[] password1 = console.readPassword();
        System.out.println("Ingrese de nuevo la contraseña");
        char[] password2 = console.readPassword();
        String pass1 = String.valueOf(password1);
        String pass2 = String.valueOf(password2);
        if (usuario1 == null) {
            if (pass1.equals(pass2)) {
                usuario1 = new Usuario(usuario, pass1);
                System.out.println("Se ha creado el usuario con éxito! :D");
            } else {
                System.out.println("Las contraseñas no coinciden");
            }
        } else if (usuario2 == null) {
            if (pass1.equals(pass2)) {
                usuario2 = new Usuario(usuario, pass1);
                System.out.println("Se ha creado el usuario con éxito! :D");
            } else {
                System.out.println("Las contraseñas no coinciden");
            }
        } else if (usuario3 == null) {
            if (pass1.equals(pass2)) {
                usuario3 = new Usuario(usuario, pass1);
                System.out.println("Se ha creado el usuario con éxito! :D");
            } else {
                System.out.println("Las contraseñas no coinciden");
            }
        } else if (usuario4 == null) {
            if (pass1.equals(pass2)) {
                usuario4 = new Usuario(usuario, pass1);
                System.out.println("Se ha creado el usuario con éxito! :D");
            } else {
                System.out.println("Las contraseñas no coinciden");
            }
        } else if (usuario5 == null) {
            if (pass1.equals(pass2)) {
                usuario5 = new Usuario(usuario, pass1);
                System.out.println("Se ha creado el usuario con éxito! :D");
            } else {
                System.out.println("Las contraseñas no coinciden");
            }
        } else {
            System.out.println("Se ha llegado al máximo de usuarios.");
        }
        //-------------PARA IDE---------------------  
//        System.out.println("Ingrese el nombre de usuario");
//        String usuario = scanner.nextLine();
//        System.out.println("Ingrese la contraseña");
//        String password1 = scanner.nextLine();
//        System.out.println("Ingrese de nuevo la contraseña");
//        String password2 = scanner.nextLine();
//
//        if (usuario1 == null) {
//            if (password1.equals(password2)) {
//                usuario1 = new Usuario(usuario, password1);
//                System.out.println("Se ha creado el usuario con éxito! :D");
//            } else {
//                System.out.println("Las contraseñas no coinciden");
//            }
//        } else if (usuario2 == null) {
//            if (password1.equals(password2)) {
//                usuario2 = new Usuario(usuario, password1);
//                System.out.println("Se ha creado el usuario con éxito! :D");
//            } else {
//                System.out.println("Las contraseñas no coinciden");
//            }
//        } else if (usuario3 == null) {
//            if (password1.equals(password2)) {
//                usuario3 = new Usuario(usuario, password1);
//                System.out.println("Se ha creado el usuario con éxito! :D");
//            } else {
//                System.out.println("Las contraseñas no coinciden");
//            }
//        } else if (usuario4 == null) {
//            if (password1.equals(password2)) {
//                usuario4 = new Usuario(usuario, password1);
//                System.out.println("Se ha creado el usuario con éxito! :D");
//            } else {
//                System.out.println("Las contraseñas no coinciden");
//            }
//        } else if (usuario5 == null) {
//            if (password1.equals(password2)) {
//                usuario5 = new Usuario(usuario, password1);
//                System.out.println("Se ha creado el usuario con éxito! :D");
//            } else {
//                System.out.println("Las contraseñas no coinciden");
//            }
//        } else {
//            System.out.println("Se ha llegado al máximo de usuarios.");
//        }
    }

    public static void addToEndFile(String pathname, String data) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(pathname, true); // True indica que se va a agregar datos al final
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            // Escribe los datos en el archivo
            bfwriter.write(data);
            bfwriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
