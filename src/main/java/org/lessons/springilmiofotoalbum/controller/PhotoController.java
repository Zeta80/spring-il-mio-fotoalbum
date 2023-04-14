package org.lessons.springilmiofotoalbum.controller;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.lessons.springilmiofotoalbum.exeption.PhotoNotFoundException;
import org.lessons.springilmiofotoalbum.model.CrudMessage;
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


    //CREATE
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("photo", new Photo());
        //associo al model di photo un attributo Listacategoria con tutte le categorie prese dal metodo getAll nel service
        model.addAttribute("categoryList", categoryService.getAll());
        return "/photos/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult br, RedirectAttributes redirectAttributes, Model model){
        if(br.hasErrors()){
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/create";
        }
        redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "New Photo successfully created"));
        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try{
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/edit";

        }catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/photos/edit";

        }
        try{
            Photo updatedPhoto = photoService.updatePhoto(formPhoto, id);
            return "redirect:/photos/" + Integer.toString(updatedPhoto.getId());

        }catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        try{
            boolean success = photoService.deleteById(id);
            if (success)
                return "redirect:/photos";
            else
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}

