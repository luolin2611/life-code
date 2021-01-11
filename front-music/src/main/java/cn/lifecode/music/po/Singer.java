package cn.lifecode.music.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Singer implements Serializable {
	private String singer_id;
	private String singer_name;
	private String singer_cover_path;
	private String add_sys_user_id;
	private Date update_time;//更新的时间
	private String status;//写真状态
	private List<Singer> singerImgList;
}
