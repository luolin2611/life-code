package cn.lifecode.backmusic.dao.music;


import cn.lifecode.backmusic.po.Music;
import cn.lifecode.backmusic.po.MusicDict;
import cn.lifecode.backmusic.po.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MusicDao {
    public void insertDictImgDictId(@Param("music_dict_id") String music_dict_id, @Param("dict_img_path") String dict_img_path);

    public String findMusiDictIdByMusiDict(@Param("music_dict_id") String music_dict_id, @Param("add_user_name") String add_user_name);

    public String findMusiDictIdByDictName(@Param("dict_name") String dict_name);

    public void updateMusicDict(MusicDict musicDict);

    List<MusicDict> findMusicDictList();

    String countMusicNumForDict(@Param("music_dict_id") String music_dict_id);

    public List<Music> findAllMusic();

    public List<Singer> findAllSinger();

    /**
     * 添加音乐 start 2019年04月16日07:58:45
     */
    public String selectMusicIdByMusicId(@Param("music_id") String music_id);

    //插入音乐文件路径
    public void insertMusicFilePath(@Param("music_id") String music_id, @Param("music_file_path") String music_file_path, @Param("file_duration") String file_duration);

    public void updateMusicFilePath(@Param("music_id") String music_id, @Param("music_file_path") String music_file_path, @Param("file_duration") String file_duration);

    //插入DictTitleImag 文件路径
    public void insertTitleImgFilePath(@Param("music_id") String music_id, @Param("music_img_path") String music_img_path);

    public void updateTitleImgFilePath(@Param("music_id") String music_id, @Param("music_img_path") String music_img_path);

    //根据歌曲的名字 和 歌曲的Title 查询是否插入过歌曲
    public String selectMusicByTitleOrName(@Param("name") String name, @Param("title") String title);

    //提交按钮的更新补齐所有信息
    public void updateMusic(@Param("music") Music music);
    /** 添加音乐end */
}
