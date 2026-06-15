package proyecto.mundial.clases;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nombre;
    private List<Seleccion> selecciones;
    private List<Partido> partidos;

    public Grupo(String nombre) {
        this.nombre = nombre;
        this.selecciones = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void agregarSeleccion(Seleccion seleccion) {
        selecciones.add(seleccion);
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }
    public List<Partido> getPartidos() {
        return partidos;
    }
}
