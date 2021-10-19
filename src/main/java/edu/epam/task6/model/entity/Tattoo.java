package edu.epam.task6.model.entity;

import java.math.BigDecimal;

public class Tattoo {

    private Long tattooId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer width;
    private Integer height;
    private Double averageRating;
    private Integer numberOfRatings;
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
                  Double averageRating,
                  Integer numberOfRatings,
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
        this.averageRating = averageRating;
        this.numberOfRatings = numberOfRatings;
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

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public String getImageUrl() {
        return imageUrl;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tattoo tattoo = (Tattoo) obj;
        return name.equals(tattoo.name)
                && description.equals(tattoo.description)
                && price.equals(tattoo.price)
                && width.equals(tattoo.width)
                && height.equals(tattoo.height)
                && averageRating.equals(tattoo.averageRating)
                && numberOfRatings.equals(tattoo.numberOfRatings)
                && imageUrl.equals(tattoo.imageUrl)
                && status == tattoo.status
                && places == tattoo.places
                && userId.equals(tattoo.userId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (name != null ? name.hashCode() : 0);
        result = result * prime + (description != null ? description.hashCode() : 0);
        result = result * prime + (price != null ? price.hashCode() : 0);
        result = result * prime + (width != null ? width.hashCode() : 0);
        result = result * prime + (height != null ? height.hashCode() : 0);
        result = result * prime + (averageRating != null ? averageRating.hashCode() : 0);
        result = result * prime + (numberOfRatings != null ? numberOfRatings.hashCode() : 0);
        result = result * prime + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = result * prime + (status != null ? status.hashCode() : 0);
        result = result * prime + (places != null ? places.hashCode() : 0);
        result = result * prime + (userId != null ? userId.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Catalog{ ")
                .append("tattooId = '").append(tattooId).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(", description = '").append(description).append('\'')
                .append(", price = '").append(price).append('\'')
                .append(", width = '").append(width).append('\'')
                .append(", height = '").append(height).append('\'')
                .append(", averageRating = '").append(averageRating).append('\'')
                .append(", numberOfRatings = '").append(numberOfRatings).append('\'')
                .append(", imageUrl = '").append(imageUrl).append('\'')
                .append(", status = '").append(status).append('\'')
                .append(", places = '").append(places).append('\'')
                .append(", userId = '").append(userId).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
