package com.springboot.app.repository;

import com.springboot.app.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PictureRepository extends JpaRepository<ImageModel, Integer> {

    @Query("DELETE FROM ImageModel img WHERE img.propertyId = :id AND img.type = :type")
    void deleteAllWithIdAndType(@Param("id") Integer propertyId, @Param("type") String type);

    @Query("SELECT img.picBase64 FROM ImageModel img WHERE img.propertyId = :id AND img.type = :type")
    Set<String> getAllWithIdAndType(@Param("id") Integer propertyId, @Param("type") String type);
}
