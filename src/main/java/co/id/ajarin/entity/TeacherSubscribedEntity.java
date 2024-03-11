// package co.id.ajarin.entity;

// import java.util.List;

// import javax.persistence.Column;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.Lob;
// import javax.persistence.ManyToMany;
// import javax.persistence.OneToOne;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// public class TeacherSubscribedEntity {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long teacher_id;

//     @Column(columnDefinition = "TEXT", name = "profile_description")
//     private String profile_description;

//     @Column(name = "education")
//     private String education;

//     @Column(name = "experience")
//     private String experience;

//     @Column(name = "achievement")
//     private String achievement;

//     @Column(name = "rating")
//     private String rating;

//     @Column(name = "cv_data")
//     @JsonIgnore
//     @Lob
//     private byte[] data;
// }
