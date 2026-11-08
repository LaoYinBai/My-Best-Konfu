package b.a.f;

import java.io.IOException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface l {

    /* renamed from: a, reason: collision with root package name */
    public static final l f922a = new l() { // from class: b.a.f.l.1
        @Override // b.a.f.l
        public void a(int i, b bVar) {
        }

        @Override // b.a.f.l
        public boolean a(int i, List<c> list) {
            return true;
        }

        @Override // b.a.f.l
        public boolean a(int i, List<c> list, boolean z) {
            return true;
        }

        @Override // b.a.f.l
        public boolean a(int i, c.e eVar, int i2, boolean z) throws IOException {
            eVar.i(i2);
            return true;
        }
    };

    void a(int i, b bVar);

    boolean a(int i, c.e eVar, int i2, boolean z) throws IOException;

    boolean a(int i, List<c> list);

    boolean a(int i, List<c> list, boolean z);
}
