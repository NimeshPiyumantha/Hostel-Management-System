package lk.hostelManagement.pos.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Nimesh Piyumantha
 * @since : 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoginTM {
    @Id
    private String userID;
    @Column
    private String name;
    private String address;
    private String contact_no;
    private String Password;
    private String gender;

}
