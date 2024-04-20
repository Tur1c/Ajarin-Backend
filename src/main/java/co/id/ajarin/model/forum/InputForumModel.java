package co.id.ajarin.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputForumModel {
    private String question_title;
    private String question;
    private String question_category;
    private String question_level;
    private String user_email;
}

