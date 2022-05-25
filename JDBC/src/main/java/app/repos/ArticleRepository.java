package app.repos;

import app.models.Article;
import app.models.ArticleOverview;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
////    List<Article> readAll();
//    Article readOne(int id);
////    void deleteOne(int id);
//    List<ArticleOverview> getOverview();
//    ArticleOverview getOverviewByName(int id);
}
