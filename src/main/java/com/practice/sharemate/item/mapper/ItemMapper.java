package com.practice.sharemate.item.mapper;

import com.practice.sharemate.item.dto.ItemCreateDto;
import com.practice.sharemate.item.dto.ItemDto;
import com.practice.sharemate.item.dto.ItemUpdateDto;
import com.practice.sharemate.item.model.Item;
import com.practice.sharemate.item.repository.ItemRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final ItemRepository itemRepository;

    public ItemMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto toDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setAvailable(item.getAvailable());
        dto.setBookingCount(itemRepository.getBookingCount(item.getId()));

        return dto;
    }

    public Item toEntity(ItemCreateDto dto, Long ownerId) {

        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setAvailable(dto.getAvailable());
        item.setOwnerId(ownerId);

        return item;

    }

    public Item toEntity(Long itemId, ItemUpdateDto dto, Long ownerId) {

        Item item = new Item();
        Item existing = itemRepository.findById(itemId);

        item.setId(itemId);
        item.setName(dto.getName()==null||dto.getName().isBlank() ? existing.getName() : dto.getName());
        item.setDescription(dto.getDescription()==null||dto.getDescription().isBlank() ? existing.getDescription() : dto.getDescription());
        item.setAvailable(dto.getAvailable()==null ? existing.getAvailable() : dto.getAvailable());
        item.setOwnerId(ownerId);

        return item;

    }

}
