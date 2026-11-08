package anet.channel.strategy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ConnEvent {
    public boolean isSuccess = false;
    public long connTime = Long.MAX_VALUE;

    public String toString() {
        return this.isSuccess ? "ConnEvent#Success" : "ConnEvent#Fail";
    }
}
