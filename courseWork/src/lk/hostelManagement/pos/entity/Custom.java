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
    private double monthly_rent;
    private String qty;

    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private String dob;
    private String gender;

    private String res_id;
    private LocalDate date;
    private double key_money;

    private double arrest_money;

    public Custom(String res_id, String student_id,String room_id, double monthly_rent, LocalDate date, double key_money, double arrest_money) {
        this.room_id = room_id;
        this.monthly_rent = monthly_rent;
        this.student_id = student_id;
        this.res_id = res_id;
        this.date = date;
        this.key_money = key_money;
        this.arrest_money = arrest_money;
    }
}
