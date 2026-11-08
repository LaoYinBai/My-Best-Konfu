package com.kwad.sdk.api.proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.core.fragment.KsFragment;
import com.kwad.sdk.api.core.fragment.KsFragmentActivity;
import com.kwad.sdk.api.loader.Loader;
import com.kwad.sdk.api.loader.Wrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BaseProxyFragmentActivity extends KsFragmentActivity {
    private IFragmentActivityProxy mDelegate;

    /* JADX WARN: Multi-variable type inference failed */
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(Wrapper.wrapContextIfNeed(context));
        this.mDelegate = getDelegate(context);
        this.mDelegate.setProxyFragmentActivity(this);
        this.mDelegate.setActivity(this);
    }

    public Context createConfigurationContext(Configuration configuration) {
        return Wrapper.wrapContextIfNeed(super.createConfigurationContext(configuration));
    }

    @NonNull
    protected abstract IFragmentActivityProxy getDelegate(Context context);

    public Intent getIntent() {
        ClassLoader externalClassLoader = Loader.get().getExternalClassLoader();
        if (externalClassLoader == null) {
            return super.getIntent();
        }
        Intent intent = super.getIntent();
        intent.setExtrasClassLoader(externalClassLoader);
        return intent;
    }

    @NonNull
    public LayoutInflater getLayoutInflater() {
        return Wrapper.wrapInflaterIfNeed(super.getLayoutInflater());
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        Resources externalResource = Loader.get().getExternalResource();
        return externalResource != null ? externalResource : resources;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mDelegate.onActivityResult(i, i2, intent);
    }

    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
        this.mDelegate.onApplyThemeResource(theme, i, z);
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragmentActivity
    public void onAttachFragment(KsFragment ksFragment) {
        super.onAttachFragment(ksFragment);
        this.mDelegate.onAttachFragment(ksFragment);
    }

    public void onBackPressed() {
        this.mDelegate.onBackPressed();
    }

    protected void onChildTitleChanged(Activity activity, CharSequence charSequence) {
        super.onChildTitleChanged(activity, charSequence);
        this.mDelegate.onChildTitleChanged(activity, charSequence);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        this.mDelegate.onPreCreate(bundle);
        super.onCreate(bundle);
        this.mDelegate.onCreate(bundle);
    }

    @Override // com.kwad.sdk.api.core.fragment.KsFragmentActivity
    protected void onDestroy() {
        this.mDelegate.onPreDestroy();
        super.onDestroy();
        this.mDelegate.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mDelegate.onKeyDown(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.mDelegate.onKeyLongPress(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mDelegate.onKeyUp(i, keyEvent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mDelegate.onNewIntent(intent);
    }

    protected void onPause() {
        this.mDelegate.onPrePause();
        super.onPause();
        this.mDelegate.onPause();
    }

    protected void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        this.mDelegate.onPostCreate(bundle);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mDelegate.onPostResume();
    }

    protected void onRestart() {
        super.onRestart();
        this.mDelegate.onRestart();
    }

    protected void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mDelegate.onRestoreInstanceState(bundle);
    }

    protected void onResume() {
        this.mDelegate.onPreResume();
        super.onResume();
        this.mDelegate.onResume();
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        this.mDelegate.onPreSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
        this.mDelegate.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        this.mDelegate.onPreStart();
        super.onStart();
        this.mDelegate.onStart();
    }

    protected void onStop() {
        this.mDelegate.onPreStop();
        super.onStop();
        this.mDelegate.onStop();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.mDelegate.onTitleChanged(charSequence, i);
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.mDelegate.onUserLeaveHint();
    }

    public void startActivity(Intent intent) {
        if (intent != null) {
            intent.putExtra("key_start_time", SystemClock.uptimeMillis());
        }
        super.startActivity(intent);
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }

    public boolean superOnKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public boolean superOnKeyLongPress(int i, KeyEvent keyEvent) {
        return super.onKeyLongPress(i, keyEvent);
    }

    public boolean superOnKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }
}
