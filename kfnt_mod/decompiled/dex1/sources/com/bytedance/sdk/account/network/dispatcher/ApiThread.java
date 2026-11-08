package com.bytedance.sdk.account.network.dispatcher;

import android.os.Looper;
import android.os.Message;
import com.bytedance.sdk.account.network.dispatcher.IRequest;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utility.WeakHandler;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class ApiThread implements IRequest, WeakHandler.IHandler, Comparable<IRequest>, Runnable {
    public static final int ENQUEUE_EXPIRE = 1000;
    private static final int MSG_ENQUEUE_EXPIRE = 0;
    private static final int MSG_EN_DOWNLOAD_QUEUE_EXPIRE = 1;
    private static RequestQueue sRequestQueue = RequestQueue.getDefaultRequestQueue();
    private final String mName;
    protected final IRequest.Priority mPriority;
    private int mSequence;
    protected final AtomicBoolean mStarted = new AtomicBoolean(false);
    protected final AtomicBoolean mCanceled = new AtomicBoolean(false);
    private WeakHandler mHandler = new WeakHandler(Looper.getMainLooper(), this);

    public boolean isDownload() {
        return false;
    }

    public boolean needTryLocal() {
        return false;
    }

    public void run() {
    }

    public boolean run4Local() {
        return false;
    }

    public static void setRequestQueue(RequestQueue requestQueue) {
        sRequestQueue = requestQueue;
    }

    protected ApiThread(String str, IRequest.Priority priority) {
        this.mPriority = priority;
        this.mName = StringUtils.isEmpty(str) ? getClass().getSimpleName() : str;
    }

    public void cancel() {
        this.mCanceled.compareAndSet(false, true);
    }

    public boolean isCanceled() {
        return this.mCanceled.get();
    }

    public String getName() {
        return this.mName;
    }

    public final void start() {
        if (this.mStarted.compareAndSet(false, true)) {
            if (sRequestQueue == null) {
                sRequestQueue = RequestQueue.getDefaultRequestQueue();
            }
            if (isDownload()) {
                sRequestQueue.addDownload(this);
            } else {
                sRequestQueue.add(this);
            }
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.IRequest
    public IRequest.Priority getPriority() {
        return this.mPriority;
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.IRequest
    public int getSequence() {
        return this.mSequence;
    }

    public final ApiThread setSequence(int i) {
        this.mSequence = i;
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(IRequest iRequest) {
        IRequest.Priority priority = getPriority();
        IRequest.Priority priority2 = iRequest.getPriority();
        if (priority == null) {
            priority = IRequest.Priority.NORMAL;
        }
        if (priority2 == null) {
            priority2 = IRequest.Priority.NORMAL;
        }
        if (priority == priority2) {
            return getSequence() - iRequest.getSequence();
        }
        return priority2.ordinal() - priority.ordinal();
    }

    @Override // com.bytedance.sdk.account.utility.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            switch (message.what) {
                case 0:
                    sRequestQueue.handleExpandRequestQueueSize();
                    break;
                case 1:
                    sRequestQueue.handleExpandDownloadRequestQueueSize();
                    break;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void sendEnQueueExpireMsg() {
        cancelEnQueueExpireMsg();
        this.mHandler.sendEmptyMessageDelayed(0, 1000L);
    }

    public void cancelEnQueueExpireMsg() {
        this.mHandler.removeMessages(0);
    }

    public void sendEnDownloadQueueExpireMsg() {
        cancelEnDownloadQueueExpireMsg();
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    }

    public void cancelEnDownloadQueueExpireMsg() {
        this.mHandler.removeMessages(1);
    }
}
