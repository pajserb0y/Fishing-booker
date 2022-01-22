package com.springboot.app.model;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "image_table")
public class ImageModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "propertyID")
    private Integer propertyId;

    @Column(name = "type")
    private String type;//house/fishing/boat

    @Lob
    @Type(type = "org.hibernate.type.StringType")
    @Column(name = "picBase64")
    private String picBase64;

    public ImageModel() {

    }

    public ImageModel(Integer id, String name, Integer propertyId, String type, String picBase64) {
        this.id = id;
        this.name = name;
        this.propertyId = propertyId;
        this.type = type;
        this.picBase64 = picBase64;
    }

    
}