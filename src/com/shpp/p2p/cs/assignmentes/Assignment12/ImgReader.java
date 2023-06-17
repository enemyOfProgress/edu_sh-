package com.shpp.p2p.cs.bvorobev.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**The class just read file (image) from folder and return the image object*/
public class ImgReader {
    /**Input filename*/
    String fileName;

    public ImgReader(String fileName) {
        this.fileName = fileName;
    }

    /**Read the file from folder and return image object
     * @return readed image*/
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            File file = new File(fileName);
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("The name of input file is incorrect! The name is " + fileName);
        }
        return image;
    }
}
