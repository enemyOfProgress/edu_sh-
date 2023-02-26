package com.shpp.p2p.cs.bvorobiov.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {

        /*
        * Create the returned array.
        * Array width is 256, 'cause count start from zero.*/
        int[] histogramLuminance = new int[256];

        /*
        * Loop is sorting all values from 2D array and add it to returned array.*/
        for (int i = 0; i < luminances.length; i++){
            for (int j = 0; j < luminances[i].length; j++){
                histogramLuminance[luminances[i][j]]++;
            }
        }
        return histogramLuminance;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {

        /*
        * Create returned array and buffer for cumulative values*/
        int [] cumulativeFrequency = new int[histogram.length];
        int bufferValue = 0;

        for (int i = 0; i < histogram.length; i++){
            bufferValue += histogram[i];
            cumulativeFrequency[i] = bufferValue;
        }
        return cumulativeFrequency;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        return luminances.length * luminances[0].length;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {

        /*
        * Create returned 2D array.
        * Create array for cumulativeSumFor method. Its need for fast work.
        * Create variable for save total pixels of image.*/
        int[][] newLuminance = new int[luminances.length][luminances[0].length];
        int[] cumulativeSum = cumulativeSumFor(histogramFor(luminances));
        int totalPixels = totalPixelsIn(luminances);

        /*
        * Loop is changing all values in input array and add in to returned array.*/
        for (int i = 0; i < luminances.length; i ++){
            for (int j = 0; j < luminances[i].length; j++){
                newLuminance[i][j] = MAX_LUMINANCE * cumulativeSum[luminances[i][j]] / totalPixels;
            }
        }
        return newLuminance;
    }
}
