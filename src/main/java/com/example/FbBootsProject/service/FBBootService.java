package com.example.FbBootsProject.service;

import com.example.FbBootsProject.entity.FBBoot;
import com.example.FbBootsProject.entity.Review;
import com.example.FbBootsProject.repository.FBBootRepository;
import com.example.FbBootsProject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FBBootService {
    @Autowired
    private FBBootRepository fbBootRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 평균값으로 FBBoot 업데이트 (리뷰 없으면 0)
    public void updateBootStatsByReviewAverage(Long bootId) {
        FBBoot boot = fbBootRepository.findById(bootId).orElse(null);
        if (boot == null) return;
        List<Review> reviews = reviewRepository.findByFbBoot_Id(bootId);

        int avgComfort = 0, avgWidth = 0, avgLength = 0, avgTouch = 0, avgShooting = 0, avgOutsole = 0, avgOverall = 0;
        if (!reviews.isEmpty()) {
            avgComfort = (int)Math.round(reviews.stream().mapToInt(Review::getComfort).average().orElse(0));
            avgWidth = (int)Math.round(reviews.stream().mapToInt(Review::getWidth).average().orElse(0));
            avgLength = (int)Math.round(reviews.stream().mapToInt(Review::getLength).average().orElse(0));
            avgTouch = (int)Math.round(reviews.stream().mapToInt(Review::getTouch).average().orElse(0));
            avgShooting = (int)Math.round(reviews.stream().mapToInt(Review::getShooting).average().orElse(0));
            avgOutsole = (int)Math.round(reviews.stream().mapToInt(Review::getOutsole).average().orElse(0));
            avgOverall = (int)Math.round(reviews.stream().mapToInt(Review::getOverall).average().orElse(0));
        }
        // 리뷰가 없으면 0, 있으면 평균값
        boot.setComfort(avgComfort);
        boot.setWidth(avgWidth);
        boot.setLength(avgLength);
        boot.setTouch(avgTouch);
        boot.setShooting(avgShooting);
        boot.setOutsole(avgOutsole);
        boot.setOverall(avgOverall);

        fbBootRepository.save(boot);
    }
}

