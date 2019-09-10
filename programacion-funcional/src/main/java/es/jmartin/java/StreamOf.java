package es.jmartin.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOf {
	
	private static final String WORD_REGEXP = "[- .:,]+";

	public static void main(String[] args) {
		
		
		List<String> strings = Stream.of("One", "Two", "Three")
				.filter(obj -> !obj.equals("One"))
				.peek(obj -> System.out.println("Peeked" + obj))
				.collect(Collectors.toList());
		
		
		IntStream.of(1,23,4,5,6,7)
		.skip(1)
		.filter(i -> i > 5)
		//.forEach(i -> System.out.println(i));
		//mejor escrito así
		.forEach(System.out::println);
		
		
		
		List<String> lista = new ArrayList<>();
		
		Stream.of("One", "Two", "Three")
		.filter(obj -> !obj.equals("One"))
		.forEach(lista::add);
		
		
		
		
		
		
		
		
		List<String> items = new ArrayList<>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");

		//lambda
		//Output : A,B,C,D,E
		items.forEach(item->System.out.println(item));

		//Output : C
		items.forEach(item->{
			if("C".equals(item)){
				System.out.println(item);
			}
		});

		//method reference
		//Output : A,B,C,D,E
		items.forEach(System.out::println);

		//Stream and filter
		//Output : B
		items.stream()
			.filter(s->s.contains("B"))
			.forEach(System.out::println);

		
		
	
		
		/**
		 * 
		 * El código anterior toma un fichero, recorre sus líneas, mapea los valores obtenidos de cada linea que correspondan con la expresión regular WORD_REGEXP, filtra aquellos que sean distintos, los pasa a minúsculas y los imprime por pantalla uno a uno.

	line -> Stream.of(line.split(WORD_REGEXP)): Devuelve un Stream con los valores obtenidos de aplicar la división de la línea mediante la expresión regular. El método flatMap irá recogiendo los streams generados de esta manera y los convertirá a un único stream que podrá ser procesado con posterioridad.
	String::toLowerCase: transforma los strings del stream a minúsculas.
	System.out::println: ya lo vimos con anterioridad, muestra por consola cada string.

		 */
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/SomeLines.txt"), StandardCharsets.UTF_8)) {
			reader.lines()
				  .flatMap(line -> Stream.of(line.split(WORD_REGEXP)))
				  .distinct()
				  .map(String::toLowerCase)
				  .forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
