package co.id.ajarin.mapper;

import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.model.dashboard.DiscussionModel;

public class DiscussionMapper {
    public static DiscussionModel.Discussion maptoDiscussionModel (DiscussionEntity discussion){
        return new DiscussionModel.Discussion(
            discussion.getDisc_id(),
            discussion.getDisc_title(),
            discussion.getDisc_participant(),
            discussion.getDisc_price(),
            discussion.getDisc_starttime(),
            discussion.getDisc_endtime(),
            discussion.getDisc_date(),
            discussion.getDisc_description(),
            discussion.getDisc_level(),
            discussion.getDisc_image(),
            discussion.getCategory()
        );
    }

    public static DiscussionEntity maptoDiscussionEntity (DiscussionModel.Discussion discussionModel){
        return new DiscussionEntity(
            discussionModel.getDisc_id(),
            discussionModel.getDisc_title(),
            discussionModel.getDisc_participant(),
            discussionModel.getDisc_price(),
            discussionModel.getDisc_starttime(),
            discussionModel.getDisc_endtime(),
            discussionModel.getDisc_date(),
            discussionModel.getDisc_description(),
            discussionModel.getDisc_level(),
            discussionModel.getDisc_image(),
            discussionModel.getCategory()
        );
    }
} 
