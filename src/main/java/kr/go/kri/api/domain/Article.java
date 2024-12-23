package kr.go.kri.api.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Schema(description = "Article의 고유 id", example = "1")
    private Long id;
    @Schema(description = "Article의 제목", example = "title")
    private String title;
    @Schema(description = "Article의 내용", example = "content")
    private String content;

    // Getter
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Setter
    public void setId(Long id) {
        this.id  = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
