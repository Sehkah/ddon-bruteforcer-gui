package org.sehkah.ddonbruteforcergui.model.pcap;

public interface SplitStreamDecryptor {
    PacketStream decrypt(String splitStreamInput, String key);
}
