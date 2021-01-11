package cn.lifecode.music.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SingerImg implements Serializable {
    private String img_id;
    private String img_path;
    private String singer_id;
    private Date update_time;//更新的时间
    private String status;//写真状态
}
