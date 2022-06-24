package lk.hostelManagement.pos.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTM  {
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String key_money;
    private Double advance;
    private String status;

}
