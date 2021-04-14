package by.nikita.web.model.dao.query;

public class SqlOrderRequest {
    public static final String ADD_ORDER = "INSERT orders(id_book, id_user, cost) VALUES (?, ?, ?)";
    public static final String UPDATE_ID_BOOK = "UPDATE orders SET id_book = ? WHERE id_book = ?";
    public static final String DELETE_ORDER_BY_ID_BOOK = "DELETE FROM orders WHERE id_book = ?";
    public static final String SELECT_ALL_BOOKS_ID = "SELECT id_book FROM orders WHERE id_user = ? ";
}
