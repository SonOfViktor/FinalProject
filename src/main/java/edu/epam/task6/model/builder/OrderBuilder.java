package edu.epam.task6.model.builder;

import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderBuilder {

    private Long orderId;
    private BigDecimal paid;
    private LocalDateTime registrationDate;
    private String userLogin;
    private OrderStatus orderStatus;

    private Long tattooId;
    private BigDecimal tattooPrice;
    private String tattooName;

    public Order build() {
        Order order = new Order(orderId, paid, registrationDate, userLogin, orderStatus, tattooId, tattooPrice, tattooName);
        this.orderId = null;
        this.paid = null;
        this.registrationDate = null;
        this.userLogin = null;
        this.orderStatus = null;
        this.tattooId = null;
        this.tattooPrice = null;
        this.tattooName = null;
        return order;
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
}
