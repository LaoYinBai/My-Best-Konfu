package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.j;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.o;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e<T extends i> extends HandlerThread implements Handler.Callback {
    public static String l = "ttad_bk";

    /* renamed from: a, reason: collision with root package name */
    public String f1955a;

    /* renamed from: b, reason: collision with root package name */
    public final bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a<T> f1956b;

    /* renamed from: c, reason: collision with root package name */
    public bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<T> f1957c;
    public final List<T> d;
    public long e;
    public boolean f;
    public int g;
    public Handler h;
    public final a i;
    public final b j;
    private boolean k;

    public interface a {
        boolean a();
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        final int f1958a;

        /* renamed from: b, reason: collision with root package name */
        final long f1959b;

        /* renamed from: c, reason: collision with root package name */
        final long f1960c;
        final int d;
        final long e;
        final long f;

        b(int i, long j, long j2, int i2, long j3, long j4) {
            this.f1958a = i;
            this.f1959b = j;
            this.f1960c = j2;
            this.d = i2;
            this.e = j3;
            this.f = j4;
        }

        public static b b() {
            Logger.d("TTMediationSDK", "AdEventThread- policy: batchSize: " + bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().j() + ", routineInterval: " + bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().m());
            return new b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().j(), bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().m(), 60000L, 5, 604800000L, 300000L);
        }

        public int a() {
            if (j.f2014a) {
                return 1;
            }
            return this.f1958a;
        }
    }

    public e(String str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a<T> aVar, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<T> aVar2, b bVar, a aVar3) {
        super(l, -4);
        this.f1955a = "AdEventThread-【";
        this.f1955a += str + "】";
        this.k = "V3".equals(str);
        a("tag=" + str);
        this.j = bVar;
        this.i = aVar3;
        this.f1956b = aVar;
        this.f1957c = aVar2;
        this.d = Collections.synchronizedList(new LinkedList());
    }

    private void a(List<T> list) {
        int iA = this.j.a();
        if (list == null) {
            return;
        }
        if (list.size() <= iA) {
            a("start and return, checkAndDeleteEvent local size:" + list.size() + "小于:" + iA);
            return;
        }
        int size = list.size() - iA;
        a("start checkAndDeleteEvent local size,deleteCnt:" + list.size() + IWeiboService.Scope.EMPTY_SCOPE + size);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i));
        }
        list.removeAll(arrayList);
        a("end checkAndDeleteEvent local size:" + list.size());
    }

    private void b(List<T> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    HashSet hashSet = new HashSet();
                    Iterator<T> it = this.d.iterator();
                    while (it.hasNext()) {
                        hashSet.add(it.next().a());
                    }
                    for (T t : list) {
                        if (!hashSet.contains(t.a())) {
                            this.d.add(t);
                        }
                    }
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
        a("reloadCacheList adEventList is empty======");
    }

    private f c(List<T> list) {
        try {
            if (this.f1957c == null) {
                this.f1957c = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.e();
            }
        } catch (Exception unused) {
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_if122.a<T> aVar = this.f1957c;
        if (aVar == null) {
            return null;
        }
        return aVar.a(list);
    }

    private void d() {
        a(4, g());
    }

    private void e() {
        a("普通失败 ，触发重试机制：" + this.j.f1960c + "毫秒后 重试.....");
        a(3, this.j.f1960c);
    }

    private void f() {
        this.h.removeMessages(3);
        this.h.removeMessages(2);
        if (o.a(this.d)) {
            this.e = System.currentTimeMillis();
            c();
            return;
        }
        if (!this.i.a()) {
            a("doRoutineUpload no net, wait retry");
            e();
            return;
        }
        f fVarC = c(this.d);
        if (fVarC != null) {
            if (fVarC.f1961a) {
                a("doRoutineUpload success: " + this.d.size() + " events");
                a();
                o();
                p();
                return;
            }
            if (b(fVarC)) {
                a("doRoutineUpload serverbusy");
                i();
            } else if (a(fVarC)) {
                a("服务端返回data error 抛弃数据 , 清空本次日志，重置上传状态....");
                a();
                o();
            } else {
                if (this.f) {
                    return;
                }
                e();
                a("doRoutineUpload net fail retry");
            }
        }
    }

    private long g() {
        a("服务器繁忙，" + (((this.g % 3) + 1) * this.j.f) + "毫秒后进行重试,当前重试次数：mServerBusyRetryCount=" + this.g + "，mServerBusyRetryBaseInternal=" + this.j.f);
        return ((this.g % 3) + 1) * this.j.f;
    }

    private int h() {
        return this.k ? 2 : 1;
    }

    private void i() {
        this.f = true;
        this.f1956b.a(h(), true);
        this.d.clear();
        this.h.removeMessages(3);
        this.h.removeMessages(2);
        d();
    }

    private boolean j() {
        a("mCacheList.size():" + this.d.size() + ",mPolicy.mMaxCacheCount=" + this.j.a() + ",System.currentTimeMillis() - mLastSuccessUploadTime =" + (System.currentTimeMillis() - this.e) + ",mPolicy.mMaxCacheTime=" + this.j.f1959b);
        return !this.f && (this.d.size() >= this.j.a() || System.currentTimeMillis() - this.e >= this.j.f1959b);
    }

    private void k() {
        a("onHandleInitEvent 初始化日志组件.....线程优先级：" + Process.getThreadPriority(0));
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a<T> aVar = this.f1956b;
        b bVar = this.j;
        aVar.a(2, bVar.d, bVar.e);
        this.f = this.f1956b.a(h());
        this.g = this.f1956b.b(h());
        if (this.f) {
            a("onHandleInitEvent serverBusy, retryCount = " + this.g);
            d();
            return;
        }
        List<T> listA = this.f1956b.a(h(), this.j.a(), "_id");
        if (Logger.isDebug()) {
            for (T t : listA) {
            }
        }
        b(listA);
        a("onHandleInitEvent cacheData count = " + this.d.size());
        f();
    }

    private void l() {
        if (!this.f) {
            a("onHandleRoutineRetryEvent");
            f();
        } else {
            a("如果在容灾状态，直接返回, 安全起见这里判断一下,mIsServerBusy=" + this.f);
        }
    }

    private void m() {
        if (!this.f) {
            a("onHandleRoutineUploadEvent");
            f();
        } else {
            a("如果在容灾状态，直接返回, 安全起见这里判断一下,mIsServerBusy=" + this.f);
        }
    }

    private void n() {
        if (!this.i.a()) {
            a(4, this.j.f1960c);
            a("onHandleServerBusyRetryEvent, no net");
            return;
        }
        List<T> listA = this.f1956b.a(h(), this.j.a(), "_id");
        a(listA);
        StringBuilder sb = new StringBuilder();
        sb.append("数据库查询出来的size=");
        sb.append(listA != null ? listA.size() : 0);
        a(sb.toString());
        if (o.a(listA)) {
            a("onHandleServerBusyRetryEvent, empty list start routine");
            b();
            c();
            return;
        }
        f fVarC = c(listA);
        if (fVarC != null) {
            if (fVarC.f1961a) {
                a("onHandleServerBusyRetryEvent, success");
                a();
                o();
                p();
                return;
            }
            if (!b(fVarC)) {
                if (!a(fVarC)) {
                    e();
                    a("onHandleServerBusyRetryEvent, net fail");
                    return;
                } else {
                    a("onHandleServerBusyRetryEvent, ---》 data Error直接抛弃数据 ");
                    a();
                    o();
                    return;
                }
            }
            this.g++;
            this.f1956b.a(h(), this.g);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.bykvm_19do.a<T> aVar = this.f1956b;
            int iH = h();
            b bVar = this.j;
            aVar.a(iH, listA, bVar.d, bVar.e);
            d();
            a("onHandleServerBusyRetryEvent, serverbusy, count = " + this.g);
        }
    }

    private void o() {
        this.e = System.currentTimeMillis();
        b();
        c();
    }

    private void p() {
        int iA = this.j.a() / 2;
        if (iA < 1) {
            iA = 1;
        }
        List<T> listA = this.f1956b.a(h(), iA, "_id");
        if (listA == null || listA.size() <= 0) {
            a("数据库日志数量: 0, 小于batchSize/2（" + iA + "），停止上传，等待新产生的事件");
            return;
        }
        this.d.addAll(listA);
        if (listA.size() < iA) {
            a("数据库日志数量: " + this.d.size() + ", 小于batchSize/2（" + iA + "），停止上传，等待新产生的事件");
            return;
        }
        this.h.removeMessages(2);
        this.h.sendEmptyMessage(2);
        a("数据库日志数量: " + this.d.size() + ", 大于等于batchSize/2（" + iA + "），继续上传.");
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                a((e<T>) message.obj);
                break;
            case 2:
                m();
                break;
            case 3:
                a("routine触发失败重试.....");
                l();
                break;
            case 4:
                a("触发服务器繁忙重试机制.....");
                n();
                break;
            case 5:
                k();
                break;
        }
        return true;
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        this.e = System.currentTimeMillis();
        this.h = new Handler(getLooper(), this);
    }

    private void c() {
        a(2, this.j.f1959b);
    }

    private static boolean b(f fVar) {
        return fVar.f1962b == 509;
    }

    private void b() {
        this.f = false;
        this.f1956b.a(h(), false);
        this.g = 0;
        this.f1956b.a(h(), 0);
        this.h.removeMessages(4);
    }

    private void a(T t) {
        this.f1956b.a(h(), (int) t);
        if (this.f) {
            a("如果在容灾状态，直接返回.......mIsServerBusy=" + this.f);
            return;
        }
        a("onHandleReceivedAdEvent");
        this.d.add(t);
        a(this.d);
        if (j()) {
            a("onHandleReceivedAdEvent upload");
            f();
        }
    }

    private void a() {
        this.f1956b.a(h(), this.d);
        this.d.clear();
    }

    private void a(int i, long j) {
        Message messageObtainMessage = this.h.obtainMessage();
        messageObtainMessage.what = i;
        this.h.sendMessageDelayed(messageObtainMessage, j);
    }

    private static boolean a(f fVar) {
        return fVar.d;
    }

    private void a(String str) {
        Logger.i(this.f1955a, str);
    }
}
