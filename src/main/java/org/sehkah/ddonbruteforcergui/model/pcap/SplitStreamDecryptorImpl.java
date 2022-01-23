package org.sehkah.ddonbruteforcergui.model.pcap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.sehkah.ddonbruteforcergui.model.crypto.CamelliaDecryptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SplitStreamDecryptorImpl implements SplitStreamDecryptor {
    private static final Logger logger = LogManager.getLogger();
    private final ObjectMapper mapper;

    public SplitStreamDecryptorImpl() {
        mapper = new ObjectMapper(new JsonFactory());
        mapper.findAndRegisterModules();
    }

    @Override
    public PacketStream decrypt(String splitStreamInput, String key) {
        logger.debug("Attempting to decrypt split stream", splitStreamInput);
        PacketStream stream = null;
        try {
            stream = mapper.readValue(splitStreamInput, PacketStream.class);
            List<Packet> packets = stream.getPackets();
            // Remove the first 2 packets
            for (int i = 0; i < packets.size(); i++) {
                Packet p = packets.get(i);
                byte[] decryptedData;
                if (i < 2) {
                    decryptedData = CamelliaDecryptor.decryptPacketKeyExchangeData(Base64.decode(p.getData()));
                } else {
                    decryptedData = CamelliaDecryptor.decryptPacketData(Base64.decode(p.getData()), key.getBytes(StandardCharsets.UTF_8));
                }
                p.setData(Base64.toBase64String(decryptedData));
            }
            logger.debug("packets {}", stream.getPackets());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
