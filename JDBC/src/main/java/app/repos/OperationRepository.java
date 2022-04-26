package app.repos;

import app.models.Article;
import app.models.OperationSummary;
import jdbc.utils.JDBCUtils;
import app.models.Operation;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationRepository {
    public void create(Operation operation) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

                stm.execute("INSERT INTO Operations VALUES (" +
                        "default, " +
                        operation.getArticleId() + ", " +
                        operation.getDebit() + ", " +
                        operation.getCredit() + ", " +
                        "'" + formatter.format(operation.getCreateDate().toLocalDateTime()) + "'" + ", " +
                        operation.getBalanceId() + ")" );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Operation operation) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

                stm.execute("UPDATE Operations SET " +
                        "ARTICLE_ID = " + operation.getArticleId() + ", " +
                        "DEBIT = " + operation.getDebit() + ", " +
                        "CREDIT = " + operation.getCredit() + ", " +
                        "CREATE_DATE = " + "'" + formatter.format(operation.getCreateDate().toLocalDateTime()) + "'" + ", " +
                        "BALANCE_ID = " + operation.getBalanceId() +
                        "WHERE ID = " + operation.getId());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Operation> readAll() {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Operations" );
                List<Operation> operations = new ArrayList<>();
                while (res.next()) {
                    operations.add(load(res));
                }
                return operations;
            }
        } catch(Exception e) {
            return null;
        }
    }

    public Operation readOne(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Operations WHERE ID = " + id);
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
                stm.execute("DELETE FROM Operations WHERE ID = " + id);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public OperationSummary getSummary(Timestamp date1, Timestamp date2) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                ResultSet res = stm.executeQuery("SELECT sum(O.DEBIT) AS TOTAL_DEBIT, sum(O.CREDIT) AS TOTAL_CREDIT " +
                        "FROM Operations O WHERE CREATE_DATE BETWEEN '" + formatter.format(date1.toLocalDateTime()) +
                        "' AND '" + formatter.format(date2.toLocalDateTime()) + "'" );
                OperationSummary summary = new OperationSummary();
                if (res.next()) {
                    summary.setDebit(res.getInt("TOTAL_DEBIT"));
                    summary.setCredit(res.getInt("TOTAL_CREDIT"));
                }
                return summary;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Operations WHERE ID = " + id);
                return res.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<Operation> findByArticleName(String name) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT Operations.* " +
                        "FROM Operations INNER JOIN Articles A on A.ID = Operations.ARTICLE_ID " +
                        "WHERE A.NAME ILIKE " + "'" + name + "%'" );
                List<Operation> operations = new ArrayList<>();
                while (res.next()) {
                    operations.add(load(res));
                }
                return operations;
            }
        } catch(Exception e) {
            return null;
        }
    }

    private Operation load(ResultSet res) throws SQLException {
        Operation operation = new Operation();
        operation.setId(res.getInt("ID"));
        operation.setArticleId(res.getInt("ARTICLE_ID"));
        operation.setDebit(res.getInt("DEBIT"));
        operation.setCredit(res.getInt("CREDIT"));
        operation.setCreateDate(res.getTimestamp("CREATE_DATE"));
        operation.setBalanceId(res.getInt("BALANCE_ID"));
        return operation;
    }
}
