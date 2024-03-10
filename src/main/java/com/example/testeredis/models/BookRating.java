package com.example.testeredis.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash
public class BookRating {
    @Id
    private String id;

    @NotNull
    @Reference
    private User user;

    @NotNull
    @Reference
    private Book book;

    @NotNull
    private Integer rating;
}
