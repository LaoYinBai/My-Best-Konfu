package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class AccsSessionManager {

    /* renamed from: a, reason: collision with root package name */
    SessionCenter f358a;

    /* renamed from: b, reason: collision with root package name */
    Set<String> f359b = Collections.EMPTY_SET;

    AccsSessionManager(SessionCenter sessionCenter) {
        this.f358a = null;
        this.f358a = sessionCenter;
    }

    public synchronized void checkAndStartSession() {
        Collection<SessionInfo> collectionA = this.f358a.g.a();
        Set<String> treeSet = Collections.EMPTY_SET;
        if (!collectionA.isEmpty()) {
            treeSet = new TreeSet<>();
        }
        for (SessionInfo sessionInfo : collectionA) {
            if (sessionInfo.isKeepAlive) {
                treeSet.add(StringUtils.concatString(StrategyCenter.getInstance().getSchemeByHost(sessionInfo.host, sessionInfo.isAccs ? "https" : HttpConstant.HTTP), HttpConstant.SCHEME_SPLIT, sessionInfo.host));
            }
        }
        for (String str : this.f359b) {
            if (!treeSet.contains(str)) {
                a(str);
            }
        }
        if (a()) {
            for (String str2 : treeSet) {
                try {
                    this.f358a.get(str2, ConnType.TypeLevel.SPDY, 0L);
                } catch (Exception unused) {
                    ALog.e("start session failed", null, com.alipay.sdk.cons.c.f, str2);
                }
            }
            this.f359b = treeSet;
        }
    }

    public synchronized void forceCloseSession(boolean z) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.AccsSessionManager", "forceCloseSession", this.f358a.f378c, "reCreate", Boolean.valueOf(z));
        }
        Iterator<String> it = this.f359b.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        if (z) {
            checkAndStartSession();
        }
    }

    private boolean a() {
        return !GlobalAppRuntimeInfo.isAppBackground() && NetworkStatusHelper.isConnected();
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ALog.d("awcn.AccsSessionManager", "closeSessions", this.f358a.f378c, com.alipay.sdk.cons.c.f, str);
        this.f358a.a(str).b(false);
    }
}
