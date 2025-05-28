package com.example.FbBootsProject.repository;

//import com.example.FbBootsProject.dto.BootStatsDto;
import com.example.FbBootsProject.entity.FBBoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FBBootRepository extends JpaRepository<FBBoot, Long> {

    List<FBBoot> findByModelNameContainingIgnoreCase(String keyword);
}
