package org.example.telrostask.controller;

import org.example.telrostask.model.User;
import org.example.telrostask.model.UserPhoto;
import org.example.telrostask.repos.UserPhotoRepository;
import org.example.telrostask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users/{id}/photo")
public class PhotoController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserPhotoRepository photoRepo;

    @PostMapping
    public void upload(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        User user = userRepo.findById(id).orElseThrow();
        UserPhoto photo = UserPhoto.builder()
                .user(user)
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .content(file.getBytes())
                .build();
        photoRepo.save(photo);
    }

    @GetMapping
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        UserPhoto photo = photoRepo.findByUserId(id).orElseThrow();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .body(photo.getContent());
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        UserPhoto photo = photoRepo.findByUserId(id).orElseThrow();
        photoRepo.delete(photo);
    }
}
