package com.practice.sharemate.item.dto;

import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Long ownerId;
    private Boolean available;
    private Long bookingCount;

}
