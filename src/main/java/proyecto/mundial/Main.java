package proyecto.mundial;

import proyecto.mundial.interfaz.Menu;
import proyecto.mundial.modelos.*;
import proyecto.mundial.persistencia.LecturaJson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LecturaJson lectura = new LecturaJson();
        Menu menu = new Menu(lectura.cargarDatos());
        menu.mostrarMenuGeneral();
    }
}