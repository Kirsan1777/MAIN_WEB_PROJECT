package by.nikita.web.model.service.impl;

import by.nikita.web.exception.DaoException;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.dao.impl.BankDaoImpl;
import by.nikita.web.model.dao.impl.UserDaoImpl;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.service.UserService;
import by.nikita.web.model.validator.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private BankDaoImpl bankDao = BankDaoImpl.getInstance();
    private static final int BASIC_RATING = 0;

    private static final UserServiceImpl instance = new UserServiceImpl();

    /**
     * get instance
     *
     * @return the instance
     */
    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserServiceImpl() {

    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.findUser(login, password);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    @Override
    public void registration(String login, String password) throws ServiceException {
        UserValidator validator = new UserValidator();
        boolean chek = false;
        chek = validator.validateUser(login, password);
        if (chek) {
            try {
                userDao.addUser(login, password);
            } catch (DaoException ex) {
                throw new ServiceException(ex);
            }
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.getAllUsers();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return users;
    }

    @Override
    public List<User> getAllUsersInformation() throws ServiceException {
        List<User> users;
        try {
            users = userDao.getAllUsersInfo();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return users;
    }

   /* @Override
    public void fillUserInformation(int idUser, String name, String email, String dateRegistration) throws ServiceException {
        try{
            userDao.addUserInfo(idUser, name, dateRegistration, BASIC_RATING,  email);
        }catch (DaoException ex){
            throw new ServiceException(ex);
        }
    }*/

    @Override
    public void deleteUser(int idUser) throws ServiceException {
        UserValidator validator = new UserValidator();
        if (!validator.checkRoleUser(idUser)) {
            try {
                bankDao.deleteUserCard(idUser);
                userDao.deleteUserInfo(idUser);
                userDao.deleteUser(idUser);
            } catch (DaoException ex) {
                throw new ServiceException(ex);
            }
        }
    }

    @Override
    public void updateUserRole(int idUser, int idRole) throws ServiceException {
        try {
            userDao.updateUserRole(idUser, idRole);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void updateUserPicture(int idUser, String picture) throws ServiceException {
        try {
            userDao.updatePicture(idUser, picture);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserInfo(User user) throws ServiceException {
        try {
            if (user.getPassword() != null && !user.getPassword().equals("")) {
                userDao.updateUserPassword(user);
            }
            userDao.updateUserInfo(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.findUserById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    @Override
    public User findUserInformation(int idUser) throws ServiceException {
        User user;
        try {
            user = userDao.findUserInfoById(idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByName(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void updateBalance(int idUser, double money) throws ServiceException {
        try {
            bankDao.updateBalance(idUser, money);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double getBalance(int idUser) throws ServiceException {
        double balance;
        try {
            balance = bankDao.findBalanceById(idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return balance;
    }

    @Override
    public List<User> getAllUserByName(String name) throws ServiceException {
        List<User> users;
        try {
            users = userDao.findUserByNameSort(name);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return users;
    }

    @Override
    public List<User> getAllSortUsers(String sort) throws ServiceException {
        List<User> users;
        try {
            users = userDao.getAllUsersBySort(sort);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
        return users;
    }

}
