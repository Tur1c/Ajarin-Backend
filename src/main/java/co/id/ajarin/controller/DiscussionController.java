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
import co.id.ajarin.model.dashboard.DiscussionModel;
import co.id.ajarin.service.DiscussionService;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {
    
    @Autowired
    private DiscussionService discService;

    //Get All Discussion
    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<DiscussionModel.Response>> getAllDiscussion(){
        List <DiscussionModel.Discussion> discussions = discService.getAllDiscussion();

        DiscussionModel.Response response = new DiscussionModel.Response(discussions);

        ResponseWrapperModel<DiscussionModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

}
