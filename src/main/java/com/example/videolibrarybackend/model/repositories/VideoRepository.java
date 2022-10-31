package com.example.videolibrarybackend.model.repositories;

import com.example.videolibrarybackend.model.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
