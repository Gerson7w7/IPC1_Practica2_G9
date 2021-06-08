package Main;

import Objetos.*;
import java.io.File;
import java.util.Scanner;

public class management {
    Alumno[] alumnos;
    boolean activo = true;

    public management() {
        alumnos = new Alumno[150];

    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        do {
            mHeader();
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    loadDataCSV("alumnos");
                    break;
                case 2:
                    showData();
                    break;
                default:
                    System.out.println("Opcion no válida");
            }
        } while (activo);
    }

    private void mHeader() {
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("|                                   BIENVENIDOS                                            |");
        System.out.println("|  Ingrese la opcion deseada                                                               |");
        System.out.println("|    1.Cargar Alumnos                                     2.mostrar                        |");
        System.out.println("|                               Ingrese su opción                                          |");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private void loadDataCSV(String type) {
        System.out.println("Ingrese la ruta del archivo");
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        try {
            scanner = new Scanner(file);
            scanner.nextLine();
            if (type.equals("alumnos")) {
                loadAlumnosCSV(scanner);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAlumnosCSV(Scanner scanner) {
        int index = 0;
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(";");
            alumnos[index] = new Alumno(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3],data[4]);
            System.out.println(">>Alumno " + alumnos[index].getNombre() + " creado");
            index++;
        }
        scanner.close();
    }

    private void showData(){
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null){
                System.out.println(">> Alumno "+alumnos[i].getNombre());
            }
        }
    }
}
