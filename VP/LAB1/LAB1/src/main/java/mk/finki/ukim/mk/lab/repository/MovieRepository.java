package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Repository //annotation that lets the server know that this is a repo
public class MovieRepository  {

    //kreiranje lista od filmovi
    public static List<Movie> movies = new ArrayList<>();


    //incijaliziranje na 10 filmovi vo konstruktor na MovieRepository, toa znaci koga ke se kreira ovoj repo avtomatski ke ima 10 filmovi vnatre
    public MovieRepository(){
        movies = new ArrayList<>();
        movies.add(new Movie("Catch Me If You Can ", "Barely 21 yet, Frank is a skilled forger who has passed as a doctor, lawyer and pilot. FBI agent Carl becomes obsessed with tracking down the con man, who only revels in the pursuit.",8.0));
        movies.add(new Movie("The Current War", "The dramatic story of the cutthroat race between electricity titans Thomas A. Edison and George Westinghouse to determine whose electrical system would power the modern world.",7.0));
        movies.add(new Movie("Gone Girl", "With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected that he may not be innocent.",9.5));
        movies.add(new Movie("Shutter Island", "Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.",10.0));
        movies.add(new Movie("Talk to Me", "When a group of friends discover how to conjure spirits using an embalmed hand, they become hooked on the new thrill, until one of them goes too far and unleashes terrifying supernatural forces.",8.3));
        movies.add(new Movie("Ready Player One", "When the creator of a virtual reality called the OASIS dies, he makes a posthumous challenge to all OASIS users to find his Easter Egg, which will give the finder his fortune and control of his world.",8.3));
        movies.add(new Movie("Tetris", "The story of how one of the world's most popular video games found its way to players around the globe. Businessman Henk Rogers and Tetris inventor Alexey Pazhitnov join forces in the USSR, risking it all to bring it to the masses.",7.8));
        movies.add(new Movie("Knives Out", "A detective investigates the death of the patriarch of an eccentric, combative family.",9.2));
        movies.add(new Movie("The Breakfast Club", "Five high school students meet in Saturday detention and discover how they have a great deal more in common than they thought.",7.2));
        movies.add(new Movie("Avatar: The Way of Water ", "Jake Sully lives with his newfound family formed on the extrasolar moon Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na'vi race to protect their home.",9.7));
    }

    //returns the whole list
    public List<Movie> findAll(){
        return movies;
    }

    //returns the requested movies that have the title/summary

    public List<Movie> searchMovies(String text, double rating){
        List<Movie> searchResults = new ArrayList<>();
        for (int i=0; i< movies.size(); i++){
            if(movies.get(i).title.contains(text)){
                if(movies.get(i).rating >= rating) {
                    searchResults.add(movies.get(i));
                }
            }}
        return searchResults;
    }


}
