package cn.lifecode.backmusic.dao.singer;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.lifecode.backmusic.po.Singer;
import cn.lifecode.backmusic.po.SingerImg;

/**
 * @author luolin
 * @date 2021-01-08 19:31:30
 */
@Mapper
public interface SingerDao {
    /**
     * add singer start 2020-03-05 20:45:12
     */
    String selectSingerIdByMusicId(@Param("singer_id") String singerId);

    //插入封面路径
    void insertCoverImgFilePath(@Param("singer_id") String singerId, @Param("singer_cover_path") String singerCoverPath);

    void updateCoverImgFilePath(@Param("singer_id") String singerId, @Param("singer_cover_path") String singerCoverPath);

    //插入写真图片路径
    int selectImgsCountBySingerId(@Param("singer_id") String singerId); //插入图片的数量不能大于5张

    void insertSingerImgsFilePath(@Param("singerImg") SingerImg singerImg);

    /**
     * add singer end
     */
    String selectSingerBySingerName(@Param("singer_name") String singerName);

    void updateSinger(@Param("singer") Singer singer);

    void insertPhotoImgFilePath(@Param("singer_id") String singerId, @Param("singer_photo_path") String singerPhotoPath);

    int updatePhotoImgFilePath(@Param("singer_id") String singerId);

}
