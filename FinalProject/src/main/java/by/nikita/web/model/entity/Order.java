package by.nikita.web.model.entity;

import java.util.Objects;
/**
 * The {@code Order} class represents Order.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class Order {
    private int idUser;
    private int idBook;
    private double cost;
    private int idOrder;

    public Order(){

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idUser == order.idUser && idBook == order.idBook && Double.compare(order.cost, cost) == 0 && idOrder == order.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idBook, cost, idOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idUser=" + idUser +
                ", idBook=" + idBook +
                ", cost=" + cost +
                ", idOrder=" + idOrder +
                '}';
    }
}
