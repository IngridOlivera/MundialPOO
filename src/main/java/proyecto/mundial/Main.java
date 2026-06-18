package proyecto.mundial;

import proyecto.mundial.clases.Jugador;
import proyecto.mundial.clases.Seleccion;
import proyecto.mundial.clases.Torneo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Torneo> torneos = new ArrayList<>();
    private static Torneo torneoActual;
    private static Seleccion seleccionActual;
    private static Jugador jugadorActual;
    private static boolean salir = false;

    public static void main(String[] args) {
        mostrarMenuGeneral();
        mostrarMenuTorneoInicial();
        mostrarMenuSeleccionInicial();
        mostrarMenuJugador();
    }

    private static void mostrarMenuGeneral() {
        while (torneoActual==null && !salir) {
            mostrarMenuInicial();
            seleccionarTorneo();
        }
    }

    private static void mostrarMenuTorneoInicial() {
        while (seleccionActual==null && !salir)  {
            mostrarMenuTorneo();
            añadirSeleccion();
        }
    }

    private static void mostrarMenuSeleccionInicial () {
        while (jugadorActual == null && !salir) {
            mostrarMenuSeleccion();
            añadirJugador();
        }
    }

    private static void mostrarMenuJugador(){
        if (jugadorActual == null && !salir) {
            System.out.println("Informacion del jugador: " + jugadorActual.getNombreCompleto());
            System.out.println("Edad: " + jugadorActual.getEdad());
            System.out.println("Nacionalidad: " + jugadorActual.getNacionalidad());
            System.out.println("Posición: " + jugadorActual.getPosicion());
            System.out.println("Número: " + jugadorActual.getNumero());
            System.out.println("\nPresione 0 para volver al menu de la seleccion.");

            Scanner entrada  = new Scanner(System.in);
            int opcion = entrada.nextInt();
            entrada.nextLine();

            if (opcion == 0){
                jugadorActual = null;
                mostrarMenuSeleccionInicial();
            }

        }
    }

    private static void mostrarMenuInicial() {
        System.out.println("¡Bienvenido al sistema de gestión del Mundial de Fútbol!");
        System.out.println("==========================================================");
        System.out.println("Este sistema de informacion permite registrar un torneo mundial de futbol y gestionar todo " +
                "lo relacionado con el mismo, como las selecciones participantes, los jugadores, los partidos, las " +
                "estadisticas, etc... \n");
        System.out.println(" 1 - Crear un nuevo torneo. ");
        System.out.println(" 2 - Ingresar a un torneo existente. ");
        System.out.println(" 3 - Salir. ");

    }

    private static void seleccionarTorneo() {
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();
        entrada.nextLine();
        boolean opcionValida = false;

        while (!opcionValida) {
            switch (opcion) {
                case 1:
                    opcionValida = true;
                    System.out.println("Creando un nuevo torneo...");
                    System.out.println("Ingrese el nombre del torneo: ");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese el año del torneo: ");
                    int año = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese la sede del torneo: ");
                    String sede = entrada.nextLine();
                    torneos.add(new Torneo(nombre, año, sede));
                    break;
                case 2:
                    opcionValida = true;
                    if (!torneos.isEmpty()) {
                        System.out.println("Torneos disponibles: ");

                        for (int i = 0; i < torneos.size(); i++) {
                            System.out.println((i + 1) + " - " + torneos.get(i).getNombre());
                        }
                        while (torneoActual == null) {
                            int opcionTorneo = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionTorneo >= 1 && opcionTorneo <= torneos.size()) {
                                torneoActual = torneos.get(opcionTorneo - 1);
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay torneos registrados. Por favor, cree un nuevo torneo.");
                    }
                    break;
                case 3:
                    opcionValida = true;
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema de gestión del Mundial de Fútbol! ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private static void mostrarMenuTorneo() {
       System.out.println("¡Bienvenido al torneo " + torneoActual.getNombre() + "!");
       System.out.println("==========================================================");
       System.out.println(" Año del torneo: " + torneoActual.getAño() + " Sede:  " + torneoActual.getSede());
       System.out.println("En este menú puedes gestionar todo lo relacionado con el torneo, como las selecciones " +
               "participantes, los jugadores, los partidos, las estadisticas, etc... ");
       System.out.println("1 - Agregar una selección al torneo.");
       System.out.println("2 - Ver informacion de una seleccion exitente.");
       System.out.println("0 - Volver al menu inicial.");
    }

    private static void añadirSeleccion () {
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();
        entrada.nextLine();
        boolean opcionValida = false;

        while (!opcionValida) {
            switch (opcion) {
                case 1:
                    opcionValida = true;
                    System.out.println("Creando una nueva seleccion...");
                    System.out.println("Ingrese el nombre de la seleccion: ");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese el pais de la seleccion: ");
                    String pais = entrada.nextLine();
                    System.out.println("Ingrese la confederacion de la seleccion: ");
                    String confederacion = entrada.nextLine();
                    torneoActual.agregarSeleccion(new Seleccion(nombre, pais, confederacion));
                    break;
                case 2:
                    opcionValida = true;
                    if (!torneoActual.getSelecciones().isEmpty()) {
                        System.out.println("Selecciones disponibles: ");

                        for (int i = 0; i < torneoActual.getSelecciones().size(); i++) {
                            System.out.println((i + 1) + " - " + torneoActual.getSelecciones().get(i).getNombre());
                        }
                        while (seleccionActual == null) {
                            int opcionSeleccion = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionSeleccion >= 1 && opcionSeleccion <= torneoActual.getSelecciones().size()) {
                                seleccionActual = torneoActual.getSelecciones().get(opcionSeleccion - 1);
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay selecciones registradas. Por favor, cree una nueva seleccion.");
                    }
                    break;
                case 0:
                    opcionValida = true;
                    torneoActual = null;
                    mostrarMenuGeneral();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private static void mostrarMenuSeleccion() {
        System.out.println("Menú de la seleccion:");
        System.out.println("1 - Agregar un jugador a la seleccion.");
        System.out.println("2 - Ver informacion de un jugador exitente.");
        System.out.println("0 - Volver al menu del torneo.");
    }

    private static void añadirJugador () {
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();
        entrada.nextLine();
        boolean opcionValida = false;

        while(!opcionValida) {
            switch (opcion) {
                case 1:
                    opcionValida = true;
                    System.out.println("Añadiendo un nuevo jugador...");
                    System.out.println("Ingrese el nombre del jugador: ");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese el apellido del jugador: ");
                    String apellido = entrada.nextLine();
                    System.out.println("Ingrese la edad del jugador: ");
                    int edad = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese la nacionalidad del jugador: ");
                    String nacionalidad = entrada.nextLine();
                    System.out.println("Ingrese la posición del jugador: ");
                    String posicion = entrada.nextLine();
                    System.out.println("Ingrese el número del jugador: ");
                    int numero = entrada.nextInt();
                    entrada.nextLine();

                    seleccionActual.agregarJugador(new Jugador(nombre, apellido, edad, nacionalidad, posicion, numero));
                    break;
                case 2:
                    opcionValida = true;
                    if (!seleccionActual.getJugadores().isEmpty()) {
                        System.out.println("Jugadores registrados: ");

                        for (int i = 0; i < seleccionActual.getJugadores().size(); i++) {
                            System.out.println((i + 1) + " - " + seleccionActual.getJugadores().get(i).getNombreCompleto());
                        }
                        while (jugadorActual == null) {
                            int opcionJugador = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionJugador >= 1 && opcionJugador <= seleccionActual.getJugadores().size()) {
                                jugadorActual = seleccionActual.getJugadores().get(opcionJugador - 1);
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay jugadores registrados. Por favor, añada un nuevo jugador.");
                    }
                    break;
                case 0:
                    opcionValida = true;
                    seleccionActual = null;
                    mostrarMenuTorneoInicial();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }
}