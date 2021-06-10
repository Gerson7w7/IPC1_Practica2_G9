package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import Objetos.*;
import java.io.IOException;

public class Reportes {

    Alumno[] alumnos;
    Profesor[] profesores;
    Curso[] cursos;

    public Scanner scanner = new Scanner(System.in);
    public static LocalDateTime fechaHoraActuales = LocalDateTime.now();
    public static FileWriter fichero = null;
    public static PrintWriter pw = null;

    public Reportes() {
        this.alumnos = Management.alumnos;
        this.profesores = Management.profesores;
        this.cursos = Management.cursos;
    }

    public void encabezado() {
        System.out.println("===============GENERAR REPORTES=============");
        System.out.println("\t 1. Reporte de alumnos");
        System.out.println("\t 2. Reporte de asignación de alumnos");
        System.out.println("\t 3. Reporte de asignación de profesores");
        System.out.println("\t 4. Reporte general de curso");
        System.out.println("\t 5. Reporte de un curso específico");
        System.out.println("\t 6. Reporte TOP 5 mejores alumnos");
        System.out.println("\t 7. Regresar al menú principal");
    }

    public void menuReportes() {
        boolean cicloR = true;
        while (cicloR) {
            try {
                encabezado();
                int reporte = Integer.parseInt(scanner.nextLine());
                switch (reporte) {
                    case 1:
                        rAlumnos();
                        break;
                    case 2:
                        rAsigAlumnos();
                        break;
                    case 3:
                        //rAsigProfesores();
                        break;
                    case 4:
                        //rGeneralC;
                        break;
                    case 6:
                        //rTOP5();
                        break;
                    case 7:
                        cicloR = false;
                        break;
                    default:
                        System.out.println("Opción fuera de rango.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Por favor introduce un número.");
            }
        }
    }

    public void rAlumnos() {
        if (alumnos != null) {
            try {
                String[] fecha = new String[3];
                int edad = 0;
                fichero = new FileWriter("Reportes/Alumnos.html");
                pw = new PrintWriter(fichero);

                pw.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                        + "<html>\n"
                        + "\n"
                        + "<!--Encabezado-->\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                        + "\n"
                        + "\n"
                        + "<meta name=\"name\" content=\"Reporte\"><!--nombre de la pagina-->\n"
                        + "<meta name=\"description\" content=\"name\"><!--autor de la pagina-->\n"
                        + "<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\n"
                        + "<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\n"
                        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\n"
                        + "\n"
                        + "\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                        + "\n"
                        + "<title>Reporte</title><!--Titulo visible de la pagina-->\n"
                        + "</head>\n"
                        + "\n");
                pw.println("Hora de generación:" + fechaHoraActuales + "<br><br>\n");
                pw.println("<body>\n"
                        + "\n"
                        + "<center><!--centra todos lo que este dentro--> \n"
                        + "<h6 class=titulos><b> Operaciones </b></h6>");

                pw.println(" <br>  <br>  <br> \n"
                        + "\n"
                        + "<!----tabla 2-->\n"
                        + "<table class=\"steelBlueCols\">\n"
                        + "<thead>\n"
                        + "   <tr><th>CARNÉ</th> <th>NOMBRE</th> <th>EDAD</th><th>GÉNERO</th></tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n");
                for (Alumno alumno : alumnos) {
                    pw.println(" <tr>");
                    pw.println("<td>" + String.valueOf(alumno.getCarné()) + "</td>");
                    pw.println("<td>" + String.valueOf(alumno.getNombre()) + "</td>");
                    fecha = alumno.getFechaNac().split("/");
                    edad = 2021 - Integer.parseInt(fecha[2]);
                    pw.println("<td>" + String.valueOf(edad) + "</td>");
                    if (alumno.getGenero().equals("M")) {
                        pw.println("<td>" + "Masculino" + "</td>");
                    } else {
                        pw.println("<td>" + "Femenino" + "</td>");
                    }
                    pw.println("</tr>");
                }
                pw.println("</tr> \n"
                        + "</tbody>\n"
                        + "</table>\n"
                        + " <!----termina tabla 2-->");

                pw.println("</center>\n"
                        + "\n"
                        + "</body>\n"
                        + "</html>");

                fichero.close();
                System.out.println("El reporte se ha generado correctamente :D \n");
            } catch (IOException e) {
            }
        } else {
            System.out.println("Necesito más información para generar este reporte.");
        }
    }
    
    public void rAsigAlumnos() {
        if (alumnos != null && cursos != null) {
            try {
                String[] fecha = new String[3];
                int edad = 0;
                fichero = new FileWriter("Reportes/Alumnos.html");
                pw = new PrintWriter(fichero);

                pw.println("<!DOCTYPE html><!--Declarar el tipo de cumento -->\n"
                        + "<html>\n"
                        + "\n"
                        + "<!--Encabezado-->\n"
                        + "<head>\n"
                        + "<meta charset=\"UTF-8\"><!--codififcaion de caracteres ñ y á-->\n"
                        + "\n"
                        + "\n"
                        + "<meta name=\"name\" content=\"Reporte\"><!--nombre de la pagina-->\n"
                        + "<meta name=\"description\" content=\"name\"><!--autor de la pagina-->\n"
                        + "<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\n"
                        + "<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\n"
                        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\n"
                        + "\n"
                        + "\n"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\n"
                        + "\n"
                        + "<title>Reporte</title><!--Titulo visible de la pagina-->\n"
                        + "</head>\n"
                        + "\n");
                pw.println("Hora de generación:" + fechaHoraActuales + "<br><br>\n");
                pw.println("<body>\n"
                        + "\n"
                        + "<center><!--centra todos lo que este dentro--> \n"
                        + "<h6 class=titulos><b> Operaciones </b></h6>");

                pw.println(" <br>  <br>  <br> \n"
                        + "\n"
                        + "<!----tabla 2-->\n"
                        + "<table class=\"steelBlueCols\">\n"
                        + "<thead>\n"
                        + "   <tr><th>CARNÉ</th> <th>NOMBRE</th> <th>EDAD</th><th>GÉNERO</th></tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n");
                for (Alumno alumno : alumnos) {
                    pw.println(" <tr>");
                    pw.println("<td>" + String.valueOf(alumno.getCarné()) + "</td>");
                    pw.println("<td>" + String.valueOf(alumno.getNombre()) + "</td>");
                    fecha = alumno.getFechaNac().split("/");
                    edad = 2021 - Integer.parseInt(fecha[2]);
                    pw.println("<td>" + String.valueOf(edad) + "</td>");
                    if (alumno.getGenero().equals("M")) {
                        pw.println("<td>" + "Masculino" + "</td>");
                    } else {
                        pw.println("<td>" + "Femenino" + "</td>");
                    }
                    pw.println("</tr>");
                }
                pw.println("</tr> \n"
                        + "</tbody>\n"
                        + "</table>\n"
                        + " <!----termina tabla 2-->");

                pw.println("</center>\n"
                        + "\n"
                        + "</body>\n"
                        + "</html>");

                fichero.close();
                System.out.println("El reporte se ha generado correctamente :D \n");
            } catch (IOException e) {
            }
        } else {
            System.out.println("Necesito más información para generar este reporte.");
        }
    }
}
