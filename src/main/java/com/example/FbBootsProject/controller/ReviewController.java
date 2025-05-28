package com.example.FbBootsProject.controller;

import com.example.FbBootsProject.dto.ReviewForm;
import com.example.FbBootsProject.entity.FBBoot;
import com.example.FbBootsProject.entity.Review;
import com.example.FbBootsProject.repository.FBBootRepository;
import com.example.FbBootsProject.repository.ReviewRepository;
import com.example.FbBootsProject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    private FBBootRepository fbBootRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    // 리뷰 남기기 폼 (GET)
    @GetMapping("/boots/{id}/review")
    public String reviewForm(@PathVariable Long id, Model model) {
        FBBoot boot = fbBootRepository.findById(id).orElse(null);
        if (boot == null) {
            return "redirect:/";
        }
        model.addAttribute("boot", boot);
        model.addAttribute("reviewForm", new ReviewForm());
        return "reviews/review_form";
    }

    // 리뷰 등록 처리 (POST)
    @PostMapping("/boots/{id}/review")
    public String submitReview(@PathVariable Long id, @ModelAttribute ReviewForm reviewForm) {
        reviewService.saveReview(id, reviewForm);
        return "redirect:/boots/" + id;
    }
}
