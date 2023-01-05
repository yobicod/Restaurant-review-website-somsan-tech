package com.example.commentservice.query;

import com.example.commentservice.core.CommentEntity;
import com.example.commentservice.core.data.CommentRepository;
import com.example.commentservice.query.rest.CommentRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentQueryHandler {
    private final CommentRepository commentRepository;

    public CommentQueryHandler(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @QueryHandler
    List<CommentRestModel> findComments(FindCommentsQuery query){
        List<CommentRestModel> commentsRest = new ArrayList<>();
        List<CommentEntity> storedComments = commentRepository.findByNonBanUser(query.isBan());
        for (CommentEntity commentEntity : storedComments){
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            commentsRest.add(commentRestModel);
        }
        return commentsRest;
    }

    @QueryHandler
    List<CommentRestModel> handle(FindCommentByReviewIdQuery query){
        List<CommentRestModel> commentRest =  new ArrayList<>();
        List<CommentEntity> storedComments = commentRepository
                .findByReviewId(query.getReviewId());
        for (CommentEntity commentEntity : storedComments){
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            commentRest.add(commentRestModel);
        }
        return commentRest;
    }

    @QueryHandler
    CommentEntity handle(FindCommentByIdQuery query){
        CommentEntity comment = commentRepository
                .findByCommentId(query.get_id());
        return comment;
    }

    @QueryHandler
    List<CommentRestModel> handle(FindCommentByUserIdQuery query){
        List<CommentRestModel> commentRest =  new ArrayList<>();
        List<CommentEntity> storedComments = commentRepository
                .findByUserId(query.getUserId());
        for (CommentEntity commentEntity : storedComments){
            CommentRestModel commentRestModel = new CommentRestModel();
            BeanUtils.copyProperties(commentEntity, commentRestModel);
            commentRest.add(commentRestModel);
        }
        return commentRest;
    }
}
