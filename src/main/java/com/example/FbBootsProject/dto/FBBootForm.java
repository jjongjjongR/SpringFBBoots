package com.example.FbBootsProject.dto;

import com.example.FbBootsProject.entity.FBBoot;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class FBBootForm {
    private Long id;
    private String modelName;
    private int comfort;
    private int width;
    private int length;
    private int touch;
    private int shooting;
    private int outsole;
    private int overall;

    public FBBoot toEntity() {
        return new FBBoot(id, modelName, comfort, width, length, touch, shooting, outsole, overall);
    }

    public void logInfo() {
        log.info("FBBootForm - id: {}, modelName: {}, comfort: {}, width: {}, length: {}, touch: {}, shooting: {}, outsole: {}, overall: {}",
                id, modelName, comfort, width, length, touch, shooting, outsole, overall);
    }
}
