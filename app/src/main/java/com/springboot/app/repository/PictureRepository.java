package com.springboot.app.repository;

import com.springboot.app.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Set;

public interface PictureRepository extends JpaRepository<ImageModel, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ImageModel WHERE propertyId = :id AND type = :type")
    void deleteAllWithIdAndType(@Param("id") Integer propId, @Param("type") String type);

    @Query("SELECT img.picBase64 FROM ImageModel img WHERE img.propertyId = :id AND img.type = :type")
    Set<String> getAllWithIdAndType(@Param("id") Integer propId, @Param("type") String type);
}
