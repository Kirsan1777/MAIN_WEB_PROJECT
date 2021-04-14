package by.nikita.web.model.dao;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.exception.TransactionException;
import by.nikita.web.model.dao.impl.BankDaoImpl;
import by.nikita.web.model.dao.impl.BookDaoImpl;
import by.nikita.web.model.dao.impl.OrderDaoImpl;
import by.nikita.web.model.dao.impl.UserDaoImpl;
import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.Comment;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.entity.UserRole;
import by.nikita.web.model.service.impl.BookServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Transaction {
    private static final Transaction instance = new Transaction();
    private Transaction(){
    }

    public static Transaction getInstance() {
        return instance;
    }

    private static final int MAX_BLOCKED_COMMENTS_TO_BLOCK_USER = 10;
    private static final int BLOCKED_USER_ROLE = 4;

    public void createAccountUserAndBank(User user) throws TransactionException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        BankDaoImpl bankDao = BankDaoImpl.getInstance();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            userDao.addUser(user.getLogin(), user.getPassword());
            User userToSave = userDao.findUser(user.getLogin(), user.getPassword());
            if (userToSave == null) {
                throw new DaoException("For some reason cant find newly created user");
            } else {
                bankDao.addBankCard(userToSave.getId());
                userDao.addUserInfo(userToSave.getId(), user.getName(), user.getDateRegistration(), userToSave.getRating(), user.getEmail());
            }
        } catch (ConnectionDataBaseException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't add user and his money account", e);
        } finally {
            closeConnection(connection);
        }
    }

    public void buyBook(User user, Book book) throws TransactionException {
        BankDaoImpl bankDao = BankDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            bankDao.updateBalance(user.getId(), book.getCost() * -1);
            bankDao.updateBalance(book.getAuthorId(), book.getCost());
            orderDao.addOrder(user.getId(), book.getId(), book.getCost());
        }catch (ConnectionDataBaseException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't buy book", e);
        }finally {
            closeConnection(connection);
        }
    }

    public void changeStateComment(int idComment, int idUser, int valueComment) throws TransactionException {
        BookDaoImpl bookDao = BookDaoImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            User user = userDao.findUserById(idUser);
            if(user.getRole() != UserRole.ADMIN) {
                bookDao.updateCommentStatus(idComment, valueComment);
                List<Comment> bannedComments = bookDao.findCommentsByStatus(idUser);
                if (bannedComments.size() > MAX_BLOCKED_COMMENTS_TO_BLOCK_USER) {
                    userDao.updateUserRole(idUser, BLOCKED_USER_ROLE);
                }
            }
        }catch (ConnectionDataBaseException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't block comment", e);
        }finally {
            closeConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            //LOGGER.error("Error while rollback transaction: ", e);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                //LOGGER.error("Error while closing connection: ", e);
            }
        }
    }
}
