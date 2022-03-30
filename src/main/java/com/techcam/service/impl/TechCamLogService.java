package com.techcam.service.impl;

import com.techcam.dto.request.techcamlog.TechCamlogRequest;
import com.techcam.dto.response.techcamlog.TechCamlogResponse;
import com.techcam.entity.TechcamBlogEntity;
import com.techcam.repo.ITechCamBlogRepo;
import com.techcam.service.ITechCamLogService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/28/2022
 * Project_name: Tech-cam
 */
@EnableAsync
@Service
public class TechCamLogService implements ITechCamLogService {
    @Autowired
    private ITechCamBlogRepo techCamBlogRepo;
    private  final ModelMapper MODEL_MAPPER=new ModelMapper();
    @Async
    @Override
    public void saveLog(TechCamlogRequest request){
        TechcamBlogEntity techcamBlogEntity =new TechcamBlogEntity();
        techcamBlogEntity.setId(UUID.randomUUID().toString());
        techcamBlogEntity.setCreateDate(new Date());
        techcamBlogEntity.setOperationKey(request.getOperationKey());
        techcamBlogEntity.setOperationAct(request.getOperationAct());
        techcamBlogEntity.setOperationDesc(request.getOperationDesc());
        techcamBlogEntity.setOperationAct(request.getOperationAct());
        techcamBlogEntity.setCreateBy(request.getCreateBy());
        techCamBlogRepo.save(techcamBlogEntity);
    }
    @Override
    public List<TechCamlogResponse> getALlTechCamLog(){
        Type type = new TypeToken<List<TechCamlogResponse>>(){}.getType();
        List<TechcamBlogEntity> techcamBlogEntities = techCamBlogRepo.findAllByOrderByCreateDateDesc();
        return CollectionUtils.isEmpty(techcamBlogEntities)? null : MODEL_MAPPER.map(techcamBlogEntities,type);
    }
}
