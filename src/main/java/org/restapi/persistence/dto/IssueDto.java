package org.restapi.persistence.dto;

import org.springframework.web.multipart.MultipartFile;

public class IssueDto {

    private Long id;

    private String title;

    private String category;

    private String description;


    private MultipartFile photos;

    //private float longitude;

    //private float latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public MultipartFile getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(MultipartFile photos) {
//        this.photos = photos;
//    }

//    public float getLongitude() {
//        return longitude;
//    }
//
////    public void setLongitude(float longitude) {
////        this.longitude = longitude;
////    }
////
////    public float getLatitude() {
////        return latitude;
////    }
//
//    public void setLatitude(float latitude) {
//        this.latitude = latitude;
//    }
}
