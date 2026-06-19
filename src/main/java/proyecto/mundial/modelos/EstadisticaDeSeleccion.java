package proyecto.mundial.modelos;

public class EstadisticaDeSeleccion {
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int partidosEmpatados;
    private int golesAFavor;
    private int golesEnContra;
    private int diferenciaDeGoles;

    public EstadisticaDeSeleccion() {}

    public void actualizarEstadistica(int golesAFavor, int golesEnContra) {}

    private int calcularPuntos(int golesAFavor, int golesEnContra) {
        return 0;
    }

    private int calcularDiferenciaDeGoles(int golesAFavor, int golesEnContra) {
        return 0;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public int getDiferenciaDeGoles() {
        return diferenciaDeGoles;
    }
}
