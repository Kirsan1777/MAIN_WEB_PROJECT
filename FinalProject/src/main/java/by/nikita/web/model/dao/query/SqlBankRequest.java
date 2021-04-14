package by.nikita.web.model.dao.query;

public class SqlBankRequest {
    public static final String ADD_BANK_CARD = "INSERT bank_accounts(id_card) VALUES (?)";
    public static final String UPDATE_BALANCE = "UPDATE bank_accounts SET balance = balance + ? WHERE id_card = ?";
    public static final String DELETE_USER_CARD_BY_ID = "DELETE FROM bank_accounts WHERE id_card = ?";
    public static final String GET_MONEY_BY_ID = "SELECT balance FROM bank_accounts WHERE id_card = ?";
}
