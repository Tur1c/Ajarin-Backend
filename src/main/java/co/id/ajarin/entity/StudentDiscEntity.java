package co.id.ajarin.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.id.ajarin.entity.composite.StudentDiscKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "student_disc")
public class StudentDiscEntity {
    
    @JsonBackReference
    @EmbeddedId
    private StudentDiscKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;

    @ManyToOne
    @MapsId("disc_id")
    @JoinColumn(name = "disc_id")
    private DiscussionEntity disc;

    @Column(name = "status")
    private String status;
    
}
