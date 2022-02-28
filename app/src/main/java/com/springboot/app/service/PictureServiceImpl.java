package com.springboot.app.service;

import com.springboot.app.model.ImageModel;
import com.springboot.app.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void saveImagesForWeekendHouse(Set<String> imagePath, Integer houseId) {
        for (String image : imagePath) {
            String name = "weekend-house" + houseId.toString();
            ImageModel imageModel = new ImageModel(0, name, houseId, "house", image);
            pictureRepository.save(imageModel);
        }
    }

    @Override
    public void saveImagesForBoat(Set<String> imagePath, Integer boatId) {
        for (String image : imagePath) {
            String name = "boat" + boatId.toString();
            ImageModel imageModel = new ImageModel(0, name, boatId, "boat", image);
            pictureRepository.save(imageModel);
        }
    }

    @Override
    public void saveImagesForFishingLesson(Set<String> imagePath, Integer lessonId) {
        for (String image : imagePath) {
            String name = "fishing-lesson" + lessonId.toString();
            ImageModel imageModel = new ImageModel(0, name, lessonId, "lesson", image);
            pictureRepository.save(imageModel);
        }
    }

    @Override
    public Set<String> getAllImagesForProperty(Integer propertyId, String type) {
        return pictureRepository.getAllWithIdAndType(propertyId, type);
    }


    @Override
    public void deleteAll(Integer id, String type) {
        pictureRepository.deleteAllWithIdAndType(id, type);
    }


}
