package com.ss.android.downloadlib.utils;

import android.annotation.TargetApi;
import android.os.AsyncTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AsyncTaskUtils {
    static final BaseImpl IMPL = new HoneyCombImpl();

    private static class BaseImpl {
        private BaseImpl() {
        }

        public <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.execute(tArr);
            } catch (Throwable unused) {
            }
        }
    }

    @TargetApi(11)
    private static class HoneyCombImpl extends BaseImpl {
        private HoneyCombImpl() {
            super();
        }

        @Override // com.ss.android.downloadlib.utils.AsyncTaskUtils.BaseImpl
        public <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tArr);
            } catch (Throwable unused) {
            }
        }
    }

    public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        IMPL.executeAsyncTask(asyncTask, tArr);
    }
}
