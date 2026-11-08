package com.qq.e.comm.net;

import com.qq.e.comm.net.NetworkClient;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.GDTLogger;
import com.umeng.message.MsgConstant;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkClientImpl implements NetworkClient {
    private static final NetworkClient c = new NetworkClientImpl();
    private PriorityBlockingQueue<Runnable> b = new PriorityBlockingQueue<>(15);
    private final ExecutorService a = new ThreadPoolExecutor(5, 10, 180, TimeUnit.SECONDS, this.b);

    /* renamed from: com.qq.e.comm.net.NetworkClientImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Request.Method.values().length];
            a = iArr;
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class NetFutureTask<T> extends FutureTask<T> implements Comparable<NetFutureTask<T>> {
        private final NetworkClient.Priority a;

        public NetFutureTask(Callable<T> callable, NetworkClient.Priority priority) {
            super(callable);
            this.a = priority;
        }

        @Override // java.lang.Comparable
        public int compareTo(NetFutureTask<T> netFutureTask) {
            if (netFutureTask == null) {
                return 1;
            }
            return this.a.value() - netFutureTask.a.value();
        }

        public boolean equals(Object obj) {
            return obj != null && obj.getClass() == NetFutureTask.class && compareTo((NetFutureTask) obj) == 0;
        }

        public int hashCode() {
            return this.a.value();
        }
    }

    private static class TaskCallable implements Callable<Response> {
        final Request a;
        final NetworkCallBack b;

        public TaskCallable(Request request) {
            this(request, null);
        }

        public TaskCallable(Request request, NetworkCallBack networkCallBack) {
            this.a = request;
            this.b = networkCallBack;
        }

        private Response a() throws Exception {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.a.getUrlWithParas()).openConnection();
            for (Map.Entry<String, String> entry : this.a.getHeaders().entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection.setRequestProperty("User-Agent", "GDTADNetClient-[" + System.getProperty("http.agent") + "]");
            if (this.a.getConnectionTimeOut() > 0) {
                httpURLConnection.setConnectTimeout(this.a.getConnectionTimeOut());
            } else {
                httpURLConnection.setConnectTimeout(30000);
            }
            if (this.a.getSocketTimeOut() > 0) {
                httpURLConnection.setReadTimeout(this.a.getSocketTimeOut());
            } else {
                httpURLConnection.setReadTimeout(30000);
            }
            if (this.a.getMethod().ordinal() == 1) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);
                byte[] postData = this.a.getPostData();
                if (postData != null && postData.length > 0) {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                    try {
                        bufferedOutputStream.write(postData);
                        bufferedOutputStream.flush();
                    } finally {
                        bufferedOutputStream.close();
                    }
                }
            }
            return this.a.initResponse(NetworkClientImpl.followRedirect(httpURLConnection));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.qq.e.comm.net.rr.Response call() throws java.lang.Exception {
            /*
                r4 = this;
                r0 = 0
                com.qq.e.comm.net.NetworkCallBack r1 = r4.b     // Catch: java.lang.Exception -> L8
                com.qq.e.comm.net.rr.Response r1 = r4.a()     // Catch: java.lang.Exception -> L8
                goto Lc
            L8:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
            Lc:
                if (r0 != 0) goto L20
                com.qq.e.comm.net.NetworkCallBack r0 = r4.b
                if (r0 == 0) goto L17
                com.qq.e.comm.net.rr.Request r2 = r4.a
                r0.onResponse(r2, r1)
            L17:
                com.qq.e.comm.net.rr.Request r0 = r4.a
                boolean r0 = r0.isAutoClose()
                if (r0 == 0) goto L33
                goto L30
            L20:
                com.qq.e.comm.net.NetworkCallBack r2 = r4.b
                if (r2 == 0) goto L34
                java.lang.String r2 = "网络异常"
                com.qq.e.comm.util.GDTLogger.w(r2, r0)
                com.qq.e.comm.net.NetworkCallBack r2 = r4.b
                r2.onException(r0)
                if (r1 == 0) goto L33
            L30:
                r1.close()
            L33:
                return r1
            L34:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.net.NetworkClientImpl.TaskCallable.call():com.qq.e.comm.net.rr.Response");
        }
    }

    private NetworkClientImpl() {
    }

    public static HttpURLConnection followRedirect(HttpURLConnection httpURLConnection) throws IOException {
        String requestProperty = null;
        if (httpURLConnection == null) {
            return null;
        }
        int i = 0;
        int connectTimeout = httpURLConnection.getConnectTimeout();
        int readTimeout = httpURLConnection.getReadTimeout();
        try {
            requestProperty = httpURLConnection.getRequestProperty("User-Agent");
        } catch (Exception unused) {
        }
        while (i < 3) {
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 302 && responseCode != 301 && responseCode != 303) {
                break;
            }
            String headerField = httpURLConnection.getHeaderField(MsgConstant.KEY_LOCATION_PARAMS);
            httpURLConnection.disconnect();
            httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
            httpURLConnection.setConnectTimeout(connectTimeout);
            httpURLConnection.setReadTimeout(readTimeout);
            if (requestProperty != null) {
                httpURLConnection.setRequestProperty("User-Agent", requestProperty);
            }
            i++;
        }
        if (i != 3) {
            return httpURLConnection;
        }
        throw new IOException("HttpURLConnection exceed max redirect 3 " + httpURLConnection.getURL());
    }

    public static NetworkClient getInstance() {
        return c;
    }

    @Override // com.qq.e.comm.net.NetworkClient
    public Future<Response> submit(Request request) {
        return submit(request, NetworkClient.Priority.Mid);
    }

    @Override // com.qq.e.comm.net.NetworkClient
    public Future<Response> submit(Request request, NetworkClient.Priority priority) {
        NetFutureTask netFutureTask = new NetFutureTask(new TaskCallable(request), priority);
        this.a.execute(netFutureTask);
        GDTLogger.d("QueueSize:" + this.b.size());
        return netFutureTask;
    }

    @Override // com.qq.e.comm.net.NetworkClient
    public void submit(Request request, NetworkCallBack networkCallBack) {
        submit(request, NetworkClient.Priority.Mid, networkCallBack);
    }

    @Override // com.qq.e.comm.net.NetworkClient
    public void submit(Request request, NetworkClient.Priority priority, NetworkCallBack networkCallBack) {
        submit(request, priority, networkCallBack, this.a);
    }

    @Override // com.qq.e.comm.net.NetworkClient
    public void submit(Request request, NetworkClient.Priority priority, NetworkCallBack networkCallBack, Executor executor) {
        String str;
        if (executor == null) {
            str = "Submit failed for no executor";
        } else {
            executor.execute(new NetFutureTask(new TaskCallable(request, networkCallBack), priority));
            str = "QueueSize:" + this.b.size();
        }
        GDTLogger.d(str);
    }
}
