package org.sehkah.ddon.tools.streamdecryptor.model.pcap.packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import org.sehkah.ddon.tools.streamdecryptor.model.serialization.Serializer;
import org.sehkah.ddon.tools.streamdecryptor.model.serialization.SerializerImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PacketIdConstants {
    private static Map<String, String> packetIdNameMap;

    static {
        Serializer serializer = new SerializerImpl();
        String packetIdYaml = null;
        try {
            packetIdYaml = IOUtils.toString(ClassLoader.getSystemClassLoader().getResource("streamdecryptor_packet-ids.yaml"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            packetIdNameMap = serializer.deserializeYaml(packetIdYaml, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private PacketIdConstants() {

    }

    public static String getPacketIdName(int groupId, int id, int subId) {
        return packetIdNameMap.get(groupId + " " + id + " " + subId);
    }
}
