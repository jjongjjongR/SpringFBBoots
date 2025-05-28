package com.example.FbBootsProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BootViewDto {
    private Long id;
    private String modelName;
    private double avgComfort, avgWidth, avgLength, avgTouch, avgShooting, avgOutsole;
    private String overall; // 소수점 1자리 종합 평점
}
