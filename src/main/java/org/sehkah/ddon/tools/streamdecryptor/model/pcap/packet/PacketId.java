package org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet;

/**
 * Based on https://github.com/Andoryuuta/DDON_RE/blob/master/packet_docs/GamePackets.md
 */
public record PacketId(int groupId, int id, int subId, String name) {
}
