package org.restapi.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class Issue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    private Category category;

    private String description;

    @OneToMany(mappedBy="issue")
    private List<Photo> photos;

    private float longitude;

    private float latitude;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }


    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        final int prime = 27;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Issue [name=").append(title).append("]");
        return builder.toString();
    }

}
