package com.bytedance.sdk.account.network.dispatcher;

import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.sdk.account.utility.Logger;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utility.WeakHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ApiDispatcher extends Thread implements WeakHandler.IHandler {
    protected static final int MSG_SHRINK_DISPATCHER_EXPIRE = 0;
    public static final int SHRINK_EXPIRE = 2000;
    private static final String TAG = "ApiDispatcher";
    protected WeakHandler mHandler;
    private volatile boolean mIsRunning;
    private String mLogTag;
    private final BlockingQueue<IRequest> mQueue;
    private volatile boolean mQuit;
    protected static RequestQueue sRequestQueue = RequestQueue.getDefaultRequestQueue();
    protected static final AtomicInteger sCount = new AtomicInteger();

    public ApiDispatcher(BlockingQueue<IRequest> blockingQueue, String str, String str2) {
        super(TextUtils.isEmpty(str) ? "ApiDispatcher-Thread" : str);
        this.mHandler = new WeakHandler(Looper.getMainLooper(), this);
        this.mQuit = false;
        this.mIsRunning = false;
        this.mLogTag = TAG;
        this.mQueue = blockingQueue;
        this.mLogTag = str2;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException, SecurityException, IllegalArgumentException {
        Process.setThreadPriority(10);
        while (true) {
            try {
                IRequest iRequestTake = this.mQueue.take();
                cancelShrinkExpireMsg();
                if (iRequestTake != null && (iRequestTake instanceof ApiThread)) {
                    processLegacyApiThread((ApiThread) iRequestTake);
                }
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    private void processLegacyApiThread(ApiThread apiThread) {
        String name;
        String name2;
        try {
            this.mIsRunning = true;
            cancelEnQueueExpireMsg(apiThread);
        } catch (Throwable th) {
            th = th;
            name = null;
            name2 = null;
        }
        if (apiThread.isCanceled()) {
            this.mIsRunning = false;
            return;
        }
        name = Thread.currentThread().getName();
        try {
            name2 = apiThread.getName();
            try {
                if (!StringUtils.isEmpty(name2) && !StringUtils.isEmpty(name)) {
                    Thread.currentThread().setName(name2);
                }
                Logger.d(this.mLogTag, "thread (inc) count: " + sCount.incrementAndGet());
                apiThread.run();
                sendShrinkExpireMsg();
            } catch (Throwable th2) {
                th = th2;
                Logger.e(this.mLogTag, "Unhandled exception: " + th);
                this.mIsRunning = false;
                if (!StringUtils.isEmpty(name2)) {
                    Thread.currentThread().setName(name);
                }
                Logger.d(this.mLogTag, "thread (dec) count: " + sCount.decrementAndGet());
            }
        } catch (Throwable th3) {
            th = th3;
            name2 = null;
        }
        this.mIsRunning = false;
        if (!StringUtils.isEmpty(name2) && !StringUtils.isEmpty(name)) {
            Thread.currentThread().setName(name);
        }
        Logger.d(this.mLogTag, "thread (dec) count: " + sCount.decrementAndGet());
    }

    @Override // com.bytedance.sdk.account.utility.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            if (message.what != 0) {
                return;
            }
            sRequestQueue.handleShrinkRequestQueueSize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancelEnQueueExpireMsg(ApiThread apiThread) {
        if (apiThread != null) {
            apiThread.cancelEnQueueExpireMsg();
        }
    }

    public void sendShrinkExpireMsg() {
        cancelShrinkExpireMsg();
        this.mHandler.sendEmptyMessageDelayed(0, 2000L);
    }

    public void cancelShrinkExpireMsg() {
        this.mHandler.removeMessages(0);
    }
}
