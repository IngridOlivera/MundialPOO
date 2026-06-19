package proyecto.mundial.modelos;

public class DirectorTecnico extends Persona {
    private int experiencia;
    private String estiloDeJuego;

    public DirectorTecnico(String nombre, String apellido, int edad, String nacionalidad, int experiencia, String estiloDeJuego) {
        super(nombre, apellido, edad, nacionalidad);
        this.experiencia = experiencia;
        this.estiloDeJuego = estiloDeJuego;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getEstiloDeJuego() {
        return estiloDeJuego;
    }
}
