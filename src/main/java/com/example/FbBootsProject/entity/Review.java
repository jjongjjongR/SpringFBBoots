package com.example.FbBootsProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;
    private int comfort;
    private int width;
    private int length;
    private int touch;
    private int shooting;
    private int outsole;
    private int overall;

    @ManyToOne
    @JoinColumn(name = "boot_id")
    private FBBoot fbBoot;

    public Review(String modelName, int comfort, int width, int length, int touch, int shooting, int outsole, int overall){
        this.modelName = modelName;
        this.comfort = comfort;
        this.width = width;
        this.length = length;
        this.touch = touch;
        this.shooting = shooting;
        this.outsole = outsole;
        this.overall = overall;
    }
}
