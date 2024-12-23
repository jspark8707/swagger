package kr.go.kri.api.service;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.go.kri.api.domain.Article;
import kr.go.kri.api.repository.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    private static final Logger log;

    static {
        log = org.slf4j.LoggerFactory.getLogger(ArticleService.class);
    }

    public Article create(Article article) {
        log.info("Request to create Article : " +  article);
        articleMapper.insert(article);
        return article;
    }

    public List<Article> findAll() {
        log.info("Request to get all Articles");
        //return this.articleMapper.findAll();
        return articleMapper.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Article findById(Long id) {
        log.debug("Request to get Article : {}", id);
        return articleMapper.findById(id).get();
    }


    public List<Article> findAllActive() {
        log.debug("Request to get all Articles");
        return this.articleMapper.findAllByEnabled(true);
    }

    public List<Article> findAllInactive() {
        log.debug("Request to get all Articles");
        return this.articleMapper.findAllByEnabled(false);
    }

    public Article update( @RequestBody @Schema(description = "수정할 title과 content가 포함된 Article 데이터") Article article) {
        log.debug("Request to update article : {}", article);
        this.articleMapper.update(article);
        return article;
    }
    public void delete(Long id) {
        log.debug("Request to delete article : {}", id);

        Article article = this.articleMapper.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Article with id " + id));
        //article.setEnabled(false);
        this.articleMapper.delete(article);
    }
}