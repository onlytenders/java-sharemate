package com.practice.sharemate.item.service;

import com.practice.sharemate.item.dto.ItemCreateDto;
import com.practice.sharemate.item.dto.ItemDto;
import com.practice.sharemate.item.dto.ItemUpdateDto;
import com.practice.sharemate.item.mapper.ItemMapper;
import com.practice.sharemate.item.model.Item;
import com.practice.sharemate.item.repository.ItemRepository;
import com.practice.sharemate.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemDto createItem(ItemCreateDto createDto, Long ownerId) {

        if (ownerId == null) { throw new NullPointerException("Invalid ownerId"); }
        if (userRepository.findById(ownerId) == null) { throw new ValidationException("User with this id does not exist"); }
        Item item = itemRepository.save(itemMapper.toEntity(createDto, ownerId));
        return itemMapper.toDto(item);

    }

    @Override
    public ItemDto updateItem(Long itemId, ItemUpdateDto updateDto, Long ownerId) {

        Item item = itemRepository.findById(itemId);
        if (ownerId == null) { throw new NullPointerException("OwnerId must be specified"); }
        if (userRepository.findById(ownerId) == null) { throw new ValidationException("User does not exist"); }
        if (item.getOwnerId() != null && !item.getOwnerId().equals(ownerId)) { throw new IllegalAccessError("User has no permission to access this item"); }

        Item updatedItem = itemRepository.save(itemMapper.toEntity(itemId, updateDto, ownerId));
        return itemMapper.toDto(updatedItem);

    }

    @Override
    public List<ItemDto> getAllItemsByUserId(Long ownerId) {
        if (ownerId == null) { throw new NullPointerException("OwnerId must be specified"); }

        List<ItemDto> items = new ArrayList<>();
        for (Item i : itemRepository.getAllItemsByUserId(ownerId)) {
            items.add(itemMapper.toDto(i));
        }

        return items;
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        List<ItemDto> items = new ArrayList<>();

        for (Item i : itemRepository.searchItems(text) ) {
            if (i.getAvailable()) {
                items.add(itemMapper.toDto(i));
            }
        }

        return items;
    }

    @Override
    public ItemDto findById(Long itemId) {
        if (itemId == null) { throw new NullPointerException("ItemId must be specified"); }

        return itemMapper.toDto(itemRepository.findById(itemId));
    }

    public Long getBookingCount(Long itemId) {
        return 0L;
    }
}
