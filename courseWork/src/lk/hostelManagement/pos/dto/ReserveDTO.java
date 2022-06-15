package lk.hostelManagement.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    private String res_id;
    private String student_id;
    private String room_id;
    private Date date;
    private double key_money;

}
