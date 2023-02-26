package com.shpp.p2p.cs.bvorobiov.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        /*
        * Create loop that check column and active cell.
        * If cell return true — play sound.*/
        for (int i = 0; i < toneMatrix.length; i++){
            for (int j = 0; j < toneMatrix[i].length; j++){
                if (column == i && toneMatrix[j][i]){
                    calculateSamples(samples, result, column);
                }
            }
        }


        return result;
    }

    /**
     * The method is calculated input 2D array of samples.
     * <P>
     * 1. Create loop that check all rows and cols of 2D array and add it to result array.
     * <P>
     * 2. Then declare max and min value for that we can divide into them the other numbers.
     * <P>
     * 3. Then we calculate and dived all numbers from > 0 on maxValue and all numbers from < 0 divide on minValue
     * <P>
     * 4. Return calculated array.*/
    public static double[] calculateSamples(double[][] samples, double[] result, int column) {
        for (int i = 0; i < samples.length; i++) {
            for (int j = 0; j < samples[i].length; j++) {
                if (samples[i][j] != 0 && column == i) {
                    result[j] += samples[i][j];
                }
            }
        }

        double maxValue = 0;
        double minValue = 0;

        for (double i : result) {
            if (i > maxValue) {
                maxValue = i;
            } else if (i < minValue) {
                minValue = i;
            }
        }

        for (int k = 0; k < result.length; k++) {
            if (result[k] > 0) {
                result[k] = result[k] / maxValue;
            }
            if (result[k] < 0) {
                result[k] = result[k] / minValue;
            }
        }

        return result;
    }
}
