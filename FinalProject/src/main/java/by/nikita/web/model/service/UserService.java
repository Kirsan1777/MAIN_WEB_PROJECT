package by.nikita.web.model.service;

import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;

import java.util.List;

/**
 * The {@code UserService} class represents User Service.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public interface UserService {
    /**
     * authorization user
     *
     * @param login    the user login
     * @param password the user password
     * @return the user info
     */
    User authorization(String login, String password) throws ServiceException;

    /**
     * registers a new user
     *
     * @param login    the user login
     * @param password the user password
     */
    void registration(String login, String password) throws ServiceException;

    /**
     * gets all users
     *
     * @return users
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * delete the user
     *
     * @param idUser the id user
     */
    void deleteUser(int idUser) throws ServiceException;

    /**
     * update user role
     *
     * @param idUser the id user
     * @param idRole the new id role for user
     */
    void updateUserRole(int idUser, int idRole) throws ServiceException;

    /**
     * get all users info
     *
     * @return get information about all users
     */
    List<User> getAllUsersInformation() throws ServiceException;

    /**
     * update user picture
     *
     * @param idUser  the id user
     * @param picture the new picture name
     */
    void updateUserPicture(int idUser, String picture) throws ServiceException;

    /**
     * update user information
     *
     * @param user the user
     */
    void updateUserInfo(User user) throws ServiceException;

    /**
     * find user
     *
     * @param id the id user
     * @return the user
     */
    User findUserById(int id) throws ServiceException;

    /**
     * find user info
     *
     * @param idUser the id user
     * @return the user info
     */
    User findUserInformation(int idUser) throws ServiceException;

    /**
     * searches for matches by login
     *
     * @param login the login
     * @return the user
     */
    User findUserByLogin(String login) throws ServiceException;

    /**
     * update user balance
     *
     * @param idUser the id user
     * @param money  the quantity of money
     */
    void updateBalance(int idUser, double money) throws ServiceException;

    /**
     * returns the user's balance
     *
     * @param idUser the id user
     * @return the user balance
     */
    double getBalance(int idUser) throws ServiceException;

    /**
     * sort users by criteria
     *
     * @param sort the search criteria
     * @return the users
     */
    List<User> getAllSortUsers(String sort) throws ServiceException;

    /**
     * get all users by name
     *
     * @param name the username
     * @return the users
     */
    List<User> getAllUserByName(String name) throws ServiceException;

}
