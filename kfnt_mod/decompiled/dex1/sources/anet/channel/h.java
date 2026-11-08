package anet.channel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements Runnable {
    h() {
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            anet.channel.b.a aVar = new anet.channel.b.a();
            aVar.a();
            anetwork.channel.a.b.a(aVar, new i(this), 1);
        } catch (Exception unused) {
        }
    }
}
