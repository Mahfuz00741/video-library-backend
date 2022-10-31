package com.example.videolibrarybackend.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "VIDEO_TABLE")
public class Video extends BaseEntity{

    private String videoTitle;

    private String videoUrl;

    private Long totalView;

    @OneToMany(cascade = CascadeType.ALL)
    private List<React> react;

}
