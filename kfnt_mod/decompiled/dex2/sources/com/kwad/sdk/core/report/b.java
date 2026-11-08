package com.kwad.sdk.core.report;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.core.network.g;
import com.kwad.sdk.core.report.c;
import com.kwad.sdk.core.response.model.BatchReportResult;
import com.kwad.sdk.utils.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b<T extends c, R extends com.kwad.sdk.core.network.g> {
    private static volatile Handler c;
    private static ExecutorService d;
    private Context e;
    private volatile long a = 120000;
    private h b = new j();
    private AtomicInteger f = new AtomicInteger(0);
    private AtomicInteger g = new AtomicInteger(0);
    private int h = 5;

    public class a implements Runnable {
        private final h b;
        private final Context c;

        public a(Context context, h hVar) {
            this.c = context;
            this.b = hVar;
        }

        private void a(@NonNull List<T> list, boolean z) {
            List listA = y.a(list, 200);
            int size = listA.size();
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            for (int i = 0; i < size; i++) {
                b.this.a((List) listA.get(i), atomicBoolean, z);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f.get() > 0 || !com.ksad.download.c.b.a(this.c)) {
                return;
            }
            List<T> listB = this.b.b();
            ArrayList arrayList = new ArrayList();
            if (listB != null && !listB.isEmpty()) {
                Iterator<T> it = listB.iterator();
                while (it.hasNext()) {
                    T next = it.next();
                    if (next instanceof m) {
                        arrayList.add(next);
                        it.remove();
                    }
                }
            }
            if (listB != null && listB.size() > 0) {
                a(listB, false);
            }
            if (arrayList.size() > 0) {
                a(arrayList, true);
            }
        }
    }

    protected b() {
        if (d == null) {
            d = com.kwad.sdk.core.i.b.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(long j) {
        if (c == null) {
            return;
        }
        c.removeMessages(R.attr.childDivider);
        Message messageObtain = Message.obtain(c, new a(this.e, this.b));
        messageObtain.what = R.attr.childDivider;
        c.sendMessageDelayed(messageObtain, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        int i = this.g.get();
        if (i > 16) {
            i = 16;
        }
        return this.b.a() >= ((long) (com.kwad.sdk.core.config.b.j() << i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int andIncrement = this.g.getAndIncrement();
        if (andIncrement <= this.h) {
            if (andIncrement > 0) {
                this.a *= 2;
            }
            b(this.a);
        }
    }

    protected abstract R a(List<T> list, boolean z);

    public void a() {
        b(0L);
    }

    protected void a(long j) {
        this.a = j < 60 ? 60000L : j * 1000;
    }

    public synchronized void a(Context context) {
        this.e = context;
        if (c == null) {
            c = com.kwad.sdk.core.i.a.b();
        }
    }

    protected void a(final T t, final Boolean bool) {
        if (t != null) {
            new com.kwad.sdk.core.network.i<R, BatchReportResult>() { // from class: com.kwad.sdk.core.report.b.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.kwad.sdk.core.network.i
                @NonNull
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public BatchReportResult b(String str) throws Throwable {
                    JSONObject jSONObject = new JSONObject(str);
                    BatchReportResult batchReportResult = new BatchReportResult();
                    batchReportResult.parseJson(jSONObject);
                    return batchReportResult;
                }

                @Override // com.kwad.sdk.core.network.a
                @NonNull
                protected R b() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(t);
                    return (R) b.this.a(arrayList, bool.booleanValue());
                }

                @Override // com.kwad.sdk.core.network.i
                protected boolean c() {
                    return false;
                }
            }.a(new com.kwad.sdk.core.network.j<R, BatchReportResult>() { // from class: com.kwad.sdk.core.report.b.6
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
                public void a(@NonNull R r, int i, String str) {
                    b.this.a((g) new g<T>() { // from class: com.kwad.sdk.core.report.b.6.1
                        @Override // com.kwad.sdk.core.report.g
                        @NonNull
                        public T a() {
                            return (T) t;
                        }
                    });
                }

                @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
                public /* bridge */ /* synthetic */ void a(@NonNull com.kwad.sdk.core.network.g gVar, @NonNull BaseResultData baseResultData) {
                    a((AnonymousClass6) gVar, (BatchReportResult) baseResultData);
                }

                public void a(@NonNull R r, @NonNull BatchReportResult batchReportResult) {
                    com.kwad.sdk.core.d.a.a("BaseBatchReporter", "立即上报 onSuccess action= " + t + " result " + batchReportResult.getResult());
                }
            });
        }
    }

    public void a(@NonNull final g<T> gVar) {
        d.execute(new Runnable() { // from class: com.kwad.sdk.core.report.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (b.c != null && !b.c.hasMessages(R.attr.childDivider)) {
                    b.this.b(b.this.a);
                }
                b.this.b.a((h) gVar.a());
                if (b.this.c()) {
                    b.this.a();
                }
            }
        });
    }

    protected void a(h hVar) {
        this.b = hVar;
    }

    protected void a(final List<T> list, final AtomicBoolean atomicBoolean, final boolean z) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f.getAndIncrement();
        new com.kwad.sdk.core.network.i<R, BatchReportResult>() { // from class: com.kwad.sdk.core.report.b.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public BatchReportResult b(String str) throws Throwable {
                JSONObject jSONObject = new JSONObject(str);
                BatchReportResult batchReportResult = new BatchReportResult();
                batchReportResult.parseJson(jSONObject);
                return batchReportResult;
            }

            @Override // com.kwad.sdk.core.network.a
            @NonNull
            protected R b() {
                return (R) b.this.a(list, z);
            }

            @Override // com.kwad.sdk.core.network.i
            protected boolean c() {
                return false;
            }
        }.a(new com.kwad.sdk.core.network.j<R, BatchReportResult>() { // from class: com.kwad.sdk.core.report.b.3
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull R r, int i, String str) {
                atomicBoolean.set(true);
                if (b.this.f.decrementAndGet() == 0) {
                    b.this.d();
                }
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public /* bridge */ /* synthetic */ void a(@NonNull com.kwad.sdk.core.network.g gVar, @NonNull BaseResultData baseResultData) {
                a((AnonymousClass3) gVar, (BatchReportResult) baseResultData);
            }

            public void a(@NonNull R r, @NonNull BatchReportResult batchReportResult) {
                b.this.b.a(list);
                if (b.this.f.decrementAndGet() == 0 && atomicBoolean.get()) {
                    b.this.d();
                }
                b.this.a(batchReportResult.getInterval());
                b.this.b(b.this.a);
            }
        });
    }

    public void b(@NonNull final g<T> gVar) {
        d.execute(new Runnable() { // from class: com.kwad.sdk.core.report.b.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                b bVar;
                c cVarA = gVar.a();
                if (cVarA instanceof m) {
                    z = true;
                    bVar = b.this;
                } else {
                    z = false;
                    bVar = b.this;
                }
                bVar.a((b) cVarA, Boolean.valueOf(z));
            }
        });
    }
}
