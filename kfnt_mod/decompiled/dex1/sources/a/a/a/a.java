package a.a.a;

import com.cgs.out.CGManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends Thread {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ byte[] f316a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CGManager f317b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(CGManager cGManager, String str, byte[] bArr) {
        super(str);
        this.f317b = cGManager;
        this.f316a = bArr;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        new a.a.b.b.b(this.f317b.getContext(), "").a(1, 2, this.f316a);
    }
}
