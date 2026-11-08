package anet.channel.monitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum NetworkSpeed {
    Slow("弱网络", 1),
    Fast("强网络", 5);


    /* renamed from: a, reason: collision with root package name */
    private final String f456a;

    /* renamed from: b, reason: collision with root package name */
    private final int f457b;

    NetworkSpeed(String str, int i) {
        this.f456a = str;
        this.f457b = i;
    }

    public String getDesc() {
        return this.f456a;
    }

    public int getCode() {
        return this.f457b;
    }

    public static NetworkSpeed valueOfCode(int i) {
        return i == 1 ? Slow : Fast;
    }
}
