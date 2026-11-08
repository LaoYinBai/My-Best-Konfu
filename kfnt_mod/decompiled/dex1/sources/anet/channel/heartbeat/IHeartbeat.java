package anet.channel.heartbeat;

import anet.channel.Session;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IHeartbeat {
    void reSchedule();

    void start(Session session);

    void stop();
}
