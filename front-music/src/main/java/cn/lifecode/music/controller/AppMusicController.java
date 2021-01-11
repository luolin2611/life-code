package cn.lifecode.music.controller;

import cn.lifecode.music.po.MusicDict;
import cn.lifecode.music.service.musicdict.MusicDictService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-06 22:35:37
 */
@RestController
@RequestMapping("/musicDict")
public class AppMusicController {
    private final MusicDictService musicDictService;

    public AppMusicController(MusicDictService musicDictService) {
        this.musicDictService = musicDictService;
    }

    @PostMapping("/getAllMusicDict")
    public List<MusicDict> getAllMusicDict() {
        return musicDictService.getAllerMusicDict();
    }

    @PostMapping("/queryAllMusicDict")
    public List<MusicDict> queryAllMusicDict() {
        return musicDictService.queryAllMusicDict();
    }
}
