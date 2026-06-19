package proyecto.mundial.persistencia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import proyecto.mundial.modelos.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LecturaJson {
    public List<Torneo> cargarDatos() throws IOException {
        HashMap<Integer, Jugador> jugadores = cargarJugadores();
        HashMap<Integer, DirectorTecnico> directoresTecnicos = cargarDirectoresTecnico();
        HashMap<Integer, Seleccion> selecciones = cargarSelecciones(jugadores, directoresTecnicos);
        HashMap<Integer, Partido> partidos =  cargarPartidos(selecciones);
        HashMap<Integer, Grupo> grupos = cargarGrupos(selecciones, partidos);

        return cargarTorneo(selecciones, partidos, grupos);
    }

    private JsonArray leerArchivo(String nombreArchivo) throws IOException {
        InputStream archivo = getClass().getClassLoader().getResourceAsStream(nombreArchivo);

        assert archivo != null;
        try (InputStreamReader lector = new InputStreamReader(archivo, StandardCharsets.UTF_8)){
            return JsonParser.parseReader(lector).getAsJsonArray();

        }
    }

    public HashMap<Integer, Jugador> cargarJugadores () throws IOException {
        HashMap<Integer, Jugador> jugadores = new HashMap<>();
        JsonArray jugadoresJson = leerArchivo("jugador.json");

        for (JsonElement jugadorJson : jugadoresJson) {
            JsonObject jugador = jugadorJson.getAsJsonObject();
            int id = jugador.get("id").getAsInt();
            String nombre = jugador.get("nombre").getAsString();
            String apellido = jugador.get("apellido").getAsString();
            int edad = jugador.get("edad").getAsInt();
            String nacionalidad = jugador.get("nacionalidad").getAsString();
            String posicion = jugador.get("posicion").getAsString();
            int numero = jugador.get("numero").getAsInt();

            jugadores.put(id, new Jugador(nombre, apellido, edad, nacionalidad, posicion, numero));
        }
        return jugadores;
    }
    public HashMap<Integer, DirectorTecnico> cargarDirectoresTecnico () throws IOException {
        HashMap<Integer, DirectorTecnico> directoresTecnicos = new HashMap<>();
        JsonArray directorTecnicoJson = leerArchivo("director_tecnico.json");

        for(JsonElement directorJson : directorTecnicoJson){
            JsonObject director = directorJson.getAsJsonObject();

            int id = director.get("id").getAsInt();
            String nombre = director.get("nombre").getAsString();
            String apellido = director.get("apellido").getAsString();
            int edad = director.get("edad").getAsInt();
            String nacionalidad = director.get("nacionalidad").getAsString();
            int experiencia = director.get("experiencia").getAsInt();
            String estiloDeJuego = director.get("estiloDeJuego").getAsString();

            directoresTecnicos.put(id, new DirectorTecnico(nombre,apellido, edad, nacionalidad, experiencia, estiloDeJuego));
        }
        return directoresTecnicos;

    }

    public HashMap<Integer, Seleccion> cargarSelecciones (HashMap<Integer, Jugador>jugadores, HashMap<Integer,
            DirectorTecnico>directoresTecnicos) throws IOException {
        HashMap<Integer, Seleccion> selecciones = new HashMap<>();
        JsonArray seleccionesJson = leerArchivo("seleccion.json");

        for(JsonElement seleccionJson : seleccionesJson){

            JsonObject seleccion = seleccionJson.getAsJsonObject();
            int id = seleccion.get("id").getAsInt();
            String nombre = seleccion.get("nombre").getAsString();
            String pais = seleccion.get("pais").getAsString();
            String confederacion = seleccion.get("confederacion").getAsString();
            JsonArray jugadoresJson = seleccion.get("jugadorIds").getAsJsonArray();

            Seleccion seleccionActual = new Seleccion(nombre, pais, confederacion);

            for (JsonElement jugadorJson : jugadoresJson) {
                int jugadorId = jugadorJson.getAsInt();
                Jugador jugador = jugadores.get(jugadorId);
                seleccionActual.agregarJugador(jugador);
            }
            JsonElement directorIdJson = seleccion.get("directorTecnicoId");
            if (directorIdJson != null && !directorIdJson.isJsonNull()) {
                seleccionActual.asignarDirectorTecnico(directoresTecnicos.get(directorIdJson.getAsInt()));
            }

            selecciones.put(id, seleccionActual);

        }
        return selecciones;
    }
    public HashMap<Integer, Partido>  cargarPartidos (HashMap<Integer, Seleccion> selecciones) throws IOException {
        HashMap<Integer, Partido> partidos = new HashMap<>();
        JsonArray partidosJson = leerArchivo("partido.json");

        for(JsonElement partidoJson : partidosJson){
            JsonObject partido = partidoJson.getAsJsonObject();
            int id = partido.get("id").getAsInt();
            int localId  = partido.get("seleccionLocalId").getAsInt();
            int visitanteId = partido.get("seleccionVisitanteId").getAsInt();
            String fecha = partido.get("fecha").getAsString();
            int golesLocal = partido.get("golesLocal").getAsInt();
            int golesVisitante = partido.get("golesVisitante").getAsInt();
            String estado = partido.get("estado").getAsString();

            Seleccion seleccionLocal =  selecciones.get(localId);
            Seleccion seleccionVisitante =  selecciones.get(visitanteId);

            Partido partidoActual = new Partido(seleccionLocal, seleccionVisitante, fecha);
            partidoActual.actualizarGolesLocal(golesLocal);
            partidoActual.actualizarGolesVisitante(golesVisitante);
            partidoActual.actualizarEstado(estado);

            partidos.put(id, partidoActual);
        }

        return partidos;
    }

    public HashMap<Integer, Grupo> cargarGrupos (HashMap<Integer, Seleccion> selecciones, HashMap<Integer, Partido> partidos) throws IOException {
        HashMap<Integer, Grupo> grupos = new HashMap<>();
        JsonArray gruposJson = leerArchivo("grupo.json");

        for(JsonElement grupoJson : gruposJson){
            JsonObject grupo = grupoJson.getAsJsonObject();
            int id = grupo.get("id").getAsInt();
            String nombre  = grupo.get("nombre").getAsString();
            JsonArray seleccionIds = grupo.get("seleccionIds").getAsJsonArray();
            JsonArray partidoIds = grupo.get("partidoIds").getAsJsonArray();

            Grupo grupoActual = new Grupo(nombre);

            for  (JsonElement seleccionIdJson : seleccionIds) {
                int seleccionId = seleccionIdJson.getAsInt();
                Seleccion seleccion = selecciones.get(seleccionId);
                grupoActual.agregarSeleccion(seleccion);
            }

            for(JsonElement partidoIdJson : partidoIds) {
                int partidoId = partidoIdJson.getAsInt();
                Partido partido = partidos.get(partidoId);
                grupoActual.agregarPartido(partido);
            }

            grupos.put(id, grupoActual);
        }

        return grupos;
    }

    public List<Torneo> cargarTorneo (HashMap<Integer, Seleccion> selecciones, HashMap<Integer, Partido> partidos,
                                HashMap<Integer, Grupo> grupos) throws IOException {
        JsonArray torneosJson = leerArchivo("torneo.json");
        List<Torneo> torneos = new ArrayList<>();

        for(JsonElement elemento : torneosJson){
            JsonObject torneoJson = elemento.getAsJsonObject();
            String nombre = torneoJson.get("nombre").getAsString();
            int año = torneoJson.get("año").getAsInt();
            String sede = torneoJson.get("sede").getAsString();
            JsonArray seleccionIds = torneoJson.get("seleccionIds").getAsJsonArray();
            JsonArray partidoIds = torneoJson.get("partidoIds").getAsJsonArray();
            JsonArray grupoIds = torneoJson.get("grupoIds").getAsJsonArray();

            Torneo torneo = new Torneo(nombre, año, sede);

            for(JsonElement seleccionIdJson : seleccionIds){
                int seleccionId = seleccionIdJson.getAsInt();
                Seleccion seleccion = selecciones.get(seleccionId);
                torneo.agregarSeleccion(seleccion);
            }

            for(JsonElement partidoIdJson : partidoIds){
                int partidoId = partidoIdJson.getAsInt();
                Partido partido = partidos.get(partidoId);
                torneo.agregarPartido(partido);
            }

            for (JsonElement grupoIdJson : grupoIds){
                int grupoId = grupoIdJson.getAsInt();
                Grupo grupo = grupos.get(grupoId);
                torneo.agregarGrupo(grupo);
            }

            torneos.add(torneo);

        }

        return torneos;
    }
}
