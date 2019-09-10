package es.jmartin.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Libro {
	 
    private String titulo;
    private List<Capitulo> capitulos=new ArrayList<Capitulo>();
     
    public Libro(String titulo) {
        super();
        this.titulo = titulo;
         
    }
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public void addCapitulo(Capitulo c) {
         
        capitulos.add(c);
         
    }
    
    /**
    public Capitulo buscarCapitulo (String nombre) {
         
         
         
        for (Capitulo c: capitulos) {
            if (c.getNombre().equals("nombre")) {
                 
                return c;
            }
             
        }
         
        return null;
    }
    **/
    
    
    public Optional<Capitulo> buscarCapitulo (String nombre)  {
        for (Capitulo c: capitulos) {
            if (c.getNombre().equals(nombre)) {
                 
                return Optional.of(c);
            }
             
        }
        return Optional.empty();
    }	    
}
