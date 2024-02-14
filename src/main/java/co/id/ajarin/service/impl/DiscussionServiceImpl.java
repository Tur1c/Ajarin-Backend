package co.id.ajarin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.mapper.DiscussionMapper;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;
import co.id.ajarin.repository.DiscussionRepository;
import co.id.ajarin.service.DiscussionService;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Override
    public List<Discussion> getAllDiscussion() {
        List<DiscussionEntity> discussions = discussionRepository.findAll();

        return discussions.stream().map( (discussion) -> DiscussionMapper.maptoDiscussionModel(discussion)).collect(Collectors.toList());
        
    }
    
}
