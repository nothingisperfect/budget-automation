package app.repos;

import app.models.Article;
import app.models.ArticleOverview;
import jdbc.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    public void create(Article article) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                stm.execute("INSERT INTO Articles VALUES (" +
                        "default, " +
                        "'" + article.getName() + "'" + ")" );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Article article) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                stm.execute("UPDATE Articles SET " +
                        "NAME = " + "'" + article.getName() + "'" +
                        "WHERE ID = " + article.getId());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Article> readAll() {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Articles" );
                List<Article> articles = new ArrayList<>();
                while (res.next()) {
                    articles.add(load(res));
                }
                return  articles;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Article readOne(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Articles WHERE ID = " + id);
                if (res.next()) {
                    return load(res);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteOne(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                stm.executeQuery("DELETE FROM Articles WHERE ID = " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean exists(int id) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery("SELECT * FROM Articles WHERE ID = " + id);
                return res.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<ArticleOverview> getOverview() {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            try (Statement stm = connection.createStatement()) {
                ResultSet res = stm.executeQuery(
                        "SELECT Articles.ID, Articles.NAME, " +
                                "count(O.ID) AS OP_COUNT, sum(O.DEBIT) AS TOTAL_DEBIT, sum(O.CREDIT) AS TOTAL_CREDIT " +
                                "FROM Articles LEFT JOIN Operations O on Articles.ID = O.ARTICLE_ID " +
                                "GROUP BY Articles.ID, Articles.NAME" );
                List<ArticleOverview> articles = new ArrayList<>();
                while (res.next()) {
                    ArticleOverview article = new ArticleOverview();
                    article.setId(res.getInt("ID"));
                    article.setName(res.getString("NAME"));
                    article.setOpCount(res.getInt("OP_COUNT"));
                    article.setTotalDebit(res.getInt("TOTAL_DEBIT"));
                    article.setTotalCredit(res.getInt("TOTAL_CREDIT"));
                    articles.add(article);
                }
                return  articles;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Article load(ResultSet res) throws SQLException {
        Article article = new Article();
        article.setId(res.getInt("ID"));
        article.setName(res.getString("NAME"));
        return article;
    }
}
