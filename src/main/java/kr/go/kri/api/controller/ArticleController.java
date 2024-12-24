package kr.go.kri.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kr.go.kri.api.domain.Article;
import kr.go.kri.api.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Operation(summary = "전임교원 조회", description = "등록된 전임교원을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 목록 반환", content = @Content)
    @GetMapping("/{id}")
    public Article findById(@PathVariable Long id) {
        return this.articleService.findById(id);
    }

    @Operation(summary = "전임교원 등록", description = "전임교원을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공적으로 전임교원 생성 반환", content = @Content)
    @PostMapping
    public Article create(@org.springframework.web.bind.annotation.RequestBody Article article) {
        return this.articleService.create(article);
    }

    @Operation(summary = "전임교원 수정", description = "전임교원의 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 수정 반환", content = @Content)
    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody Article article) {
        article.setId(id); // ID 설정 (기존 자원 수정)
        return this.articleService.update(article);
    }

    @Operation(summary = "전임교원 삭제", description = "등록된 전임교원을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 전임교원 삭제 반환", content = @Content)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.articleService.delete(id);
    }

    // 로컬 디렉토리 경로
    //@Value("${fil1e.upload-dir}")
    private String uploadDir;

    @Operation(summary = "첨부파일 업로드", description = "첨부파일을 업로드합니다.")
    @ApiResponse(responseCode = "200", description = "파일 업로드 성공")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart("file") @RequestBody @Schema(description = "업로드할 파일", type = "string", format = "binary") MultipartFile file) {
        try {

        String projectDir = System.getProperty("user.dir");
        // 업로드 디렉토리 설정 (프로젝트 루트의 uploads 폴더)
         uploadDir = projectDir + File.separator + "uploads";

        // 디렉토리가 없다면 생성
        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // 업로드된 파일 저장
        File uploadedFile = new File(uploadDirectory, file.getOriginalFilename());
        file.transferTo(uploadedFile);

        return ResponseEntity.ok("파일 업로드 성공: " + file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}