package by.bsuir.publisher.controllers;

import by.bsuir.publisher.dto.requests.StickerRequestDto;
import by.bsuir.publisher.dto.responses.StickerResponseDto;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.services.StickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stickers")
@RequiredArgsConstructor
public class StickerController {
    private final StickerService stickerService;

    @PostMapping
    public ResponseEntity<StickerResponseDto> create(@RequestBody StickerRequestDto sticker) throws EntityExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(stickerService.create(sticker));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StickerResponseDto> read(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(stickerService.read(id).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
    }

    @GetMapping
    public ResponseEntity<List<StickerResponseDto>> read() {
        return ResponseEntity.status(HttpStatus.OK).body(stickerService.readAll());
    }

    @PutMapping
    public ResponseEntity<StickerResponseDto> update(@RequestBody StickerRequestDto sticker) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(stickerService.update(sticker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws NoEntityExistsException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stickerService.delete(id));
    }
}
