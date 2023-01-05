package com.example.reviewservice.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Document("review")
public class ReviewEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1778265464960090939L;
    @Id
    @Column(unique = true)
    private String _id;
    private String name;
    private String branch;
    private String store_type;
    private String description;
    private String imageId;
    private String address;
    private String timeOpen;
    private String timeClose;
    private int rating;
    private int personReview;
    private String phone;
}
