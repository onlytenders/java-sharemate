package com.practice.sharemate.item.controller;

import com.practice.sharemate.item.dto.ItemCreateDto;
import com.practice.sharemate.item.dto.ItemDto;
import com.practice.sharemate.item.dto.ItemUpdateDto;
import com.practice.sharemate.item.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemCreateDto createDto, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        ItemDto item = itemService.createItem(createDto, ownerId);
        return ResponseEntity.ok(item);

    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long itemId, @Valid @RequestBody ItemUpdateDto updateDto, @RequestHeader("X-Sharer-User-Id") Long ownerId ) {
        ItemDto item = itemService.updateItem(itemId, updateDto, ownerId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long itemId) {
        ItemDto item = itemService.findById(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItemsByUserId(@RequestHeader("X-Sharer-User-Id") Long ownerId) {
        List<ItemDto> items = itemService.getAllItemsByUserId(ownerId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItems(@RequestParam String text) {
        List<ItemDto> items = itemService.searchItems(text);
        return ResponseEntity.ok(items);
    }

}
