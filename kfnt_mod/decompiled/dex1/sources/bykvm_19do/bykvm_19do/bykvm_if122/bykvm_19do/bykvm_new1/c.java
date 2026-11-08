package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.b> f1889a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, ConcurrentHashMap<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a>> f1890b;

    private static class b {

        /* renamed from: a, reason: collision with root package name */
        private static c f1891a = new c();
    }

    public static c a() {
        return b.f1891a;
    }

    private c() {
        this.f1890b = new ConcurrentHashMap();
    }

    public void a(ConcurrentHashMap<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.b> concurrentHashMap) {
        this.f1889a = concurrentHashMap;
        if (concurrentHashMap == null) {
            Logger.d("TTMediationSDK_AdnShowControl", "mAdnControlMap is null ");
            return;
        }
        Logger.d("TTMediationSDK_AdnShowControl", "mAdnControlMap = " + concurrentHashMap);
    }

    public void a(String str, String str2, String str3) {
        boolean z;
        Logger.d("TTMediationSDK_AdnShowControl", "setAdnError adnName = " + str + " slotId = " + str2 + " error = " + str3);
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.b> map = this.f1889a;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.b bVar = map != null ? map.get(str) : null;
        if (bVar != null) {
            for (String str4 : bVar.b()) {
                if (TextUtils.equals(str4, str3)) {
                    Logger.d("TTMediationSDK_AdnShowControl", "setAdnError 命中errorCode = " + str4);
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (z) {
            long time = new Date().getTime() + bVar.a();
            Logger.d("TTMediationSDK_AdnShowControl", "setAdnError 更新时间 = " + f.b(time));
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a(str, str2, time);
            if (this.f1890b != null) {
                ConcurrentHashMap<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a> concurrentHashMap = new ConcurrentHashMap<>();
                concurrentHashMap.put(str2, aVar);
                this.f1890b.put(str, concurrentHashMap);
                Logger.d("TTMediationSDK_AdnShowControl", "setAdnError mAdnControlMap = " + this.f1890b);
            }
        }
    }

    public boolean a(String str, String str2) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a aVar;
        ConcurrentHashMap<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a> concurrentHashMap;
        Logger.d("TTMediationSDK_AdnShowControl", "checkLoad adnName = " + str + " slotId = " + str2);
        Map<String, ConcurrentHashMap<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.a>> map = this.f1890b;
        if (map == null || (concurrentHashMap = map.get(str)) == null) {
            aVar = null;
        } else {
            aVar = concurrentHashMap.get(str2);
            Logger.d("TTMediationSDK_AdnShowControl", "checkLoad 取出的showBean = " + aVar);
        }
        if (aVar == null || aVar.a() == 0) {
            return true;
        }
        long time = new Date().getTime();
        if (time > aVar.a()) {
            return true;
        }
        Logger.d("TTMediationSDK_AdnShowControl", "checkLoad 当前时间 = " + f.b(time));
        Logger.d("TTMediationSDK_AdnShowControl", "checkLoad 规则有效时间 = " + f.b(aVar.a()));
        return false;
    }
}
