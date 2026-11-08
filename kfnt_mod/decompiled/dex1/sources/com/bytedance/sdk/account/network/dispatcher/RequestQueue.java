package com.bytedance.sdk.account.network.dispatcher;

import com.bytedance.sdk.account.network.dispatcher.IRequest;
import com.bytedance.sdk.account.utility.Logger;
import com.bytedance.sdk.account.utility.ThreadPlus;
import java.lang.Thread;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RequestQueue {
    private static final int DEFAULT_DOWNLOAD_THREAD_POOL_SIZE = 4;
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private static volatile RequestQueue sRequestQueue;
    private ApiDispatcher[] mDispatchers;
    private DownloadDispatcher[] mDownloadDispatchers;
    private final PriorityBlockingQueue<IRequest> mDownloadQueue;
    private int mInitDownloadThreadPoolSize;
    private int mInitThreadPoolSize;
    private volatile long mLastExpandDownloadRequestQueueTime;
    private volatile long mLastExpandRequestQueueTime;
    private volatile long mLastShrinkDownloadRequestQueueTime;
    private volatile long mLastShrinkRequestQueueTime;
    private ApiLocalDispatcher mLocalDispatcher;
    private final PriorityBlockingQueue<IRequest> mLocalQueue;
    private final PriorityBlockingQueue<IRequest> mQueue;
    private volatile boolean mStarted;
    private static AtomicInteger sSequenceGenerator = new AtomicInteger();
    private static volatile boolean sDynamicAdjustThreadPoolSizeOpen = true;

    public static void setDynamicAdjustThreadPoolSizeOpen(boolean z) {
        sDynamicAdjustThreadPoolSizeOpen = z;
    }

    public static RequestQueue getDefaultRequestQueue() {
        if (sRequestQueue == null) {
            synchronized (RequestQueue.class) {
                if (sRequestQueue == null) {
                    sRequestQueue = new RequestQueue();
                }
            }
        }
        return sRequestQueue;
    }

    public static int getSequenceNumber() {
        return sSequenceGenerator.incrementAndGet();
    }

    public RequestQueue() {
        this(4, 4);
    }

    public RequestQueue(int i, int i2) {
        this.mStarted = false;
        this.mLocalQueue = new PriorityBlockingQueue<>();
        this.mQueue = new PriorityBlockingQueue<>();
        this.mDownloadQueue = new PriorityBlockingQueue<>();
        this.mLastExpandRequestQueueTime = 0L;
        this.mLastExpandDownloadRequestQueueTime = 0L;
        this.mLastShrinkRequestQueueTime = 0L;
        this.mLastShrinkDownloadRequestQueueTime = 0L;
        this.mInitThreadPoolSize = i;
        this.mInitDownloadThreadPoolSize = i2;
        this.mDispatchers = new ApiDispatcher[i * 4];
        this.mDownloadDispatchers = new DownloadDispatcher[i2 * 4];
    }

    public synchronized void addDownload(ApiThread apiThread) {
        if (apiThread == null) {
            return;
        }
        apiThread.setSequence(getSequenceNumber());
        if (!this.mStarted) {
            start();
        }
        if (apiThread.getPriority() == IRequest.Priority.IMMEDIATE) {
            ThreadPlus.submitRunnable(apiThread);
        } else {
            apiThread.sendEnDownloadQueueExpireMsg();
            this.mDownloadQueue.add(apiThread);
        }
    }

    public synchronized void add(ApiThread apiThread) {
        if (apiThread == null) {
            return;
        }
        apiThread.setSequence(getSequenceNumber());
        if (!this.mStarted) {
            start();
        }
        if (apiThread.needTryLocal()) {
            this.mLocalQueue.add(apiThread);
        } else if (apiThread.getPriority() == IRequest.Priority.IMMEDIATE) {
            ThreadPlus.submitRunnable(apiThread);
        } else {
            apiThread.sendEnQueueExpireMsg();
            this.mQueue.add(apiThread);
        }
    }

    public synchronized void start() {
        stop();
        this.mLocalDispatcher = new ApiLocalDispatcher(this.mLocalQueue, this.mQueue);
        this.mLocalDispatcher.start();
        for (int i = 0; i < this.mInitThreadPoolSize; i++) {
            ApiDispatcher apiDispatcher = new ApiDispatcher(this.mQueue, "ApiDispatcher-Thread", "ApiDispatcher");
            this.mDispatchers[i] = apiDispatcher;
            apiDispatcher.start();
        }
        for (int i2 = 0; i2 < this.mInitDownloadThreadPoolSize; i2++) {
            DownloadDispatcher downloadDispatcher = new DownloadDispatcher(this.mDownloadQueue, "DownloadDispatcher-Thread", "DownloadDispatcher");
            this.mDownloadDispatchers[i2] = downloadDispatcher;
            downloadDispatcher.start();
        }
        this.mStarted = true;
    }

    public synchronized void stop() {
        this.mStarted = false;
        if (this.mLocalDispatcher != null) {
            this.mLocalDispatcher.quit();
        }
        for (int i = 0; i < this.mDispatchers.length; i++) {
            if (this.mDispatchers[i] != null) {
                this.mDispatchers[i].quit();
                this.mDispatchers[i] = null;
            }
        }
        for (int i2 = 0; i2 < this.mDownloadDispatchers.length; i2++) {
            if (this.mDownloadDispatchers[i2] != null) {
                this.mDownloadDispatchers[i2].quit();
                this.mDownloadDispatchers[i2] = null;
            }
        }
    }

    public synchronized void handleExpandRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (sDynamicAdjustThreadPoolSizeOpen) {
            Logger.d("RequestQueue", "handleExpandRequestQueueSize");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.mLastExpandRequestQueueTime > jCurrentTimeMillis) {
                this.mLastExpandRequestQueueTime = jCurrentTimeMillis;
            }
            if (jCurrentTimeMillis - this.mLastExpandRequestQueueTime <= 1000) {
                Logger.d("RequestQueue", "handleExpandRequestQueueSize (now - mLastExpandRequestQueueTime) <= ApiThread.ENQUEUE_EXPIRE");
                return;
            }
            this.mLastExpandRequestQueueTime = jCurrentTimeMillis;
            int i = 0;
            for (int i2 = 0; i2 < this.mDispatchers.length; i2++) {
                if (this.mDispatchers[i2] == null) {
                    i++;
                    if (i > this.mInitThreadPoolSize) {
                        break;
                    }
                    ApiDispatcher apiDispatcher = new ApiDispatcher(this.mQueue, "ApiDispatcher-Thread", "ApiDispatcher");
                    Logger.d("RequestQueue", "apiDispatcher : " + apiDispatcher.toString() + " create");
                    this.mDispatchers[i2] = apiDispatcher;
                    apiDispatcher.start();
                }
            }
        }
    }

    public synchronized void handleExpandDownloadRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (sDynamicAdjustThreadPoolSizeOpen) {
            Logger.d("RequestQueue", "handleExpandDownloadRequestQueueSize");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.mLastExpandDownloadRequestQueueTime > jCurrentTimeMillis) {
                this.mLastExpandDownloadRequestQueueTime = jCurrentTimeMillis;
            }
            if (jCurrentTimeMillis - this.mLastExpandDownloadRequestQueueTime <= 1000) {
                Logger.d("RequestQueue", "handleExpandDownloadRequestQueueSize (now - mLastExpandDownloadRequestQueueTime) <= ApiThread.ENQUEUE_EXPIRE");
                return;
            }
            this.mLastExpandDownloadRequestQueueTime = jCurrentTimeMillis;
            int i = 0;
            for (int i2 = 0; i2 < this.mDownloadDispatchers.length; i2++) {
                if (this.mDownloadDispatchers[i2] == null) {
                    i++;
                    if (i > this.mInitDownloadThreadPoolSize) {
                        break;
                    }
                    DownloadDispatcher downloadDispatcher = new DownloadDispatcher(this.mDownloadQueue, "DownloadDispatcher-Thread", "DownloadDispatcher");
                    Logger.d("RequestQueue", "downloadDispatcher : " + downloadDispatcher.toString() + " create");
                    this.mDownloadDispatchers[i2] = downloadDispatcher;
                    downloadDispatcher.start();
                }
            }
        }
    }

    public synchronized void handleShrinkRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (sDynamicAdjustThreadPoolSizeOpen) {
            Logger.d("RequestQueue", "handleShrinkRequestQueueSize");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.mLastShrinkRequestQueueTime > jCurrentTimeMillis) {
                this.mLastShrinkRequestQueueTime = jCurrentTimeMillis;
            }
            if (jCurrentTimeMillis - this.mLastShrinkRequestQueueTime <= 2000) {
                Logger.d("RequestQueue", "handleShrinkRequestQueueSize (now - mLastShrinkRequestQueueTime) <= ApiDispatcher.SHRINK_EXPIRE");
                return;
            }
            boolean z = true;
            boolean z2 = true;
            for (int length = this.mDispatchers.length - 1; length >= this.mInitThreadPoolSize; length--) {
                ApiDispatcher apiDispatcher = this.mDispatchers[length];
                if (apiDispatcher != null && apiDispatcher.isRunning()) {
                    z = false;
                }
                if (apiDispatcher != null) {
                    z2 = false;
                }
            }
            this.mLastShrinkRequestQueueTime = jCurrentTimeMillis;
            if (z && !z2) {
                for (int length2 = this.mDispatchers.length - 1; length2 >= this.mInitThreadPoolSize; length2--) {
                    try {
                        ApiDispatcher apiDispatcher2 = this.mDispatchers[length2];
                        if (apiDispatcher2 != null && apiDispatcher2.getState() != Thread.State.RUNNABLE && !apiDispatcher2.isRunning()) {
                            Logger.d("RequestQueue", "apiDispatcher : " + apiDispatcher2.toString() + " quit");
                            apiDispatcher2.quit();
                            this.mDispatchers[length2] = null;
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                return;
            }
            Logger.d("RequestQueue", "handleShrinkRequestQueueSize shouldShrink = " + z + " allNull = " + z2);
        }
    }

    public synchronized void handleShrinkDownloadRequestQueueSize() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (sDynamicAdjustThreadPoolSizeOpen) {
            Logger.d("RequestQueue", "handleShrinkDownloadRequestQueueSize");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.mLastShrinkDownloadRequestQueueTime > jCurrentTimeMillis) {
                this.mLastShrinkDownloadRequestQueueTime = jCurrentTimeMillis;
            }
            if (jCurrentTimeMillis - this.mLastShrinkDownloadRequestQueueTime <= 2000) {
                Logger.d("RequestQueue", "handleShrinkDownloadRequestQueueSize (now - mLastShrinkDownloadRequestQueueTime) <= ApiDispatcher.SHRINK_EXPIRE");
                return;
            }
            boolean z = true;
            boolean z2 = true;
            for (int length = this.mDownloadDispatchers.length - 1; length >= this.mInitDownloadThreadPoolSize; length--) {
                DownloadDispatcher downloadDispatcher = this.mDownloadDispatchers[length];
                if (downloadDispatcher != null && downloadDispatcher.isRunning()) {
                    z = false;
                }
                if (downloadDispatcher != null) {
                    z2 = false;
                }
            }
            this.mLastShrinkDownloadRequestQueueTime = jCurrentTimeMillis;
            if (z && !z2) {
                for (int length2 = this.mDownloadDispatchers.length - 1; length2 >= this.mInitDownloadThreadPoolSize; length2--) {
                    try {
                        DownloadDispatcher downloadDispatcher2 = this.mDownloadDispatchers[length2];
                        if (downloadDispatcher2 != null && downloadDispatcher2.getState() != Thread.State.RUNNABLE && !downloadDispatcher2.isRunning()) {
                            Logger.d("RequestQueue", "apiDispatcher : " + downloadDispatcher2.toString() + " quit");
                            downloadDispatcher2.quit();
                            this.mDownloadDispatchers[length2] = null;
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                return;
            }
            Logger.d("RequestQueue", "handleShrinkDownloadRequestQueueSize shouldShrink " + z + " allNull = " + z2);
        }
    }
}
