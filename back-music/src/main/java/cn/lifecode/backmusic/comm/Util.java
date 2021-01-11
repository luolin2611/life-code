package cn.lifecode.backmusic.comm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacInfoReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.wav.WavFileReader;
import org.jaudiotagger.tag.TagException;

import com.qf.core.MD5;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Util {
    public static String getMD5(String str) {
        return new MD5().toMD5(str);
    }

    public static String getTimeSuggest() {
        String res = "";
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 6) {
            res = "凌晨好！";
        }
        if (a > 6 && a <= 12) {
            res = "上午好！";
        }
        if (a > 12 && a <= 13) {
            res = "中午好！";
        }
        if (a > 13 && a <= 18) {
            res = "下午好！";
        }
        if (a > 18 && a <= 24) {
            res = "晚上好！";
        }
        return res;
    }

    /**
     * Get mp3 Duration. return int (seconde)
     *
     * @return
     */
    public static int getMP3Duration(String filePath) {
        int seconde = 0;
        try {
            MP3File mp3 = new MP3File(new File(filePath));
            AudioHeader ah = mp3.getAudioHeader();
            seconde = ah.getTrackLength();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return seconde;
    }

    /**
     * Get flac Duration. return int (seconde)
     *
     * @return
     */
    public static int getFlacDuration(String filePath) {
        int seconde = 0;
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(new File(filePath), "r");
            FlacInfoReader fir = new FlacInfoReader();
            GenericAudioHeader gah = fir.read(raf);
            seconde = gah.getTrackLength();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seconde;
    }

    /**
     * Get wav Duration. return int (seconde)
     *
     * @return
     */
    public static int getWavDuration(String filePath) {
        int seconde = 0;
        try {
            WavFileReader wfr = new WavFileReader();
            AudioFile af = wfr.read(new File(filePath));
            AudioHeader ah = af.getAudioHeader();
            seconde = ah.getTrackLength();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return seconde;
    }

    /**
     * Return minute:second (eg: 05:34)
     *
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long minutes = second / 60;//得到分钟
        second = second % 60;//剩余秒数
        String min = minutes < 10 ? "0" + minutes : "" + minutes;
        String sec = second < 10 ? "0" + second : "" + second;
        return min + ":" + sec;
    }

    /**
     * Conversion of Chinese characters to pinyin
     *
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}
