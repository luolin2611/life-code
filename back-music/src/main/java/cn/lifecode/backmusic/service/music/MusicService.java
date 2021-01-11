package cn.lifecode.backmusic.service.music;

import java.util.List;
import cn.lifecode.backmusic.po.Music;
import cn.lifecode.backmusic.po.MusicDict;
import cn.lifecode.backmusic.po.Singer;

public interface MusicService {
	public void insertDictImgDictId(String music_dict_id, String dict_img_path);
	public String createDict(String music_dict_id, String add_user_name, String dict_name);
	public List<MusicDict> findMusicDictList();
	public List<Music> findAllMusic();
	/** 添加音乐 start 2019年04月16日08:01:33 */
	public void insertMusicFilePath(String musicId, String musicFilePath, String fileDuration);
	public void insertMusicTitleImgPath(String musicId, String musicTitleImgPath);
	public String insertMusic(Music music);
	/** 添加音乐end */
	List<Singer> findAllSinger();
}
