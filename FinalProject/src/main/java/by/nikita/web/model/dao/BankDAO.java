package by.nikita.web.model.dao;

import by.nikita.web.exception.DaoException;

/**
 * The {@code BankDAO} class represents Bank DAO.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface BankDAO {
    /**
     * add bank card
     *
     * @param idUser  the user id
     */
    void addBankCard(int idUser) throws DaoException;

    /**
     * delete bank card
     *
     * @param idUser  the user id
     */
    void deleteUserCard(int idUser) throws DaoException;

    /**
     * update user balance
     *
     * @param idUser the id user
     * @param cost  the quantity of money
     */
    void updateBalance(int idUser, double cost) throws DaoException;

    /**
     * returns the user's balance
     *
     * @param idUser the id user
     * @return the user balance
     */
    double findBalanceById(int idUser) throws DaoException;

}
