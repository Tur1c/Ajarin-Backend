package co.id.ajarin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.mapper.ForumMapper;
import co.id.ajarin.model.forum.ForumModel.Forum;
import co.id.ajarin.repository.ForumRepository;
import co.id.ajarin.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumRepository forumRepository;

    @Override
    public List<Forum> getAllForum() {
        List<ForumEntity> forums = forumRepository.findAll();
        // TODO Auto-generated method stub
        return forums.stream().map((forum) -> 
                ForumMapper.maptoForumModel(forum))
                .collect(Collectors.toList());
    }
    
}
