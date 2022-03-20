package org.sehkah.ddon.tools.streamdecryptor.model.pcap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.Packet;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.PacketId;
import org.sehkah.ddon.common.model.crypto.CamelliaDecryptor;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.PacketIdConstants;
import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.PacketStream;
import org.sehkah.ddon.tools.streamdecryptor.model.serialization.Serializer;
import org.sehkah.ddon.tools.streamdecryptor.model.serialization.SerializerImpl;

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
            encryptedStream = serializer.deserializeJson(splitStreamInput, PacketStream.class);
            List<Packet> encryptedPackets = encryptedStream.packets();
            List<Packet> decryptedPackets = new ArrayList<>(encryptedPackets.size());
            for (int i = 0; i < encryptedPackets.size(); i++) {
                Packet encryptedPacket = encryptedPackets.get(i);
                byte[] decryptedData;
                if (i < 2) {
                    // The first 2 packets contain key exchange data, as this is not interesting, not handling it here
                    decryptedPackets.add(encryptedPacket);
                } else {
                    decryptedData = CamelliaDecryptor.decryptPacketData(Base64.decode(encryptedPacket.data()), key.getBytes(StandardCharsets.UTF_8));
                    int groupId = decryptedData[0];
                    int id = Integer.parseInt(decryptedData[1] + "" + decryptedData[2]);
                    int subId = decryptedData[3];
                    int packetCounter = decryptedData[8];
                    decryptedPackets.add(new Packet(encryptedPacket.timestamp(), encryptedPacket.direction(), Base64.toBase64String(decryptedData),
                            new PacketId(groupId, id, subId, PacketIdConstants.getPacketIdName(groupId, id, subId)),
                            packetCounter));
                }
            }
            decryptedStream = new PacketStream(false, key, encryptedStream.logStartTime(), encryptedStream.serverType(), encryptedStream.serverIp(), decryptedPackets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return decryptedStream;
    }
}
