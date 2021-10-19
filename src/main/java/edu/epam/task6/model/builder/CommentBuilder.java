package edu.epam.task6.model.builder;

import edu.epam.task6.model.entity.Comment;

import java.time.LocalDateTime;

public class CommentBuilder {
    private Long commentId;
    private String text;
    private LocalDateTime registrationDate;
    private Long userId;
    private String userLogin;

    public Comment build() {
        Comment comment = new Comment(commentId, text, registrationDate, userId, userLogin);
        this.commentId = null;
        this.text = null;
        this.registrationDate = null;
        this.userId = null;
        this.userLogin = null;
        return comment;
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

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
