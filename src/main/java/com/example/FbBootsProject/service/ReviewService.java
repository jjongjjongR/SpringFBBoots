package com.example.FbBootsProject.service;

import com.example.FbBootsProject.dto.ReviewForm;
import com.example.FbBootsProject.entity.FBBoot;
import com.example.FbBootsProject.entity.Review;
import com.example.FbBootsProject.repository.FBBootRepository;
import com.example.FbBootsProject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private FBBootRepository fbBootRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 저장 + 평균값 자동 반영
    public void saveReview(Long bootId, ReviewForm reviewForm) {
        FBBoot boot = fbBootRepository.findById(bootId).orElse(null);
        if (boot == null) return;

        Review review = reviewForm.toEntity();
        review.setFbBoot(boot);
        reviewRepository.save(review);

        // 리뷰 저장 후 평균값 자동 반영
        updateBootStatsByReviewAverage(bootId);
    }

    // 평균값 계산 및 반영
    public void updateBootStatsByReviewAverage(Long bootId) {
        FBBoot boot = fbBootRepository.findById(bootId).orElse(null);
        if (boot == null) return;
        List<Review> reviews = reviewRepository.findByFbBoot_Id(bootId);
        if (reviews.isEmpty()) return;

        double avgComfort = reviews.stream().mapToInt(Review::getComfort).average().orElse(0);
        double avgWidth = reviews.stream().mapToInt(Review::getWidth).average().orElse(0);
        double avgLength = reviews.stream().mapToInt(Review::getLength).average().orElse(0);
        double avgTouch = reviews.stream().mapToInt(Review::getTouch).average().orElse(0);
        double avgShooting = reviews.stream().mapToInt(Review::getShooting).average().orElse(0);
        double avgOutsole = reviews.stream().mapToInt(Review::getOutsole).average().orElse(0);
        double avgOverall = reviews.stream().mapToInt(Review::getOverall).average().orElse(0);

        boot.setComfort((int)Math.round(avgComfort));
        boot.setWidth((int)Math.round(avgWidth));
        boot.setLength((int)Math.round(avgLength));
        boot.setTouch((int)Math.round(avgTouch));
        boot.setShooting((int)Math.round(avgShooting));
        boot.setOutsole((int)Math.round(avgOutsole));
        boot.setOverall((int)Math.round(avgOverall));

        fbBootRepository.save(boot);
    }
}
