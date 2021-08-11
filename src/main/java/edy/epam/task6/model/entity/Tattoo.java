package edy.epam.task6.model.entity;

import java.math.BigDecimal;
import java.util.List;

public class Tattoo {

    private Long tattooId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer width;
    private Integer height;
    private String imageUrl;
    private TattooStatus status;
    private BodyPart places;
    private Long userId;

    public Tattoo(Long tattooId,
                  String name,
                  String description,
                  BigDecimal price,
                  Integer width,
                  Integer height,
                  String imageUrl,
                  TattooStatus status,
                  BodyPart places,
                  Long userId) {
        this.tattooId = tattooId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.width = width;
        this.height = height;
        this.imageUrl = imageUrl;
        this.status = status;
        this.places = places;
        this.userId = userId;
    }

    public Long getTattooId() {
        return tattooId;
    }

    public void setTattooId(Long catalogId) {
        this.tattooId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public BodyPart getPlaces() {
        return places;
    }

    public void setPlaces(BodyPart places) {
        this.places = places;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TattooStatus getStatus() {
        return status;
    }

    public void setStatus(TattooStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Catalog{ ")
                .append("catalog_id = '").append(tattooId).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(", description = '").append(description).append('\'')
                .append(", price = '").append(price).append('\'')
                .append(", width = '").append(width).append('\'')
                .append(", height = '").append(height).append('\'')
                .append(", places = '").append(places).append('\'')
                .append(", image_url = '").append(imageUrl).append('\'')
                .append(", tattoo_status = '").append(status).append('\'')
                .append(", user_id = '").append(userId).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
