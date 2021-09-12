package edu.epam.task6.model.builder;

import edu.epam.task6.model.entity.BodyPart;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;

import java.math.BigDecimal;

public class TattooBuilder {
    private Long catalogId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer width;
    private Integer height;
    private Double averageRating;
    private String imageUrl;
    private TattooStatus status;
    private BodyPart places;
    private Long userId;

    public Tattoo build() {
        Tattoo tattoo = new Tattoo(catalogId, name, description, price, width, height, averageRating, imageUrl, status, places, userId);
        this.catalogId = null;
        this.name = null;
        this.description = null;
        this.price = null;
        this.width = null;
        this.height = null;
        this.averageRating = null;
        this.imageUrl = null;
        this.status = null;
        this.places = null;
        this.userId = null;
        return tattoo;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
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
}
