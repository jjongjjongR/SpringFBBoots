package com.example.FbBootsProject.dto;

import com.example.FbBootsProject.entity.Review;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ReviewForm {
    private Long id;
    private String modelName;
    private int comfort;
    private int width;
    private int length;
    private int touch;
    private int shooting;
    private int outsole;
    private int overall;
    // 실제 연관관계 설정은 서비스에서
    private Long fbBootId;

    public Review toEntity() {
        return new Review(modelName, comfort, width, length, touch, shooting, outsole, overall);
    }

    public void logInfo() {
        log.info("ReviewForm - id: {}, modelName: {}, comfort: {}, width: {}, length: {}, touch: {}, shooting: {}, outsole: {}, overall: {}, fbBootId: {}",
                id, modelName, comfort, width, length, touch, shooting, outsole, overall, fbBootId);
    }
}
