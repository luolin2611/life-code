package cn.lifecode.backmusic.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.comm.FileUploadUtil;
import cn.lifecode.backmusic.comm.UUID;
import cn.lifecode.backmusic.po.Singer;
import cn.lifecode.backmusic.po.SingerImg;
import cn.lifecode.backmusic.po.SysUser;
import cn.lifecode.backmusic.service.singer.SingerService;

@Controller
public class SingerController {
	@Autowired
	private SingerService singerService;
	
	/**
	 * Singer upload file
	 * 
	 * @param upload_file
	 *            上传的文件
	 * @param file_id
	 *            文件ID
	 * @param file_type
	 *            上传的文件类型：1.cover_file （上传封面图片文件）2.photo_file（上传写真图片文件）
	 * @return
	 */
	@RequestMapping("/uploadSingerImg")
	@ResponseBody
	public String uploadSingerImg(HttpServletRequest request, @RequestParam("upload_file") MultipartFile upload_file,
			@RequestParam("file_id") String file_id, @RequestParam("file_type") String file_type) {
		String dirPath = request.getServletContext().getRealPath("/upload/" + file_type + "/");
		String fileName = "";
		if("photo_file".equals(file_type)) {
			fileName = UUID.getUUID("Y","45");
		}  else {
			fileName = file_id;
		}
		JSONObject res = FileUploadUtil.singleFileUpload(upload_file, dirPath, file_id);
		JSONObject object = new JSONObject();
		object.put("status","fail");
		object.put("message","服务器不知道怎么了，他报错啦！！");
		String insertFlag = "";
		if (!"fail".equals(res.get("status"))) {
			object.put("status", "success");
			String insertFliePath = "upload/" + file_type + "/" + res.getString("file_full_name");
			switch (file_type) {
			case "cover_file" :
				singerService.insertSingerCoverImgPath(file_id, insertFliePath);
				insertFlag = object.toString();
				break;
			case "photo_file":
				SingerImg si = new SingerImg();
				si.setImg_id(fileName); //将新ID作为图片ID
				si.setImg_path(insertFliePath);
				si.setSinger_id(file_id);
				si.setStatus("00");
				si.setUpdate_time(new Date());
				insertFlag = singerService.insertSingerPhotoImgPath(si);
				break;
			default:
				break;
			}
		}
		return insertFlag;
	}
	
	@RequestMapping("/addSinger")
	@ResponseBody
	public String addSinger(@RequestParam("singer_name") String singer_name, @RequestParam("file_id") String file_id, HttpSession session) {
		SysUser su = (SysUser) session.getAttribute("USER_SESSION");
		Singer singer = new Singer();
		singer.setAdd_sys_user_id(su.getUser_id());
		singer.setSinger_id(file_id);
		singer.setSinger_name(singer_name);
		singer.setStatus("00");
		singer.setUpdate_time(new Date());
		return singerService.insertSinger(singer);
	}
}
