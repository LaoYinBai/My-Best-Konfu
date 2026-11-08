package com.baidu.mobads.sdk.api;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.bb;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppActivity extends Activity {
    public static String activityName;
    private static boolean canShowWhenLock;
    private static ActionBarColorTheme mSActionBarColorTheme = ActionBarColorTheme.ACTION_BAR_WHITE_THEME;
    private ClassLoader mLoader;
    private IActivityImpl mProxyActivity;

    public static void setActivityName(String str) {
        activityName = str;
    }

    public static boolean isAnti() {
        return !TextUtils.isEmpty(activityName);
    }

    public static Class<?> getActivityClass() {
        if (TextUtils.isEmpty(activityName)) {
            return AppActivity.class;
        }
        try {
            return Class.forName(activityName);
        } catch (Exception e) {
            bb.a().c(e);
            return AppActivity.class;
        }
    }

    public static void setActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
        if (actionBarColorTheme != null) {
            mSActionBarColorTheme = actionBarColorTheme;
        }
    }

    public static void canLpShowWhenLocked(boolean z) {
        canShowWhenLock = z;
    }

    public static boolean getLpShowWhenLocked() {
        return canShowWhenLock;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mProxyActivity != null ? this.mProxyActivity.dispatchKeyEvent(keyEvent) : false) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mProxyActivity != null ? this.mProxyActivity.dispatchTouchEvent(motionEvent) : false) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onConfigurationChanged(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) throws JSONException {
        Object objNewInstance;
        super.onCreate(bundle);
        Intent intent = getIntent();
        try {
            this.mLoader = ba.a(this);
            if (intent != null) {
                intent.setExtrasClassLoader(this.mLoader);
            }
            String stringExtra = intent != null ? intent.getStringExtra("activityImplName") : "";
            if (TextUtils.isEmpty(stringExtra)) {
                objNewInstance = null;
            } else {
                try {
                    objNewInstance = aj.a(stringExtra, this.mLoader).getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (objNewInstance != null) {
                this.mProxyActivity = (IActivityImpl) objNewInstance;
            }
            if (this.mProxyActivity != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("bar_close_color", mSActionBarColorTheme.mCloseColor);
                    jSONObject.put("bar_pro_color", mSActionBarColorTheme.mProgressColor);
                    jSONObject.put("bar_title_color", mSActionBarColorTheme.mTitleColor);
                    jSONObject.put("bar_bg_color", mSActionBarColorTheme.mBackgroundColor);
                    jSONObject.put("showWhenLocked", canShowWhenLock);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.mProxyActivity.setLpBussParam(jSONObject);
                this.mProxyActivity.setActivity(this);
                if (intent != null) {
                    this.mProxyActivity.onCreate(bundle);
                }
            }
        } catch (Exception e2) {
            bb.a().c(e2);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onDestroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mProxyActivity != null ? this.mProxyActivity.onKeyDown(i, keyEvent) : false) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mProxyActivity != null ? this.mProxyActivity.onKeyUp(i, keyEvent) : false) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onNewIntent(intent);
        }
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onRestoreInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onSaveInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onStart();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onStop();
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mProxyActivity != null ? this.mProxyActivity.onTouchEvent(motionEvent) : false) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onWindowFocusChanged(z);
        }
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(i, i2);
        if (this.mProxyActivity != null) {
            this.mProxyActivity.overridePendingTransition(i, i2);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onAttachedToWindow();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onDetachedFromWindow();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.mProxyActivity != null) {
            this.mProxyActivity.onBackPressed();
        }
    }
}
