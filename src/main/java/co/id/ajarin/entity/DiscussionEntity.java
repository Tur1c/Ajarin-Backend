package co.id.ajarin.entity;

import java.util.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "discussion")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discussion")
public class DiscussionEntity {
public DiscussionEntity(String title, String maxParticipant, String price, Date startDate,
            Date endDate, Date startDate2, String description, String filename, byte[] bytes,
            String level, CategoryEntity category2, TeacherEntity teacher) {

        long start = startDate.getTime();
        long end = endDate.getTime();
        Time startTime = new Time(start);
        Time endTime = new Time(end);

        this.disc_title = title;
        this.disc_participant = maxParticipant;
        this.disc_price = Integer.parseInt(price);
        this.disc_date = startDate2;
        this.disc_endtime = endTime;
        this.disc_starttime = startTime;
        this.disc_description = description;
        this.disc_image = filename;
        this.disc_image_data = bytes;
        this.disc_level = level;
        this.disc_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/discussion/files/").path(filename).toUriString();
        this.category = category2;
        this.teacher = teacher;
    }

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

    @Lob
    @JsonIgnore
    @Column(name = "disc_image_data")
    private byte[] disc_image_data;

    @Column(name = "disc_level")
    private String disc_level;

    @Column(name = "disc_url")
    private String disc_url;

    @ManyToOne
    @JoinColumn(name="disc_category")
    private CategoryEntity category;

    @ManyToOne
    @JsonBackReference(value = "teacher_disc")
    @JoinColumn(name="teacher_id")
    private TeacherEntity teacher;

}
