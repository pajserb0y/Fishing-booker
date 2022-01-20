package com.springboot.app.model;
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

    @Column(name = "name")
    private Integer propertyId;

    @Column(name = "type")
    private String type;//house/fishing/boat

    @Column(name = "picBase64")
    private String picBase64;


}