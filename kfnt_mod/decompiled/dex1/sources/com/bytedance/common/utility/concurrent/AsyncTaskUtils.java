package com.bytedance.common.utility.concurrent;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AsyncTaskUtils {
    static final BaseImpl IMPL;

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

        @Override // com.bytedance.common.utility.concurrent.AsyncTaskUtils.BaseImpl
        public <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
            try {
                asyncTask.executeOnExecutor(TTExecutors.getNormalExecutor(), tArr);
            } catch (Throwable unused) {
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            IMPL = new HoneyCombImpl();
        } else {
            IMPL = new BaseImpl();
        }
    }

    public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        IMPL.executeAsyncTask(asyncTask, tArr);
    }
}
