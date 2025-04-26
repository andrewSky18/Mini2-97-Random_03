package com.example.miniapp.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ratings")
public class Rating {

    @Id
    private String id;

    private Long entityId;

    private String entityType;

    @Min(1)
    @Max(5)
    private Integer score;

    private String comment;

    private LocalDateTime ratingDate;


    public Rating() {
    }

    public Rating(Long entityId, String entityType, Integer score, LocalDateTime ratingDate) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.ratingDate = ratingDate;
    }

    public Rating(String id, Long entityId, String entityType, Integer score, String comment, LocalDateTime ratingDate) {
        this.id = id;
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = ratingDate;
    }
    public Rating(long entityId, String entityType, int score, String comment, LocalDateTime ratingDate) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = ratingDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }


    public String getEntityType() {
        return entityType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (id != null ? !id.equals(rating.id) : rating.id != null) return false;
        if (entityId != null ? !entityId.equals(rating.entityId) : rating.entityId != null) return false;
        if (entityType != null ? !entityType.equals(rating.entityType) : rating.entityType != null) return false;
        if (score != null ? !score.equals(rating.score) : rating.score != null) return false;
        if (comment != null ? !comment.equals(rating.comment) : rating.comment != null) return false;
        return ratingDate != null ? ratingDate.equals(rating.ratingDate) : rating.ratingDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (entityType != null ? entityType.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (ratingDate != null ? ratingDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", entityId=" + entityId +
                ", entityType='" + entityType + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", ratingDate=" + ratingDate +
                '}';
    }
}