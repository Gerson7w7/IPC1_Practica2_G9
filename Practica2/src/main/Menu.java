package main;

import java.io.Console;
import java.util.Scanner;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuUsuario menuUsuario = new MenuUsuario();
        Management management = new Management();
        while (true) {
//-----------------------------------PARA CMD------------------------------------------

        Console console = System.console();
        System.out.println("============================== LOGIN ============================");
        System.out.println("Ingrese su usuario ");
        String username = console.readLine();
        System.out.println("Ingrese su contraseña: ");
        char[] password = console.readPassword();
        String pass = String.valueOf(password);
//      --------------------- PARA IDE ------------------------------------
//            System.out.println("============================== LOGIN ============================");
//            System.out.println("Si desea cerrar el programa ingrese un cero (0) en usuario y contraseña");
//            System.out.println("Ingrese su usuario: ");
//            String username = scanner.nextLine();
//            System.out.println("Ingrese su contraseña: ");
//            String pass = scanner.nextLine();

// --------------------PARA ENTRAR AL MENÚ -----------------------------------
            if (username.equals("admin") && pass.equals("admin")) {
                management.menu();
            } else if (Management.usuario1 != null && username.equals(Management.usuario1.getUsuario()) && pass.equals(Management.usuario1.getPassword())) {
                menuUsuario.menuReportes();
            } else if (Management.usuario2 != null && username.equals(Management.usuario2.getUsuario()) && pass.equals(Management.usuario2.getPassword())) {
                menuUsuario.menuReportes();
            } else if (Management.usuario3 != null && username.equals(Management.usuario3.getUsuario()) && pass.equals(Management.usuario3.getPassword())) {
                menuUsuario.menuReportes();
            } else if (Management.usuario4 != null && username.equals(Management.usuario4.getUsuario()) && pass.equals(Management.usuario4.getPassword())) {
                menuUsuario.menuReportes();
            } else if (Management.usuario5 != null && username.equals(Management.usuario5.getUsuario()) && pass.equals(Management.usuario5.getPassword())) {
                menuUsuario.menuReportes();
            } else if (username.equals("0") && pass.equals("0")) {
                System.exit(0);
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }
    }
}
