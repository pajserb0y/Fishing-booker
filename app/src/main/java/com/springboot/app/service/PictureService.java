package com.springboot.app.service;


import com.springboot.app.model.ImageModel;

import java.util.Set;

public interface PictureService {

    void saveImagesForWeekendHouse(Set<String> imagePath, Integer houseId);

    void saveImagesForBoat(Set<String> imagePath, Integer houseId);

    void saveImagesForFishingLesson(Set<String> imagePath, Integer houseId);

    Set<String> getAllImagesForProperty(Integer propertyId, String type);

    void deleteAll(Integer id, String type);
}
