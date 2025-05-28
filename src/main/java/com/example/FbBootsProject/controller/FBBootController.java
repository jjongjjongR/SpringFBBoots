package com.example.FbBootsProject.controller;

import com.example.FbBootsProject.dto.BootViewDto;
import com.example.FbBootsProject.entity.FBBoot;
import com.example.FbBootsProject.entity.Review;
import com.example.FbBootsProject.repository.FBBootRepository;
import com.example.FbBootsProject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.*; // List, ArrayList, Comparator, Collectors 등 사용을 위해 추가

@Controller
public class FBBootController {

    @Autowired
    private FBBootRepository fbBootRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // 메인 티어표 화면
    @GetMapping("/")
    public String index(Model model) {
        List<FBBoot> boots = fbBootRepository.findAll();

        List<BootViewDto> dtos = boots.stream().map(boot -> {
                    List<Review> reviews = boot.getReviews();
                    Function<ToIntFunction<Review>, Double> avg = f -> reviews.stream().mapToInt(f).average().orElse(0.0);

                    return new BootViewDto(
                            boot.getId(),
                            boot.getModelName(),
                            avg.apply(Review::getComfort),
                            avg.apply(Review::getWidth),
                            avg.apply(Review::getLength),
                            avg.apply(Review::getTouch),
                            avg.apply(Review::getShooting),
                            avg.apply(Review::getOutsole),
                            String.format("%.1f", avg.apply(Review::getOverall))
                    );
                }).sorted(Comparator.comparing((BootViewDto dto) -> Double.parseDouble(dto.getOverall())).reversed())
                .toList();

        model.addAttribute("boots", dtos);
        model.addAttribute("top3", dtos.stream().limit(3).toList());
        return "index";
    }




    // 검색 기능
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<FBBoot> boots = fbBootRepository.findByModelNameContainingIgnoreCase(keyword);

        List<BootViewDto> dtos = boots.stream().map(boot -> {
                    List<Review> reviews = boot.getReviews();
                    Function<ToIntFunction<Review>, Double> avg = f -> reviews.stream().mapToInt(f).average().orElse(0.0);

                    return new BootViewDto(
                            boot.getId(),
                            boot.getModelName(),
                            avg.apply(Review::getComfort),
                            avg.apply(Review::getWidth),
                            avg.apply(Review::getLength),
                            avg.apply(Review::getTouch),
                            avg.apply(Review::getShooting),
                            avg.apply(Review::getOutsole),
                            String.format("%.1f", avg.apply(Review::getOverall))
                    );
                }).sorted(Comparator.comparing((BootViewDto dto) -> Double.parseDouble(dto.getOverall())).reversed())
                .toList();

        model.addAttribute("boots", dtos);
        model.addAttribute("top3", dtos.stream().limit(3).toList());

        if (dtos.isEmpty()) {
            model.addAttribute("errorMsg", "검색 결과가 없습니다.");
        }

        return "index";
    }



    // 축구화 상세 화면
    @GetMapping("/boots/{id}")
    public String detail(@PathVariable Long id, Model model) {
        FBBoot boot = fbBootRepository.findById(id).orElse(null);
        List<Review> reviews = reviewRepository.findByFbBoot_Id(id);

        model.addAttribute("boot", boot);
        model.addAttribute("reviews", reviews);

        // 평균 구하는 함수
        Function<ToIntFunction<Review>, String> avg = f -> {
            double a = reviews.stream().mapToInt(f).average().orElse(0.0);
            return String.format("%.1f", a); // 소수점 1자리
        };

        // 각 항목 평균 계산 후 모델에 담기
        model.addAttribute("avgComfort", avg.apply(Review::getComfort));
        model.addAttribute("avgWidth", avg.apply(Review::getWidth));
        model.addAttribute("avgLength", avg.apply(Review::getLength));
        model.addAttribute("avgTouch", avg.apply(Review::getTouch));
        model.addAttribute("avgShooting", avg.apply(Review::getShooting));
        model.addAttribute("avgOutsole", avg.apply(Review::getOutsole));
        model.addAttribute("avgOverall", avg.apply(Review::getOverall));

        return "fbboots/detail";
    }
}
