package com.bytedance.sdk.account.network.dispatcher;

import android.os.Message;
import java.util.concurrent.BlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DownloadDispatcher extends ApiDispatcher {
    private static final int MSG_SHRINK_DOWNLOAD_DISPATCHER_EXPIRE = 1;

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher
    public /* bridge */ /* synthetic */ void quit() {
        super.quit();
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher, java.lang.Thread, java.lang.Runnable
    public /* bridge */ /* synthetic */ void run() throws InterruptedException, SecurityException, IllegalArgumentException {
        super.run();
    }

    public DownloadDispatcher(BlockingQueue<IRequest> blockingQueue, String str, String str2) {
        super(blockingQueue, str, str2);
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher, com.bytedance.sdk.account.utility.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            if (message.what != 1) {
                return;
            }
            sRequestQueue.handleShrinkDownloadRequestQueueSize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher
    public void cancelEnQueueExpireMsg(ApiThread apiThread) {
        if (apiThread != null) {
            apiThread.cancelEnDownloadQueueExpireMsg();
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher
    public void sendShrinkExpireMsg() {
        cancelShrinkExpireMsg();
        this.mHandler.sendEmptyMessageDelayed(1, 2000L);
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.ApiDispatcher
    public void cancelShrinkExpireMsg() {
        this.mHandler.removeMessages(1);
    }
}
