package es.jmartin.java;

import java.util.Optional;

public class JavaOptional {

	public static void main (String[] args) {
        
        Libro l= new Libro("libro A");
        l.addCapitulo(new Capitulo ("capitulo1",50));
        l.addCapitulo(new Capitulo ("capitulo2",20));
        l.addCapitulo(new Capitulo ("capitulo3",40));
         
            Optional<Capitulo> busqueda;
         
            busqueda = l.buscarCapitulo("capitulo2");
            busqueda.ifPresent((x)->System.out.println(x.getLongitud() + " " + x.getNombre()));
    }
}




