package com.learning.fotoalbum.service;

import com.learning.fotoalbum.exceptions.CategoryNotFoundException;
import com.learning.fotoalbum.model.Category;
import com.learning.fotoalbum.model.CrudMessage;
import com.learning.fotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //GETTER BY ID
    public Category getById(Integer id) throws CategoryNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new CategoryNotFoundException(Integer.toString(id));
        }
    }

    //INDEX
    public List<Category> getAll(){
        return categoryRepository.findAll(Sort.by("name"));
    }

    //CREATE
    public Category create(Category formCategory) {
        Category categoryToPersist = new Category();
        categoryToPersist.setName(formCategory.getName());
        categoryToPersist.setDescription(formCategory.getDescription());
        return categoryRepository.save(categoryToPersist);
    }

    //EDIT
    public Category update(Category formCategory) {
        Category categoryToUpdate = new Category();
        categoryToUpdate.setName(formCategory.getName());
        categoryToUpdate.setDescription(formCategory.getDescription());
        return categoryRepository.save(formCategory);
    }

    //DELETE
    public boolean deleteById(Integer id) throws CategoryNotFoundException {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(Integer.toString(id)));
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
