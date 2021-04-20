package by.nikita.web.model.dao;


import by.nikita.web.exception.DaoException;
import by.nikita.web.model.entity.User;

import java.util.List;

/**
 * The {@code UserDAO} class represents user DAO.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface UserDAO {
    /**
     * add user
     *
     * @param login    the login
     * @param password the password
     */
    void addUser(String login, String password) throws DaoException;

    /**
     * gets all users
     *
     * @return users
     */
    List<User> getAllUsers() throws DaoException;

    /**
     * find user
     *
     * @param login    the login
     * @param password thw password
     * @return the user
     */
    User findUser(String login, String password) throws DaoException;

    /**
     * find user by name
     *
     * @param login the username
     * @return the user
     */
    User findUserByName(String login) throws DaoException;

    /**
     * find and sort user
     *
     * @param name the username
     * @return the users
     */
    List<User> findUserByNameSort(String name) throws DaoException;

    /**
     * get all users info
     *
     * @return get information about all users
     */
    List<User> getAllUsersInfo() throws DaoException;

    /**
     * find user by id
     *
     * @param idUser the id user
     * @return the user
     */
    User findUserById(int idUser) throws DaoException;

    /**
     * add user indo
     *
     * @param idUser           the user id
     * @param name             the user name
     * @param dateRegistration the user registration date
     * @param rating           the rating
     * @param email            the email
     */
    void addUserInfo(int idUser, String name, String dateRegistration, int rating, String email) throws DaoException;

    /**
     * delete the user
     *
     * @param idUser the id user
     */
    void deleteUser(int idUser) throws DaoException;

    /**
     * delete the user info
     *
     * @param idUser the id user
     */
    void deleteUserInfo(int idUser) throws DaoException;

    /**
     * update user role
     *
     * @param idUser the id user
     * @param idRole the new id role for user
     */
    void updateUserRole(int idUser, int idRole) throws DaoException;

    /**
     * find user info
     *
     * @param idUser the id user
     * @return the user info
     */
    User findUserInfoById(int idUser) throws DaoException;

    /**
     * find user info by name
     *
     * @param name the user name
     * @return the users
     */
    List<User> findUserInfoByName(String name) throws DaoException;

    /**
     * update user picture
     *
     * @param idUser  the id user
     * @param picture the new picture name
     */
    void updatePicture(int idUser, String picture) throws DaoException;

    /**
     * update user information
     *
     * @param user the user
     */
    void updateUserInfo(User user) throws DaoException;

    /**
     * sort users by criteria
     *
     * @param sort the search criteria
     * @return the users
     */
    List<User> getAllUsersBySort(String sort) throws DaoException;

    /**
     * update user password
     *
     * @param user the user
     */
    void updateUserPassword(User user) throws DaoException;
}
