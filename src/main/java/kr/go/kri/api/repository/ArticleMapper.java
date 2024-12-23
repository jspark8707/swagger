package kr.go.kri.api.repository;

import kr.go.kri.api.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {

    public long getId();
    public long insert(Article article);
    public long update(Article article);
    public long delete(Article article);
    public Optional<Article> findById(Long id);

    //@Select("SELECT * FROM article")
    public List<Article> findAll();

    // Active/Inctive 상태에 따라 Article 조회
    List<Article> findAllByEnabled(@Param("enabled") boolean enabled);
}