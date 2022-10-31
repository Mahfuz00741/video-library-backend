package com.example.videolibrarybackend.model.services.implementation;

import com.example.videolibrarybackend.model.domain.React;
import com.example.videolibrarybackend.model.domain.Video;
import com.example.videolibrarybackend.model.repositories.ReactRepository;
import com.example.videolibrarybackend.model.repositories.VideoRepository;
import com.example.videolibrarybackend.model.services.VideoService;
import com.example.videolibrarybackend.web.dto.request.ReactRequestDto;
import com.example.videolibrarybackend.web.dto.request.VideoRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ReactRepository reactRepository;

    @Override
    public Video saveVideo(VideoRequestDto dto) {
        Video video = new Video();
        BeanUtils.copyProperties(dto, video);
        videoRepository.save(video);
        return video;
    }

    @Override
    public Video updateVideo(VideoRequestDto dto) {
        Optional<Video> video = videoRepository.findById(dto.getId());
        Video copyVideo = video.get();
        BeanUtils.copyProperties(dto, copyVideo, "id");
        videoRepository.save(copyVideo);
        return copyVideo;
    }

    @Override
    public Video findOneVideo(Long videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.get();
    }

    @Override
    public List<Video> getVideoList() {
        return videoRepository.findAll();
    }

    @Override
    public void reactVideoById(Long videoId, Long userId, ReactRequestDto dto) {
        Optional<Video> v = videoRepository.findById(videoId);
        Video video = v.get();
        Optional<React> find = video.getReact().stream().filter(f -> f.getUserId().equals(userId)).findAny();
        if (find.isPresent()) {
            React react = reactRepository.findByUserId(userId);
            dto.setId(react.getId());
            BeanUtils.copyProperties(dto, react);
            reactRepository.save(react);
        } else {
            React react = new React();
            BeanUtils.copyProperties(dto, react);
            video.getReact().add(react);
            videoRepository.save(video);
        }
    }

    @Override
    public void videoViewIncrease(Long videoId) {
        Optional<Video> v = videoRepository.findById(videoId);
        Video video = v.get();
        video.setTotalView(video.getTotalView() + 1);
        videoRepository.save(video);
    }


}
