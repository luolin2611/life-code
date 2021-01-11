package cn.lifecode.backmusic.service.singer;

import cn.lifecode.backmusic.po.Singer;
import cn.lifecode.backmusic.po.SingerImg;

/**
 * @author luolin
 * time 2020-03-05 20:52:08
 */
public interface SingerService {
	/** insert singer photo start */
	public void insertSingerCoverImgPath(String singerId, String singerCoverPath);
	public String insertSingerPhotoImgPath(SingerImg si);
	/** insert singer photo end */
	public String insertSinger(Singer singer);
}
