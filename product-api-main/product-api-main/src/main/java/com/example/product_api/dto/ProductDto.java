package com.example.product_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private int store_id;
    private String image_url;
    private int merchant_card_number;
    private Integer merchantcvv ;
    private Long userid;
}
