package co.id.ajarin.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Long discid;

    @Column(name = "disctitle")
    private String disctitle;
    
    @Column(name = "discparticipant")
    private String discparticipant;

    @Column(name = "disctime")
    private Time disctime;

    @Column(name = "discdate")
    private Date discdate;

    @Column(name = "discdescription")
    private String discdescription;

    @Column(name = "disccategory")
    private String disccategory;
    
}
