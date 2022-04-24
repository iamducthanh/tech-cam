package com.techcam.repo;

import com.techcam.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILogRepository extends JpaRepository<LogEntity, String> {
    List<LogEntity> findAllByOrderByCreateDateDesc();
}
