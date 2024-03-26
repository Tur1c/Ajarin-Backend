package co.id.ajarin.model.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputReplyModel {
    private String reply;
    private String email;
    private Long forum_id;
}
