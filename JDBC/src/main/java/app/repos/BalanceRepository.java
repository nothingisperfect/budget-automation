package app.repos;

import jdbc.utils.JDBCUtils;
import app.models.Balance;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BalanceRepository {
    public void create(Balance balance) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                stm.execute("INSERT INTO Balance VALUES (" +
                        "default, " +
                        "'" + formatter.format(balance.getCreateDate().toLocalDateTime()) + "'" + ", " +
                        balance.getDebit() + ", " +
                        balance.getCredit() + ", " +
                        balance.getAmount() + ")" );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Balance balance) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                stm.execute("UPDATE Balance SET " +
                        "DEBIT = " + balance.getDebit() + ", " +
                        "CREDIT = " + balance.getCredit() + ", " +
                        "AMOUNT = " + balance.getAmount() +
                        "WHERE ID = " + balance.getId());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Balance> readAll() {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Balance");
                List<Balance> balances = new ArrayList<>();
                while (res.next()) {
                    balances.add(load(res));
                }
                return balances;
            }
        } catch(Exception e) {
            return null;
        }
    }

    public Balance readOne(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                    ResultSet res = stm.executeQuery("SELECT * FROM Balance WHERE ID = " + id);
                if (res.next()) {
                    return load(res);
                } else {
                    return null;
                }
            }
        } catch(Exception e) {
            return null;
        }
    }

    public void deleteOne(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                stm.execute("DELETE FROM Balance WHERE ID = " + id);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean exists(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Balance WHERE ID = " + id);
                return res.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

    private Balance load(ResultSet res) throws SQLException {
        Balance balance = new Balance();
        balance.setId(res.getInt("ID"));
        balance.setCreateDate(res.getTimestamp("CREATE_DATE"));
        balance.setDebit(res.getInt("DEBIT"));
        balance.setCredit(res.getInt("CREDIT"));
        balance.setAmount(res.getInt("AMOUNT"));
        return balance;
    }
}
