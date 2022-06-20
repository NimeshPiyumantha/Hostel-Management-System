package lk.hostelManagement.pos.entity;

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
public class Custom {

    private String room_id;
    private String type;
    private String key_money;
    private int qty;

    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String dob;
    private String gender;

    private String res_id;
    private LocalDate date;
    private String room_type_id;
    private String status;

    private double arrest_money;

    public Custom( String res_id,String student_id, String room_type_id,LocalDate date,String key_money, String status, double arrest_money) {
        this.key_money = key_money;
        this.student_id = student_id;
        this.res_id = res_id;
        this.date = date;
        this.room_type_id = room_type_id;
        this.status = status;
        this.arrest_money = arrest_money;
    }
}
