package co.id.ajarin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @SuppressWarnings("rawtypes")
    @PostMapping("add")
    public ResponseEntity<ResponseWrapperModel> addDiscussion(
        @RequestParam("title") String title, @RequestParam("category") String category, 
        @RequestParam("education_level") String level,
        @RequestParam("description") String description, 
        @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate, 
        @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
        @RequestParam("max_participant") String maxParticipant, @RequestParam("price") String price, @RequestParam("file") MultipartFile file, 
        @RequestParam("link") String urlLink, @RequestParam("teacher_id") Long userId
    ) throws IOException{
        System.out.println("masuk sini aaaaaa");
        System.out.println(userId);
        String message = discService.addDiscussion(title, category, level, description, startDate, endDate, maxParticipant, price, urlLink, file, userId);

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    // @GetMapping("/files/{name}")
    // public ResponseEntity<byte[]> getImageDiscussion(@PathVariable(name = "name") String name) throws UnsupportedEncodingException {
    //     DiscussionEntity discussion = discService.getImage(name);

    //     return ResponseEntity.ok()
    //         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + discussion.getDisc_image() + "\"")
    //         .body(discussion.getDisc_image_data());
    // }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("cancelDisc")
    public ResponseEntity<ResponseWrapperModel<DiscussionModel.Response>> cancelDiscussion(@RequestParam(name = "account") Long acc, @RequestParam(name = "disc") Long disc){
        String message = discService.cancelDiscussion(acc, disc);

        // DiscussionModel.Response response = new DiscussionModel.Response(discussions);

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

}
