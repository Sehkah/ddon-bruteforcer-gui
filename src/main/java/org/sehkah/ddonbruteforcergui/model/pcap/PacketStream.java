package org.sehkah.ddonbruteforcergui.model.pcap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class PacketStream {
    @JsonProperty("Encrypted")
    private boolean encrypted;
    @JsonProperty("EncryptionKey")
    private String encryptionKey;
    @JsonProperty("LogStartTime")
    private Date logStartTime;
    @JsonProperty("ServerType")
    private String serverType;
    @JsonProperty("ServerIP")
    private String serverIp;
    @JsonProperty("Packets")
    private List<Packet> packets;

    @JsonCreator
    public PacketStream(@JsonProperty("Encrypted") boolean encrypted, @JsonProperty("EncryptionKey") String encryptionKey,
                        @JsonProperty("LogStartTime") Date logStartTime, @JsonProperty("ServerType") String serverType,
                        @JsonProperty("ServerIP") String serverIp, @JsonProperty("Packets") List<Packet> packets) {
        this.encrypted = encrypted;
        this.encryptionKey = encryptionKey;
        this.logStartTime = logStartTime;
        this.serverType = serverType;
        this.serverIp = serverIp;
        this.packets = packets;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public Date getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(Date logStartTime) {
        this.logStartTime = logStartTime;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    @Override
    public String toString() {
        return "SplitStream{" +
                "encrypted=" + encrypted +
                ", encryptionKey='" + encryptionKey + '\'' +
                ", logStartTime=" + logStartTime +
                ", serverType='" + serverType + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", packets=" + packets +
                '}';
    }

}
