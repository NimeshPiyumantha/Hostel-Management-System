package lk.hostelManagement.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "customer")
public class Customer {
    @Id
    private String id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(columnDefinition = "TEXT")
    private String address;
}
