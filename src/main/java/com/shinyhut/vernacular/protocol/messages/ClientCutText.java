package com.shinyhut.vernacular.protocol.messages;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientCutText implements Encodable {

    private final String text;

    public ClientCutText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(baos);

        dataOutput.writeByte(0x06);
        dataOutput.write(new byte[3]);
        dataOutput.writeInt(text.length());
        dataOutput.write(text.getBytes(StandardCharsets.ISO_8859_1));
        dataOutput.flush(); // Ensure all data is written to the ByteArrayOutputStream

        byte[] bytes = baos.toByteArray();
        out.write(bytes);   // Send all bytes at once
        out.flush();        // Ensure the data is sent immediately

//        DataOutput dataOutput = new DataOutputStream(out);
//        dataOutput.writeByte(0x06);
//        dataOutput.write(new byte[3]);
//        dataOutput.writeInt(text.length());
//        dataOutput.write(text.getBytes(StandardCharsets.ISO_8859_1));
    }
}
