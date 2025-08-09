package electro.CRUD.service;

import electro.CRUD.dao.ItemRepository;
import electro.CRUD.entity.Item;
import electro.CRUD.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemDto create(ItemDto dto) {
        Item item = Item.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        Item saved = itemRepository.save(item);
        return toDto(saved);
    }

    public List<ItemDto> readAll() {
        return itemRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ItemDto read(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(this::toDto).orElse(null);
    }

    public ItemDto update(Long id, ItemDto dto) {
        return itemRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    return toDto(itemRepository.save(existing));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .build();
    }
}

