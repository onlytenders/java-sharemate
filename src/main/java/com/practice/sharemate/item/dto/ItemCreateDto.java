package com.practice.sharemate.item.dto;

import lombok.Data;

@Data
public class ItemCreateDto {

    private String name;
    private String description;
    private Boolean available;

}
