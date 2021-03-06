package edu.epam.task6.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {

    private Long userId;
    private String email;
    private String login;
    private String name;
    private String surname;
    private Integer discount;
    private BigDecimal balance;
    private LocalDateTime registrationDate;
    private Double averageRating;
    private Integer numberOfRatings;
    private Integer registerCode;
    private UserStatus status;
    private UserRole role;

    public User(Long userId,
                String email,
                String login,
                String name,
                String surname,
                Integer discount,
                BigDecimal balance,
                LocalDateTime registrationDate,
                Double averageRating,
                Integer numberOfRatings,
                Integer registerCode,
                UserStatus status,
                UserRole role) {
        this.userId = userId;
        this.email = email;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.discount = discount;
        this.balance = balance;
        this.registrationDate = registrationDate;
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
        this.registerCode = registerCode;
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

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public Integer getRegisterCode() {
        return registerCode;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return email.equals(user.email)
                && login.equals(user.login)
                && name.equals(user.name)
                && surname.equals(user.surname)
                && discount.equals(user.discount)
                && balance.equals(user.balance)
                && registrationDate.equals(user.registrationDate)
                && averageRating.equals(user.averageRating)
                && numberOfRatings.equals(user.numberOfRatings)
                && registerCode.equals(user.registerCode)
                && status.equals(user.status)
                && role.equals(user.role);

    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (email != null ? email.hashCode() : 0);
        result = result * prime + (login != null ? login.hashCode() : 0);
        result = result * prime + (name != null ? name.hashCode() : 0);
        result = result * prime + (surname != null ? surname.hashCode() : 0);
        result = result * prime + (discount != null ? discount.hashCode() : 0);
        result = result * prime + (balance != null ? balance.hashCode() : 0);
        result = result * prime + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = result * prime + (averageRating != null ? averageRating.hashCode() : 0);
        result = result * prime + (numberOfRatings != null ? numberOfRatings.hashCode() : 0);
        result = result * prime + (registerCode != null ? registerCode.hashCode() : 0);
        result = result * prime + (status != null ? status.hashCode() : 0);
        result = result * prime + (role != null ? role.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User{ ")
                .append(" user_id = '").append(userId).append('\'')
                .append(", email = '").append(email).append('\'')
                .append(", login = '").append(login).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(", soname = '").append(surname).append('\'')
                .append(", discount = '").append(discount).append('\'')
                .append(", balance = '").append(balance).append('\'')
                .append(", registration_date = '").append(registrationDate).append('\'')
                .append(", average_rating = '").append(averageRating).append('\'')
                .append(", numberOfRatings = '").append(numberOfRatings).append('\'')
                .append(", registerCode = '").append(registerCode).append('\'')
                .append(", status = '").append(status).append('\'')
                .append(", role = '").append(role).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
