package org.sehkah.ddonbruteforcergui.model.pcap.packet;

import java.time.Instant;

public record Packet(Instant timestamp, String direction, String data) {
}
