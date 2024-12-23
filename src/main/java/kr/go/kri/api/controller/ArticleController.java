package kr.go.kri.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.go.kri.api.domain.Article;
import kr.go.kri.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "전임교원 조회", description = "등록된 전임교원을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 목록 반환", content = @Content)
    @GetMapping("/{id}")
    public Article findById(@PathVariable Long id) {
        return this.articleService.findById(id);
    }

    @Operation(summary = "전임교원 등록", description = "전임교원을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "성공적으로 전임교원 생성 반환", content = @Content)
    @PostMapping
    public Article create(@RequestBody Article article) {
        return this.articleService.create(article);
    }

    @Operation(summary = "전임교원 수정", description = "기존 전임교원의 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 수정 반환", content = @Content)
    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id); // ID 설정 (기존 자원 수정)
        return this.articleService.update(article);
    }

    @Operation(summary = "전임교원 삭제", description = "등록된 전임교원을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 삭제 반환", content = @Content)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.articleService.delete(id);
    }
}