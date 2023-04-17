package com.learning.fotoalbum.service;

import com.learning.fotoalbum.exceptions.PhotoNotFoundException;
import com.learning.fotoalbum.model.Photo;
import com.learning.fotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    //INDEX
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("id"));
    }

    public List<Photo> getAllVisiblePhotos() {
        return photoRepository.findByVisibleTrue();
    }

    //RESEARCH
        // for back-end
    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }
        // for front-end
    public List<Photo> getAllFilteredVisiblePhotos(String keyword){
            return photoRepository.findByTitleContainingIgnoreCaseAndVisibleTrue(keyword);
    }

    //SHOW
    public Photo getById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }

    //CREATE
    public Photo createPhoto(Photo formPhoto) {
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setUrl(formPhoto.getUrl());
        photoToPersist.setCategories(formPhoto.getCategories());
        photoToPersist.setVisible(formPhoto.isVisible());
        return photoRepository.save(photoToPersist);
    }

    //EDIT
    public Photo updatePhoto(Photo formPhoto, Integer id) throws PhotoNotFoundException {
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getUrl());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setVisible(formPhoto.isVisible());
        return photoRepository.save(photoToUpdate);
    }

    //DELETE
    public boolean deleteById(Integer id) throws PhotoNotFoundException {
        photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(Integer.toString(id)));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }






}
