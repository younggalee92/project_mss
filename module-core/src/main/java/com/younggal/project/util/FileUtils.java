package com.younggal.project.util;

import com.younggal.project.enumType.FileType;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static String getFileFormat(MultipartFile file) throws IOException {
        Tika tika = new Tika();
        return tika.detect(file.getInputStream());
    }

    public static FileType isImageOrVideo(MultipartFile file) throws IOException {
        String fileType = getFileFormat(file);

        if (fileType.startsWith("image/")) {
            return FileType.IMAGE;
        } else if (fileType.startsWith("video/")) {
            return FileType.VIDEO;
        } else {
            throw new RuntimeException();
        }
    }

    public static BufferedImage getBufferedImage(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return ImageIO.read(inputStream);
    }

    public static Integer getImageWidth(MultipartFile file) throws IOException {
        BufferedImage bufferedImage = getBufferedImage(file);
        return bufferedImage.getWidth();
    }

    public static Integer getImageHeight(MultipartFile file) throws IOException {
        BufferedImage bufferedImage = getBufferedImage(file);
        return bufferedImage.getHeight();
    }
}
