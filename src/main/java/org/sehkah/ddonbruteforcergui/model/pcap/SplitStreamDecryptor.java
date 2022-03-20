package org.sehkah.ddonbruteforcergui.model.pcap;

import org.sehkah.ddonbruteforcergui.model.pcap.packet.PacketStream;

public interface SplitStreamDecryptor {
    PacketStream decrypt(String splitStreamInput, String key);
}
