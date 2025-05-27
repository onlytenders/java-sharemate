package com.practice.sharemate.item.service;

import com.practice.sharemate.item.dto.ItemCreateDto;
import com.practice.sharemate.item.dto.ItemDto;
import com.practice.sharemate.item.dto.ItemUpdateDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ItemService {

    ItemDto createItem(@Valid ItemCreateDto createDto, Long ownerId);

    ItemDto updateItem(Long itemId, @Valid ItemUpdateDto updateDto, Long ownerId);

    List<ItemDto> getAllItemsByUserId(Long ownerId);

    List<ItemDto> searchItems(String text);

    ItemDto findById(Long itemId);
}
