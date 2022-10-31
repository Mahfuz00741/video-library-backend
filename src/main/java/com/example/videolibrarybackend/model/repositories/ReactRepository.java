package com.example.videolibrarybackend.model.repositories;

import com.example.videolibrarybackend.model.domain.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactRepository extends JpaRepository<React, Long> {

    React findByUserId(Long userId);
}
