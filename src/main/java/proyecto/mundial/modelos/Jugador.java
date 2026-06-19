package proyecto.mundial.modelos;

public class Jugador extends Persona {
    private String posicion;
    private int numero;

    public Jugador(String nombre, String apellido,int edad, String nacionalidad, String posicion, int numero) {
        super(nombre, apellido, edad, nacionalidad);
        this.posicion = posicion;
        this.numero = numero;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getNumero() {
        return numero;
    }
}
