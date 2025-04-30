package com.shinyhut.vernacular.client.rendering;

public enum ColorDepth {

    /** 8 bits per pixel indexed color **/
    BPP_8_INDEXED(8, 8, false, 0, 0, 0, 0, 0, 0),

    /** 8 bits per pixel true color **/
    BPP_8_TRUE(8, 8, true, 7, 7, 3, 5, 2, 0),
//    BPP_8_TRUE(8, 8, true, 7, 7, 3, 0, 3, 6),

    /** 16 bits per pixel true color, RGB565 **/
    BPP_16_TRUE(16, 16, true, 31, 63, 31, 11, 5, 0),

    /** 24 bits per pixel true color, RGB888 **/
    BPP_24_TRUE(32, 24, true, 255, 255, 255, 16, 8, 0);

    private final int bitsPerPixel;
    private final int depth;
    private final boolean trueColor;
    private final int redMax;
    private final int greenMax;
    private final int blueMax;
    private final int redShift;
    private final int greenShift;
    private final int blueShift;

    /**
     * Create a ColorDepth object to track RGB elements of the connection
     *
     * @param bitsPerPixel Total bits per pixel
     * @param depth Total bits responsible for color data
     * @param trueColor Is true color and not indexed color
     * @param redMax max value for red
     * @param greenMax max value for green
     * @param blueMax max value for blue
     * @param redShift bit shift to get red out of presented data
     * @param greenShift bit shift to get green out of presented data
     * @param blueShift bit shift to get blue out of presented data
     */
    ColorDepth(int bitsPerPixel, int depth, boolean trueColor, int redMax, int greenMax, int blueMax, int redShift, int greenShift, int blueShift) {
        this.bitsPerPixel = bitsPerPixel;
        this.depth = depth;
        this.trueColor = trueColor;
        this.redMax = redMax;
        this.blueMax = blueMax;
        this.greenMax = greenMax;
        this.redShift = redShift;
        this.blueShift = blueShift;
        this.greenShift = greenShift;
    }

    public int getBitsPerPixel() {
        return bitsPerPixel;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isTrueColor() {
        return trueColor;
    }

    public int getRedMax() {
        return redMax;
    }

    public int getGreenMax() {
        return greenMax;
    }

    public int getBlueMax() {
        return blueMax;
    }

    public int getRedShift() {
        return redShift;
    }

    public int getGreenShift() {
        return greenShift;
    }

    public int getBlueShift() {
        return blueShift;
    }
}
