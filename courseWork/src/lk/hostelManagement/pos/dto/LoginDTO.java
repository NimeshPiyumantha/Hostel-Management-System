package lk.hostelManagement.pos.dto;

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
public class LoginDTO {
    private String userID;
    private String name;
    private String address;
    private String contact_no;
    private String Password;
    private String gender;

}
