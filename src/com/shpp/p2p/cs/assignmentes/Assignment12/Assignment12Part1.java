package com.shpp.p2p.cs.bvorobev.assignment12;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assignment12Part1 {
    /**
     * The value that specifies the threshold for red color for the pixel. If The value > 140 it is the background.
     */
    static int THRESHOLD_VALUE = 120;
    /**
     * Mark pixel as part of background
     */
    static char BACKGROUND = 'B';
    /**
     * Mark pixel as part of silhouette
     */
    static char SILHOUETTE = 'S';
    /**
     * Mark pixel as visited
     */
    static char VISITED = 'V';
    /**
     * Count the silhouettes, everytime when return from dfs
     */
    static int numberOfSilhouettes = 0;
    /**Counter of visited pixels, it's needed for find the silhouettes and the trash*/
    static int counter = 0;
    /**
     * Width of image*/
    static int width;
    /**
     * Height of image*/
    static int height;

    public static void main(String[] args) {
        try {
            findSilhouettes(args);
        } catch (Exception e){
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    /**
     * Find the silhouettes on image.
     * <p>
     * 1. Read the image
     * <p>
     * 2. Scan the image and add pixels as silhouette or background to array
     * <p>
     * 3. Count silhouettes*/
    private static void findSilhouettes(String[] args) {
        BufferedImage image = getImg(args);
        char[][] pixels = createPixelsArray(image);
        countSilhouettes(pixels);
        System.out.println("Number of silhouettes is: " + numberOfSilhouettes);

    }

    /**
     * Get image from folder.
     * @param args first string from input array, it should be file name.
     *             If the array is empty, constant file name is test.jpg*/
    private static BufferedImage getImg(String[] args) {
        String fileName = args.length == 0 ? "test.jpg" : args[0];
        ImgReader imgReadr = new ImgReader(fileName);
        width = imgReadr.getImage().getWidth();
        height = imgReadr.getImage().getHeight();
        return imgReadr.getImage();
    }

    /**
     * Create array from pixels image.
     * <p>
     * The method scan image and check all pixels. If pixel has red higher than threshold value - it is the background.
     * So, when it found, add as char 'B' (background) to char array.
     * <p>
     * And if the pixel has red color less than threshold value - it is the part of silhouette. So, when it found, add
     * as char 'S' (silhouette) to char array.
     * <p>
     * Finally, each pixel replace by the chars.*/
    private static char[][] createPixelsArray(BufferedImage image) {
        // Create new char array
        char[][] pixels = new char[image.getHeight()][image.getWidth()];
        // Check if there is alpha channel
        boolean isImageHasAlphaChannel = image.getColorModel().hasAlpha();
        Color pixel;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (isImageHasAlphaChannel) {
                    pixel = new Color((image.getRGB(j, i) & 0xff000000) >> 24);
                    if (pixel.getRed() < THRESHOLD_VALUE) {
                        pixels[i][j] = BACKGROUND;
                    } else {
                        pixels[i][j] = SILHOUETTE;
                    }
                } else {
                    pixel = new Color(image.getRGB(j, i));
                    if (pixel.getRed() < THRESHOLD_VALUE) {
                        pixels[i][j] = SILHOUETTE;
                    } else {
                        pixels[i][j] = BACKGROUND;
                    }
                }

            }
        }
        return pixels;
    }

    /**
     * Count the silhouettes on image. If there is 'S' in array, than start dfs.
     * @param pixels 2d char array with chars 'B' as background and 'S' as silhouette.*/
    private static void countSilhouettes(char[][] pixels) {
        // Count number of all pixels on image
        float allPixels = height * width;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                if (pixels[i][j] == SILHOUETTE) {
                    dfs(i, j, pixels);
                    // If visited pixels more than 0.5% of all image, it's not thrash and silhouettes count ++
                    if (counter / allPixels * 100 > 0.5) {
                        numberOfSilhouettes++;
                    }
                    counter = 0;
                }
            }
        }
    }

    /**
     * Start to depth first search.
     * @param i rows of input array (rows of image)
     * @param j cols of input array (cols of image)
     * @param pixels input array created from pixels image*/
    private static void dfs(int i, int j, char[][] pixels) {
        // Count visited pixels, it always ++1 when dfs is start
        counter++;
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return;
        }

        if (pixels[i][j] != SILHOUETTE) {
            return;
        }

        pixels[i][j] = VISITED;
        dfs(i - 1, j, pixels);
        dfs(i + 1, j, pixels);
        dfs(i, j - 1, pixels);
        dfs(i, j + 1, pixels);
    }
}