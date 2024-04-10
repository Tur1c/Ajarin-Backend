package co.id.ajarin.model.dashboard;

import java.sql.Date;
import java.sql.Time;

import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.TeacherModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateDiscModel extends OutputRepositoryModel{
    public PrivateDiscModel(Long private_id2,String title2, String subject2, String education2, String difficulty2, Date private_date,
            Time date_start, Time date_end, Long offered_coin, AccountModel acc, Object teacher2) {
        
        this.private_id = private_id2;
        this.title = title2;
        this.subject = subject2;
        this.education = education2;
        this.difficulty = difficulty2;
        this.date = private_date.toString();
        this.start_time = date_start.toString();
        this.end_time = date_end.toString();
        this.coin = offered_coin;
        this.teacher = null;
        this.user = acc;
    }
    private Long private_id;
    private String title;
    private String subject;
    private String education;
    private String difficulty;
    private String date;
    private String start_time;
    private String end_time;
    private Long coin;
    private AccountModel user;
    private TeacherModel.Teacher teacher;
}
