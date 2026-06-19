package proyecto.mundial;

import proyecto.mundial.interfaz.Menu;
import proyecto.mundial.modelos.Torneo;
import proyecto.mundial.persistencia.Persistencia;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Persistencia persistencia = new Persistencia();
        List<Torneo> torneos = persistencia.consultarDatos();
        Menu menu = new Menu(torneos);
        menu.mostrarMenuGeneral();
        persistencia.guardarDatos(torneos);
    }
}