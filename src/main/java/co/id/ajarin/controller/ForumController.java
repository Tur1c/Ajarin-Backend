package co.id.ajarin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.service.ForumService;

@RestController
@RequestMapping("/api/forum")
public class ForumController {
    
    @Autowired
    private ForumService forumService;

    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<ForumModel.Response>> getForumList() {
        List<ForumModel.Forum> forums = forumService.getAllForum();

        ForumModel.Response response = new ForumModel.Response(forums);

        ResponseWrapperModel<ForumModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }
}
