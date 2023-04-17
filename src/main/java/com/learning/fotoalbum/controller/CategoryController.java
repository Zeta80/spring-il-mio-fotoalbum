package com.learning.fotoalbum.controller;

import com.learning.fotoalbum.exceptions.CategoryNotFoundException;
import com.learning.fotoalbum.model.Category;
import com.learning.fotoalbum.model.CrudMessage;
import com.learning.fotoalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //INDEX
    @GetMapping
    public String index(Model model){
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("category", new Category());
        return "categories/index";
    }

    //CREATE
    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute(name = "category") Category category,
                         BindingResult bs,
                         Model model, RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            model.addAttribute("allCategories", categoryService.getAll());
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.ERROR, "Something gone wrong"));

            return "categories/index";
        }
        categoryService.create(category);
        redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "Category successfully created"));
        return "redirect:/categories";
    }

    //EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("allCategories", categoryService.getAll());

        return "categories/index";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Category category, BindingResult bs, Model model,
                         @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.ERROR, "Something gone wrong"));
            return "categories/index";
        }
        categoryService.update(category);
        model.addAttribute("categories", categoryService.getAll());
        redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "Category successfully modified"));
        return "redirect:/categories";
    }

    //DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if(categoryService.deleteById(id)){
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.SUCCESS, "Category successfully deleted"));
        }else{
            redirectAttributes.addFlashAttribute("message", new CrudMessage(CrudMessage.CrudMessageType.ERROR, "Cannot delete this category because is currently assigned to a photo"));
        }
        return "redirect:/categories";
    }

}
