package cn.lifecode.music.service.music;

import cn.lifecode.music.po.Music;
import cn.lifecode.music.po.MusicDict;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-06 22:35:37
 */
public interface MusicService {
    List<Music> queryAllMusic();

    List<MusicDict> findMusicDictList();
}
