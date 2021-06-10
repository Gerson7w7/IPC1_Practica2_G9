package Main;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.Console;
import java.util.Scanner;

public class Main {
    private Alumno[] alumnos;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        inicio del programa, el usuario ingresa sus credenciales

        Console console = System.console();
        System.out.println("============================== LOGIN ============================");
        System.out.println("Ingrese su usuario ");
        String username  = console.readLine();
        System.out.println("Ingrese su contraseña: ");
        char [] password = console.readPassword();
        System.out.println("Repita su contraseña");
        char [] password2 = console.readPassword();
        String pass=String.valueOf(password);

//        dejo esto para que no esten haciendo el jar a cada rato
        /*System.out.println("Ingrese su usuario ");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña");
        String pass = scanner.nextLine();*/

        if (username.equals("admin") && pass.equals("admin")){
            Management management = new Management();
            management.menu();

        } else {
//            AQUI IRIA EL MENU DE USUARIOS
            System.out.println("usted es un usuario");
        }


    }

}
