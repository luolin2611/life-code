package cn.lifecode.backmusic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.po.Music;
import cn.lifecode.backmusic.service.music.MusicService;


@Controller
@RequestMapping("/app")
public class AppMusicController {
	@Autowired
	private MusicService musicService;
	
	@RequestMapping("/queryAllMusic")
	@ResponseBody
	public String queryAllMusic(HttpServletRequest request) {
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "请求数据为空");
		List<Music> musicList = musicService.findAllMusic();
		if (musicList.size() > 0) {
			object.put("status", "success");
			object.put("data", musicList);
		}
		return object.toString();
	}
	
	/**
	 * play music
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/playMusic")
	public ResponseEntity<byte[]> playMusic(HttpServletRequest request) throws IOException {
		String path = request.getParameter("music_file_path");
		String rootPath =request.getSession().getServletContext().getRealPath(""); //得到根路径
	    File file = new File(rootPath+"/"+path);
	    System.out.println(rootPath+"/"+path);
	    byte[] body = null;
	    InputStream is = new FileInputStream(file);
	    body = new byte[is.available()];
	    is.read(body);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	}
	
	/**
	 * play music
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/backgroundImg")
	public ResponseEntity<byte[]> backgroundImg(HttpServletRequest request) throws IOException {
		String rootPath =request.getSession().getServletContext().getRealPath(""); //得到根路径
		int a = (int) ((Math.random() * 10) % 5);
		while(a == 0) {
			a = (int) ((Math.random() * 10) % 5);
		}
	    File file = new File(rootPath+"/upload/bg_img/"+a+".jpg");
	    byte[] body = null;
	    InputStream is = new FileInputStream(file);
	    body = new byte[is.available()];
	    is.read(body);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	} 
	
	/**
	 * play music
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/photoBgImg")
	public ResponseEntity<byte[]> photoBgImg(HttpServletRequest request) throws IOException {
		String path = request.getParameter("img_path");
		String rootPath =request.getSession().getServletContext().getRealPath(""); //得到根路径
	    File file = new File(rootPath+"/"+path);
	    byte[] body = null;
	    InputStream is = new FileInputStream(file);
	    body = new byte[is.available()];
	    is.read(body);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	}
}
