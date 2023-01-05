package com.example.commentservice.query;

import com.example.commentservice.core.CommentEntity;
import com.example.commentservice.core.data.CommentRepository;
import com.example.commentservice.core.event.CommentCreatedEvent;
import com.example.commentservice.core.event.CommentUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;

@Component
public class CommentEventsHandler {
    private final CommentRepository commentRepository;

    public CommentEventsHandler(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @EventHandler
    public void on(CommentCreatedEvent event){
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(event, commentEntity);
        commentRepository.save(commentEntity);
    }

    @EventHandler
    public void on(CommentUpdatedEvent event){
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(event, commentEntity);
        commentRepository.save(commentEntity);
    }
}
