package com.ss.union.game.sdk.common.activityresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.activityresult.request.Request;
import com.ss.union.game.sdk.common.activityresult.request.RequestFabric;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ActivityResultFragment extends Fragment {
    private static final String a = "INTENT_TO_START";
    private static final int b = 30024;

    @Nullable
    private Request c;

    @Nullable
    private a d;

    interface a {
        void a(int i, int i2, Intent intent);

        void a(Throwable th);
    }

    public ActivityResultFragment() {
        setRetainInstance(true);
    }

    public static ActivityResultFragment newInstance(Intent intent, a aVar) {
        return newInstance(RequestFabric.create(intent), aVar);
    }

    public static ActivityResultFragment newInstance(Request request, a aVar) {
        ActivityResultFragment activityResultFragment = new ActivityResultFragment();
        activityResultFragment.a(request);
        activityResultFragment.a(aVar);
        return activityResultFragment;
    }

    @Override // android.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        b();
    }

    @Override // android.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        executeRequest();
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == b) {
            if (this.d != null) {
                this.d.a(i, i2, intent);
            }
            a();
        }
    }

    public void executeRequest() {
        if (this.c != null) {
            try {
                this.c.execute(this, b);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                if (this.d != null) {
                    this.d.a(th);
                }
                a();
                return;
            }
        }
        if (this.d != null) {
            this.d.a(new NullPointerException("request is empty"));
        }
        a();
    }

    private void a() {
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
    }

    private void a(Request request) {
        this.c = request;
        c();
    }

    private void a(@Nullable a aVar) {
        if (aVar != null) {
            this.d = aVar;
        }
    }

    private void b() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.c = (Request) arguments.getParcelable(a);
        }
    }

    private void c() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, this.c);
        setArguments(bundle);
    }
}
