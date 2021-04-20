package by.nikita.web.model.dao.impl;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.model.dao.BankDAO;
import by.nikita.web.model.dao.ConnectionPool;
import by.nikita.web.model.dao.query.SqlBankRequest;
import by.nikita.web.model.dao.query.SqlUserRequest;
import by.nikita.web.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDaoImpl implements BankDAO {

    private static final BankDaoImpl instance = new BankDaoImpl();

    private BankDaoImpl() {
    }
    /**
     * get instance
     *
     * @return the instance
     */
    public static BankDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void addBankCard(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBankRequest.ADD_BANK_CARD)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void deleteUserCard(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBankRequest.DELETE_USER_CARD_BY_ID)) {
            statement.setInt(1, idUser);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateBalance(int idUser, double cost) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBankRequest.UPDATE_BALANCE)) {
            statement.setDouble(1, cost);
            statement.setInt(2, idUser);
            statement.executeUpdate();//command from update DB

        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public double findBalanceById(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        double money = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlBankRequest.GET_MONEY_BY_ID)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            money = readMoneyOnAccount(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return money;
    }

    private double readMoneyOnAccount(ResultSet resultSet) throws SQLException {
        double money = 0;
        while (resultSet.next()) {
            money = resultSet.getInt(1);
        }
        return money;
    }

}
