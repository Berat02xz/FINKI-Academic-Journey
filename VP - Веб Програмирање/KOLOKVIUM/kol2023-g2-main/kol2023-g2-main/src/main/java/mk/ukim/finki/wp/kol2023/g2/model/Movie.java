package mk.ukim.finki.wp.kol2023.g2.model;

public class Movie {

    public Movie() {
    }

    public Movie(String name, String description, Double rating, Genre genre, Director director) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.votes = 0;
    }

    private Long id;

    private String name;

    private String description;

    private Double rating;

    private Genre genre;

    private Director director;

    private Integer votes = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre position) {
        this.genre = position;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
