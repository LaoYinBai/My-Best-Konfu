package a.a.a;

import com.cgs.out.CGManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends Thread {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ byte[] f325a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CGManager f326b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(CGManager cGManager, String str, byte[] bArr) {
        super(str);
        this.f326b = cGManager;
        this.f325a = bArr;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        new a.a.b.b.a(this.f326b.getContext(), "").a(1, 2, this.f325a);
    }
}
