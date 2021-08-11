package edy.epam.task6.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
    private Long orderId;
    private BigDecimal paid;
    private Timestamp registrationDate;
    private String userLogin;
    private OrderStatus orderStatus;

    private Long tattooId;
    private BigDecimal tattooPrice;
    private String tattooName;

    public Order(Long orderId,
                 BigDecimal paid,
                 Timestamp registrationDate,
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
                 Timestamp registrationDate,
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

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
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
