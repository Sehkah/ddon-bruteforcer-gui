package org.sehkah.ddonbruteforcergui.model.pcap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.sehkah.ddonbruteforcergui.model.crypto.CamelliaDecryptor;
import org.sehkah.ddonbruteforcergui.model.pcap.packet.Packet;
import org.sehkah.ddonbruteforcergui.model.serialization.Serializer;
import org.sehkah.ddonbruteforcergui.model.serialization.SerializerImpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SplitStreamDecryptorImpl implements SplitStreamDecryptor {
    private static final Logger logger = LogManager.getLogger();
    private static final Serializer serializer = new SerializerImpl();

    public SplitStreamDecryptorImpl() {
    }

    @Override
    public PacketStream decrypt(String splitStreamInput, String key) {
        logger.debug("Attempting to decrypt split stream");
        PacketStream encryptedStream = null;
        PacketStream decryptedStream = null;
        try {
            encryptedStream = serializer.deserialize(splitStreamInput, PacketStream.class);
            List<Packet> encryptedPackets = encryptedStream.packets();
            List<Packet> decryptedPackets = new ArrayList<>(encryptedPackets.size());
            for (int i = 0; i < encryptedPackets.size(); i++) {
                Packet encryptedPacket = encryptedPackets.get(i);
                byte[] decryptedData;
                if (i < 2) {
                    // The first 2 packets contain key exchange data, as this is not interesting, not handling it here
                    decryptedData = encryptedPacket.data().getBytes(StandardCharsets.UTF_8);
                } else {
                    decryptedData = CamelliaDecryptor.decryptPacketData(Base64.decode(encryptedPacket.data()), key.getBytes(StandardCharsets.UTF_8));
                }
                decryptedPackets.add(new Packet(encryptedPacket.timestamp(), encryptedPacket.direction(), Base64.toBase64String(decryptedData)));
            }
            decryptedStream = new PacketStream(false, key, encryptedStream.logStartTime(), encryptedStream.serverType(), encryptedStream.serverIp(), decryptedPackets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return decryptedStream;
    }
}
