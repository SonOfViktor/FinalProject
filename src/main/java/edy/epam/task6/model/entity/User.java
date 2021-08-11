package edy.epam.task6.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

    private Long userId;
    private String email;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer discount;
    private BigDecimal balance;
    private Timestamp registrationDate;
    private UserStatus status;
    private UserRole role;

    public User(Long userId,
                String email,
                String login,
                String password,
                String name,
                String surname,
                Integer discount,
                BigDecimal balance,
                Timestamp registrationDate,
                UserStatus status,
                UserRole role) {
        this.userId = userId;
        this.email = email;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.balance = balance;
        this.registrationDate = registrationDate;
        this.status = status;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User{ ")
                .append(" user_id = '").append(userId).append('\'')
                .append(", email = '").append(email).append('\'')
                .append(", login = '").append(login).append('\'')
                .append(", password = '").append(password).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(", soname = '").append(surname).append('\'')
                .append(", discount = '").append(discount).append('\'')
                .append(", balance = '").append(balance).append('\'')
                .append(", registration_date = '").append(registrationDate).append('\'')
                .append(", status = '").append(status).append('\'')
                .append(", role = '").append(role).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
