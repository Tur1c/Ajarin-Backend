package co.id.ajarin.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditReplyModel {
    private String reply;
    private Long fr_id;
    private Long question_id;
}
