package by.nikita.web.model.service;

import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.entity.User;

import java.util.Date;
import java.util.List;

public interface UserService {
     User authorization(String login, String password) throws ServiceException;
     void registration(String login, String password) throws ServiceException;
     List<User> getAllUsers() throws ServiceException;
     void deleteUser(int idUser) throws ServiceException;
     void updateUserRole(int idUser, int idRole) throws ServiceException;
     List<User> getAllUsersInformation() throws ServiceException;
     void updateUserPicture(int idUser, String picture) throws ServiceException;
     void updateUserInfo(User user) throws ServiceException;
     User findUserById(int id) throws ServiceException;
     User findUserInformation(int idUser) throws ServiceException;
     User findUserByLogin(String login) throws ServiceException;
     void updateBalance(int idUser, double money) throws ServiceException;
     double getBalance(int idUser) throws ServiceException;
     List<User> getAllSortUsers(String sort) throws ServiceException;
     List<User> getAllUserByName(String name) throws ServiceException;

}
