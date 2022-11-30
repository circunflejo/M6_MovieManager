package com.logic.app;

import com.logic.utils.*;
import java.util.stream.Stream;
        
public class AppMain {

    public static void main(String[] args) {
     
        MovieManager mm = new MovieManager();
//        System.out.println(mm.findByTitle("2001"));
//        System.out.println(mm.findByTitle("pollito"));
//        mm.moviesCount();
//        mm.showAllMovies(2);
//        System.out.println("-".repeat(40));
//        mm.showAllMovies(8, 3);  //Indica el numero de peliculas que quiero mostar con un int //El segundo numero es el Skip, salta hasta el numero correspondiente de pelicula, en este caso, a partir de la 3º
//    
//        System.out.println("/--------------------------------------/");
//        mm.userMovies.forEach((k,v) -> v.forEach(m -> System.out.println(m.getRating())));
        
        
//          mm.sortedMoviesByRating(mm.moviePass().get(true), false).forEach(m -> System.out.println("TITULO: " + m.getTitle() + 
//                " - RATING: " + String.format("%.2f", m.getRating())));
          mm.topByGenre(0, 5);


          

//        Stream<String> nombres = Stream.of("Pablo","Cristina","Esther","Juan Carlos");
//        
//        //Sin un método final no se ejecuta el peek
//        Stream<String> names = nombres.peek(nombre -> System.out.println(nombre));
////                .allMatch(n -> n.length()<=5);
////        names.forEach(nombre -> System.out.println(nombre));
        
        
    
    }
    
}