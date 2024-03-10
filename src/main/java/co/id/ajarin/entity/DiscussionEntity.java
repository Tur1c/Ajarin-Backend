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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discussion")
public class DiscussionEntity {
//     -DiscID
// -DiscTitle
// -DiscParticipant
// -DiscPrice
// -DiscTime
// -DiscDate
// -DiscDescription
// -DiscCategory (FK)
// -LecturerID (FK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disc_id;

    @Column(name = "disc_title")
    private String disc_title;
    
    @Column(name = "disc_participant")
    private String disc_participant;

    @Column(name = "disc_price")
    private Integer disc_price;

    @Column(name = "disc_starttime")
    private Time disc_starttime;

    @Column(name = "disc_endtime")
    private Time disc_endtime;

    @Column(name = "disc_date")
    private Date disc_date;

    @Column(name = "disc_description")
    private String disc_description;
    
    @Column(name = "disc_image")
    private String disc_image;

    @Column(name = "disc_level")
    private String disc_level;

    @ManyToOne
    @JoinColumn(name="disc_category")
    private CategoryEntity category;

    @ManyToOne
    @JsonBackReference(value = "teacher_disc")
    @JoinColumn(name="teacher_id")
    private TeacherEntity teacher;

}
