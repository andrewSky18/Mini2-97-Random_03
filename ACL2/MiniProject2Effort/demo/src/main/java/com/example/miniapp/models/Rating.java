package com.example.miniapp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Rating {

    @Id
    private String id;

    @NonNull
    private Long entityId;

    @NonNull
    private String entityType;

    @NonNull
    private Integer score;

    private String comment;

    @NonNull
    private LocalDateTime ratingDate;
}
