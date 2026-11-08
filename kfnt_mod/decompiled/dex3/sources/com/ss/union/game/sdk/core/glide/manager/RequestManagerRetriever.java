package com.ss.union.game.sdk.core.glide.manager;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.RequestManager;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestManagerRetriever implements Handler.Callback {
    static final String a = "com.ss.union.game.sdk.core.glide.manager";
    private static final String d = "RMRetriever";
    private static final int e = 1;
    private static final int f = 2;
    private static final String g = "key";
    private static final RequestManagerFactory n = new RequestManagerFactory() { // from class: com.ss.union.game.sdk.core.glide.manager.RequestManagerRetriever.1
        @Override // com.ss.union.game.sdk.core.glide.manager.RequestManagerRetriever.RequestManagerFactory
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    private volatile RequestManager h;
    private final Handler i;
    private final RequestManagerFactory j;
    final Map<FragmentManager, RequestManagerFragment> b = new HashMap();
    final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> c = new HashMap();
    private final ArrayMap<View, Fragment> k = new ArrayMap<>();
    private final ArrayMap<View, android.app.Fragment> l = new ArrayMap<>();
    private final Bundle m = new Bundle();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context);
    }

    public RequestManagerRetriever(RequestManagerFactory requestManagerFactory) {
        this.j = requestManagerFactory == null ? n : requestManagerFactory;
        this.i = new Handler(Looper.getMainLooper(), this);
    }

    private RequestManager a(Context context) {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = this.j.build(Glide.get(context.getApplicationContext()), new b(), new d(), context.getApplicationContext());
                }
            }
        }
        return this.h;
    }

    public RequestManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return get((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        return a(context);
    }

    public RequestManager get(FragmentActivity fragmentActivity) {
        if (Util.isOnBackgroundThread()) {
            return get(fragmentActivity.getApplicationContext());
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager(), (Fragment) null, c(fragmentActivity));
    }

    public RequestManager get(Fragment fragment) {
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        }
        return a((Context) fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        b(activity);
        return a(activity, activity.getFragmentManager(), (android.app.Fragment) null, c(activity));
    }

    public RequestManager get(View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activityB = b(view.getContext());
        if (activityB == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (activityB instanceof FragmentActivity) {
            Fragment fragmentA = a(view, (FragmentActivity) activityB);
            return fragmentA != null ? get(fragmentA) : get(activityB);
        }
        android.app.Fragment fragmentA2 = a(view, activityB);
        if (fragmentA2 == null) {
            return get(activityB);
        }
        return get(fragmentA2);
    }

    private static void a(Collection<Fragment> collection, Map<View, Fragment> map) {
        if (collection == null) {
            return;
        }
        for (Fragment fragment : collection) {
            if (fragment != null && fragment.getView() != null) {
                map.put(fragment.getView(), fragment);
                a(fragment.getChildFragmentManager().getFragments(), map);
            }
        }
    }

    private Fragment a(View view, FragmentActivity fragmentActivity) {
        this.k.clear();
        a((Collection<Fragment>) fragmentActivity.getSupportFragmentManager().getFragments(), (Map<View, Fragment>) this.k);
        View viewFindViewById = fragmentActivity.findViewById(R.id.content);
        Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = (Fragment) this.k.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.k.clear();
        return fragment;
    }

    @Deprecated
    private android.app.Fragment a(View view, Activity activity) {
        this.l.clear();
        a(activity.getFragmentManager(), this.l);
        View viewFindViewById = activity.findViewById(R.id.content);
        android.app.Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = (android.app.Fragment) this.l.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.l.clear();
        return fragment;
    }

    @TargetApi(26)
    @Deprecated
    private void a(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    a(fragment.getChildFragmentManager(), arrayMap);
                }
            }
            return;
        }
        b(fragmentManager, arrayMap);
    }

    @Deprecated
    private void b(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            this.m.putInt("key", i);
            android.app.Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.m, "key");
            } catch (Exception unused) {
            }
            if (fragment == null) {
                return;
            }
            if (fragment.getView() != null) {
                arrayMap.put(fragment.getView(), fragment);
                if (Build.VERSION.SDK_INT >= 17) {
                    a(fragment.getChildFragmentManager(), arrayMap);
                }
            }
            i = i2;
        }
    }

    private Activity b(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return b(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    @Deprecated
    public RequestManager get(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < 17) {
            return get(fragment.getActivity().getApplicationContext());
        }
        return a(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    @Deprecated
    RequestManagerFragment a(Activity activity) {
        return a(activity.getFragmentManager(), (android.app.Fragment) null, c(activity));
    }

    private RequestManagerFragment a(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag(a);
        if (requestManagerFragment == null && (requestManagerFragment = this.b.get(fragmentManager)) == null) {
            requestManagerFragment = new RequestManagerFragment();
            requestManagerFragment.a(fragment);
            if (z) {
                requestManagerFragment.a().a();
            }
            this.b.put(fragmentManager, requestManagerFragment);
            fragmentManager.beginTransaction().add(requestManagerFragment, a).commitAllowingStateLoss();
            this.i.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment;
    }

    @Deprecated
    private RequestManager a(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragmentA = a(fragmentManager, fragment, z);
        RequestManager requestManager = requestManagerFragmentA.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager requestManagerBuild = this.j.build(Glide.get(context), requestManagerFragmentA.a(), requestManagerFragmentA.getRequestManagerTreeNode(), context);
        requestManagerFragmentA.setRequestManager(requestManagerBuild);
        return requestManagerBuild;
    }

    SupportRequestManagerFragment a(FragmentActivity fragmentActivity) {
        return a(fragmentActivity.getSupportFragmentManager(), (Fragment) null, c(fragmentActivity));
    }

    private static boolean c(Activity activity) {
        return !activity.isFinishing();
    }

    private SupportRequestManagerFragment a(androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(a);
        if (supportRequestManagerFragment == null && (supportRequestManagerFragment = this.c.get(fragmentManager)) == null) {
            supportRequestManagerFragment = new SupportRequestManagerFragment();
            supportRequestManagerFragment.a(fragment);
            if (z) {
                supportRequestManagerFragment.a().a();
            }
            this.c.put(fragmentManager, supportRequestManagerFragment);
            fragmentManager.beginTransaction().add(supportRequestManagerFragment, a).commitAllowingStateLoss();
            this.i.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return supportRequestManagerFragment;
    }

    private RequestManager a(Context context, androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment, boolean z) {
        SupportRequestManagerFragment supportRequestManagerFragmentA = a(fragmentManager, fragment, z);
        RequestManager requestManager = supportRequestManagerFragmentA.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager requestManagerBuild = this.j.build(Glide.get(context), supportRequestManagerFragmentA.a(), supportRequestManagerFragmentA.getRequestManagerTreeNode(), context);
        supportRequestManagerFragmentA.setRequestManager(requestManagerBuild);
        return requestManagerBuild;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object objRemove;
        androidx.fragment.app.FragmentManager fragmentManager = null;
        boolean z = true;
        switch (message.what) {
            case 1:
                fragmentManager = (FragmentManager) message.obj;
                objRemove = this.b.remove(fragmentManager);
                break;
            case 2:
                fragmentManager = (androidx.fragment.app.FragmentManager) message.obj;
                objRemove = this.c.remove(fragmentManager);
                break;
            default:
                z = false;
                objRemove = null;
                break;
        }
        if (z && objRemove == null && Log.isLoggable(d, 5)) {
            Log.w(d, "Failed to remove expected request manager fragment, manager: " + fragmentManager);
        }
        return z;
    }
}
