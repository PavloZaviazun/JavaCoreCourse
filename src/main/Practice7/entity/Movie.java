package main.Practice7.entity;

public class Movie {
    private int id;
    private String title;
    private int year;
    private Genre genre;

    public Movie(int id, String title, int year, Genre genre) {
        if(id <= 0 || year < 1850) throw new IllegalArgumentException();
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", year=" + year + ", genre=" + genre + '}';
    }

    public enum Genre {
        HORROR("Horror"),
        THRILLER("Thriller"),
        COMEDY("Comedy");

        private String value;

        Genre(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
