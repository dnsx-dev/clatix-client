package com.clatix.event.events;

import com.clatix.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketEvent implements Event {
    private Packet<?> packet;
    private Type type;

    public PacketEvent(Packet<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    public Packet<?> getPacket() { return packet; }
    public Type getType() { return type; }

    public enum Type {
        SEND, RECEIVE
    }
}