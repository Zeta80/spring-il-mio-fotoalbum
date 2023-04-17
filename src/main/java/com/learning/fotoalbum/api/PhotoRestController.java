package com.learning.fotoalbum.api;

import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam(name = "keyword") Optional<String> search) {
        if (search.isPresent()) {
            return photoService.getAllFilteredVisiblePhotos(search.get());
        }
        return photoService.getAllVisiblePhotos();
    }
}
