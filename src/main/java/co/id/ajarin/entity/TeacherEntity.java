package co.id.ajarin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class TeacherEntity {

    public TeacherEntity(Long teacher_id, String profile_description, String education, String experience,
            String achievement, byte[] data, List<AccountRegisterEntity> subscribed_student, AccountRegisterEntity user) {
        this.teacher_id = teacher_id;
        this.profile_description = profile_description;
        this.education = education;
        this.experience = experience;
        this.achievement = achievement;
        this.data = data;
        this.subscribed_student = subscribed_student;
        this.user = new AccountRegisterEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole(), user.getGender(), user.getCity(), user.getCountry(), user.getSchool(), user.getAge(), user.getPhoneNumber(), user.getEducation(), null, null, user.getCoin());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;

    @Column(columnDefinition = "TEXT", name = "profile_description")
    private String profile_description;

    @Column(name = "education")
    private String education;

    @Column(name = "experience")
    private String experience;

    @Column(name = "achievement")
    private String achievement;


    @Column(name = "points")
    private Integer points;

    @Column(name = "cv_data")
    @JsonIgnore
    @Lob
    private byte[] data;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "subscribed_lecturer",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AccountRegisterEntity> subscribed_student;
    

    @JsonManagedReference(value = "teacher_disc")
    @OneToMany(mappedBy = "teacher")
    private List<DiscussionEntity> discussions;

    @JsonManagedReference(value = "teacher_course")
    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> course;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<PrivateDiscEntity> privateDisc;
    
}
