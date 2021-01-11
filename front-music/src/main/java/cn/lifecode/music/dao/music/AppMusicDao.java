package cn.lifecode.music.dao.music;


import cn.lifecode.music.po.Music;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface AppMusicDao {
	/** 添加音乐 start 2019年04月16日07:58:45 */
	List<Music> queryAllMusic();
	/** 添加音乐end */
}
