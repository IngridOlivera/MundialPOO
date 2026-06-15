package proyecto.mundial.clases;

import java.util.ArrayList;
import java.util.List;

public class Seleccion {
    private String nombre;
    private String pais;
    private String confederacion;
    private List<Jugador> jugadores;
    private DirectorTecnico directorTecnico;
    private EstadisticaDeSeleccion estadistica;

    public Seleccion(String nombre, String pais, String confederacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.confederacion = confederacion;
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador){
        jugadores.add(jugador);
    }

    public void asignarDirectorTecnico(DirectorTecnico dt){
        this.directorTecnico = dt;
    }

    public void actualizarEstadistica(Partido partido){
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getConfederacion() {
        return confederacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public DirectorTecnico getDirectorTecnico() {
        return directorTecnico;
    }

    public EstadisticaDeSeleccion getEstadistica() {
        return estadistica;
    }
}
