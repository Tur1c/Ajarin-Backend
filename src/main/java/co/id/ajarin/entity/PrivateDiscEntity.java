package co.id.ajarin.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "private_disc")
public class PrivateDiscEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long private_id;

    @Column(name = "title")
    private String title;

    @Column(name = "subject")
    private String subject;

    @Column(name = "education")
    private String education;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "private_date")
    private Date private_date;

    @Column(name = "date_start")
    private Time date_start;

    @Column(name = "date_end")
    private Time date_end;

    @Column(name = "status")
    private String status;

    @Column(name = "offered_coin")
    private Long offered_coin;

    @Column(name = "link")
    private String link;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;
}
