package cn.lifecode.music.service.musicdict;

import cn.lifecode.music.po.MusicDict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-06 22:35:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MusicDictServiceImpl implements MusicDictService {
//    private final MusicDictMapper musicDictMapper;

//    public MusicDictServiceImpl(MusicDictMapper musicDictMapper) {
//        this.musicDictMapper = musicDictMapper;
//    }

    @Override
    public List<MusicDict> getAllerMusicDict() {
        return null;
//        return musicDictMapper.getAllerMusicDict();
    }

    @Override
    public List<MusicDict> queryAllMusicDict() {
        return null;
//        return musicDictMapper.queryAllMusicDict();
    }
}
