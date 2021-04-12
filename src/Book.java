public class Book {
    private Long id;
    private String name;
    private String year;
    private String author;
    private String genre;
    private Long book_id;

    public Book(String name, String year, String author, String genre, Long book_id) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.book_id = book_id;
    }

    public Book(Long id, String name, String year, String author, String genre, Long book_id) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.book_id = book_id;
    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }
}

