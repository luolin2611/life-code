package cn.lifecode.backmusic.comm;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 用于处理视频时间/大小
 */
public class DurationUtil {

	/**
	 * 获取视频时长(时分秒)
	 *
	 * @param ms
	 * @return
	 */
	public static String ReadVideoTime(long ms) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		String strDay = day < 10 ? "0" + day : "" + day; // 天
		String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
		String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
		String strSecond = second < 10 ? "0" + second : "" + second;// 秒
		String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
		strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
		if (strHour.equals("00")) {
			return strMinute + ":" + strSecond;
		} else {
			return strHour + ":" + strMinute + ":" + strSecond;
		}
	}

	/**
	 * 获取视频时长(毫秒)
	 *
	 * @param file
	 * @return
	 */
	public static String readFileTimeMs(MultipartFile file) {
		Encoder encoder = new Encoder();
		String length = "";
		try {
			// 获取文件类型
			String fileName = file.getContentType();
			// 获取文件后缀
			String pref = fileName.indexOf("/") != -1
					? fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length())
					: null;
			String prefix = "." + pref;
			// 用uuid作为文件名，防止生成的临时文件重复
			final File excelFile = File.createTempFile(UUID.getUUID("Y", "10"), prefix);
			// MultipartFile to File
			file.transferTo(excelFile);
			MultimediaInfo m = encoder.getInfo(excelFile);
			long ls = m.getDuration();
			length = String.valueOf(ls);
			// 程序结束时，删除临时文件
			DurationUtil.deleteFile(excelFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}

	public static String getTime(String filePath) {
		String path1 = "";
		String path2 = "/Users/luolin/Public/Project/JavaProject/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/music_background/music_file/aaa.mp3";
		File file = new File(path2);
		AudioInputStream audioInputStream;
		String length = "";
		try {
			audioInputStream = AudioSystem.getAudioInputStream(file);
			// AudioFormat format = audioInputStream.getFormat();
			long frames = audioInputStream.getFrameLength();
			// double durationInSeconds = (frames+0.0) / format.getFrameRate();
			length = ReadVideoTime(frames);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return length;
	}

	/**
	 * 删除
	 *
	 * @param files
	 */
	private static void deleteFile(File... files) {
		for (File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public static void main(String[] args) {
		getTime("");
	}
}
