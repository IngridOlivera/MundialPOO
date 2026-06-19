package proyecto.mundial.persistencia;

import com.google.gson.*;
import proyecto.mundial.modelos.*;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EscrituraJson {
    private Gson gson;
    private List<Jugador>  jugadores;
    private List<DirectorTecnico>  directoresTecnicos;
    private List<Seleccion> selecciones;
    private List<Partido> partidos;
    private List<Grupo> grupos;
    private HashMap<Jugador, Integer> jugadoresIds;
    private HashMap<DirectorTecnico, Integer> directoresTecnicosIds;
    private HashMap<Seleccion, Integer> seleccionesIds;
    private HashMap<Partido, Integer> partidosIds;
    private HashMap<Grupo, Integer> gruposIds;

    public EscrituraJson() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jugadores = new ArrayList<>();
        this.directoresTecnicos = new ArrayList<>();
        this.selecciones = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.grupos = new ArrayList<>();
        this.jugadoresIds = new HashMap<>();
        this.directoresTecnicosIds = new HashMap<>();
        this.seleccionesIds = new HashMap<>();
        this.partidosIds = new HashMap<>();
        this.gruposIds = new HashMap<>();
    }

    public void guardarDatos(List<Torneo> torneos) throws IOException {
        prepararDatos(torneos);

        guardarJugadores();
        guardarDirectoresTecnicos();
        guardarSelecciones();
        guardarPartidos();
        guardarGrupos();
        guardarTorneos(torneos);
    }

    private void prepararDatos(List<Torneo> torneos) {
        limpiarDatos();

        for (Torneo torneo : torneos) {
            for (Seleccion seleccion : torneo.getSelecciones()) {
                agregarSeleccion(seleccion);
            }

            for (Partido partido : torneo.getPartidos()) {
                agregarPartido(partido);
            }

            for (Grupo grupo : torneo.getGrupos()) {
                agregarGrupo(grupo);
            }
        }
    }

    private void agregarSeleccion(Seleccion seleccion) {
        if (seleccion == null || seleccionesIds.containsKey(seleccion)) {
            return;
        }

        this.selecciones.add(seleccion);
        seleccionesIds.put(seleccion, seleccionesIds.size());

        for (Jugador jugador : seleccion.getJugadores()) {
            if (jugador == null) {
                continue;
            }

            if (!jugadoresIds.containsKey(jugador)) {
                jugadores.add(jugador);
                jugadoresIds.put(jugador, jugadores.size());
            }
        }

        DirectorTecnico directorTecnico = seleccion.getDirectorTecnico();
        if (directorTecnico != null && !directoresTecnicosIds.containsKey(directorTecnico)) {
            directoresTecnicos.add(directorTecnico);
            directoresTecnicosIds.put(directorTecnico, directoresTecnicos.size());
        }
    }

    private void agregarPartido(Partido partido) {
        if (partido == null || partidosIds.containsKey(partido)) {
            return;
        }

        partidos.add(partido);
        partidosIds.put(partido, partidos.size());
    }

    private void agregarGrupo(Grupo grupo) {
        if (grupo == null || gruposIds.containsKey(grupo)) {
            return;
        }

        grupos.add(grupo);
        gruposIds.put(grupo, grupos.size());
    }

    private void limpiarDatos() {
        jugadores.clear();
        directoresTecnicos.clear();
        selecciones.clear();
        partidos.clear();
        grupos.clear();
        jugadoresIds.clear();
        directoresTecnicosIds.clear();
        seleccionesIds.clear();
        partidosIds.clear();
        gruposIds.clear();
    }

    private void guardarJugadores() throws IOException {
        JsonArray jugadoresJson = new JsonArray();

        for (Jugador jugador: jugadores) {
            JsonObject jugadorJson = new JsonObject();
            jugadorJson.addProperty("id", jugadoresIds.get(jugador));
            jugadorJson.addProperty("nombre", jugador.getNombre());
            jugadorJson.addProperty("apellido", jugador.getApellido());
            jugadorJson.addProperty("edad", jugador.getEdad());
            jugadorJson.addProperty("nacionalidad", jugador.getNacionalidad());
            jugadorJson.addProperty("posicion", jugador.getPosicion());
            jugadorJson.addProperty("numero",  jugador.getNumero());
            jugadoresJson.add(jugadorJson);
        }

        escribirArchivo("jugador.json", jugadoresJson);
    }

    private void guardarDirectoresTecnicos() throws IOException {
        JsonArray directoresJson = new JsonArray();

        for (DirectorTecnico directorTecnico: directoresTecnicos) {
            JsonObject directorJson = new JsonObject();
            directorJson.addProperty("id", directoresTecnicosIds.get(directorTecnico));
            directorJson.addProperty("nombre", directorTecnico.getNombre());
            directorJson.addProperty("apellido", directorTecnico.getApellido());
            directorJson.addProperty("edad", directorTecnico.getEdad());
            directorJson.addProperty("nacionalidad", directorTecnico.getNacionalidad());
            directorJson.addProperty("experiencia",  directorTecnico.getExperiencia());
            directorJson.addProperty("estiloDeJuego",   directorTecnico.getEstiloDeJuego());
            directoresJson.add(directorJson);
        }

        escribirArchivo("director_tecnico.json", directoresJson);
    }

    private void guardarSelecciones() throws IOException {
        JsonArray seleccionesJson = new JsonArray();

        for (Seleccion seleccion: selecciones) {
            JsonObject seleccionJson = new JsonObject();
            seleccionJson.addProperty("id", seleccionesIds.get(seleccion));
            seleccionJson.addProperty("nombre", seleccion.getNombre());
            seleccionJson.addProperty("pais", seleccion.getPais());
            seleccionJson.addProperty("confederacion", seleccion.getConfederacion());

            JsonArray idsJugadores = new JsonArray();
            for (Jugador jugador: seleccion.getJugadores()) {
                idsJugadores.add(jugadoresIds.get(jugador));
            }

            seleccionJson.add("jugadorIds", idsJugadores);

            DirectorTecnico directorTecnico = seleccion.getDirectorTecnico();

            if (directorTecnico != null) {
                seleccionJson.addProperty("directorTecnicoId", directoresTecnicosIds.get(directorTecnico));
            } else {
                seleccionJson.add("directorTecnicoId", null);
            }

            seleccionesJson.add(seleccionJson);
        }

        escribirArchivo("seleccion.json", seleccionesJson);
    }

    private void guardarPartidos() throws IOException {
        JsonArray partidosJson = new JsonArray();

        for (Partido partido: partidos) {
            JsonObject partidoJson = new JsonObject();
            partidoJson.addProperty("id", partidosIds.get(partido));
            partidoJson.addProperty("seleccionLocalId", seleccionesIds.get(partido.getSeleccionLocal()));
            partidoJson.addProperty("seleccionVisitanteId", seleccionesIds.get(partido.getSeleccionVisitante()));
            partidoJson.addProperty("fecha", partido.getFecha());
            partidoJson.addProperty("golesLocal", partido.getGolesLocal());
            partidoJson.addProperty("golesVisitante", partido.getGolesVisitante());
            partidoJson.addProperty("estado", partido.getEstado());

            partidosJson.add(partidoJson);
        }

        escribirArchivo("partido.json", partidosJson);
    }

    private void guardarGrupos() throws IOException {
        JsonArray gruposJson = new JsonArray();

        for (Grupo grupo: grupos) {
            JsonObject grupoJson = new JsonObject();
            grupoJson.addProperty("id", gruposIds.get(grupo));
            grupoJson.addProperty("nombre", grupo.getNombre());

            JsonArray idsSelecciones = new JsonArray();
            for (Seleccion seleccion: grupo.getSelecciones()) {
                idsSelecciones.add(seleccionesIds.get(seleccion));
            }

            grupoJson.add("seleccionIds", idsSelecciones);

            JsonArray idsPartidos = new JsonArray();
            for (Partido partido: grupo.getPartidos()) {
                idsPartidos.add(partidosIds.get(partido));
            }
            grupoJson.add("partidoIds", idsPartidos);

            gruposJson.add(grupoJson);
        }

        escribirArchivo("grupo.json", gruposJson);
    }

    private void guardarTorneos(List<Torneo> torneos) throws IOException {
        JsonArray torneosJson = new JsonArray();

        for (int i = 0; i < torneos.size(); i++) {
            Torneo torneo = torneos.get(i);
            JsonObject torneoJson = new JsonObject();
            torneoJson.addProperty("id", i + 1);
            torneoJson.addProperty("nombre", torneo.getNombre());
            torneoJson.addProperty("año", torneo.getAño());
            torneoJson.addProperty("sede", torneo.getSede());

            JsonArray idsGrupos = new JsonArray();
            for (Grupo grupo: torneo.getGrupos()) {
                idsGrupos.add(gruposIds.get(grupo));
            }
            torneoJson.add("grupoIds", idsGrupos);

            JsonArray idsPartidos = new JsonArray();
            for (Partido partido: torneo.getPartidos()) {
                idsPartidos.add(partidosIds.get(partido));
            }
            torneoJson.add("partidoIds", idsPartidos);

            JsonArray idsSelecciones = new JsonArray();
            for (Seleccion seleccion: torneo.getSelecciones()) {
                idsSelecciones.add(seleccionesIds.get(seleccion));
            }
            torneoJson.add("seleccionIds", idsSelecciones);

            torneosJson.add(torneoJson);
        }
        escribirArchivo("torneo.json", torneosJson);
    }

    private void escribirArchivo(String nombreArchivo, JsonElement contenido) throws IOException {
        Path ruta = Paths.get("src", "main", "resources", nombreArchivo);

        try (Writer writer = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            gson.toJson(contenido, writer);
        }
    }
}
