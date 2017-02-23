package com.babyduncan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        BufferedImage bufferedImage1 = ImageIO.read(new FileInputStream("/Users/zhaoguohao/Desktop/pic1.jpeg"));
        BufferedImage bufferedImage2 = ImageIO.read(new FileInputStream("/Users/zhaoguohao/Desktop/pic2.jpeg"));

        mergeImage(bufferedImage1, bufferedImage2);
        mergeImageAndCut(bufferedImage1, bufferedImage2);
        System.out.println("Hello World!");
    }


    public static void mergeImage(BufferedImage bufferedImage1, BufferedImage bufferedImage2) throws IOException {
        BufferedImage combined = new BufferedImage(bufferedImage1.getWidth(), bufferedImage1.getHeight() + bufferedImage2.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.drawImage(bufferedImage1, 0, 0, null);
        g.drawImage(bufferedImage2, 0, bufferedImage1.getHeight(), null);
        ImageIO.write(combined, "JPG", new File("/Users/zhaoguohao/Desktop/pic3.jpeg"));
    }

    public static void mergeImageAndCut(BufferedImage bufferedImage1, BufferedImage bufferedImage2) throws IOException {
        BufferedImage bufferedImage3 = bufferedImage2.getSubimage(0, 0, bufferedImage2.getWidth(), getCutPoint(bufferedImage2));
        BufferedImage combined = new BufferedImage(bufferedImage1.getWidth(), bufferedImage1.getHeight() + bufferedImage3.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.drawImage(bufferedImage1, 0, 0, null);
        g.drawImage(bufferedImage3, 0, bufferedImage1.getHeight(), null);
        ImageIO.write(combined, "JPG", new File("/Users/zhaoguohao/Desktop/pic4.jpeg"));
    }

    /**
     * 如果最后一张图片的底色是直的,那么这个会很准确
     *
     * @param bufferedImage
     * @return
     */
    public static int getCutPoint(BufferedImage bufferedImage) {
        int p = bufferedImage.getHeight();
        int blank = bufferedImage.getRGB(1, 1);
        int imageEnd = p;
        for (int i = p - 1; i > 0; i--) {
            if (blank != bufferedImage.getRGB(100, i)) {
                imageEnd = i;
            }
        }
        return p - imageEnd + 30;
    }

}
