package com.example.testeredis.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItem {
    private String isbn;
    private Double price;
    private Long quantity;
}
