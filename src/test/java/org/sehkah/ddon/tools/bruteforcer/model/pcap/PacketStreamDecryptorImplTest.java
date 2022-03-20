package org.sehkah.ddon.tools.bruteforcer.model.pcap;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.SplitStreamDecryptor;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.SplitStreamDecryptorImpl;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.PacketStream;
import org.sehkah.ddon.tools.streamdecryptor.model.serialization.SerializerImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacketStreamDecryptorImplTest {

    @Test
    void decrypt() throws IOException {
        SplitStreamDecryptor splitStreamDecryptor = new SplitStreamDecryptorImpl();
        String splitStream = IOUtils.toString(ClassLoader.getSystemClassLoader().getResource("stream61-remarked.pcapng_0_login.json"), StandardCharsets.UTF_8);
        String key = "UpJlo7MYHVbxS3Xs7LAx-sptfA5Q3Mw-";
        PacketStream stream = splitStreamDecryptor.decrypt(splitStream, key);
        assertEquals("AQAAAjQAAAAAAAAAAAAAAAAQEIl57taYrVB4VQLMuYiuyYhHxUDv5rixc585qXJf0znXihkAAAAAKP58AAAAAABw7BsFAAAAACK5dj8BAAAAKmVASrH9fCbAZp/qUwEm",
                stream.packets().get(2).data());
        System.out.println(new SerializerImpl().serializeJson(stream));
    }
}