package proyecto.mundial.modelos;

public class Partido {
    private Seleccion seleccionLocal;
    private Seleccion seleccionVisitante;
    private String fecha;
    private int golesLocal;
    private int golesVisitante;
    private String estado;

    public Partido( Seleccion seleccionLocal, Seleccion seleccionVisitante, String fecha) {
        this.seleccionLocal = seleccionLocal;
        this.seleccionVisitante = seleccionVisitante;
        this.fecha = fecha;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.estado = "Programado";
    }

    public void actualizarGolesLocal(int golesLocales) {
        if (!estado.equals("Finalizado")) {
            this.golesLocal = golesLocales;
        }
    }
    public void actualizarGolesVisitante(int golesVisitante) {
        if (!estado.equals("Finalizado")) {
            this.golesVisitante = golesVisitante;
        }
    }
    public void actualizarEstado(String estado) {
        if (!this.estado.equals("Finalizado")) {
            this.estado = estado;

            if (estado.equals("Finalizado")) {
                seleccionLocal.actualizarEstadistica(golesLocal,golesVisitante);
                seleccionVisitante.actualizarEstadistica(golesVisitante,golesLocal);
            }
        }
    }

    public Seleccion getSeleccionLocal() {
        return seleccionLocal;
    }

    public Seleccion getSeleccionVisitante() {
        return seleccionVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
