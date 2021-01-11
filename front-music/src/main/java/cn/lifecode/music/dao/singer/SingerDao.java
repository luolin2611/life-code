//package cn.lifecode.music.dao.singer;
//
//
//import org.apache.ibatis.annotations.Param;
//
//import com.music.core.po.Singer;
//import com.music.core.po.SingerImg;
//
//public interface SingerDao {
//	/** add singer start 2020-03-05 20:45:12 */
//	public String selectSingerIdByMusicId(@Param("singer_id")String singer_id);
//	//插入封面路径
//	public void insertCoverImgFilePath(@Param("singer_id")String singer_id, @Param("singer_cover_path")String singer_cover_path);
//	public void updateCoverImgFilePath(@Param("singer_id")String singer_id, @Param("singer_cover_path")String singer_cover_path);
//	//插入写真图片路径
//	public int selectImgsCountBySingerId(@Param("singer_id")String singer_id); //插入图片的数量不能大于5张
//	public void insertSingerImgsFilePath(@Param("singerImg")SingerImg singerImg);
//	/** add singer end */
//	public String selectSingerBySingerName(@Param("singer_name")String singer_name);
//	public void updateSinger(@Param("singer")Singer singer);
//}
