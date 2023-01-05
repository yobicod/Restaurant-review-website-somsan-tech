package com.example.commentservice.query.rest;

import com.example.commentservice.core.CommentEntity;
import com.example.commentservice.query.FindCommentByIdQuery;
import com.example.commentservice.query.FindCommentByReviewIdQuery;
import com.example.commentservice.query.FindCommentByUserIdQuery;
import com.example.commentservice.query.FindCommentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/comment")
@Service
public class CommentQueryController {
    @Autowired
    QueryGateway queryGateway;

//    @GetMapping
    @RabbitListener(queues = "GetAllComment")
    public List<CommentRestModel> getComments(String message){
        FindCommentsQuery findCommentsQuery = new FindCommentsQuery(false);
        List<CommentRestModel> comments = queryGateway
                .query(findCommentsQuery, ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }

    @RabbitListener(queues = "GetCommentByReviewId")
    public List<CommentRestModel> getCommentByReviewId(String reviewId){
        List<CommentRestModel> comments = queryGateway
                .query(new FindCommentByReviewIdQuery(reviewId), ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }

    @RabbitListener(queues = "GetCommentById")
    public CommentEntity getCommentById(String id){
        CommentEntity comment = queryGateway.query(new FindCommentByIdQuery(id), CommentEntity.class).join();
        if(comment == null){
            return new CommentEntity();
        }
        return comment;
    }

    @RabbitListener(queues = "GetCommentByUserId")
    public List<CommentRestModel> getCommentByUserId(String userId){
        List<CommentRestModel> comments = queryGateway
                .query(new FindCommentByUserIdQuery(userId), ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }
}
