package cn.lifecode.backmusic.service.music;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.dao.music.MusicDao;
import cn.lifecode.backmusic.po.Music;
import cn.lifecode.backmusic.po.MusicDict;
import cn.lifecode.backmusic.po.Singer;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicDao musicDao;

	@Override
	public void insertDictImgDictId(String music_dict_id, String dict_img_path) {
		musicDao.insertDictImgDictId(music_dict_id, dict_img_path);
	}

	@Override
	public String createDict(String music_dict_id, String add_user_name, String dict_name) {
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "添加失败，请先上传图片！");
		String str = musicDao.findMusiDictIdByMusiDict(music_dict_id, add_user_name);
		if(str != null && music_dict_id.equals(str)) {
			str = musicDao.findMusiDictIdByDictName(dict_name);
			if(str == null) {
				MusicDict musicDict = new MusicDict();
				musicDict.setMusicDictId(music_dict_id);
				musicDict.setDictName(dict_name);
				musicDict.setUpdateTime(new Date());
				musicDict.setAddUserName(add_user_name);
				musicDao.updateMusicDict(musicDict);
				object.put("status", "success");
				object.put("message", "上传种类成功！");
			} else {
				object.put("status", "fail");
				object.put("message", "添加失败，该类别名称已经添加过了！");
			}
		}
		return object.toString();
	}

	@Override
	public List<MusicDict> findMusicDictList() {
		List<MusicDict> musicDictList = musicDao.findMusicDictList();
		return musicDictList;
	}

	@Override
	public List<Music> findAllMusic() {
		return musicDao.findAllMusic();
	}
	
	@Override
	public void insertMusicFilePath(String music_id, String music_file_path, String file_duration) {
		String musicId = musicDao.selectMusicIdByMusicId(music_id);
		if(musicId != null && musicId.equals(music_id)) { //已经插入过了，更新即可
			musicDao.updateMusicFilePath(music_id, music_file_path, file_duration);
		} else { //插入
			musicDao.insertMusicFilePath(music_id, music_file_path, file_duration);
		}
	}

	@Override
	public void insertMusicTitleImgPath(String music_id, String music_img_path) {
		String musicId = musicDao.selectMusicIdByMusicId(music_id);
		if(musicId != null && musicId.equals(music_id)) { //已经插入过了，更新即可
			musicDao.updateTitleImgFilePath(music_id, music_img_path);
		} else { //插入
			musicDao.insertTitleImgFilePath(music_id, music_img_path);
		}
	}
	
	@Override
	public String insertMusic(Music music) {
		String res = musicDao.selectMusicByTitleOrName(music.getName(), music.getTitle());
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "歌曲标题或歌曲名称重复！");
		if(res == null) {
			object.put("status", "success");
			object.put("message", "添加歌曲成功！");
			musicDao.updateMusic(music);
		}
		return object.toString();
	}

	@Override
	public List<Singer> findAllSinger() {
		return musicDao.findAllSinger();
	}
}
