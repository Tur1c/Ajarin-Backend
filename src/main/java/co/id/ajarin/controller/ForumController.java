package co.id.ajarin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.InputForumModel;
import co.id.ajarin.service.ForumService;

@RestController
@RequestMapping("/api/forum")
public class ForumController {
    
    private ForumService service;

    public ForumController(ForumService service) {
        this.service = service;
    }

    @PostMapping("add")
    public ForumEntity createInputForum(@RequestBody InputForumModel inputForum) {

        ForumEntity newForum = service.inputForum(inputForum.getQuestion_title(), inputForum.getQuestion(), inputForum.getQuestion_category(), inputForum.getQuestion_level(), inputForum.getUser_email());

        ResponseWrapperModel<ForumModel.Forum> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return null;
    }

    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<ForumModel.Response>> getForumList() {
        List<ForumModel.Forum> forums = service.getAllForum();

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
