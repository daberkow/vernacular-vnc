package com.shinyhut.vernacular.protocol.messages;

import java.io.*;

public class FramebufferUpdateRequest implements Encodable {

    private final boolean incremental;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public FramebufferUpdateRequest(boolean incremental, int x, int y, int width, int height) {
        this.incremental = incremental;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(baos);

        dataOutput.writeByte(0x03);
        dataOutput.writeBoolean(incremental);
        dataOutput.writeShort(x);
        dataOutput.writeShort(y);
        dataOutput.writeShort(width);
        dataOutput.writeShort(height);
        dataOutput.flush(); // Ensure all data is written to the ByteArrayOutputStream

        byte[] bytes = baos.toByteArray();
        out.write(bytes);   // Send all bytes at once
        out.flush();        // Ensure the data is sent immediately
    }
}
