package es.jmartin.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgramacionFuncionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramacionFuncionalApplication.class, args);
		
		
		List<String> nombres = new ArrayList<>();
		nombres.add("Javi");
		nombres.add("Rosa");
		nombres.add("Juan");
		
		System.out.println(greet(nombres));
		
		
		
		
		
		
		List<User> users = new LinkedList<>();

	    User birthdayChild = new User("peter", "20.02.1990");
	    User otherUser = new User("kid", "23.02.2008");
	    User birthdayChild2 = new User("bruce", "20.02.1980");

	    users.addAll(Arrays.asList(birthdayChild, otherUser, birthdayChild2));

	    greetAllBirthdayChildren(users);
	}
	
	
	public static void greetAllBirthdayChildren(List<User> users) {
	    String today = "20.02"; //Just to make the example easier. In production, you would use LocalDateTime or so.
	    users.stream()
	        .filter(user -> user.getBirthday().startsWith(today))
	        .forEach(user -> sendMessage("Happy birthday, ".concat(user.getUsername()).concat("!"), user));
	}
	
	
	public static String greet(List<String> names) {
	    String greeting = names
	            .stream()
	            .map(name -> name + " ")
	            .reduce("Welcome ",
	                    (acc, name) -> acc + name);
	    return greeting + "!";
	}
	
	/**
	 * "asignar" el número + 1 a cada elemento de la lista 
	 * y almacenarlo en una nueva Lista.
	 * @param numbers
	 * @return
	 */
	public List<Integer> addOne(List<Integer> numbers) {
	    return numbers
	            .stream()
	            .map(number -> number + 1)
	            .collect(Collectors.toList());
	}
	
	
	public void playingWithOptionals() {
	    String s = "Hello World!";
	    String nullString = null;

	    Optional<String> optionalS1 = Optional.of(s); // Will work
	    Optional<String> optionalS2 = Optional.ofNullable(s); // Will work too
	    Optional<String> optionalNull1 = Optional.of(nullString); // -> NullPointerException
	    Optional<String> optionalNull2 = Optional.ofNullable(nullString); // Will work

	    System.out.println(optionalS1.get()); // prints "Hello World!"
	    System.out.println(optionalNull2.get()); // -> NoSuchElementException
	    if(!optionalNull2.isPresent()) {
	        System.out.println("Is empty"); // Will be printed
	    }
	}
	
	
	/**
	 * duplicar el valor de un opcional, si está presente. 
	 * De lo contrario, devolveremos cero
	 * @param value
	 * @return
	 */
	public Integer doubleValueOrZero(Optional<Integer> value) {
	    return value.map(i -> i * 2).orElse(0);
	}
	
	
	public void showFlatMap() {
	    Optional<Double> two = Optional.of(2.0);
	    Optional<Double> zero = Optional.of(0.0);
	    two.flatMap(num -> divide(1.0, num)).orElse(0.0); // 0.5
	    zero.flatMap(num -> divide(1.0, num)).orElse(0.0); // 0.0
	}

	public Optional<Double> divide(Double first, Double second) {
	    if(second == 0.0) {
	       return Optional.empty();
	    }

	    return Optional.of(first / second);
	}
	
	
	public String defaultIfOptional(String string) {
	    return Optional.ofNullable(string).orElse("default");
	}
	
	public String defaultIfOptional2(String string) {
	    return (string != null) ? string : "default";
	}
	
	
	public void convertStuff() {
	    String[] array = {"apple", "banana"};
	    Set<String> emptySet = new HashSet<>();
	    List<Integer> emptyList = new LinkedList<>();

	    Stream<String> arrayStream = Arrays.stream(array);
	    Stream<String> setStream = emptySet.stream();
	    Stream<Integer> listStream = emptyList.stream();
	}
	
	public void showMap() {
	    Stream.of(1, 2, 3)
	        .map(num -> num * num)
	        .forEach(System.out::println); // 1 4 9
	}
	
	public void showFlatMapLists() {
	    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
	    List<Integer> numbers2 = Arrays.asList(4, 5, 6);

	    Stream.of(numbers1, numbers2) //Stream<List<Integer>>
	        .flatMap(List::stream)  //Stream<Integer>
	        .forEach(System.out::println); // 1 2 3 4 5 6
	}
	
	
	
	public void showFilter() {
	    Stream.of(0, 1, 2, 3)
	        .filter(num -> num < 2)
	        .forEach(System.out::println); // 0 1
	}
	
	
	public void negateFilter() {
	    Predicate<Integer> small = num -> num < 2;

	    Stream.of(0, 1, 2, 3)
	        .filter(small.negate()) // Now every big number passes
	        .forEach(System.out::println); // 2 3
	}

	public void filterNull() {
	    Stream.of(0, 1, null, 3)
	        .filter(Objects::nonNull)
	        .map(num -> num * 2) // without filter, you would've got a NullPointerExeception
	        .forEach(System.out::println); // 0 2 6
	}
	
	
	/**
	 * transformar su flujo de nuevo en otra estructura de datos
	 */
	public void showCollect() {
	    List<Integer> filtered = Stream.of(0, 1, 2, 3)
	        .filter(num -> num < 2)
	        .collect(Collectors.toList());
	}
	
	
	public void showJoining() {
	    String sentence = Stream.of("Who", "are", "you?")
	        .collect(Collectors.joining(" "));

	    System.out.println(sentence); // Who are you?

	}
	
	
	/**
	 * almacena todos los objetos de la secuencia en un objeto. 
	 * Puede concat todas las cadenas en una cadena, suma todos 
	 * los números y así sucesivamente. 
	 * Allí, los parámetros de inicio serían la cadena vacía o cero
	 */
	public void showReduceSum() {
	    Integer sum = Stream.of(1, 2, 3)
	        .reduce(0, Integer::sum);

	    System.out.println(sum); // 6
	}
	
	
	/**
	 * se basa en como en la compareTo()función, 
	 * nos muestra si el primer objeto es "menor" que el primero (int <0), 
	 * es tan grande como el segundo (int == 0), o es más grande que el segundo ( Int> 0).
	 */
	public void showSort() {
	    Stream.of(3, 2, 4, 0)
	        .sorted((c1, c2) -> c1 - c2)
	        .forEach(System.out::println); // 0 2 3 4
	}
	
	
	public void sumWithIntStream() {
	    Integer sum = Stream.of(0, 1, 2, 3)
	        .mapToInt(num -> num)
	        .sum();
	}
	
	/*
	@Test
	public void testIfNameIsStored() {
	    String testName = "Albert Einstein";

	    Datebase names = new Datebase();
	    names.drop();

	    db.put(testName);
	    assertTrue(db.getData()
	        .stream()
	        .anyMatch(name -> name.equals(testName)));
	}
	*/
	
	
	
	
	public class User {

	    private String username;
	    private String birthday;

	    public User(String username, String birthday) {
	        this.username = username;
	        this.birthday = birthday;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public String getBirthday() {
	        return birthday;
	    }

	}
	
	/**
	 * Para cada número par, sólo queremos imprimir este número. 
	 * Pero para cada número impar, queremos cuadrarlos antes de imprimirlos.
	 */
	public void workOnNumbers() {
	    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

	    Splitter.splitBy(numbers, num -> num%2 == 0)
	            .workWithPassed(passed ->
	                    passed.forEach(even -> System.out.println("" + even + " -> " + even)))
	            .workWithNotPassed(notPassed ->
	                    notPassed.map(odd -> odd * odd)
	                            .forEach(odd -> System.out.println("" + Math.sqrt(odd) + " -> " + odd)
	            ));
	}
	
	
	/**
	 * Enviar a todos los ganadores una confirmación y todos los perdedores una cancelación
	 * @param candidates
	 */
	public void sendEmails(List<Candidates> candidates) {
	    Splitter.splitBy(candidates, Candidates::hasWon)
	            .workWithPassed(winners ->
	                    winners.forEach(winner -> Email.send(winner.getEmail(), "You won!"))
	    )
	    .workWithNotPassed(losers ->
	            losers.forEach(loser -> Email.send(loser.getEmail(), "You lost, sorry!"))
	    );
	}
	
	
	/**
	 * Dada una lista de números decimales queremos descontar un 20% a todos los que sean mayores de 25 y sumarlos:
	 */
	public void descuento(List<BigDecimal> numbers) {
	
		/*
		BigDecimal sum = BigDecimal.ZERO;
		
		for (BigDecimal number : numbers) {
		   if (number.compareTo(BigDecimal.valueOf(25)) > 0) {
		      sum = sum.add(number.multiply(BigDecimal.valueOf(0.8)));
		   }
		}*/
		final BigDecimal sum = numbers.stream()
			.filter(number -> number.compareTo(BigDecimal.valueOf(25)) > 0)	//Filtrar los números mayores de 25.
			.map(number -> number.multiply(BigDecimal.valueOf(0.8)))		//Asignar a cada número un número igual a su 80%.
			.reduce(BigDecimal.ZERO, BigDecimal::add);						//Usar el método add para reducir esa lista.

		System.out.println("Total: " + sum);

	}
	
	public static class Splitter<T> {

	    private List<T> passed;
	    private List<T> notPassed;

	    private Splitter(List<T> passed, List<T> notPassed) {
	        this.passed = passed;
	        this.notPassed = notPassed;
	    }

	    public static <T> Splitter<T> splitBy(Collection<T> items,Predicate<T> test) {
	        List<T> passed = new LinkedList<T>();
	        List<T> notPassed = new LinkedList<T>();

	        items.stream()
	                .forEach(item -> {
	                    if(test.test(item)){
	                        passed.add(item);
	                        return;
	                    }
	                    notPassed.add(item);
	                });

	        return new Splitter<T>(passed, notPassed);
	    }

	    
	    public Splitter<T> workWithPassed(Consumer<Stream<T>> func) {
	        func.accept(passed.stream());
	        return this;
	    }

	    public Splitter<T> workWithNotPassed(Consumer<Stream<T>> func) {
	        func.accept(notPassed.stream());
	        return this;
	    }
	    
	}
	
	
}
