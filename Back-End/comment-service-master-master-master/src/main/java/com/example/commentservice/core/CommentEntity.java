package com.example.commentservice.core;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Document("Comment_store")
@Data
public class CommentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 2792236869846008522L;
    @Id
    @Column(unique = true)
    private String _id;
    private String user;
    private String userid;
    private int rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private int like;
    private String reviewId;
    private int report;
    private boolean ban;
}
