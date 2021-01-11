package cn.lifecode.backmusic.comm;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

public class FileUploadUtil {
	public static String handleFormUpload(MultipartFile uploadfile, String dirPath, String imgId) {
		// 获取上传文件的原始名称
		String originalFilename = uploadfile.getOriginalFilename();
		// 设置上传文件的保存地址目录
		File filePath = new File(dirPath);
		System.out.println(dirPath);
		// 如果保存文件的地址不存在，就先创建目录
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		// 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
//		String newFilename = UUID.randomUUID() + "_" + originalFilename;
		try {
			// 使用MultipartFile接口的方法完成文件上传到指定位置
			uploadfile.transferTo(new File(filePath.getAbsolutePath() + imgId+originalFilename.substring(originalFilename.lastIndexOf("."))));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return dirPath + imgId+originalFilename.substring(originalFilename.lastIndexOf("."));
	}
	
//	public static String singleFileUpload(MultipartFile uploadfile, String dirPath, String fileName) {
//		String originalFilename = uploadfile.getOriginalFilename();// 获取上传文件的原始名称
//		File filePath = new File(dirPath);// 设置上传文件的保存地址目录
//		if (!filePath.exists()) {
//			filePath.mkdirs();
//		}
//		String fileFullName = filePath.getAbsolutePath() + "/" + fileName + originalFilename.substring(originalFilename.lastIndexOf("."));
//		File file = new File(fileFullName);
//		try {
//			uploadfile.transferTo(file);// 使用MultipartFile接口的方法完成文件上传到指定位置
//			System.out.println(file.getAbsolutePath());
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return file.getAbsolutePath();
//	}
	
	public static JSONObject singleFileUpload(MultipartFile uploadfile, String dirPath, String fileName) {
		String originalFilename = uploadfile.getOriginalFilename();// 获取上传文件的原始名称
		File filePath = new File(dirPath);// 设置上传文件的保存地址目录
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));//后缀 （例如： .mp3）
		String fileFullName = filePath.getAbsolutePath() + "/" + fileName + suffix;
		try {
			File file = new File(fileFullName);
			uploadfile.transferTo(file);// 使用MultipartFile接口的方法完成文件上传到指定位置
			String size = getPrintSize(file.length());
			object.put("status", "success");
			object.put("file_path",fileFullName);
			object.put("file_size", size);
			object.put("file_full_name",fileName + suffix); //文件全名称，例如：88cba9da9c2b45f2af52e2db190419c5ed77bde54f274.jpg
			System.out.println(fileFullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * 获取文件大小
	 *
	 * @param size
	 * @return
	 */
	public static String getPrintSize(long size) {
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			// 因为如果以MB为单位的话，要保留最后1位小数，
			// 因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
		}
	}
}
