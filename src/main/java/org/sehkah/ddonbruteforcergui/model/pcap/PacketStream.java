package org.sehkah.ddonbruteforcergui.model.pcap;

import org.sehkah.ddonbruteforcergui.model.pcap.packet.Packet;

import java.util.Date;
import java.util.List;

public record PacketStream(boolean encrypted, String encryptionKey, Date logStartTime, String serverType,
                           String serverIp, List<Packet> packets) {
}
