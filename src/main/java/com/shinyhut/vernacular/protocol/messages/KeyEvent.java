package com.shinyhut.vernacular.protocol.messages;

import java.io.*;

public class KeyEvent implements Encodable {

    private final int keysym;
    private final boolean pressed;

    public KeyEvent(int keysym, boolean pressed) {
        this.keysym = keysym;
        this.pressed = pressed;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(baos);

        dataOutput.writeByte(0x04);
        dataOutput.writeBoolean(pressed);
        dataOutput.write(new byte[]{0x00, 0x00});
        dataOutput.writeInt(keysym);
        dataOutput.flush(); // Ensure all data is written to the ByteArrayOutputStream

        byte[] bytes = baos.toByteArray();
        out.write(bytes);   // Send all bytes at once
        out.flush();        // Ensure the data is sent immediately
    }
}
