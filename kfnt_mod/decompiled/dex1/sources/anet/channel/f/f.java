package anet.channel.f;

import android.content.SharedPreferences;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f implements IStrategyListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SharedPreferences f446a;

    f(SharedPreferences sharedPreferences) {
        this.f446a = sharedPreferences;
    }

    @Override // anet.channel.strategy.IStrategyListener
    public void onStrategyUpdated(l.d dVar) {
        if (dVar == null || dVar.f580b == null) {
            return;
        }
        for (int i = 0; i < dVar.f580b.length; i++) {
            String str = dVar.f580b[i].f574a;
            l.a[] aVarArr = dVar.f580b[i].h;
            if (aVarArr != null && aVarArr.length > 0) {
                for (l.a aVar : aVarArr) {
                    String str2 = aVar.f572b;
                    if (ConnType.QUIC.equals(str2) || ConnType.QUIC_PLAIN.equals(str2)) {
                        if (!str.equals(a.f440a)) {
                            String unused = a.f440a = str;
                            SharedPreferences.Editor editorEdit = this.f446a.edit();
                            editorEdit.putString("quic_detector_host", a.f440a);
                            editorEdit.apply();
                        }
                        a.a(NetworkStatusHelper.getStatus());
                        return;
                    }
                }
            }
        }
    }
}
