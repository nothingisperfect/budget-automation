package app.controllers;

import app.models.Article;
import app.models.ArticleOverview;
import app.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles")
    public ResponseEntity<?> create(@RequestBody Article article) {
        articleService.create(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<List<Article>> read() {
        final List<Article> articles = articleService.readAll();

        return articles != null
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/articles/{id}")
    public ResponseEntity<Article> read(@PathVariable(name = "id") int id) {
        final Article article = articleService.read(id);

        return article != null
                ? new ResponseEntity<>(article, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/articles/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Article article) {
        final boolean updated = articleService.update(article, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/articles/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = articleService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/articles/overview")
    public ResponseEntity<List<ArticleOverview>> getOverview() {
        final List<ArticleOverview> articles = articleService.getOverview();

        return articles != null
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/articles/overview/{id}")
    public ResponseEntity<ArticleOverview> getOverviewByName(@PathVariable(name = "id") int id) {
        final ArticleOverview article = articleService.getOverviewByName(id);

        return article != null
                ? new ResponseEntity<>(article, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
