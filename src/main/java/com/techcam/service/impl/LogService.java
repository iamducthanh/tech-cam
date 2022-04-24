package com.techcam.service.impl;

import com.techcam.entity.LogEntity;
import com.techcam.repo.ILogRepository;
import com.techcam.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService implements ILogService {
    private final ILogRepository logRepository;

    @Override
    public List<LogEntity> findAllLog̣() {
        return logRepository.findAllByOrderByCreateDateDesc();
    }
}
