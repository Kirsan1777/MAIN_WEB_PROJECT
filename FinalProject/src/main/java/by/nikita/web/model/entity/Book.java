package by.nikita.web.model.entity;

import java.util.Objects;

public class Book {
    private String nameBook;
    private int id;
    private int authorId;
    private Genre genre;
    private String text;
    private String description;
    private String photoReference;
    private double cost;
    private String dateAdd;
    private int access;

    public Book(){

    }

    public Book(String nameBook, int id, int authorId, Genre genre, String text, String description, String photoReference, double cost, String dateAdd, int access){
        this.nameBook = nameBook;
        this.id = id;
        this.authorId = authorId;
        this.cost = cost;
        this.description = description;
        this.dateAdd = dateAdd;
        this.genre = genre;
        this.photoReference = photoReference;
        this.text = text;
        this.access = access;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && authorId == book.authorId && Double.compare(book.cost, cost) == 0 && access == book.access &&  Objects.equals(nameBook, book.nameBook) && genre == book.genre && Objects.equals(text, book.text) && Objects.equals(description, book.description) && Objects.equals(photoReference, book.photoReference) && Objects.equals(dateAdd, book.dateAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBook, id, authorId, genre, text, description, photoReference, cost, dateAdd, access);
    }

    @Override
    public String toString() {
        return "Book{" +
                "nameBook='" + nameBook + '\'' +
                ", id=" + id +
                ", authorId=" + authorId +
                ", genre=" + genre +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", photoReference='" + photoReference + '\'' +
                ", cost=" + cost +
                ", dateAdd='" + dateAdd + '\'' +
                ", access=" + access +
                '}';
    }
}
