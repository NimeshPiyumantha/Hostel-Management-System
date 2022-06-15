package lk.hostelManagement.pos.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTM {
    private String room_id;
    private String type;
    private double monthly_rent;
    private String qty;
}
