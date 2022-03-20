package org.sehkah.ddon.tools.streamdecryptor.model.pcap;

import org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet.PacketStream;

public interface SplitStreamDecryptor {
    PacketStream decrypt(String splitStreamInput, String key);
}
