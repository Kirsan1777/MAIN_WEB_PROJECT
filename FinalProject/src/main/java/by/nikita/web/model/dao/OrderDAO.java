package by.nikita.web.model.dao;


import by.nikita.web.exception.DaoException;

import java.util.List;

/**
 * The {@code OrderDAO} class represents Order DAO.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface OrderDAO {

    /**
     * add order
     *
     * @param idUser the user id
     * @param idBook the id book
     * @param cost the cost of the book
     */
    void addOrder(int idUser, int idBook, double cost) throws DaoException;

    /**
     * delete order
     *
     * @param idBook the book id
     */
    void deleteOrders(int idBook) throws DaoException;

    /**
     * search all books by user id
     *
     * @param idUser the user id
     * @return the books
     */
    List<Integer> getAllBooksByIdUser(int idUser) throws DaoException;

}
