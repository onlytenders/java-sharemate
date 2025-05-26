package com.practice.sharemate.item.mapper;

import com.practice.sharemate.item.dto.ItemCreateDto;
import com.practice.sharemate.item.dto.ItemDto;
import com.practice.sharemate.item.model.Item;
import com.practice.sharemate.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item, Long bookingCount) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setAvailable(item.getAvailable());
        dto.setBookingCount(bookingCount);

        return dto;
    }

    public Item toEntity(ItemCreateDto dto, User owner) {

        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setAvailable(dto.getAvailable());
        item.setOwner(owner);

        return item;

    }

}
