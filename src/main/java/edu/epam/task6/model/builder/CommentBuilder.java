package edu.epam.task6.model.builder;

import edu.epam.task6.model.entity.Comment;

import java.time.LocalDateTime;

public class CommentBuilder {
    private Long commentId;
    private String text;
    private LocalDateTime registrationDate;
    private Long userId;

    public Comment build() {
        Comment comment = new Comment(commentId, text, registrationDate, userId);
        this.commentId = null;
        this.text = null;
        this.registrationDate = null;
        this.userId = null;
        return comment;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
