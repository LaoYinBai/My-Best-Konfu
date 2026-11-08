package com.ss.union.game.sdk.common.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.ss.union.game.sdk.common.dialog.BaseDialogFragment;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OperationBuilder {
    private static final String a = "Core_OperationBuilder";
    private BaseFragment b;
    private Bundle c = new Bundle();

    public OperationBuilder(BaseFragment baseFragment) {
        this.b = baseFragment;
        this.c.putBoolean(MainDialog.BUNDLE_STATUS_BAR_STATUS, true);
    }

    public OperationBuilder transparentBackground() {
        this.c.putBoolean(MainDialog.BUNDLE_TRANSPARENT_BACKGROUND, true);
        return this;
    }

    public OperationBuilder animation(Animation animation) {
        if (animation != null) {
            this.c.putInt(MainDialog.BUNDLE_FLOW_ANIMATION, animation.type);
        }
        return this;
    }

    public OperationBuilder arg(String str, int i) {
        this.c.putInt(str, i);
        return this;
    }

    public OperationBuilder arg(String str, Parcelable parcelable) {
        this.c.putParcelable(str, parcelable);
        return this;
    }

    public OperationBuilder arg(String str, Bundle bundle) {
        this.c.putBundle(str, bundle);
        return this;
    }

    public OperationBuilder arg(String str, ArrayList<Parcelable> arrayList) {
        this.c.putParcelableArrayList(str, arrayList);
        return this;
    }

    public OperationBuilder arg(String str, String[] strArr) {
        this.c.putStringArray(str, strArr);
        return this;
    }

    public OperationBuilder arg(String str, boolean z) {
        this.c.putBoolean(str, z);
        return this;
    }

    public OperationBuilder arg(String str, String str2) {
        this.c.putString(str, str2);
        return this;
    }

    public OperationBuilder arg(Bundle bundle) {
        if (bundle != null) {
            this.c.putAll(bundle);
        }
        return this;
    }

    public OperationBuilder showStatusBar() {
        this.c.putBoolean(MainDialog.BUNDLE_STATUS_BAR_STATUS, false);
        return this;
    }

    public OperationBuilder softInputMode(int i) {
        this.c.putInt(MainDialog.BUNDLE_WINDOW_SOFT_INPUT_MODE, i);
        return this;
    }

    public OperationBuilder adjustPan() {
        return softInputMode(32);
    }

    public OperationBuilder adjustResize() {
        return softInputMode(16);
    }

    public OperationBuilder cancelable(boolean z) {
        return arg(MainDialog.BUNDLE_CANCELABLE, z);
    }

    public void show() {
        a.a.a(this);
    }

    public enum Animation {
        DEFAULT(0),
        TOP(1),
        LEFT(2),
        RIGHT(3),
        BOTTOM(4),
        NONE(5);

        public int type;

        Animation(int i) {
            this.type = i;
        }
    }

    static class a {
        static a a = new a();
        final List<OperationBuilder> b = new ArrayList();

        private a() {
            ActivityUtils.registerForegroundListener(new ActivityUtils.ForegroundListener() { // from class: com.ss.union.game.sdk.common.dialog.OperationBuilder.a.1
                @Override // com.ss.union.game.sdk.common.util.ActivityUtils.ForegroundListener
                public void onBackground() {
                }

                @Override // com.ss.union.game.sdk.common.util.ActivityUtils.ForegroundListener
                public void onForeground(Activity activity) {
                    a.this.a();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(OperationBuilder operationBuilder) {
            try {
                Activity topActivity = ActivityUtils.getTopActivity();
                if (topActivity != null) {
                    LogUtils.log(OperationBuilder.a, "dialog try show：" + operationBuilder.b.getClass().getSimpleName());
                    MainDialog mainDialog = new MainDialog();
                    mainDialog.setBaseFragment(operationBuilder.b);
                    mainDialog.setArguments(operationBuilder.c);
                    mainDialog.show(topActivity.getFragmentManager(), "lg_dialog");
                    mainDialog.addOnDismissListener(new BaseDialogFragment.OnDismissListener() { // from class: com.ss.union.game.sdk.common.dialog.OperationBuilder.a.2
                        @Override // com.ss.union.game.sdk.common.dialog.BaseDialogFragment.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            a.this.a();
                        }
                    });
                } else {
                    LogUtils.log(OperationBuilder.a, "dialog show but activity is null");
                    this.b.add(operationBuilder);
                }
            } catch (Throwable th) {
                if (!ProcessUtils.appIsForeground()) {
                    LogUtils.log(OperationBuilder.a, "dialog show exception appIsForeground = false");
                    this.b.add(operationBuilder);
                } else {
                    LogUtils.log(OperationBuilder.a, "dialog show exception appIsForeground = true " + Log.getStackTraceString(th));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            LogUtils.log(OperationBuilder.a, "scheduleShowDialog isEmpty: " + this.b.isEmpty());
            if (this.b.isEmpty()) {
                return;
            }
            a(this.b.remove(this.b.size() - 1));
        }
    }
}
