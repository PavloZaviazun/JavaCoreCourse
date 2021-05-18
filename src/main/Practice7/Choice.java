package main.Practice7;

public class Choice {
    private String id;
    private String title;
    private String year;
    private String genre;

    public Choice() {
        this.id = "";
        this.title = "";
        this.year = "";
        this.genre = "";
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public void makeYourChoice(String localName, String context) {
        if (!context.isEmpty()) {
            switch (localName) {
                case "id":
                    id = context;
                    break;
                case "title":
                    title = context;
                    break;
                case "year":
                    year = context;
                    break;
                case "genre":
                    genre = context;
                    break;
                default:
                    System.out.println("Something went wrong with element name");
            }
        }
    }
}
