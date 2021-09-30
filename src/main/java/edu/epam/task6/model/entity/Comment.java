package edu.epam.task6.model.entity;

import java.time.LocalDateTime;

public class Comment {
    private Long commentId;
    private String text;
    private LocalDateTime registrationDate;
    private Long userId;
    private String userLogin;

    public Comment(Long commentId, String text, LocalDateTime registrationDate, Long userId, String userLogin) {
        this.commentId = commentId;
        this.text = text;
        this.registrationDate = registrationDate;
        this.userId = userId;
        this.userLogin = userLogin;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Comment comment = (Comment) obj;
        return text.equals(comment.text)
                && registrationDate.equals(comment.registrationDate)
                && userId.equals(comment.userId)
                && userLogin.equals(comment.userLogin);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + (text != null ? text.hashCode() : 0);
        result = result * prime + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = result * prime + (userId != null ? userId.hashCode() : 0);
        result = result * prime + (userLogin != null ? userLogin.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Comment{ ")
                .append(" commentId = '").append(commentId).append('\'')
                .append(", text = '").append(text).append('\'')
                .append(", registrationDate = '").append(registrationDate).append('\'')
                .append(", userId = '").append(userId).append('\'')
                .append(", userLogin = '").append(userLogin).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
