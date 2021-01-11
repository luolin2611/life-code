package cn.lifecode.music.controller;

import cn.lifecode.music.po.Music;
import cn.lifecode.music.po.MusicDict;
import cn.lifecode.music.service.music.MusicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-06 22:35:37
 */
@RestController
@RequestMapping("/music")
public class WebMusicController {
    private final MusicService musicService;

    public WebMusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/queryAllMusic")
    public List<Music> queryAllMusic() {
        return musicService.queryAllMusic();
    }

    @PostMapping("/findMusicDictList")
    public List<MusicDict> findMusicDictList() {
        return musicService.findMusicDictList();
    }
}
