package edu.epam.task6.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long orderId;
    private BigDecimal paid;
    private LocalDateTime registrationDate;
    private String userLogin;
    private OrderStatus orderStatus;

    private Long tattooId;
    private BigDecimal tattooPrice;
    private String tattooName;

    public Order(Long orderId,
                 BigDecimal paid,
                 LocalDateTime registrationDate,
                 String userLogin,
                 OrderStatus orderStatus) {
        this.orderId = orderId;
        this.paid = paid;
        this.registrationDate = registrationDate;
        this.userLogin = userLogin;
        this.orderStatus = orderStatus;
        this.tattooId = null;
        this.tattooPrice = null;
        this.tattooName = null;
    }

    public Order(Long orderId,
                 BigDecimal paid,
                 LocalDateTime registrationDate,
                 String userLogin,
                 OrderStatus orderStatus,
                 Long tattooId,
                 BigDecimal tattooPrice,
                 String tattooName) {
        this.orderId = orderId;
        this.paid = paid;
        this.registrationDate = registrationDate;
        this.userLogin = userLogin;
        this.orderStatus = orderStatus;
        this.tattooId = tattooId;
        this.tattooPrice = tattooPrice;
        this.tattooName = tattooName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getTattooId() {
        return tattooId;
    }

    public void setTattooId(Long tattooId) {
        this.tattooId = tattooId;
    }

    public BigDecimal getTattooPrice() {
        return tattooPrice;
    }

    public void setTattooPrice(BigDecimal tattooPrice) {
        this.tattooPrice = tattooPrice;
    }

    public String getTattooName() {
        return tattooName;
    }

    public void setTattooName(String tattooName) {
        this.tattooName = tattooName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return paid.equals(order.paid)
                && registrationDate.equals(order.registrationDate)
                && Objects.equals(userLogin, order.userLogin)
                && orderStatus == order.orderStatus
                && tattooId.equals(order.tattooId)
                && tattooPrice.equals(order.tattooPrice)
                && tattooName.equals(order.tattooName);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (paid != null ? paid.hashCode() : 0);
        result = result * prime + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = result * prime + (userLogin != null ? userLogin.hashCode() : 0);
        result = result * prime + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = result * prime + (tattooId != null ? tattooId.hashCode() : 0);
        result = result * prime + (tattooPrice != null ? tattooPrice.hashCode() : 0);
        result = result * prime + (tattooName != null ? tattooName.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order{ ")
                .append("order_id = '").append(orderId).append('\'')
                .append(", paid = '").append(paid).append('\'')
                .append(", registration_date = '").append(registrationDate).append('\'')
                .append(", login = '").append(userLogin).append('\'')
                .append(", status = '").append(orderStatus).append('\'')
                .append(", tattoo_id = '").append(tattooId).append('\'')
                .append(", tattoo_price = '").append(tattooPrice).append('\'')
                .append(", tattoo_name = '").append(tattooName).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
