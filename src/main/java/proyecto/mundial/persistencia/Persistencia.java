package proyecto.mundial.persistencia;

import proyecto.mundial.modelos.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Persistencia { ;
    private final LecturaJson lectura;
    private final EscrituraJson escritura;

    public Persistencia() {
        this.lectura = new LecturaJson();
        this.escritura = new EscrituraJson();
    }

    public List<Torneo> consultarDatos() throws IOException {
        return lectura.cargarDatos();
    }

    public void guardarDatos(List<Torneo> torneos) throws IOException {
        escritura.guardarDatos(torneos);
    }
}
