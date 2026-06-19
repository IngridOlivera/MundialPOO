package proyecto.mundial.modelos;

public class EstadisticaDeSeleccion {
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int partidosEmpatados;
    private int golesAFavor ;
    private int golesEnContra  ;
    private int diferenciaDeGoles;

    public EstadisticaDeSeleccion() {
        this.puntos = 0;
        this.partidosJugados = 0;
        this.partidosGanados = 0;
        this.partidosPerdidos = 0;
        this.partidosEmpatados = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
        this.diferenciaDeGoles = 0;
    }

    public void actualizarEstadistica(int golesAFavor, int golesEnContra) {
        this.golesAFavor += golesAFavor;
        this.golesEnContra += golesEnContra;
        this.partidosJugados += 1;

        this.diferenciaDeGoles += calcularDiferenciaDeGoles(golesAFavor, golesEnContra);
        this.puntos += calcularPuntos(golesAFavor, golesEnContra);

    }

    private int calcularPuntos(int golesAFavor, int golesEnContra) {
        return 0;
        int puntosObtenidos = 0;
        if (golesAFavor > golesEnContra) {
            this.partidosGanados += 1;
            puntosObtenidos = 3;
        } else if (golesAFavor < golesEnContra) {
            this.partidosPerdidos += 1;
        } else {
            this.partidosEmpatados += 1;
            puntosObtenidos = 1;
        }
        return puntosObtenidos;
    }

    private int calcularDiferenciaDeGoles(int golesAFavor, int golesEnContra) {
        return golesAFavor - golesEnContra;
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
