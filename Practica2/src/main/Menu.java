package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {    
    public static String[] datos;
  
    public static void main(String[] args) {
        
        datos = cargaDatos("prueba.csv"); 
        cargaAlumnos(datos);

    }

    public static String[] cargaDatos(String ruta) {
        String columnas[] = null;
        try {         
            //Leyendo el csv
            File csv = new File(ruta);
            Scanner scanner = new Scanner(csv);
            String datos = "";
            //Guardando los datos del csv
            while (scanner.hasNextLine()) {
                datos += scanner.nextLine() + "\n";
            }
            //Partiendo cada dato por medio de comas (,)
            String filas[] = datos.split("\n");
            int numFilas = filas.length;
            int numDatos = numFilas - 1;
            columnas = filas[0].split(";");
            int numColumnas = columnas.length;   
            for (int i = 1; i < numFilas; i++) {
                columnas = filas[i].split(";");
            }                  
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("No encuentro a tus Pokemones :(");
            System.out.println("Intenta de nuevo. \n");           
        }
         return columnas;
    }
    
    public static void cargaAlumnos(String[] columnas){
        try{
            
            
        }catch(Exception e){
            
        }
    }
}
