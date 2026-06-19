package proyecto.mundial.interfaz;

import proyecto.mundial.modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Torneo> torneos = new ArrayList<>();
    private Torneo torneoActual;
    private Seleccion seleccionActual;
    private Seleccion seleccionActualGrupo;
    private Jugador jugadorActual;
    private boolean salir = false;
    private Grupo grupoActual;
    private String menuActual = "general";
    private String menuAnterior = "";
    private String menuPadre = "";
    private Partido partidoActual;
    private Partido partidoActualGrupo;

    public Menu(List<Torneo> torneos) {
        this.torneos = torneos;
    }

    public void mostrarMenuGeneral() {
        while (menuActual.equals ("general") && !salir) {
            mostrarMenuInicial();
            seleccionarTorneo();
        }
    }

    private void mostrarMenuTorneoInicial() {
        while (menuActual.equals("torneo") && !salir)  {
            mostrarMenuTorneo();
            opcionesDeTorneo();
        }
    }

    private void mostrarMenuSeleccionInicial () {
        while (menuActual.equals("seleccion")&& !salir) {
            mostrarMenuSeleccion();
            opcionesDeSeleccion();
        }
    }

    private void mostrarMenuJugador(){
        if (jugadorActual != null && menuActual.equals("jugador") && !salir) {
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
                menuActual = menuAnterior;
                menuAnterior = menuPadre;
                mostrarMenuSeleccionInicial();
            }

        }
    }
    private void mostrarMenuGrupoInicial(){
        while (menuActual.equals("grupo") && !salir) {
            mostrarMenuGrupo();
            opcionesDeGrupo();
        }
    }
    private void mostrarMenuPartidoInicial(){
        while (menuActual.equals("partido") && !salir) {
            mostrarMenuPartido();
            opcionesDePartido();
        }
    }
    private void mostrarMenuInicial() {
        System.out.println("¡Bienvenido al sistema de gestión del Mundial de Fútbol!");
        System.out.println("==========================================================");
        System.out.println("Este sistema de informacion permite registrar un torneo mundial de futbol y gestionar todo " +
                "lo relacionado con el mismo, como las selecciones participantes, los jugadores, los partidos, las " +
                "estadisticas, etc... \n");
        System.out.println(" 1 - Crear un nuevo torneo. ");
        System.out.println(" 2 - Ingresar a un torneo existente. ");
        System.out.println(" 3 - Salir. ");

    }

    private void seleccionarTorneo() {
        Scanner entrada = new Scanner(System.in);
        boolean opcionValida = false;

        while (!opcionValida) {
            int opcion = entrada.nextInt();
            entrada.nextLine();
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
                                menuActual = "torneo";
                                menuAnterior = "general";
                                mostrarMenuTorneoInicial();
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

    private void mostrarMenuTorneo() {
        System.out.println("¡Bienvenido al torneo " + torneoActual.getNombre() + "!");
        System.out.println("==========================================================");
        System.out.println(" Año del torneo: " + torneoActual.getAño() + " Sede:  " + torneoActual.getSede());
        System.out.println("En este menú puedes gestionar todo lo relacionado con el torneo, como las selecciones " +
                "participantes, los jugadores, los partidos, las estadisticas, etc... ");
        System.out.println("1 - Agregar una selección al torneo.");
        System.out.println("2 - Ver informacion de una seleccion exitente.");
        System.out.println("3 - Agregar un nuevo grupo al torneo.");
        System.out.println("4 - Ver informacion de un grupo existente.");
        System.out.println("5 - Agregar un nuevo partido al torneo.");
        System.out.println("6 - Ver informacion de un partido existente.");
        System.out.println("0 - Volver al menu inicial.");
    }

    private void opcionesDeTorneo() {
        Scanner entrada = new Scanner(System.in);
        boolean opcionValida = false;

        while (!opcionValida) {
            int opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 0:
                    opcionValida = true;
                    torneoActual = null;
                    menuActual = "general";
                    menuAnterior = "";
                    mostrarMenuGeneral();
                    break;
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
                                menuActual = "seleccion";
                                menuAnterior = "torneo";
                                mostrarMenuSeleccionInicial();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay selecciones registradas. Por favor, cree una nueva seleccion.");
                    }
                    break;
                case 3:
                    opcionValida = true;
                    System.out.println("Creando un nuevo grupo...");
                    System.out.println("Ingrese el nombre del grupo: ");
                    String nombreGrupo = entrada.nextLine();
                    torneoActual.agregarGrupo(new Grupo(nombreGrupo));
                    break;
                case 4:
                    opcionValida = true;
                    if (!torneoActual.getGrupos().isEmpty()) {
                        System.out.println("Grupos disponibles: ");

                        for (int i = 0; i < torneoActual.getGrupos().size(); i++) {
                            System.out.println((i + 1) + " - " + torneoActual.getGrupos().get(i).getNombre());
                        }
                        while (grupoActual == null) {
                            int opcionGrupo = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionGrupo >= 1 && opcionGrupo <= torneoActual.getGrupos().size()) {
                                grupoActual = torneoActual.getGrupos().get(opcionGrupo - 1);
                                menuActual = "grupo";
                                menuAnterior = "torneo";
                                mostrarMenuGrupoInicial();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay grupos registradas. Por favor, cree un nuevo grupo.");
                    }

                    break;
                case 5:
                    opcionValida = true;
                    System.out.println("Creando un nuevo partido...");
                    System.out.println("Escriba el nombre de la seleccion local: ");
                    Seleccion local = null;
                    while (local == null) {
                        String seleccionLocal = entrada.nextLine();
                        local = torneoActual.buscarSeleccionPorNombre(seleccionLocal);

                        if (local == null) {
                            System.out.println("Seleccion no encontrada. Por favor, ingrese un nombre de seleccion valido.");
                        }
                    }
                    System.out.println("Escriba el nombre de la seleccion visitante: ");
                    Seleccion visitante = null;
                    while (visitante == null) {
                        String seleccionVisitante = entrada.nextLine();
                        visitante = torneoActual.buscarSeleccionPorNombre(seleccionVisitante);

                        if (visitante == null || visitante == local) {
                            System.out.println("Seleccion no encontrada. Por favor, ingrese un nombre de seleccion valido.");
                            visitante = null;
                        }
                    }

                    System.out.println("Escriba la fecha del partido (dd/mm/yyyy): ");
                    String partidoFecha = entrada.nextLine();
                    torneoActual.agregarPartido(new Partido(local, visitante, partidoFecha));

                    break;
                case 6:
                    opcionValida = true;
                    if (!torneoActual.getPartidos().isEmpty()) {
                        System.out.println("Partidos disponibles: ");

                        for (int i = 0; i < torneoActual.getPartidos().size(); i++) {
                            System.out.println((i + 1) + " - " + torneoActual.getPartidos().get(i).getFecha() + ": " +
                                    torneoActual.getPartidos().get(i).getSeleccionLocal().getNombre() + " vs " +
                                    torneoActual.getPartidos().get(i).getSeleccionVisitante().getNombre());
                        }
                        while (partidoActual == null) {
                            int opcionPartido = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionPartido >= 1 && opcionPartido <= torneoActual.getPartidos().size()) {
                                partidoActual = torneoActual.getPartidos().get(opcionPartido - 1);
                                menuActual = "partido";
                                menuAnterior = "torneo";
                                mostrarMenuPartidoInicial();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay partido registradas. Por favor, cree un nuevo partido.");
                    }

                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private void mostrarMenuSeleccion() {
        Seleccion seleccion;
        if (menuAnterior.equals("torneo")) {
            seleccion = seleccionActual;
        } else {
            seleccion = seleccionActualGrupo;
        }
        System.out.println("Menú de la seleccion:");
        System.out.println("1 - Agregar un jugador a la seleccion.");
        System.out.println("2 - Ver informacion de un jugador exitente.");
        if (seleccion != null && seleccion.getDirectorTecnico() == null) {
            System.out.println("3 - Agregar director tecnico a la seleccion. ");
        }else {
            System.out.println("3 - Ver informacion del director tecnico de la seleccion. ");
        }
        System.out.println("4 - Ver estadisticas de la seleccion. ");
        System.out.println("0 - Volver al menu anterior.");
    }

    private void opcionesDeSeleccion() {
        Scanner entrada = new Scanner(System.in);
        boolean opcionValida = false;
        Seleccion seleccion;
        if (menuAnterior.equals("torneo")) {
            seleccion = seleccionActual;
        } else {
            seleccion = seleccionActualGrupo;
        }

        while(!opcionValida) {
            int opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 0:
                    opcionValida = true;
                    if (menuAnterior.equals("torneo")) {
                        seleccionActual = null;
                        menuActual = "torneo";
                        menuAnterior = "general";
                        mostrarMenuTorneoInicial();
                    } else if (menuAnterior.equals("grupo")) {
                        seleccionActualGrupo = null;
                        menuActual = "grupo";
                        menuAnterior = "torneo";
                        mostrarMenuGrupoInicial();
                    }
                    break;
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

                    seleccion.agregarJugador(new Jugador(nombre, apellido, edad, nacionalidad, posicion, numero));
                    break;
                case 2:
                    opcionValida = true;
                    if (!seleccion.getJugadores().isEmpty()) {
                        System.out.println("Jugadores registrados: ");

                        for (int i = 0; i < seleccion.getJugadores().size(); i++) {
                            System.out.println((i + 1) + " - " + seleccion.getJugadores().get(i).getNombreCompleto());
                        }
                        while (jugadorActual == null) {
                            int opcionJugador = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionJugador >= 1 && opcionJugador <= seleccion.getJugadores().size()) {
                                jugadorActual = seleccion.getJugadores().get(opcionJugador - 1);
                                menuActual = "jugador";
                                menuPadre = menuAnterior;
                                menuAnterior = "seleccion";
                                mostrarMenuJugador();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                    } else {
                        System.out.println("No hay jugadores registrados. Por favor, añada un nuevo jugador.");
                    }
                    break;
                case 3:
                    opcionValida = true;
                    if (seleccion.getDirectorTecnico() == null) {
                        System.out.println("Añadiendo un nuevo director tecnico...");
                        System.out.println("Ingrese el nombre del director tecnico: ");
                        String nombreDT = entrada.nextLine();
                        System.out.println("Ingrese el apellido del director tecnico: ");
                        String apellidoDT = entrada.nextLine();
                        System.out.println("Ingrese la edad del director tecnico: ");
                        int edadDT = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese la nacionalidad del director tecnico: ");
                        String nacionalidadDT = entrada.nextLine();
                        System.out.println("Ingrese la experiencia del director tecnico (en años): ");
                        int experienciaDT = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese el estilo de juego del director tecnico: ");
                        String estiloDeJuegoDT = entrada.nextLine();

                        seleccion.asignarDirectorTecnico(new DirectorTecnico(nombreDT, apellidoDT, edadDT, nacionalidadDT, experienciaDT, estiloDeJuegoDT));
                    } else {
                        DirectorTecnico dt = seleccion.getDirectorTecnico();
                        System.out.println("Información del director técnico:");
                        System.out.println("Nombre completo: " + dt.getNombreCompleto());
                        System.out.println("Edad: " + dt.getEdad());
                        System.out.println("Nacionalidad: " + dt.getNacionalidad());
                        System.out.println("Experiencia: " + dt.getExperiencia() + " años");
                        System.out.println("Estilo de juego: " + dt.getEstiloDeJuego());
                    }
                    break;
                case 4:
                    opcionValida = true;
                    if  (seleccion.getEstadistica() != null) {
                        System.out.println("Estadistica del seleccion: ");
                        System.out.println("puntos: " + seleccion.getEstadistica().getPuntos());
                        System.out.println("partidos jugados: " + seleccion.getEstadistica().getPartidosJugados());
                        System.out.println("partidos ganados: " + seleccion.getEstadistica().getPartidosGanados());
                        System.out.println("partidos perdidos: " + seleccion.getEstadistica().getPartidosPerdidos());
                        System.out.println("partidos empatados: " + seleccion.getEstadistica().getPartidosEmpatados());
                        System.out.println("goles a favor: " + seleccion.getEstadistica().getGolesAFavor());
                        System.out.println("goles en contra: " + seleccion.getEstadistica().getGolesEnContra());
                        System.out.println("diferencia de goles: " + seleccion.getEstadistica().getDiferenciaDeGoles());
                    } else {
                        System.out.println("No hay estadisticas registradas para esta seleccion.");
                    }
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }
    private void mostrarMenuGrupo() {
        System.out.println("¡Bienvenido al grupo " + grupoActual.getNombre() + " ! ");
        System.out.println("==========================================================");
        System.out.println("En este menú puedes gestionar todo lo relacionado con el grupo, como las selecciones " +
                "participantes y los partidos");
        System.out.println("Menu del grupo: ");
        System.out.println("1. Agregar una seleccion al grupo.");
        System.out.println("2. Ver la informacion de las selecciones del grupo.");
        System.out.println("3. Agregar un partido al grupo.");
        System.out.println("4. Ver informacion de los partidos del grupo.");
        System.out.println("0. Volver al menu del torneo.");
    }
    private void opcionesDeGrupo() {
        Scanner entrada = new Scanner(System.in);
        boolean opcionValida = false;

        while (!opcionValida) {
            int opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 0:
                    opcionValida = true;
                    grupoActual = null;
                    menuActual = "torneo";
                    menuAnterior = "general";
                    mostrarMenuTorneoInicial();
                    break;
                case 1:
                    opcionValida = true;
                    if (!torneoActual.getSelecciones().isEmpty()) {
                        System.out.println("Agregando una seleccion al grupo...");
                        System.out.println("Escriba el nombre de la seleccion que desea agregar al grupo: ");
                        Seleccion seleccionAgregar = null;
                        while (seleccionAgregar == null) {
                            String nombreSeleccionAgregar = entrada.nextLine();
                            seleccionAgregar = torneoActual.buscarSeleccionPorNombre(nombreSeleccionAgregar);

                            if (seleccionAgregar == null) {
                                System.out.println("Seleccion no encontrada. Por favor, ingrese un nombre de seleccion valido.");
                            }
                        }
                        grupoActual.agregarSeleccion(seleccionAgregar);
                    } else {
                        System.out.println("No hay selecciones registradas en el torneo. Por favor, cree una nueva seleccion.");
                    }
                    break;
                case 2:
                    opcionValida = true;
                    if (!grupoActual.getSelecciones().isEmpty()) {
                        System.out.println("Selecciones del grupo " + grupoActual.getNombre() + ": ");
                        for (int i = 0; i < grupoActual.getSelecciones().size(); i++) {
                            System.out.println((i + 1) + " - " + grupoActual.getSelecciones().get(i).getNombre());
                        }
                        while (seleccionActualGrupo == null) {
                            int opcionSeleccionGrupo = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionSeleccionGrupo >= 1 && opcionSeleccionGrupo <= grupoActual.getSelecciones().size()) {
                                seleccionActualGrupo = grupoActual.getSelecciones().get(opcionSeleccionGrupo - 1);
                                menuActual = "seleccion";
                                menuAnterior = "grupo";
                                mostrarMenuSeleccionInicial();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }

                    } else {
                        System.out.println("No hay selecciones registradas en este grupo.");
                    }
                    break;
                case 3:
                    opcionValida = true;
                    if (!torneoActual.getPartidos().isEmpty()) {
                        System.out.println("Agregando un partido al grupo...");
                        System.out.println("Elija el partido que desea agregar al grupo.");
                        Partido partidoAgregar = null;
                        for (int i = 0; i < torneoActual.getPartidos().size(); i++) {
                            System.out.println((i + 1) + " - " + torneoActual.getPartidos().get(i).getFecha() + ": " +
                                    torneoActual.getPartidos().get(i).getSeleccionLocal().getNombre() + " vs " +
                                    torneoActual.getPartidos().get(i).getSeleccionVisitante().getNombre());
                        }

                        while  (partidoAgregar == null) {
                            int opcionPartido = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionPartido >= 1 && opcionPartido <= torneoActual.getPartidos().size()) {
                                partidoAgregar = torneoActual.getPartidos().get(opcionPartido - 1);
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }
                        grupoActual.agregarPartido(partidoAgregar);
                    } else {
                        System.out.println("No hay selecciones registradas en el torneo. Por favor, cree una nueva seleccion.");
                    }
                    break;
                case 4:
                    opcionValida = true;
                    if (!grupoActual.getPartidos().isEmpty()) {
                        System.out.println("Partidos del grupo " + grupoActual.getNombre() + ": ");
                        for (int i = 0; i < grupoActual.getPartidos().size(); i++) {
                            System.out.println((i + 1) + " - " + torneoActual.getPartidos().get(i).getFecha() + ": " +
                                    torneoActual.getPartidos().get(i).getSeleccionLocal().getNombre() + " vs " +
                                    torneoActual.getPartidos().get(i).getSeleccionVisitante().getNombre());
                        }
                        while (partidoActualGrupo == null) {
                            int opcionPartido = entrada.nextInt();
                            entrada.nextLine();

                            if (opcionPartido >= 1 && opcionPartido <= grupoActual.getPartidos().size()) {
                                partidoActualGrupo = grupoActual.getPartidos().get(opcionPartido - 1);
                                menuActual = "partido";
                                menuAnterior = "grupo";
                                mostrarMenuPartidoInicial();
                            } else {
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                            }
                        }

                    } else {
                        System.out.println("No hay partidos registradas en este grupo.");
                    }

                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }
    private void mostrarMenuPartido() {
        Partido partido;

        if (menuAnterior.equals("torneo")) {
            partido = partidoActual;
        } else {
            partido = partidoActualGrupo;
        }
        System.out.println("¡Bienvenido al partido " + partido.getSeleccionLocal().getNombre() + " vs " +
                partido.getSeleccionVisitante().getNombre() + " ! ");
        System.out.println("==========================================================");
        System.out.println("Fecha del partido: " + partido.getFecha());
        System.out.println("Goles a favor: " + partido.getGolesLocal());
        System.out.println("Goles en contra: " + partido.getGolesVisitante());
        System.out.println("\nEn este menú puedes gestionar todo lo relacionado con el partido, como el resultado, los goles, etc...");
        System.out.println("1. Actualizar goles a favor.");
        System.out.println("2. Actualizar goles en contra.");
        System.out.println("3. Actualizar estado del partido.");
        System.out.println("0. Volver al menu anterior.");
    }

    private void opcionesDePartido() {
        Scanner entrada = new Scanner(System.in);
        boolean opcionValida = false;
        Partido partido;

        if (menuAnterior.equals("torneo")) {
            partido = partidoActual;
        } else {
            partido = partidoActualGrupo;
        }

        while (!opcionValida) {
            int opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 0:
                    opcionValida = true;
                    if (menuAnterior.equals("torneo")) {
                        partidoActual = null;
                        menuActual = "torneo";
                        menuAnterior = "general";
                        mostrarMenuTorneoInicial();
                    }else if (menuAnterior.equals("grupo")) {
                        partidoActualGrupo = null;
                        menuActual = "grupo";
                        menuAnterior = "torneo";
                        mostrarMenuGrupoInicial();
                    }
                    break;
                case 1:
                    opcionValida = true;
                    System.out.println("Actualizando goles a favor...");
                    System.out.println("Ingrese el nuevo número de goles a favor: ");
                    int golesAFavor = entrada.nextInt();
                    entrada.nextLine();
                    partido.actualizarGolesLocal(golesAFavor);
                    break;
                case 2:
                    opcionValida = true;
                    System.out.println("Actualizando goles en contra...");
                    System.out.println("Ingrese el nuevo número de goles en contra: ");
                    int golesContra= entrada.nextInt();
                    entrada.nextLine();
                    partido.actualizarGolesVisitante(golesContra);
                    break;
                case 3:
                    opcionValida = true;
                    String[] estados ={"Programado" , "En curso" , "Finalizado" };
                    System.out.println("Actualizando estado del partido...");
                    System.out.println("Seleccione estado del partido: ");
                    System.out.println("1 - Programado");
                    System.out.println("2 - En curso");
                    System.out.println("3 - Finalizado.");
                    int estado = entrada.nextInt();
                    entrada.nextLine();
                    partido.actualizarEstado(estados [estado - 1]);
                    break;
            }
        }

    }
}
