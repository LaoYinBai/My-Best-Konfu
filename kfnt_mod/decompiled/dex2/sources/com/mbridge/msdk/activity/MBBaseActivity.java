package com.mbridge.msdk.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.OrientationEventListener;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.tools.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class MBBaseActivity extends Activity {
    private OrientationEventListener a;
    private Display b;
    private int c = -1;

    public abstract void setTopControllerPadding(int i, int i2, int i3, int i4, int i5);

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().addFlags(512);
            a();
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                getWindow().setAttributes(attributes);
            }
        } catch (Exception e) {
            s.d("MBBaseActivity", e.getMessage());
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        getNotchParams();
        a();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        a();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.disable();
            this.a = null;
        }
    }

    public void getNotchParams() {
        getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.mbridge.msdk.activity.MBBaseActivity.1
            @Override // java.lang.Runnable
            public final void run() {
                int safeInsetLeft;
                int safeInsetRight;
                int safeInsetTop;
                int i;
                DisplayCutout displayCutout;
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        WindowInsets rootWindowInsets = MBBaseActivity.this.getWindow().getDecorView().getRootWindowInsets();
                        int i2 = 0;
                        if (rootWindowInsets != null && Build.VERSION.SDK_INT >= 28 && (displayCutout = rootWindowInsets.getDisplayCutout()) != null) {
                            safeInsetLeft = displayCutout.getSafeInsetLeft();
                            safeInsetRight = displayCutout.getSafeInsetRight();
                            safeInsetTop = displayCutout.getSafeInsetTop();
                            int safeInsetBottom = displayCutout.getSafeInsetBottom();
                            s.d("MBBaseActivity", "NOTCH Left:" + safeInsetLeft + " Right:" + safeInsetRight + " Top:" + safeInsetTop + " Bottom:" + safeInsetBottom);
                            int iA = MBBaseActivity.a(MBBaseActivity.this);
                            if (MBBaseActivity.this.c == -1) {
                                MBBaseActivity mBBaseActivity = MBBaseActivity.this;
                                int i3 = 2;
                                if (iA == 0) {
                                    i3 = 3;
                                } else if (iA == 1) {
                                    i3 = 1;
                                } else if (iA == 2) {
                                    i3 = 4;
                                } else if (iA != 3) {
                                    i3 = -1;
                                }
                                mBBaseActivity.c = i3;
                                s.d("MBBaseActivity", MBBaseActivity.this.c + "");
                            }
                            switch (iA) {
                                case 0:
                                    i = safeInsetBottom;
                                    break;
                                case 1:
                                    i = safeInsetBottom;
                                    i2 = 90;
                                    break;
                                case 2:
                                    i = safeInsetBottom;
                                    i2 = 180;
                                    break;
                                case 3:
                                    i = safeInsetBottom;
                                    i2 = 270;
                                    break;
                                default:
                                    i = safeInsetBottom;
                                    i2 = -1;
                                    break;
                            }
                        } else {
                            i2 = -1;
                            safeInsetLeft = 0;
                            safeInsetRight = 0;
                            safeInsetTop = 0;
                            i = 0;
                        }
                        MBBaseActivity.this.setTopControllerPadding(i2, safeInsetLeft, safeInsetRight, safeInsetTop, i);
                        if (MBBaseActivity.this.a == null) {
                            MBBaseActivity.d(MBBaseActivity.this);
                        }
                    }
                } catch (Exception e) {
                    s.d("MBBaseActivity", e.getMessage());
                }
            }
        }, 500L);
    }

    private void a() {
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().addFlags(67108864);
                getWindow().getDecorView().setSystemUiVisibility(Message.MESSAGE_APP);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(2);
            }
        } catch (Throwable th) {
            s.d("MBBaseActivity", th.getMessage());
        }
    }

    static /* synthetic */ int a(MBBaseActivity mBBaseActivity) {
        if (mBBaseActivity.b == null) {
            mBBaseActivity.b = ((WindowManager) mBBaseActivity.getSystemService("window")).getDefaultDisplay();
        }
        if (mBBaseActivity.b != null) {
            return mBBaseActivity.b.getRotation();
        }
        return -1;
    }

    static /* synthetic */ void d(MBBaseActivity mBBaseActivity) {
        mBBaseActivity.a = new OrientationEventListener(mBBaseActivity, 1) { // from class: com.mbridge.msdk.activity.MBBaseActivity.2
            @Override // android.view.OrientationEventListener
            public final void onOrientationChanged(int i) {
                if (MBBaseActivity.a(MBBaseActivity.this) == 1 && MBBaseActivity.this.c != 1) {
                    MBBaseActivity.this.c = 1;
                    MBBaseActivity.this.getNotchParams();
                    s.d("MBBaseActivity", "Orientation Left");
                    return;
                }
                if (MBBaseActivity.a(MBBaseActivity.this) == 3 && MBBaseActivity.this.c != 2) {
                    MBBaseActivity.this.c = 2;
                    MBBaseActivity.this.getNotchParams();
                    s.d("MBBaseActivity", "Orientation Right");
                } else if (MBBaseActivity.a(MBBaseActivity.this) == 0 && MBBaseActivity.this.c != 3) {
                    MBBaseActivity.this.c = 3;
                    MBBaseActivity.this.getNotchParams();
                    s.d("MBBaseActivity", "Orientation Top");
                } else {
                    if (MBBaseActivity.a(MBBaseActivity.this) != 2 || MBBaseActivity.this.c == 4) {
                        return;
                    }
                    MBBaseActivity.this.c = 4;
                    MBBaseActivity.this.getNotchParams();
                    s.d("MBBaseActivity", "Orientation Bottom");
                }
            }
        };
        if (mBBaseActivity.a.canDetectOrientation()) {
            mBBaseActivity.a.enable();
        } else {
            mBBaseActivity.a.disable();
            mBBaseActivity.a = null;
        }
    }
}
