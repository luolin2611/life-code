package cn.lifecode.music.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Music implements Serializable {
	private String music_id; //musicId
	private String author; //作者
	private String file_duration;//歌曲时长
	private String title;//歌曲title
	private SysUser sysUser;//后台用户
	private String name;//歌曲名称
	private Date update_time;//更新的时间
	private String music_img_path;//歌曲图片
	private String music_file_path;//歌曲文件路径
	private String status;//歌曲状态
	private Singer singer;//演唱者信息
	private String singer_id;
	private MusicDict musicDict;//歌曲类型
}
