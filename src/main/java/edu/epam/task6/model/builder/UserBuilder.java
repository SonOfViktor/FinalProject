package edu.epam.task6.model.builder;

import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.entity.UserStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserBuilder {

    private Long userId;
    private String email;
    private String login;
    private String name;
    private String surname;
    private Integer discount;
    private BigDecimal balance;
    private LocalDateTime registrationDate;
    private UserStatus status;
    private UserRole role;

    public User build() {
        User user = new User(userId, email, login, name, surname,
                discount, balance, registrationDate, status, role);
        this.userId = null;
        this.email = null;
        this.login = null;
        this.name = null;
        this.surname = null;
        this.discount = null;
        this.balance = null;
        this.registrationDate = null;
        this.status = null;
        this.role = null;
        return user;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
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
}
