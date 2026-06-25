package model;

public class Author extends Person {
    private String nationality;
    private String biography;

    public Author(int id, String name, String email) {
        super(id, name, email);
        this.nationality = "Unknown";
    }

    public Author(int id, String name, String email, String nationality) {
        super(id, name, email);
        this.nationality = nationality;
    }

    public Author(int id, String name, String email, String nationality, String biography) {
        super(id, name, email);
        this.nationality = nationality;
        this.biography = biography;
    }

    // Getters and Setters
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    @Override
    public String getRole() {
        return "Author";
    }

    @Override
    public String toString() {
        return getName() + " (" + nationality + ")";
    }
}
