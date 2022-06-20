package lk.hostelManagement.pos.view.tm;

import lk.hostelManagement.pos.entity.Room;
import lk.hostelManagement.pos.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTM {
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String status;

}
