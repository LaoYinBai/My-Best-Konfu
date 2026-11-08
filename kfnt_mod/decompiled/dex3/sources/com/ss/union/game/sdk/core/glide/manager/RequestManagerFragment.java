package com.ss.union.game.sdk.core.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestManagerFragment extends Fragment {
    private static final String a = "RMFragment";
    private final com.ss.union.game.sdk.core.glide.manager.a b;
    private final RequestManagerTreeNode c;
    private final Set<RequestManagerFragment> d;
    private RequestManager e;
    private RequestManagerFragment f;
    private Fragment g;

    public RequestManagerFragment() {
        this(new com.ss.union.game.sdk.core.glide.manager.a());
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(com.ss.union.game.sdk.core.glide.manager.a aVar) {
        this.c = new a();
        this.d = new HashSet();
        this.b = aVar;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.e = requestManager;
    }

    com.ss.union.game.sdk.core.glide.manager.a a() {
        return this.b;
    }

    public RequestManager getRequestManager() {
        return this.e;
    }

    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.c;
    }

    private void a(RequestManagerFragment requestManagerFragment) {
        this.d.add(requestManagerFragment);
    }

    private void b(RequestManagerFragment requestManagerFragment) {
        this.d.remove(requestManagerFragment);
    }

    @TargetApi(17)
    Set<RequestManagerFragment> b() {
        if (equals(this.f)) {
            return Collections.unmodifiableSet(this.d);
        }
        if (this.f == null || Build.VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment requestManagerFragment : this.f.b()) {
            if (b(requestManagerFragment.getParentFragment())) {
                hashSet.add(requestManagerFragment);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    void a(Fragment fragment) {
        this.g = fragment;
        if (fragment == null || fragment.getActivity() == null) {
            return;
        }
        a(fragment.getActivity());
    }

    @TargetApi(17)
    private Fragment c() {
        Fragment parentFragment = Build.VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        return parentFragment != null ? parentFragment : this.g;
    }

    @TargetApi(17)
    private boolean b(Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (true) {
            Fragment parentFragment2 = fragment.getParentFragment();
            if (parentFragment2 == null) {
                return false;
            }
            if (parentFragment2.equals(parentFragment)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    private void a(Activity activity) {
        d();
        this.f = Glide.get(activity).getRequestManagerRetriever().a(activity);
        if (equals(this.f)) {
            return;
        }
        this.f.a(this);
    }

    private void d() {
        if (this.f != null) {
            this.f.b(this);
            this.f = null;
        }
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Unable to register fragment with root", e);
            }
        }
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        d();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.b.a();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.b.b();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.b.c();
        d();
    }

    @Override // android.app.Fragment
    public String toString() {
        return super.toString() + "{parent=" + c() + "}";
    }

    private class a implements RequestManagerTreeNode {
        a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.manager.RequestManagerTreeNode
        public Set<RequestManager> getDescendants() {
            Set<RequestManagerFragment> setB = RequestManagerFragment.this.b();
            HashSet hashSet = new HashSet(setB.size());
            for (RequestManagerFragment requestManagerFragment : setB) {
                if (requestManagerFragment.getRequestManager() != null) {
                    hashSet.add(requestManagerFragment.getRequestManager());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + RequestManagerFragment.this + "}";
        }
    }
}
