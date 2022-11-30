package com.logic.utils;

import com.logic.models.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MovieManager {
    
    public final List<Movie> movies;
    public final Map<User, List<Movie>> userMovies = new TreeMap<>();
     
    
    
    public MovieManager(){
        
        User u1 = new User ("Ricardo", "1234", "Ricardo@gmail.com");
        User u2 = new User ("Alberto", "6845", "Alberto@gmail.com");
        User u3 = new User ("Susana", "8547", "Susana@gmail.com");
        User u4 = new User ("Paula", "12222", "paula@gmail.com");
        
        
        Director d1 = new Director ("Quentin", "Tarantino", LocalDate.of(1963, Month.MARCH, 27), Locale.US);
        Director d2 = new Director ("Stanley", "Kubrik", LocalDate.of(1928, Month.JULY, 26), Locale.US);
        Director d3 = new Director ("Steven", "Spielberg", LocalDate.of(1946, Month.DECEMBER, 27), Locale.US);
        Director d4 = new Director ("Pedro", "Almodovar", LocalDate.of(1929, Month.SEPTEMBER, 25), Locale.ITALY);
        Director d5 = new Director ("David", "Fincher", LocalDate.of(1962, Month.AUGUST, 28), Locale.US);
        Director d6 = new Director ("Werner", "Herzog", LocalDate.of(1942, Month.SEPTEMBER, 5), Locale.GERMANY);
        
        
        Movie m1 = new Movie("El club de la lucha", Genre.THRILLER,d5, 1999, false, PEGI.PEGI18);
        Movie m2 = new Movie("Seven", Genre.THRILLER,d5, 1965, false, PEGI.PEGI16);
        Movie m3 = new Movie("Alien 3", Genre.SCIFY,d5, 1992, false, PEGI.PEGI18);
        
        Movie m4 = new Movie("2001: Una Odisea en el Espacio", Genre.SCIFY, d2, 1968, false, PEGI.PEGI16);
        Movie m5 = new Movie("La chaqueta metálica", Genre.BELLIC, d2, 1987, false, PEGI.PEGI18);
        Movie m6 = new Movie("Lolita", Genre.ROMANTIC, d2, 1962, false, PEGI.PEGI16);
   
        Movie m7 = new Movie("ET", Genre.SCIFY, d3, 1982, false, PEGI.PEGI12);
        Movie m8 = new Movie("Tiburon", Genre.TERROR, d3, 1975, false, PEGI.PEGI18);
        Movie m9 = new Movie("En busca del valle encantado", Genre.ANIME, d3, 1988, false, PEGI.PUBLIC);
        
        Movie m10 = new Movie("Todo sobre mi madre", Genre.DRAMA, d4, 1999, false, PEGI.PUBLIC);
        Movie m11 = new Movie("Hablé con ella", Genre.ROMANTIC, d4, 2000, false, PEGI.PUBLIC);
        Movie m12 = new Movie("Volver", Genre.DRAMA, d4, 2005, false, PEGI.PUBLIC);
        
        Movie m13 = new Movie("Los odiosos 8", Genre.WESTERN, d1, 2015, false, PEGI.PEGI16);
        Movie m14 = new Movie("Pulp Fiction", Genre.THRILLER, d1, 1994, false, PEGI.PEGI18);
        Movie m15 = new Movie("Kill Bill Volumen II", Genre.WESTERN, d1, 2003, false, PEGI.PEGI18);
        
        Movie m16 = new Movie("Aguirre, la cólera de Dios", Genre.HISTORY, d6, 2003, false, PEGI.PEGI18);
        Movie m17 = new Movie("Stroszek", Genre.DRAMA, d6, 1977, false, PEGI.PEGI12);
        Movie m18 = new Movie("Nosferatu", Genre.TERROR, d6, 1979, false, PEGI.PEGI18);
       
       movies = addMovies (new ArrayList<Movie> (), m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18);
       
       userMovies.put(u1, addMovies(new ArrayList<Movie>(),m1, m2, m3, m4, m5, m6, m7, m8));
       userMovies.put(u2, addMovies(new ArrayList<Movie>(),m9, m10, m11, m12, m13));
       userMovies.put(u3, addMovies(new ArrayList<Movie>(), m14,m15,m16,m17));
       userMovies.put(u4, addMovies(new ArrayList<Movie>(), m18));
         
//       setValueRating(u1, m3.getTitle(), 4.5);
//       setValueRating(u2, m3.getTitle(), 4.5);
//       setValueRating(u3, m3.getTitle(), 4.5);
       //setValueRating(u3, m3.getTitle(),((Math.random() * (10-3))+3));
               
       randomizeTitles();
       updateRatings();
//       System.out.println(userMovies.values());
//       System.out.println(movies.get(0).getRating());
//       System.out.println(userMovies.get(u3).get(0));
    }
    
    public List <Movie> addMovies(List<Movie> moviesList, Movie...movies){
        moviesList.addAll(Arrays.asList(movies));
        return moviesList;
    }
 
    public Movie findByTitle(String title){
        
        try {
            return movies.stream()
                .filter((Movie mov)
                        -> {
                    return mov.getTitle().toLowerCase().contains(title.trim().toLowerCase());
                })
                .findFirst().get();
            
        } catch (NoSuchElementException e) {
            
            System.out.println("No hemos encontrado ninguna pelicula con ese titulo");
        }
           return null;
    } 
    
    public long moviesCount (){
        
        long count= movies.stream()
                .count();
        System.out.println("El numero de peliculas es "+ count);
        return count;
    }
        
    public void showAllMovies(){

        //Metodo antiguo
//        for (Movie movy : movies) {
//            System.out.println(movy);
//        }     
        //Metodo nuevo
        movies.stream()
                .forEach(movi -> System.out.println(movi));
    }
    
    public void showAllMovies(int limit){

        movies.stream()
                .limit(limit)
                .forEach(movi -> System.out.println(movi));
    }
    
    public void showAllMovies(int limit, long skip){

        movies.stream()
                .skip(skip)
                .limit(limit)
                .forEach(movi -> System.out.println(movi));
    }
     
    public void setValueRating(User user, String title, double rating){
        
        List<Movie> mv = userMovies.get(user).stream()
//                .map(n -> findByTitle(title))
                .peek((Movie n) -> {
                    if(n.getTitle().equals(title))
                        n.setRating(rating);   
                })
                .collect(Collectors.toList());
        userMovies.replace(user, mv);
              
    }
    
    public void randomizeTitles(){
        
        userMovies.forEach((User k, List<Movie> v) -> v.stream()
                .forEach((Movie m) -> setValueRating(k, m.getTitle()
                        , ((Math.random() * (10-3))+3)))                
//                        , (new Random().nextDouble(10-4)+4)))
                );
        
//        for (User user: userMovies.keySet()){
//            for (Movie mov : userMovies.get(user)){
//                setValueRating(user, mov.getTitle(), ((Math.random() * (10-3))+3));
//            }
//        }
        
    }
    
    public double averageRating(){
        return movies.stream()               
                .mapToDouble((Movie m) -> m.getRating())
                .filter((double d) -> d > 0) //Descarto las valoraciones que esten a 0 - No valoradas
                .average()  //Devuelve un OptionalDouble
                .getAsDouble();               
    }
    
    public double averageRating(Collection<Movie> cm){
        return cm.stream()
                .filter((Movie m) -> m.getRating() > (0))
                .mapToDouble(m -> m.getRating())
                .average()
                .getAsDouble();               
    }
    
    //Por cada película recorrer las valoraciones de todos los usuarios para esa película, 
    // y hacer la media de las valoraciones
    public List<Movie>  flapMapToList(){
        
        return userMovies.values().stream()
                .flatMap((List<Movie> c) -> c.stream().map(m -> m)) //Devolvemos Stream de películas como lista, para poder tener duplicados
                        .toList();        
    }
    
    public Map<String,List<Movie>> groupByMovies(List<Movie> ml){
        
        return ml.stream()
                .collect(Collectors.groupingBy(m -> m.getTitle()));
        
    } 
    
    public Double getAverage(String title, Map<String,List<Movie>> ml){
        return ml.get(title).stream()
                .mapToDouble(m -> m.getRating())
                .average()
                .orElse(0.0);
    }
    
    public void updateRatings(){
        
        Map<String,List<Movie>> data = groupByMovies(flapMapToList());
        movies.stream()
                .forEach((Movie m) -> m.setRating(getAverage(m.getTitle(),data)));
//                    Double rate = (
//                            getAverage(m.getTitle(),data) != 0) ? getAverage(m.getTitle(),data):0;
//                    m.setRating(rate);
//                });
                //getAverage(m.getTitle(),data)
    }
    
    public Map<Boolean,List<Movie>> moviePass(){
        
        return movies.stream()
                .collect(Collectors.partitioningBy((Movie m) -> m.getRating()>=5));
    }
    
    public List<Movie> showMoviePass(boolean op){
        
        System.out.println((op) ? "Películas aprobadas: " : "Películas suspendidas: ");
        //Usamos referencia a métodos en el forEach Objeto|Instancia::nombre_metodo  (método sin paréntesis)
        //Si fuera referencia a métodos estáticos nombre_Clase::nombre_metodo  (método sin paréntesis)
        //moviePass().get(op).forEach(System.out::println); //Es equivalente a (m -> System.out.println(m))
        List<Movie> lm = moviePass().get(op);
        lm.forEach(m -> System.out.println("TITULO: " + m.getTitle() + 
                " - RATING: " + String.format("%.2f", m.getRating())));
        return lm;
    }
    
    public List<Movie> sortedMoviesByRating(List<Movie> lm, boolean op){
        // m1=9.5 m2=9.1 --> con casteo = 9 - 9
        // Si casteo el número, multiplico por 10/100/100 depende del número de 
        //decimales obtendremos la diferencia correcta entre ambos valores
        //lm.sort((m1, m2) -> (int)m1.getRating().doubleValue() - (int)m2.getRating().doubleValue());
        
        //Asigno a un ocjeto una expresión Lambda
        Comparator<Movie> compare = (op) ? 
                (m1, m2) -> (int)(m1.getRating() * 100 - m2.getRating() * 100) 
                : 
                (m1, m2) -> (int)(m2.getRating() * 100 - m1.getRating() * 100);
        
        lm.sort(compare);
        //NOTA- En Streams el método es sorted
        return lm;
        
    }
    
    public void topByGenre(int num, int limit){
        
        if(num>=0 && num<=10){
            movies.stream()
                    .filter(m -> m.getGenre() == Genre.values()[num])
                    .sorted(Comparator
                            .comparing((Movie m) -> m.getRating() * 100)
                            .reversed()
                            .thenComparing((Movie a,Movie b) -> a.getYear() - b.getYear()))
                    .limit(limit)
                    .forEach(m -> System.out.println("TITULO: " + m.getTitle() + 
                            " - RATING: " + String.format("%.2f", m.getRating()) + 
                            " - YEAR: " + m.getYear()));                    
        }
    }
    
}