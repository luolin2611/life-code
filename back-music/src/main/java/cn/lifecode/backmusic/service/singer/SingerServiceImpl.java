package cn.lifecode.backmusic.service.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.dao.singer.SingerDao;
import cn.lifecode.backmusic.po.Singer;
import cn.lifecode.backmusic.po.SingerImg;

@Service
public class SingerServiceImpl implements SingerService {
	@Autowired
	private SingerDao singerDao;

	@Override
	public void insertSingerCoverImgPath(String singerId, String singerCoverPath) {
		String _singerId = singerDao.selectSingerIdByMusicId(singerId);
		if(_singerId != null && _singerId.equals(_singerId)) { //已经插入过了，更新即可
			singerDao.updateCoverImgFilePath(singerId, singerCoverPath);
		} else { //插入
			singerDao.insertCoverImgFilePath(singerId, singerCoverPath);
		}
	}

	@Override
	public String insertSingerPhotoImgPath(SingerImg si) {
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "插入的失败，原因：歌曲相册最多能上传3张！");
		int count = singerDao.selectImgsCountBySingerId(si.getSinger_id());
		if(count < 4) {
			singerDao.insertSingerImgsFilePath(si);
			object.put("status", "success");
			object.put("message", "插入成功！");
		}
		return object.toString();
	}

	@Override
	public String insertSinger(Singer singer) {
		String res = singerDao.selectSingerBySingerName(singer.getSinger_name());
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "该歌手已经上传过了！");
		if(res == null) {
			object.put("status", "success");
			object.put("message", "添加歌手成功！");
			singerDao.updateSinger(singer);
		}
		return object.toString();
	}
}
