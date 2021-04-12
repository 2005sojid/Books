public enum BookGenre {
    SCIENTIFIC("Scientific"), BUSINESS("Business"), NOVEL("Novel");
    private String genre;

    BookGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void total() {
        System.out.println("1. " + SCIENTIFIC.getGenre());
        System.out.println("2. " + BUSINESS.getGenre());
        System.out.println("3. " + NOVEL.getGenre());
    }
}
