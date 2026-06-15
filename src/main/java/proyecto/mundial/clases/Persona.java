package proyecto.mundial.clases;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;

    public Persona(String nombre, String apellido, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    public int getEdad() {
        return edad;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
}
