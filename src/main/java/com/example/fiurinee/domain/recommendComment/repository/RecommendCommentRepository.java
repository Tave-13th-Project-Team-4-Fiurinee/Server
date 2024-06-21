package com.example.fiurinee.domain.recommendComment.repository;

import com.example.fiurinee.domain.recommendComment.entity.RecommendComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendCommentRepository extends JpaRepository<RecommendComment,Long> {
}
