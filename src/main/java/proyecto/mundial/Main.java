package proyecto.mundial;

import proyecto.mundial.clases.Grupo;
import proyecto.mundial.clases.Seleccion;
import proyecto.mundial.clases.Torneo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("¡Bienvenido al sistema de gestión del Mundial de Fútbol!");
        System.out.println("==========================================================");
        System.out.println("Este sistema de informacion permite registrar un torneo mundial de futbol y gestionar todo " +
                "lo relacionado con el mismo, como las selecciones participantes, los jugadores, los partidos, las " +
                "estadisticas, etc... \n");
        System.out.println(" 1 - Crear un nuevo torneo. ");
        System.out.println(" 2 - Ingresar a un torneo existente. ");
        System.out.println(" 3 - Salir. ");

        Scanner entrada = new Scanner(System.in);
        int opcion;
        Torneo torneoActual = null;

        do {
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Creando un nuevo torneo...");
                    System.out.println("Ingrese el nombre del torneo: ");
                    String nombre = entrada.next();
                    System.out.println("Ingrese el año del torneo: ");
                    int año = entrada.nextInt();
                    System.out.println("Ingrese la sede del torneo: ");
                    String sede = entrada.next();
                    torneoActual = new Torneo(nombre, año, sede);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el sistema de gestión del Mundial de Fútbol! ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 3);


    }
    private static void añadirSelecciones (Torneo torneo) {

    }
}