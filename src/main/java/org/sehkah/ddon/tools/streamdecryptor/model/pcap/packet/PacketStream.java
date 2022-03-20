package org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet;

import java.util.Date;
import java.util.List;

public record PacketStream(boolean encrypted, String encryptionKey, Date logStartTime, String serverType,
                           String serverIp, List<Packet> packets) {
}
