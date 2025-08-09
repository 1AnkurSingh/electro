package electro.CRUD.controller;

import electro.CRUD.model.ItemDto;
import electro.CRUD.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/items")
@CrossOrigin("*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.create(itemDto));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> readAll() {
        return ResponseEntity.ok(itemService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> read(@PathVariable Long id) {
        ItemDto dto = itemService.read(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        ItemDto updated = itemService.update(id, itemDto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

