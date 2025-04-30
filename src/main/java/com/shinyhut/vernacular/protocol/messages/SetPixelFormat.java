package com.shinyhut.vernacular.protocol.messages;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SetPixelFormat implements Encodable {

    private final PixelFormat pixelFormat;

    public SetPixelFormat(PixelFormat pixelFormat) {
        this.pixelFormat = pixelFormat;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(baos);

        dataOutput.writeByte(0x00);
        dataOutput.writeByte(0x00);
        dataOutput.writeByte(0x00);
        dataOutput.writeByte(0x00);
        pixelFormat.encode(dataOutput);
        dataOutput.flush(); // Ensure all data is written to the ByteArrayOutputStream

        byte[] bytes = baos.toByteArray();
        out.write(bytes);   // Send all bytes at once
        out.flush();        // Ensure the data is sent immediately

//        out.write(0x00);
//        out.write(new byte[3]);
//        pixelFormat.encode(out);
    }
}
