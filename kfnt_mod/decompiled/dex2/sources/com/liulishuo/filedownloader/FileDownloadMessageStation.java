package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class FileDownloadMessageStation {
    public static final int DEFAULT_INTERVAL = 10;
    public static final int DEFAULT_SUB_PACKAGE_SIZE = 5;
    static final int DISPOSE_MESSENGER_LIST = 2;
    static final int HANDOVER_A_MESSENGER = 1;
    static int INTERVAL = 10;
    static int SUB_PACKAGE_SIZE = 5;
    private final Executor blockCompletedPool;
    private final ArrayList<IFileDownloadMessenger> disposingList;
    private final Handler handler;
    private final Object queueLock;
    private final LinkedBlockingQueue<IFileDownloadMessenger> waitingQueue;

    private static final class HolderClass {
        private static final FileDownloadMessageStation INSTANCE = new FileDownloadMessageStation();

        private HolderClass() {
        }
    }

    public static FileDownloadMessageStation getImpl() {
        return HolderClass.INSTANCE;
    }

    private FileDownloadMessageStation() {
        this.blockCompletedPool = FileDownloadExecutors.newDefaultThreadPool(5, "BlockCompleted");
        this.queueLock = new Object();
        this.disposingList = new ArrayList<>();
        this.handler = new Handler(Looper.getMainLooper(), new UIHandlerCallback());
        this.waitingQueue = new LinkedBlockingQueue<>();
    }

    void requestEnqueue(IFileDownloadMessenger iFileDownloadMessenger) {
        requestEnqueue(iFileDownloadMessenger, false);
    }

    void requestEnqueue(final IFileDownloadMessenger iFileDownloadMessenger, boolean z) {
        if (iFileDownloadMessenger.handoverDirectly()) {
            iFileDownloadMessenger.handoverMessage();
            return;
        }
        if (iFileDownloadMessenger.isBlockingCompleted()) {
            this.blockCompletedPool.execute(new Runnable() { // from class: com.liulishuo.filedownloader.FileDownloadMessageStation.1
                @Override // java.lang.Runnable
                public void run() {
                    iFileDownloadMessenger.handoverMessage();
                }
            });
            return;
        }
        if (!isIntervalValid() && !this.waitingQueue.isEmpty()) {
            synchronized (this.queueLock) {
                if (!this.waitingQueue.isEmpty()) {
                    Iterator<IFileDownloadMessenger> it = this.waitingQueue.iterator();
                    while (it.hasNext()) {
                        handoverInUIThread(it.next());
                    }
                }
                this.waitingQueue.clear();
            }
        }
        if (!isIntervalValid() || z) {
            handoverInUIThread(iFileDownloadMessenger);
        } else {
            enqueue(iFileDownloadMessenger);
        }
    }

    private void handoverInUIThread(IFileDownloadMessenger iFileDownloadMessenger) {
        this.handler.sendMessage(this.handler.obtainMessage(1, iFileDownloadMessenger));
    }

    private void enqueue(IFileDownloadMessenger iFileDownloadMessenger) {
        synchronized (this.queueLock) {
            this.waitingQueue.offer(iFileDownloadMessenger);
        }
        push();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void push() {
        int i;
        synchronized (this.queueLock) {
            if (this.disposingList.isEmpty()) {
                if (this.waitingQueue.isEmpty()) {
                    return;
                }
                if (!isIntervalValid()) {
                    this.waitingQueue.drainTo(this.disposingList);
                    i = 0;
                } else {
                    i = INTERVAL;
                    int iMin = Math.min(this.waitingQueue.size(), SUB_PACKAGE_SIZE);
                    for (int i2 = 0; i2 < iMin; i2++) {
                        this.disposingList.add(this.waitingQueue.remove());
                    }
                }
                this.handler.sendMessageDelayed(this.handler.obtainMessage(2, this.disposingList), i);
            }
        }
    }

    private static class UIHandlerCallback implements Handler.Callback {
        private UIHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((IFileDownloadMessenger) message.obj).handoverMessage();
            } else if (message.what == 2) {
                dispose((ArrayList) message.obj);
                FileDownloadMessageStation.getImpl().push();
            }
            return true;
        }

        private void dispose(ArrayList<IFileDownloadMessenger> arrayList) {
            Iterator<IFileDownloadMessenger> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().handoverMessage();
            }
            arrayList.clear();
        }
    }

    public static boolean isIntervalValid() {
        return INTERVAL > 0;
    }
}
