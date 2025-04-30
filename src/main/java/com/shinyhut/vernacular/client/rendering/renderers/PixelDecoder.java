package com.shinyhut.vernacular.client.rendering.renderers;

import com.shinyhut.vernacular.protocol.messages.ColorMapEntry;
import com.shinyhut.vernacular.protocol.messages.PixelFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class PixelDecoder {

    private static final ColorMapEntry BLACK = new ColorMapEntry(0, 0, 0);

    private final Map<Long, ColorMapEntry> colorMap;
    private final boolean isBigEndian;

    /**
     * New Pixel decoder to read data offline from stream
     *
     * @param colorMap colorMap to match to
     * @param isBigEndian if the data streaming in is big endian vs little for processing
     */
    public PixelDecoder(Map<Long, ColorMapEntry> colorMap, boolean isBigEndian) {
        this.colorMap = colorMap;
        this.isBigEndian = isBigEndian;
    }

    /**
     * Each pixels data comes in here, and is converted to be ready to go to Java AWT
     *
     * @param in bit stream in
     * @param pixelFormat format we are converting to
     * @return return a Java Pixel
     * @throws IOException exception if reading a byte array fails
     */
    public Pixel decode(InputStream in, PixelFormat pixelFormat) throws IOException {
        int bytesToRead = pixelFormat.getBytesPerPixel();
        long value = 0L;

        if (isBigEndian) {
            for (int i = 0; i < bytesToRead; i++) {
                value <<= 8;
                value |= in.read();
            }
        } else {
            // Read bytes in little-endian order
            for (int i = 0; i < bytesToRead; i++) {
                value |= ((long) in.read()) << (8 * i);
            }
        }

        int red;
        int green;
        int blue;

        if (pixelFormat.isTrueColor()) {
            red = (int) (value >> pixelFormat.getRedShift()) & pixelFormat.getRedMax();
            green = (int) (value >> pixelFormat.getGreenShift()) & pixelFormat.getGreenMax();
            blue = (int) (value >> pixelFormat.getBlueShift()) & pixelFormat.getBlueMax();

            red = stretch(red, pixelFormat.getRedMax());
            green = stretch(green, pixelFormat.getGreenMax());
            blue = stretch(blue, pixelFormat.getBlueMax());
        } else {
            ColorMapEntry color = Optional.ofNullable(colorMap.get(value)).orElse(BLACK);
            red = shrink(color.getRed());
            green = shrink(color.getGreen());
            blue = shrink(color.getBlue());
        }

        return new Pixel(red, green, blue);
    }

    private static int stretch(int value, int max) {
        return max == 255 ? value : (int) (value * ((double) 255 / max));
    }

    private static int shrink(int colorMapValue) {
        return (int) Math.round(((double) colorMapValue) / 257);
    }
}
