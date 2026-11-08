package com.ss.union.game.sdk.common.activityresult;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.activityresult.ActivityResultFragment;
import com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener;
import com.ss.union.game.sdk.common.activityresult.callbacks.FailCallback;
import com.ss.union.game.sdk.common.activityresult.callbacks.SuccessCallback;
import com.ss.union.game.sdk.common.activityresult.request.Request;
import com.ss.union.game.sdk.common.activityresult.request.RequestFabric;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InlineActivityResult {
    public static final String TAG = "ACTIVITY_RESULT_FRAGMENT_WEEE";
    private final Reference<Activity> a;
    private final Reference<Fragment> b;
    private final List<ActivityResultListener> c = new ArrayList();
    private final List<SuccessCallback> d = new ArrayList();
    private final List<FailCallback> e = new ArrayList();
    private final ActivityResultFragment.a f = new ActivityResultFragment.a() { // from class: com.ss.union.game.sdk.common.activityresult.InlineActivityResult.1
        @Override // com.ss.union.game.sdk.common.activityresult.ActivityResultFragment.a
        public void a(int i, int i2, Intent intent) {
            InlineActivityResult.this.a(i, i2, intent);
        }

        @Override // com.ss.union.game.sdk.common.activityresult.ActivityResultFragment.a
        public void a(Throwable th) {
            InlineActivityResult.this.a(th);
        }
    };

    public InlineActivityResult(Activity activity) {
        if (activity != null) {
            this.a = new WeakReference(activity);
        } else {
            this.a = new WeakReference(null);
        }
        this.b = new WeakReference(null);
    }

    public InlineActivityResult(Fragment fragment) {
        Activity activity;
        if (fragment != null) {
            activity = fragment.getActivity();
            this.b = new WeakReference(fragment);
        } else {
            this.b = new WeakReference(null);
            activity = null;
        }
        if (activity != null) {
            this.a = new WeakReference(activity);
        } else {
            this.a = new WeakReference(null);
        }
    }

    public static InlineActivityResult startForResult(Activity activity, Intent intent, ActivityResultListener activityResultListener) {
        return new InlineActivityResult(activity).startForResult(intent, activityResultListener);
    }

    public InlineActivityResult startForResult(@Nullable Intent intent) {
        return startForResult(RequestFabric.create(intent));
    }

    public InlineActivityResult startForResult(@Nullable Intent intent, @Nullable ActivityResultListener activityResultListener) {
        return startForResult(RequestFabric.create(intent), activityResultListener);
    }

    public InlineActivityResult startForResult(@Nullable Request request) {
        if (request != null) {
            a(request);
        }
        return this;
    }

    public InlineActivityResult startForResult(@Nullable Request request, @Nullable ActivityResultListener activityResultListener) {
        if (request != null) {
            if (activityResultListener != null) {
                this.c.add(activityResultListener);
            }
            a(request);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, Intent intent) {
        Result result = new Result(this, i, i2, intent);
        if (i2 == -1) {
            Iterator<SuccessCallback> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().onSuccess(result);
            }
            Iterator<ActivityResultListener> it2 = this.c.iterator();
            while (it2.hasNext()) {
                it2.next().onSuccess(result);
            }
            return;
        }
        if (i2 == 0) {
            Iterator<FailCallback> it3 = this.e.iterator();
            while (it3.hasNext()) {
                it3.next().onFailed(result);
            }
            Iterator<ActivityResultListener> it4 = this.c.iterator();
            while (it4.hasNext()) {
                it4.next().onFailed(result);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Throwable th) {
        Result result = new Result(this, th);
        Iterator<FailCallback> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onFailed(result);
        }
        Iterator<ActivityResultListener> it2 = this.c.iterator();
        while (it2.hasNext()) {
            it2.next().onFailed(result);
        }
    }

    public InlineActivityResult onSuccess(SuccessCallback successCallback) {
        if (successCallback != null) {
            this.d.add(successCallback);
        }
        return this;
    }

    public InlineActivityResult onFail(FailCallback failCallback) {
        if (failCallback != null) {
            this.e.add(failCallback);
        }
        return this;
    }

    private void a(Request request) {
        final Activity activity = this.a.get();
        if (activity == null || activity.isFinishing()) {
            this.f.a(new ActivityNotFoundException("activity is null or finished"));
        } else {
            final ActivityResultFragment activityResultFragmentNewInstance = ActivityResultFragment.newInstance(request, this.f);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ss.union.game.sdk.common.activityresult.InlineActivityResult.2
                @Override // java.lang.Runnable
                public void run() {
                    FragmentManager fragmentManager;
                    Fragment fragment = (Fragment) InlineActivityResult.this.b.get();
                    if (fragment != null) {
                        fragmentManager = fragment.getChildFragmentManager();
                    } else {
                        fragmentManager = activity.getFragmentManager();
                    }
                    try {
                        fragmentManager.beginTransaction().add(activityResultFragmentNewInstance, InlineActivityResult.TAG).commit();
                    } catch (Throwable th) {
                        InlineActivityResult.this.f.a(th);
                    }
                }
            });
        }
    }
}
