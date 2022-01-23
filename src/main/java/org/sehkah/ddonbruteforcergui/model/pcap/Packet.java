package org.sehkah.ddonbruteforcergui.model.pcap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Packet {
    @JsonProperty("Timestamp")
    private Date timestamp;

    @JsonProperty("Direction")
    private String direction;

    @JsonProperty("Data")
    private String data;

    @JsonCreator
    public Packet(@JsonProperty("Timestamp") Date timestamp, @JsonProperty("Direction") String direction,
                  @JsonProperty("Data") String data) {
        this.timestamp = timestamp;
        this.direction = direction;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
