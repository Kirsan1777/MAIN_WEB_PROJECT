package by.nikita.web.model.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private UserRole role;
    private int id;
    private String name;
    private String dateRegistration;
    private int rating;
    private String email;
    private String picture;

    public User(){}

    public User(String login, String password, UserRole role, int id){
        this.login = login;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(int id, String name, String dateRegistration, int rating, String email, String picture){
        this.id = id;
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.rating = rating;
        this.email = email;
        this.picture = picture;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && rating == user.rating && Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role && Objects.equals(name, user.name) && Objects.equals(dateRegistration, user.dateRegistration) && Objects.equals(email, user.email) && Objects.equals(picture, user.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, id, name,  dateRegistration, rating, email, picture);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", dateRegistration=" + dateRegistration +
                ", rating=" + rating +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
