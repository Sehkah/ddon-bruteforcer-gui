package org.sehkah.ddonbruteforcergui.model.pcap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Packet {
    @JsonProperty("Timestamp")
    private Instant timestamp;

    @JsonProperty("Direction")
    private String direction;

    @JsonProperty("Data")
    private String data;

    @JsonCreator
    public Packet(@JsonProperty("Timestamp") Instant timestamp, @JsonProperty("Direction") String direction,
                  @JsonProperty("Data") String data) {
        this.timestamp = timestamp;
        this.direction = direction;
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "timestamp=" + timestamp +
                ", direction='" + direction + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
