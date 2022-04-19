package app.services;

import app.models.Article;
import app.models.ArticleOverview;
import app.repos.ArticleRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public void create(Article article) {
        articleRepository.create(article);
    }

    public List<Article> readAll() {
        return articleRepository.readAll();
    }

    public Article read(int id) {
        return articleRepository.readOne(id);
    }

    public boolean update(Article article, int id) {
        if (articleRepository.exists(id)) {
            article.setId(id);
            articleRepository.update(article);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (articleRepository.exists(id)) {
            articleRepository.deleteOne(id);
            return true;
        }
        return false;
    }

    public List<ArticleOverview> getOverview() {
        return articleRepository.getOverview();
    }
}
