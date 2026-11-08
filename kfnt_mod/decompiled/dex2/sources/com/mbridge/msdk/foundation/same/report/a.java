package com.mbridge.msdk.foundation.same.report;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.mbridge.msdk.foundation.db.BatchReportDao;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;

/* compiled from: MBBatchReportManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static final String a = "a";
    private static volatile a b;
    private Handler c;
    private boolean d;
    private int e;
    private long f;
    private String g = com.mbridge.msdk.foundation.same.net.g.d.c().a;
    private BatchReportDao h;
    private AtomicInteger i;
    private Stack<Long> j;

    private a() {
        this.d = false;
        this.e = 30;
        this.f = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            this.e = aVarB.ag();
            this.f = aVarB.ah() * 1000;
            this.d = aVarB.ai() == 1;
        }
        s.a(a, "初始化批量上报： " + this.g + " " + this.e + " " + this.f + " " + this.d);
        if (this.d) {
            this.i = new AtomicInteger(0);
            this.j = new Stack<>();
            this.h = BatchReportDao.getInstance(com.mbridge.msdk.foundation.controller.a.f().j());
            HandlerThread handlerThread = new HandlerThread("mb_batch_report_thread");
            handlerThread.start();
            this.c = new Handler(handlerThread.getLooper()) { // from class: com.mbridge.msdk.foundation.same.report.a.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) throws Throwable {
                    switch (message.what) {
                        case 1:
                            Bundle data = message.getData();
                            if (a.this.c != null && a.this.h != null && data != null) {
                                a.this.c.removeMessages(2);
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                ArrayList<BatchReportMessage> batchReportMessages = a.this.h.getBatchReportMessages(jCurrentTimeMillis);
                                a.a(a.this, batchReportMessages, jCurrentTimeMillis);
                                a.this.i.set(0);
                                String str = a.a;
                                StringBuilder sb = new StringBuilder();
                                sb.append("超时上报触发了，需要上报的数据： ");
                                sb.append(batchReportMessages != null ? batchReportMessages.size() : 0);
                                s.a(str, sb.toString());
                                break;
                            }
                            break;
                        case 2:
                            Bundle data2 = message.getData();
                            if (a.this.c != null && a.this.h != null && data2 != null) {
                                a.this.c.removeMessages(1);
                                long j = data2.getLong("last_report_time");
                                ArrayList<BatchReportMessage> batchReportMessages2 = a.this.h.getBatchReportMessages(j);
                                a.a(a.this, batchReportMessages2, j);
                                String str2 = a.a;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("队列上报触发了，需要上报的数据： ");
                                sb2.append(batchReportMessages2 != null ? batchReportMessages2.size() : 0);
                                s.a(str2, sb2.toString());
                                break;
                            }
                            break;
                        case 3:
                            Bundle data3 = message.getData();
                            if (data3 != null) {
                                a.b(a.this, data3.getParcelableArrayList("report_message"), data3.getLong("last_report_time"));
                                break;
                            }
                            break;
                    }
                }
            };
        }
    }

    public static synchronized a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    public final void b() {
        if (!this.d || this.c == null || this.c.hasMessages(1)) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        this.c.sendMessageDelayed(messageObtain, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    public final synchronized boolean c() {
        return this.d;
    }

    public final synchronized void a(String str) {
        if (this.h != null && this.d) {
            s.a(a, "接收到上报数据： " + str);
            this.h.addReportMessage(str);
            if (this.c != null && this.f > 0 && !this.c.hasMessages(1)) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                this.c.sendMessageDelayed(messageObtain, this.f);
            }
            if (this.i.incrementAndGet() >= this.e && this.c != null) {
                Message messageObtain2 = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putLong("last_report_time", System.currentTimeMillis());
                messageObtain2.setData(bundle);
                messageObtain2.what = 2;
                this.c.sendMessage(messageObtain2);
                this.i.set(0);
            }
        }
    }

    static /* synthetic */ void a(a aVar, ArrayList arrayList, long j) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Message messageObtain = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("report_message", arrayList);
        bundle.putLong("last_report_time", j);
        messageObtain.setData(bundle);
        messageObtain.what = 3;
        aVar.c.sendMessage(messageObtain);
    }

    static /* synthetic */ void b(a aVar, final ArrayList arrayList, final long j) throws JSONException, UnsupportedEncodingException {
        if (arrayList != null && arrayList.size() > 0) {
            s.a(a, "需要上报的数据条数： " + arrayList.size());
            Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
            if (contextJ == null) {
                return;
            }
            com.mbridge.msdk.foundation.same.net.h.d dVarA = d.a(contextJ);
            dVarA.a("app_id", com.mbridge.msdk.foundation.controller.a.f().k());
            dVarA.a("m_sdk", "msdk");
            dVarA.a("lqswt", String.valueOf(1));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                BatchReportMessage batchReportMessage = (BatchReportMessage) arrayList.get(i);
                if (batchReportMessage != null) {
                    sb.append(batchReportMessage.getReportMessage() + "&ts=" + batchReportMessage.getTimestamp());
                    if (i >= 0 && i < arrayList.size() - 1) {
                        sb.append("\n");
                    }
                }
            }
            try {
                String strEncode = URLEncoder.encode(sb.toString(), "utf-8");
                dVarA.a("data", strEncode);
                s.a(a, "需要批量上报的数据： " + strEncode);
                new com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j()).c(0, aVar.g, dVarA, new com.mbridge.msdk.foundation.same.report.d.b() { // from class: com.mbridge.msdk.foundation.same.report.a.2
                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void a(String str) {
                        s.a(a.a, "批量上报成功");
                        try {
                            if (a.this.h != null) {
                                a.this.h.deleteBatchReportMessagesByTimestamp(j);
                            }
                        } catch (Exception e) {
                            s.d(a.a, e.getMessage());
                        }
                        synchronized (a.this.j) {
                            a.this.j.clear();
                        }
                    }

                    @Override // com.mbridge.msdk.foundation.same.report.d.b
                    public final void b(String str) {
                        Stack stack;
                        s.a(a.a, "批量上报失败： " + str);
                        synchronized (a.this.j) {
                            a.this.j.add(Long.valueOf(j));
                            if (a.this.h != null) {
                                a.this.h.updateMessagesReportState(arrayList);
                            }
                            if (a.this.j.size() >= 5) {
                                s.a(a.a, "批量上报失败，上报失败的数据超过阈值");
                                try {
                                    try {
                                        a.this.j.pop();
                                        long jLongValue = ((Long) a.this.j.pop()).longValue();
                                        a.this.j.clear();
                                        if (a.this.h != null) {
                                            a.this.h.deleteBatchReportMessagesByTimestamp(jLongValue);
                                        }
                                        stack = a.this.j;
                                    } catch (Exception e) {
                                        s.d(a.a, e.getMessage());
                                        stack = a.this.j;
                                    }
                                    stack.clear();
                                } catch (Throwable th) {
                                    a.this.j.clear();
                                    throw th;
                                }
                            }
                        }
                    }
                });
                return;
            } catch (Exception e) {
                s.d(a, e.getMessage());
                return;
            }
        }
        s.a(a, "需要上报的数据条数： 0");
    }
}
