package es.jmartin.java;

public class Capitulo {
	 
    private String nombre;
    private int longitud;
     
     
    public Capitulo(String nombre, int longitud) {
        super();
        this.nombre = nombre;
        this.longitud = longitud;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getLongitud() {
        return longitud;
    }
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
     
}	
