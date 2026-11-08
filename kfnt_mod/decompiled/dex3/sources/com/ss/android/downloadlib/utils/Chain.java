package com.ss.android.downloadlib.utils;

import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.DownloadDispatcher;
import java.lang.ref.SoftReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Chain<P, R> implements Runnable {
    private Chain<R, ?> mNext;
    private P mParam;
    private Chain<?, P> mPrev;
    private R mResult;
    private SoftReference<IRun<P, R>> mRunRef;

    @ThreadType
    private int threadType;

    public interface IRun<PARAM, RESULT> {
        RESULT run(PARAM param);
    }

    public @interface ThreadType {
        public static final int CPU = 1;
        public static final int IO = 2;
        public static final int MAIN = 0;
    }

    private Chain(@ThreadType int i, IRun<P, R> iRun, P p) {
        this.threadType = i;
        this.mRunRef = new SoftReference<>(iRun);
        this.mParam = p;
    }

    public static <P, R> Chain<P, R> create(@ThreadType int i, IRun<P, R> iRun, P p) {
        return new Chain<>(i, iRun, p);
    }

    public static <P, R> Chain<P, R> createOnCpu(IRun<P, R> iRun, P p) {
        return new Chain<>(1, iRun, p);
    }

    public static <P, R> Chain<P, R> createOnIo(IRun<P, R> iRun, P p) {
        return new Chain<>(2, iRun, p);
    }

    public static <P, R> Chain<P, R> createOnMain(IRun<P, R> iRun, P p) {
        return new Chain<>(0, iRun, p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <NR> Chain<R, NR> next(@ThreadType int i, IRun<R, NR> iRun) {
        Chain chain = (Chain<R, ?>) new Chain(i, iRun, null);
        this.mNext = chain;
        chain.mPrev = this;
        return chain;
    }

    public <NR> Chain<R, NR> nextOnCpu(IRun<R, NR> iRun) {
        return next(1, iRun);
    }

    public <NR> Chain<R, NR> nextOnIo(IRun<R, NR> iRun) {
        return next(2, iRun);
    }

    public <NR> Chain<R, NR> nextOnMain(IRun<R, NR> iRun) {
        return next(0, iRun);
    }

    public void start() {
        if (this.mPrev != null) {
            this.mPrev.start();
        } else {
            run();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.threadType == 0 && !ToolUtils.isMainThread()) {
            DownloadDispatcher.getInstance().getMainHandler().post(this);
            return;
        }
        if (this.threadType == 1 && ToolUtils.isMainThread()) {
            DownloadComponentManager.getInstance().submitCPUTask(this);
            return;
        }
        if (this.threadType == 2 && ToolUtils.isMainThread()) {
            DownloadComponentManager.getInstance().submitIOTask(this);
            return;
        }
        if (this.mParam == null && this.mPrev != null) {
            this.mParam = this.mPrev.getResult();
        }
        IRun<P, R> iRun = this.mRunRef.get();
        if (iRun == null) {
            return;
        }
        this.mResult = iRun.run(this.mParam);
        if (this.mNext != null) {
            this.mNext.run();
        }
    }

    private R getResult() {
        return this.mResult;
    }
}
