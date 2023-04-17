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

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("id"));
    }

<<<<<<< HEAD
    public List<Photo> getAllVisiblePhotos() {
        return photoRepository.findByVisibleTrue();
    }

    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }
    public List<Photo> getAllFilteredVisiblePhotos(String keyword){
        return photoRepository.findByTitleContainingIgnoreCaseAndVisibleTrue(keyword);
    }
=======
    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }
>>>>>>> origin/master

    public Photo getById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }

    public Photo createPhoto(Photo formPhoto) {
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setUrl(formPhoto.getUrl());
        photoToPersist.setCategories(formPhoto.getCategories());
        photoToPersist.setVisible(formPhoto.isVisible());
        return photoRepository.save(photoToPersist);
    }

    public Photo updatePhoto(Photo formPhoto, Integer id) throws PhotoNotFoundException {
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getUrl());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setVisible(formPhoto.isVisible());
        return photoRepository.save(photoToUpdate);
    }

    public boolean deleteById(Integer id) throws PhotoNotFoundException {
        photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(Integer.toString(id)));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
<<<<<<< HEAD






}

=======
}
>>>>>>> origin/master
