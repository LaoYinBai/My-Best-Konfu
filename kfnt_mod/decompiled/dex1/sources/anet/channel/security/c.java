package anet.channel.security;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ISecurityFactory f490a;

    public static ISecurityFactory a() {
        if (f490a == null) {
            f490a = new d();
        }
        return f490a;
    }
}
