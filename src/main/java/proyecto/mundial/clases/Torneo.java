package proyecto.mundial.clases;

import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private String nombre;
    private int año;
    private String sede;
    private List<Grupo> grupos;
    private List<Partido> partidos;
    private List<Seleccion> selecciones;

    public Torneo(String nombre, int año, String sede) {
        this.nombre = nombre;
        this.año = año;
        this.sede = sede;
        this.grupos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.selecciones = new ArrayList<>();
    }
    public void agregarGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    public void agregarPartido(Partido partido) {
        this.partidos.add(partido);
    }

    public void agregarSeleccion(Seleccion seleccion) {
        this.selecciones.add(seleccion);
    }

    public String getNombre() {
        return nombre;
    }

    public int getAño() {
        return año;
    }

    public String getSede() {
        return sede;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }
}
