package org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Packet(Instant timestamp, String direction, String data, PacketId packetId, Integer packetCounter) {
}
