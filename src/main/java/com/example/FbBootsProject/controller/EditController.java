package com.example.FbBootsProject.controller;

import com.example.FbBootsProject.entity.Review;
import com.example.FbBootsProject.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class EditController {

    private final ReviewRepository reviewRepository;

    // 리뷰 수정 폼
    @GetMapping("/reviews/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) return "redirect:/";
        model.addAttribute("review", review);
        return "reviews/edit_form";
    }

    // 리뷰 수정 처리
    @PostMapping("/reviews/{id}/edit")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review updated) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            review.setComfort(updated.getComfort());
            review.setWidth(updated.getWidth());
            review.setLength(updated.getLength());
            review.setTouch(updated.getTouch());
            review.setShooting(updated.getShooting());
            review.setOutsole(updated.getOutsole());
            review.setOverall(updated.getOverall());
            reviewRepository.save(review);
            return "redirect:/boots/" + review.getFbBoot().getId();
        }
        return "redirect:/";
    }

    // 리뷰 삭제
    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            Long bootId = review.getFbBoot().getId();
            reviewRepository.deleteById(id);
            return "redirect:/boots/" + bootId;
        }
        return "redirect:/";
    }
}
