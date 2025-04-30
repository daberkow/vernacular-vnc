package com.shinyhut.vernacular.protocol.messages;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static java.util.Arrays.asList;

public class SetEncodings implements Encodable {

    private final List<Encoding> encodings;

    public SetEncodings(Encoding... encodings) {
        this.encodings = asList(encodings);
    }

    public SetEncodings(List<Encoding> encodings) {
        this.encodings = encodings;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        // DataOutputStream is acting weird and breaking into different packets
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(baos);

        dataOutput.writeByte(0x02);
        dataOutput.writeByte(0x00);
        dataOutput.writeShort(encodings.size());
        for (Encoding encoding : encodings) {
            dataOutput.writeInt(encoding.getCode());
        }
        dataOutput.flush(); // Ensure all data is written to the ByteArrayOutputStream

        byte[] bytes = baos.toByteArray();
        out.write(bytes);   // Send all bytes at once
        out.flush();        // Ensure the data is sent immediately
    }
}
