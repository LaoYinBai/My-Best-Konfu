package com.bytedance.sdk.account.network.dispatcher;

import android.os.Process;
import com.bytedance.sdk.account.network.dispatcher.IRequest;
import com.bytedance.sdk.account.utility.Logger;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utility.ThreadPlus;
import java.util.concurrent.BlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ApiLocalDispatcher extends Thread {
    private static final String TAG = "ApiLocalDispatcher";
    private final BlockingQueue<IRequest> mLocalQueue;
    private final BlockingQueue<IRequest> mNetworkQueue;
    private volatile boolean mQuit;

    public ApiLocalDispatcher(BlockingQueue<IRequest> blockingQueue, BlockingQueue<IRequest> blockingQueue2) {
        super("ApiLocalDispatcher-Thread");
        this.mQuit = false;
        this.mLocalQueue = blockingQueue;
        this.mNetworkQueue = blockingQueue2;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws InterruptedException, SecurityException, IllegalArgumentException {
        Process.setThreadPriority(10);
        while (true) {
            try {
                IRequest iRequestTake = this.mLocalQueue.take();
                ApiThread apiThread = iRequestTake instanceof ApiThread ? (ApiThread) iRequestTake : null;
                if (apiThread != null) {
                    String name = Thread.currentThread().getName();
                    String name2 = apiThread.getName();
                    try {
                    } catch (Throwable th) {
                        Logger.e(TAG, "Unhandled exception: " + th);
                    }
                    if (!apiThread.isCanceled()) {
                        if (!StringUtils.isEmpty(name2) && !StringUtils.isEmpty(name)) {
                            Thread.currentThread().setName("ApiLocalDispatcher-" + name2);
                        }
                        if (Logger.debug()) {
                            Logger.d(TAG, "run4Local " + name2 + ", queue size: " + this.mLocalQueue.size() + " " + this.mNetworkQueue.size());
                        }
                        if (!apiThread.run4Local()) {
                            if (apiThread.getPriority() == IRequest.Priority.IMMEDIATE) {
                                ThreadPlus.submitRunnable(apiThread);
                            } else {
                                apiThread.sendEnQueueExpireMsg();
                                this.mNetworkQueue.add(apiThread);
                            }
                        }
                        if (!StringUtils.isEmpty(name2) && !StringUtils.isEmpty(name)) {
                            Thread.currentThread().setName(name);
                        }
                    }
                }
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }
}
