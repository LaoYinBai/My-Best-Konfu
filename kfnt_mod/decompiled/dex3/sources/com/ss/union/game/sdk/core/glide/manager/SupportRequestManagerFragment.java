package com.ss.union.game.sdk.core.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SupportRequestManagerFragment extends Fragment {
    private static final String a = "SupportRMFragment";
    private final com.ss.union.game.sdk.core.glide.manager.a b;
    private final RequestManagerTreeNode c;
    private final Set<SupportRequestManagerFragment> d;
    private SupportRequestManagerFragment e;
    private RequestManager f;
    private Fragment g;

    public SupportRequestManagerFragment() {
        this(new com.ss.union.game.sdk.core.glide.manager.a());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(com.ss.union.game.sdk.core.glide.manager.a aVar) {
        this.c = new a();
        this.d = new HashSet();
        this.b = aVar;
    }

    public void setRequestManager(RequestManager requestManager) {
        this.f = requestManager;
    }

    com.ss.union.game.sdk.core.glide.manager.a a() {
        return this.b;
    }

    public RequestManager getRequestManager() {
        return this.f;
    }

    public RequestManagerTreeNode getRequestManagerTreeNode() {
        return this.c;
    }

    private void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.add(supportRequestManagerFragment);
    }

    private void b(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.d.remove(supportRequestManagerFragment);
    }

    Set<SupportRequestManagerFragment> b() {
        if (this.e == null) {
            return Collections.emptySet();
        }
        if (equals(this.e)) {
            return Collections.unmodifiableSet(this.d);
        }
        HashSet hashSet = new HashSet();
        for (SupportRequestManagerFragment supportRequestManagerFragment : this.e.b()) {
            if (b(supportRequestManagerFragment.c())) {
                hashSet.add(supportRequestManagerFragment);
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

    private Fragment c() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.g;
    }

    private boolean b(Fragment fragment) {
        Fragment fragmentC = c();
        while (true) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment == null) {
                return false;
            }
            if (parentFragment.equals(fragmentC)) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
    }

    private void a(FragmentActivity fragmentActivity) {
        d();
        this.e = Glide.get(fragmentActivity).getRequestManagerRetriever().a(fragmentActivity);
        if (equals(this.e)) {
            return;
        }
        this.e.a(this);
    }

    private void d() {
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            a(getActivity());
        } catch (IllegalStateException e) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Unable to register fragment with root", e);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        this.g = null;
        d();
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
        d();
    }

    public String toString() {
        return super.toString() + "{parent=" + c() + "}";
    }

    private class a implements RequestManagerTreeNode {
        a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.manager.RequestManagerTreeNode
        public Set<RequestManager> getDescendants() {
            Set<SupportRequestManagerFragment> setB = SupportRequestManagerFragment.this.b();
            HashSet hashSet = new HashSet(setB.size());
            for (SupportRequestManagerFragment supportRequestManagerFragment : setB) {
                if (supportRequestManagerFragment.getRequestManager() != null) {
                    hashSet.add(supportRequestManagerFragment.getRequestManager());
                }
            }
            return hashSet;
        }

        public String toString() {
            return super.toString() + "{fragment=" + SupportRequestManagerFragment.this + "}";
        }
    }
}
