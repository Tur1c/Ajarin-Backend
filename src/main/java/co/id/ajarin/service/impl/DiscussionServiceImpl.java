package co.id.ajarin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Discussion> getAllDiscussion() {
        List<DiscussionEntity> discussions = discussionRepository.findAll();

        System.out.println("yey");
        // Query query= em.crea("SELECT COUNT(*) FROM DISCUSSION");
        System.out.println("lewat query");
        // int testest = ((Number) query.getSingleResult()).intValue();
        System.out.println("lewat test");
        // System.out.println(testest + "ehehe");
        System.out.println("siu lewat");
        Long test =  discussionRepository.count();
        System.out.println(test + "wuttt");

        
        // DiscussionEntity discussion = discussionRepository.getById(1);

        return discussions.stream().map( (discussion) -> DiscussionMapper.maptoDiscussionModel(discussion)).collect(Collectors.toList());
        // return null;
    }
    
}
