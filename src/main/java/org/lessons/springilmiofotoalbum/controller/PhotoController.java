package org.lessons.springilmiofotoalbum.controller;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.lessons.springilmiofotoalbum.exeption.PhotoNotFoundException;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.service.CategoryService;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/photos")
public class PhotoController {
        @Autowired
        private PhotoService photoService;

        @Autowired
        private CategoryService categoryService;

        @GetMapping
        public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
            List<Photo> photos;
            if (keyword.isEmpty()) {
                photos = photoService.getAllPhotos();
            } else {
                photos = photoService.getFilteredPhotos(keyword.get());
                model.addAttribute("keyword", keyword.get());
            }
            model.addAttribute("list", photos);

            return "/photos/index";
        }





        @GetMapping("/{photoId}")
        public String show(@PathVariable("photoId") Integer id, Model model) {
            try {
                Photo photo = photoService.getById(id);
                model.addAttribute("photo", photo);
                return "/photos/show";
            } catch (PhotoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "photo with id " + id + " not found");
            }

        }


    }

