package co.id.ajarin.mapper;

import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.model.dashboard.DiscussionModel;

public class DiscussionMapper {
    public static DiscussionModel.Discussion maptoDiscussionModel (DiscussionEntity discussion){
        return new DiscussionModel.Discussion(
            discussion.getDiscid(),
            discussion.getDisctitle(),
            discussion.getDiscparticipant(),
            discussion.getDisctime(),
            discussion.getDiscdate(),
            discussion.getDiscdescription(),
            discussion.getDisccategory()
        );
    }

    public static DiscussionEntity maptoDiscussionEntity (DiscussionModel.Discussion discussionModel){
        return new DiscussionEntity(
            discussionModel.getDiscId(),
            discussionModel.getDiscTitle(),
            discussionModel.getDiscParticipant(),
            discussionModel.getDiscTime(),
            discussionModel.getDiscDate(),
            discussionModel.getDiscDescription(),
            discussionModel.getDiscCategory()
        );
    }
} 
