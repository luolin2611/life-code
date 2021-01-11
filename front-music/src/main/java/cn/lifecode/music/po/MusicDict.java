package cn.lifecode.music.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolin
 */
@Data
public class MusicDict implements Serializable {
    private String musicDictId;
    private String dictName;
    private Date updateTime;
    private String addUserName;
    private String dictImgPath;
    private String status;
    /**
     * 该类别的音乐数量
     */
    private String musicNum;
}
