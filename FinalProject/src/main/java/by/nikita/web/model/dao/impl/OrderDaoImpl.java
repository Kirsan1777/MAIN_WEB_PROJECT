package by.nikita.web.model.dao.impl;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.model.dao.ConnectionPool;
import by.nikita.web.model.dao.OrderDAO;
import by.nikita.web.model.dao.query.SqlBankRequest;
import by.nikita.web.model.dao.query.SqlBookRequest;
import by.nikita.web.model.dao.query.SqlOrderRequest;
import by.nikita.web.model.dao.query.SqlUserRequest;
import by.nikita.web.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code OrderDaoImpl} class represents Order DaoImpl.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class OrderDaoImpl implements OrderDAO {

    private static final OrderDaoImpl instance = new OrderDaoImpl();
    private static final int CODE_DELETED_BOOK = 0;

    private OrderDaoImpl() {
    }
    /**
     * get instance
     *
     * @return the instance
     */
    public static OrderDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void addOrder(int idUser, int idBook, double cost) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlOrderRequest.ADD_ORDER)) {
            statement.setInt(1, idBook);
            statement.setInt(2, idUser);
            statement.setDouble(3, cost);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }


    @Override
    public void deleteOrders(int idBook) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlOrderRequest.DELETE_ORDER_BY_ID_BOOK)) {
            statement.setInt(1, idBook);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public List<Integer> getAllBooksByIdUser(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Integer> idBooks;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlOrderRequest.SELECT_ALL_BOOKS_ID)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            idBooks = readAllIdBookByIdUser(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return idBooks;
    }

    private List<Integer> readAllIdBookByIdUser(ResultSet resultSet) throws SQLException {
        List<Integer> idBooks = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            idBooks.add(id);
        }
        return idBooks;
    }
}
