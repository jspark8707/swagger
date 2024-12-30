package kr.go.kri.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
}
