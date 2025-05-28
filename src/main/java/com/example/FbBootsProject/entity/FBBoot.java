package com.example.FbBootsProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FBBoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;
    private int comfort;   // 착화감
    private int width;     // 발볼
    private int length;    // 길이감
    private int touch;     // 터치감
    private int shooting;  // 슈팅감
    private int outsole;   // 아웃솔 안정감
    private int overall;   // 종합 별점

    public FBBoot(Long id, String modelName, int comfort, int width, int length, int touch, int shooting, int outsole, int overall){
        this.modelName = modelName;
        this.comfort = comfort;
        this.width = width;
        this.length = length;
        this.touch = touch;
        this.shooting = shooting;
        this.outsole = outsole;
        this.overall = overall;
    }

    @OneToMany(mappedBy = "fbBoot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}
