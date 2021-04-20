package by.nikita.web.model.entity;

import java.util.Objects;
/**
 * The {@code Comment} class represents Comment.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class Comment {
    private int id;
    private String comment;
    private String dateAdd;
    private int status;
    private int idUser;
    private int idBook;

    public Comment(){

    }

    public Comment(int id, String comment, String dateAdd, int status, int idUser, int idBook){
        this.id = id;
        this.comment = comment;
        this.dateAdd = dateAdd;
        this.status = status;
        this.idUser = idUser;
        this.idBook = idBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && status == comment1.status && idUser == comment1.idUser && idBook == comment1.idBook && Objects.equals(comment, comment1.comment) && Objects.equals(dateAdd, comment1.dateAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, dateAdd, status, idUser, idBook);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", dateAdd='" + dateAdd + '\'' +
                ", status=" + status +
                ", idUser=" + idUser +
                ", idBook=" + idBook +
                '}';
    }
}
