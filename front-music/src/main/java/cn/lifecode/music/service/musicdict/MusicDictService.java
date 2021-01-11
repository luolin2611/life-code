package cn.lifecode.music.service.musicdict;

import cn.lifecode.music.po.MusicDict;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-06 22:35:37
 */
public interface MusicDictService {
    // 查询所有 音乐专辑
    List<MusicDict> getAllerMusicDict();
    List<MusicDict> queryAllMusicDict();
}
