package co.id.ajarin.service;

import java.util.List;

import co.id.ajarin.model.dashboard.DiscussionModel;

public interface DiscussionService {
    List<DiscussionModel.Discussion> getAllDiscussion();
}
