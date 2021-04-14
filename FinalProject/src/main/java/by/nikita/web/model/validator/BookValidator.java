package by.nikita.web.model.validator;

import by.nikita.web.exception.DaoException;
import by.nikita.web.exception.ServiceException;
import by.nikita.web.model.dao.impl.BankDaoImpl;
import by.nikita.web.model.dao.impl.OrderDaoImpl;

import java.util.List;

public class BookValidator {

    BankDaoImpl bank = BankDaoImpl.getInstance();
    OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    public boolean chekBuyOperation(int idUser, double cost) throws ServiceException {
        boolean resultChek = false;
        try {
            resultChek = bank.findBalanceById(idUser) - cost >= 0;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultChek;
    }

    public boolean chekBookAvailability(int idUser, int idBook) throws ServiceException {
        int checkBook = 0;
        try {
            List<Integer> orders = orderDao.getAllBooksByIdUser(idUser);
            for(Integer i : orders){
                if(i == idBook){
                    checkBook++;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return checkBook==0;
    }
}
