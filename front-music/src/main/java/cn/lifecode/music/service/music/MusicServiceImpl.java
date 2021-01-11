package cn.lifecode.music.service.music;

import cn.lifecode.music.dao.music.AppMusicDao;
import cn.lifecode.music.dao.music.WebMusicDao;
import cn.lifecode.music.po.Music;
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
public class MusicServiceImpl implements MusicService {
    private final WebMusicDao musicDao;
    private final AppMusicDao appMusicDao;

    public MusicServiceImpl(WebMusicDao musicDao, AppMusicDao appMusicDao) {
        this.musicDao = musicDao;
        this.appMusicDao = appMusicDao;
    }

    @Override
    public List<Music> queryAllMusic() {
        return appMusicDao.queryAllMusic();
    }

    @Override
    public List<MusicDict> findMusicDictList() {
        return musicDao.findMusicDictList();
    }


}
