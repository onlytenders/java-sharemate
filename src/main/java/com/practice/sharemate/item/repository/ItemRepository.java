package com.practice.sharemate.item.repository;

import com.practice.sharemate.item.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemRepository {

    Long idCounter = 1L;
    Map<Long, Item> items = new HashMap<>();

    public Item save(Item item) {
        if (item.getId() == null) {
            item.setId(idCounter++);
        }

        items.put(item.getId(), item);
        return item;
    }

    public Item findById(Long itemId) {
        return items.get(itemId);
    }

    public List<Item> getAllItemsByUserId(Long ownerId) {
        List<Item> found = new ArrayList<>();

        for (Item i : items.values()) {
            if (i.getOwnerId().equals(ownerId)) {
                found.add(i);
            }
        }

        return found;
    }

    public List<Item> searchItems(String text) {
        List<Item> found = new ArrayList<>();

        log.info(items + " " + text);

        for (Item i : items.values()) {
            if (i.getName().toLowerCase().contains(text.toLowerCase()) || i.getDescription().toLowerCase().contains(text.toLowerCase())) {
                found.add(i);
            }
        }

        return found;
    }

    public Long getBookingCount(Long id) {
        return 0L;
    }
}
