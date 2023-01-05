package com.example.userreviewservice.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Document("user")
@Data
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1041217002388603763L;
    @Id
    @Column(unique = true)
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
