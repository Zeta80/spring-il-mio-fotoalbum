package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.CrudMessage;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.model.User;
import com.learning.fotoalbum.repository.UserRepository;
import com.learning.fotoalbum.service.CategoryService;
import com.learning.fotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

     @Autowired
    private PhotoService photoService;

     @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword") Optional<String> keyword, Authentication authentication) {

        User loggedUser = userRepository.findByEmail(authentication.getName()).orElseThrow();
        model.addAttribute("loggedUser", loggedUser);

        List<Photo> photos;
        if (keyword.isEmpty()) {
            photos = photoService.getAllPhotos();
        } else {
            photos = photoService.getFilteredPhotos(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", photos);
        return "photos/index";
    }

    @GetMapping("/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            return "photos/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id '" + id + "' not found");
        }
    }

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
            //per far lasciare i campi selezionati in caso di altri campi con errore e ricaricamento pagina
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/create";
        }
        redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "New Photo successfully created"));
        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            model.addAttribute("categoryList", categoryService.getAll());

            return "/photos/create"; //edit and create use the same template
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id '" + id + "' not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bs, RedirectAttributes redirectAttributes, Model model){
        if(bs.hasErrors()){
            System.out.println(bs);
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/create"; //edit and create use the same template
        }try{
            Photo updatedPhoto = photoService.updatePhoto(formPhoto, id);
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "Photo " + id + " successfully updated"));
            model.addAttribute("modifiedBy", categoryService.getAll());
            return "redirect:/photos/" + Integer.toString(updatedPhoto.getId());
        }catch(PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            boolean success = photoService.deleteById(id);
            if (success){
                redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "Photo '" + id + "' successfully deleted"));
            }else{
                redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.ERROR, "Photo '" + id + "' can NOT be deleted"));
            }
        }catch(PhotoNotFoundException e){
               /* throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                anzichè lanciare eccezione mandiamo un messaggio più userfriendly*/
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.ERROR, "Photo " + id + " can NOT be deleted, because doesn't exist"));
        }
        return "redirect:/photos";
    }
}
