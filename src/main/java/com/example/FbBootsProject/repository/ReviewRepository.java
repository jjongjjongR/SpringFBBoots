package com.example.FbBootsProject.repository;

import com.example.FbBootsProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFbBoot_Id(Long fbBootId); // 연관관계 필드 경유
}
