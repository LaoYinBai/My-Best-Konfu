package com.bytedance.pangle.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toolbar;
import androidx.annotation.Keep;
import com.bytedance.pangle.plugin.Plugin;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GenerateProxyActivity extends Activity implements b {
    public Plugin mPlugin;
    public GeneratePluginActivity mTargetActivity;

    @Override // com.bytedance.pangle.activity.b
    public abstract String getPluginPkgName();

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.addContentView(view, layoutParams);
        } else {
            super.addContentView(view, layoutParams);
        }
    }

    public void zeusSuperAddContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addContentView(view, layoutParams);
    }

    @Override // android.app.Activity
    public void closeContextMenu() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.closeContextMenu();
        } else {
            super.closeContextMenu();
        }
    }

    public void zeusSuperCloseContextMenu() {
        super.closeContextMenu();
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.closeOptionsMenu();
        } else {
            super.closeOptionsMenu();
        }
    }

    public void zeusSuperCloseOptionsMenu() {
        super.closeOptionsMenu();
    }

    @Override // android.app.Activity
    public PendingIntent createPendingResult(int i, Intent intent, int i2) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.createPendingResult(i, intent, i2);
        }
        return super.createPendingResult(i, intent, i2);
    }

    public PendingIntent zeusSuperCreatePendingResult(int i, Intent intent, int i2) {
        return super.createPendingResult(i, intent, i2);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchGenericMotionEvent(motionEvent);
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean zeusSuperDispatchGenericMotionEvent(MotionEvent motionEvent) {
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean zeusSuperDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchKeyShortcutEvent(keyEvent);
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean zeusSuperDispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean zeusSuperDispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean zeusSuperDispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.dispatchTrackballEvent(motionEvent);
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    public boolean zeusSuperDispatchTrackballEvent(MotionEvent motionEvent) {
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.dump(str, fileDescriptor, printWriter, strArr);
        } else {
            super.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void zeusSuperDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
    }

    @Override // android.app.Activity
    public void enterPictureInPictureMode() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.enterPictureInPictureMode();
        } else {
            super.enterPictureInPictureMode();
        }
    }

    public void zeusSuperEnterPictureInPictureMode() {
        super.enterPictureInPictureMode();
    }

    @Override // android.app.Activity
    public boolean enterPictureInPictureMode(PictureInPictureParams pictureInPictureParams) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.enterPictureInPictureMode(pictureInPictureParams);
        }
        return super.enterPictureInPictureMode(pictureInPictureParams);
    }

    public boolean zeusSuperEnterPictureInPictureMode(PictureInPictureParams pictureInPictureParams) {
        return super.enterPictureInPictureMode(pictureInPictureParams);
    }

    @Override // android.app.Activity
    public View findViewById(int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.findViewById(i);
        }
        return super.findViewById(i);
    }

    public View zeusSuperFindViewById(int i) {
        return super.findViewById(i);
    }

    @Override // android.app.Activity, com.bytedance.pangle.activity.b
    public void finish() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finish();
        } else {
            super.finish();
        }
    }

    public void zeusSuperFinish() {
        super.finish();
    }

    @Override // android.app.Activity
    public void finishActivity(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishActivity(i);
        } else {
            super.finishActivity(i);
        }
    }

    public void zeusSuperFinishActivity(int i) {
        super.finishActivity(i);
    }

    @Override // android.app.Activity
    public void finishActivityFromChild(Activity activity, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishActivityFromChild(activity, i);
        } else {
            super.finishActivityFromChild(activity, i);
        }
    }

    public void zeusSuperFinishActivityFromChild(Activity activity, int i) {
        super.finishActivityFromChild(activity, i);
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishAffinity();
        } else {
            super.finishAffinity();
        }
    }

    public void zeusSuperFinishAffinity() {
        super.finishAffinity();
    }

    @Override // android.app.Activity
    public void finishAfterTransition() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishAfterTransition();
        } else {
            super.finishAfterTransition();
        }
    }

    public void zeusSuperFinishAfterTransition() {
        super.finishAfterTransition();
    }

    @Override // android.app.Activity
    public void finishAndRemoveTask() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishAndRemoveTask();
        } else {
            super.finishAndRemoveTask();
        }
    }

    public void zeusSuperFinishAndRemoveTask() {
        super.finishAndRemoveTask();
    }

    @Override // android.app.Activity
    public void finishFromChild(Activity activity) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.finishFromChild(activity);
        } else {
            super.finishFromChild(activity);
        }
    }

    public void zeusSuperFinishFromChild(Activity activity) {
        super.finishFromChild(activity);
    }

    @Override // android.app.Activity
    public ActionBar getActionBar() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getActionBar();
        }
        return super.getActionBar();
    }

    public ActionBar zeusSuperGetActionBar() {
        return super.getActionBar();
    }

    @Override // android.app.Activity
    public ComponentName getCallingActivity() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getCallingActivity();
        }
        return super.getCallingActivity();
    }

    public ComponentName zeusSuperGetCallingActivity() {
        return super.getCallingActivity();
    }

    @Override // android.app.Activity
    public String getCallingPackage() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getCallingPackage();
        }
        return super.getCallingPackage();
    }

    public String zeusSuperGetCallingPackage() {
        return super.getCallingPackage();
    }

    @Override // android.app.Activity
    public int getChangingConfigurations() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getChangingConfigurations();
        }
        return super.getChangingConfigurations();
    }

    public int zeusSuperGetChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override // android.app.Activity
    public ComponentName getComponentName() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getComponentName();
        }
        return super.getComponentName();
    }

    public ComponentName zeusSuperGetComponentName() {
        return super.getComponentName();
    }

    @Override // android.app.Activity
    public Scene getContentScene() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getContentScene();
        }
        return super.getContentScene();
    }

    public Scene zeusSuperGetContentScene() {
        return super.getContentScene();
    }

    @Override // android.app.Activity
    public TransitionManager getContentTransitionManager() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getContentTransitionManager();
        }
        return super.getContentTransitionManager();
    }

    public TransitionManager zeusSuperGetContentTransitionManager() {
        return super.getContentTransitionManager();
    }

    @Override // android.app.Activity
    public View getCurrentFocus() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getCurrentFocus();
        }
        return super.getCurrentFocus();
    }

    public View zeusSuperGetCurrentFocus() {
        return super.getCurrentFocus();
    }

    @Override // android.app.Activity
    public FragmentManager getFragmentManager() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getFragmentManager();
        }
        return super.getFragmentManager();
    }

    public FragmentManager zeusSuperGetFragmentManager() {
        return super.getFragmentManager();
    }

    @Override // android.app.Activity, com.bytedance.pangle.activity.b
    public Intent getIntent() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getIntent();
        }
        return super.getIntent();
    }

    public Intent zeusSuperGetIntent() {
        return super.getIntent();
    }

    @Override // android.app.Activity
    public Object getLastNonConfigurationInstance() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getLastNonConfigurationInstance();
        }
        return super.getLastNonConfigurationInstance();
    }

    public Object zeusSuperGetLastNonConfigurationInstance() {
        return super.getLastNonConfigurationInstance();
    }

    @Override // android.app.Activity
    public LayoutInflater getLayoutInflater() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getLayoutInflater();
        }
        return super.getLayoutInflater();
    }

    public LayoutInflater zeusSuperGetLayoutInflater() {
        return super.getLayoutInflater();
    }

    @Override // android.app.Activity
    public LoaderManager getLoaderManager() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getLoaderManager();
        }
        return super.getLoaderManager();
    }

    public LoaderManager zeusSuperGetLoaderManager() {
        return super.getLoaderManager();
    }

    @Override // android.app.Activity
    public String getLocalClassName() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getLocalClassName();
        }
        return super.getLocalClassName();
    }

    public String zeusSuperGetLocalClassName() {
        return super.getLocalClassName();
    }

    @Override // android.app.Activity
    public int getMaxNumPictureInPictureActions() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getMaxNumPictureInPictureActions();
        }
        return super.getMaxNumPictureInPictureActions();
    }

    public int zeusSuperGetMaxNumPictureInPictureActions() {
        return super.getMaxNumPictureInPictureActions();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getMenuInflater();
        }
        return super.getMenuInflater();
    }

    public MenuInflater zeusSuperGetMenuInflater() {
        return super.getMenuInflater();
    }

    @Override // android.app.Activity
    public Intent getParentActivityIntent() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getParentActivityIntent();
        }
        return super.getParentActivityIntent();
    }

    public Intent zeusSuperGetParentActivityIntent() {
        return super.getParentActivityIntent();
    }

    @Override // android.app.Activity
    public SharedPreferences getPreferences(int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getPreferences(i);
        }
        return super.getPreferences(i);
    }

    public SharedPreferences zeusSuperGetPreferences(int i) {
        return super.getPreferences(i);
    }

    @Override // android.app.Activity
    public Uri getReferrer() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getReferrer();
        }
        return super.getReferrer();
    }

    public Uri zeusSuperGetReferrer() {
        return super.getReferrer();
    }

    @Override // android.app.Activity
    public int getRequestedOrientation() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getRequestedOrientation();
        }
        return super.getRequestedOrientation();
    }

    public int zeusSuperGetRequestedOrientation() {
        return super.getRequestedOrientation();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getSystemService(str);
        }
        return super.getSystemService(str);
    }

    public Object zeusSuperGetSystemService(String str) {
        return super.getSystemService(str);
    }

    @Override // android.app.Activity
    public int getTaskId() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getTaskId();
        }
        return super.getTaskId();
    }

    public int zeusSuperGetTaskId() {
        return super.getTaskId();
    }

    @Override // android.app.Activity
    public VoiceInteractor getVoiceInteractor() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getVoiceInteractor();
        }
        return super.getVoiceInteractor();
    }

    public VoiceInteractor zeusSuperGetVoiceInteractor() {
        return super.getVoiceInteractor();
    }

    @Override // android.app.Activity
    public Window getWindow() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getWindow();
        }
        return super.getWindow();
    }

    public Window zeusSuperGetWindow() {
        return super.getWindow();
    }

    @Override // android.app.Activity
    public WindowManager getWindowManager() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.getWindowManager();
        }
        return super.getWindowManager();
    }

    public WindowManager zeusSuperGetWindowManager() {
        return super.getWindowManager();
    }

    @Override // android.app.Activity
    public boolean hasWindowFocus() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.hasWindowFocus();
        }
        return super.hasWindowFocus();
    }

    public boolean zeusSuperHasWindowFocus() {
        return super.hasWindowFocus();
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.invalidateOptionsMenu();
        } else {
            super.invalidateOptionsMenu();
        }
    }

    public void zeusSuperInvalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public boolean isActivityTransitionRunning() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isActivityTransitionRunning();
        }
        return super.isActivityTransitionRunning();
    }

    public boolean zeusSuperIsActivityTransitionRunning() {
        return super.isActivityTransitionRunning();
    }

    @Override // android.app.Activity
    public boolean isChangingConfigurations() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isChangingConfigurations();
        }
        return super.isChangingConfigurations();
    }

    public boolean zeusSuperIsChangingConfigurations() {
        return super.isChangingConfigurations();
    }

    @Override // android.app.Activity
    public boolean isDestroyed() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isDestroyed();
        }
        return super.isDestroyed();
    }

    public boolean zeusSuperIsDestroyed() {
        return super.isDestroyed();
    }

    @Override // android.app.Activity
    public boolean isFinishing() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isFinishing();
        }
        return super.isFinishing();
    }

    public boolean zeusSuperIsFinishing() {
        return super.isFinishing();
    }

    @Override // android.app.Activity
    public boolean isImmersive() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isImmersive();
        }
        return super.isImmersive();
    }

    public boolean zeusSuperIsImmersive() {
        return super.isImmersive();
    }

    @Override // android.app.Activity
    public boolean isInMultiWindowMode() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isInMultiWindowMode();
        }
        return super.isInMultiWindowMode();
    }

    public boolean zeusSuperIsInMultiWindowMode() {
        return super.isInMultiWindowMode();
    }

    @Override // android.app.Activity
    public boolean isInPictureInPictureMode() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isInPictureInPictureMode();
        }
        return super.isInPictureInPictureMode();
    }

    public boolean zeusSuperIsInPictureInPictureMode() {
        return super.isInPictureInPictureMode();
    }

    @Override // android.app.Activity
    public boolean isLocalVoiceInteractionSupported() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isLocalVoiceInteractionSupported();
        }
        return super.isLocalVoiceInteractionSupported();
    }

    public boolean zeusSuperIsLocalVoiceInteractionSupported() {
        return super.isLocalVoiceInteractionSupported();
    }

    @Override // android.app.Activity
    public boolean isTaskRoot() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isTaskRoot();
        }
        return super.isTaskRoot();
    }

    public boolean zeusSuperIsTaskRoot() {
        return super.isTaskRoot();
    }

    @Override // android.app.Activity
    public boolean isVoiceInteraction() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isVoiceInteraction();
        }
        return super.isVoiceInteraction();
    }

    public boolean zeusSuperIsVoiceInteraction() {
        return super.isVoiceInteraction();
    }

    @Override // android.app.Activity
    public boolean isVoiceInteractionRoot() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.isVoiceInteractionRoot();
        }
        return super.isVoiceInteractionRoot();
    }

    public boolean zeusSuperIsVoiceInteractionRoot() {
        return super.isVoiceInteractionRoot();
    }

    @Override // android.app.Activity
    public boolean moveTaskToBack(boolean z) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.moveTaskToBack(z);
        }
        return super.moveTaskToBack(z);
    }

    public boolean zeusSuperMoveTaskToBack(boolean z) {
        return super.moveTaskToBack(z);
    }

    @Override // android.app.Activity
    public boolean navigateUpTo(Intent intent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.navigateUpTo(intent);
        }
        return super.navigateUpTo(intent);
    }

    public boolean zeusSuperNavigateUpTo(Intent intent) {
        return super.navigateUpTo(intent);
    }

    @Override // android.app.Activity
    public boolean navigateUpToFromChild(Activity activity, Intent intent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.navigateUpToFromChild(activity, intent);
        }
        return super.navigateUpToFromChild(activity, intent);
    }

    public boolean zeusSuperNavigateUpToFromChild(Activity activity, Intent intent) {
        return super.navigateUpToFromChild(activity, intent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onActionModeFinished(actionMode);
        } else {
            super.onActionModeFinished(actionMode);
        }
    }

    public void zeusSuperOnActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onActionModeStarted(actionMode);
        } else {
            super.onActionModeStarted(actionMode);
        }
    }

    public void zeusSuperOnActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
    }

    @Override // android.app.Activity
    public void onActivityReenter(int i, Intent intent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onActivityReenter(i, intent);
        } else {
            super.onActivityReenter(i, intent);
        }
    }

    public void zeusSuperOnActivityReenter(int i, Intent intent) {
        super.onActivityReenter(i, intent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onActivityResult(i, i2, intent);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }

    protected void zeusSuperOnActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onApplyThemeResource(theme, i, z);
        } else {
            super.onApplyThemeResource(theme, i, z);
        }
    }

    protected void zeusSuperOnApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, i, z);
    }

    @Override // android.app.Activity
    public void onAttachFragment(Fragment fragment) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onAttachFragment(fragment);
        } else {
            super.onAttachFragment(fragment);
        }
    }

    public void zeusSuperOnAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onAttachedToWindow();
        } else {
            super.onAttachedToWindow();
        }
    }

    public void zeusSuperOnAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void zeusSuperOnBackPressed() {
        super.onBackPressed();
    }

    @Override // android.app.Activity
    protected void onChildTitleChanged(Activity activity, CharSequence charSequence) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onChildTitleChanged(activity, charSequence);
        } else {
            super.onChildTitleChanged(activity, charSequence);
        }
    }

    protected void zeusSuperOnChildTitleChanged(Activity activity, CharSequence charSequence) {
        super.onChildTitleChanged(activity, charSequence);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onConfigurationChanged(configuration);
        } else {
            super.onConfigurationChanged(configuration);
        }
    }

    public void zeusSuperOnConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onContentChanged();
        } else {
            super.onContentChanged();
        }
    }

    public void zeusSuperOnContentChanged() {
        super.onContentChanged();
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onContextItemSelected(menuItem);
        }
        return super.onContextItemSelected(menuItem);
    }

    public boolean zeusSuperOnContextItemSelected(MenuItem menuItem) {
        return super.onContextItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onContextMenuClosed(Menu menu) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onContextMenuClosed(menu);
        } else {
            super.onContextMenuClosed(menu);
        }
    }

    public void zeusSuperOnContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        c.a(this, context);
    }

    @Override // com.bytedance.pangle.activity.b
    public void zeusSuperAttachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        c.a(this, bundle);
    }

    @Override // com.bytedance.pangle.activity.b
    public void zeusSuperOnCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onCreate(bundle, persistableBundle);
        } else {
            super.onCreate(bundle, persistableBundle);
        }
    }

    public void zeusSuperOnCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        } else {
            super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        }
    }

    public void zeusSuperOnCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.app.Activity
    public CharSequence onCreateDescription() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateDescription();
        }
        return super.onCreateDescription();
    }

    public CharSequence zeusSuperOnCreateDescription() {
        return super.onCreateDescription();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateDialog(i);
        }
        return super.onCreateDialog(i);
    }

    protected Dialog zeusSuperOnCreateDialog(int i) {
        return super.onCreateDialog(i);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateDialog(i, bundle);
        }
        return super.onCreateDialog(i, bundle);
    }

    protected Dialog zeusSuperOnCreateDialog(int i, Bundle bundle) {
        return super.onCreateDialog(i, bundle);
    }

    @Override // android.app.Activity
    public void onCreateNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onCreateNavigateUpTaskStack(taskStackBuilder);
        } else {
            super.onCreateNavigateUpTaskStack(taskStackBuilder);
        }
    }

    public void zeusSuperOnCreateNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        super.onCreateNavigateUpTaskStack(taskStackBuilder);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateOptionsMenu(menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean zeusSuperOnCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreatePanelMenu(i, menu);
        }
        return super.onCreatePanelMenu(i, menu);
    }

    public boolean zeusSuperOnCreatePanelMenu(int i, Menu menu) {
        return super.onCreatePanelMenu(i, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public View onCreatePanelView(int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreatePanelView(i);
        }
        return super.onCreatePanelView(i);
    }

    public View zeusSuperOnCreatePanelView(int i) {
        return super.onCreatePanelView(i);
    }

    @Override // android.app.Activity
    public boolean onCreateThumbnail(Bitmap bitmap, Canvas canvas) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateThumbnail(bitmap, canvas);
        }
        return super.onCreateThumbnail(bitmap, canvas);
    }

    public boolean zeusSuperOnCreateThumbnail(Bitmap bitmap, Canvas canvas) {
        return super.onCreateThumbnail(bitmap, canvas);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateView(view, str, context, attributeSet);
        }
        return super.onCreateView(view, str, context, attributeSet);
    }

    public View zeusSuperOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onCreateView(str, context, attributeSet);
        }
        return super.onCreateView(str, context, attributeSet);
    }

    public View zeusSuperOnCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onDestroy();
        } else {
            super.onDestroy();
        }
    }

    protected void zeusSuperOnDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onDetachedFromWindow();
        } else {
            super.onDetachedFromWindow();
        }
    }

    public void zeusSuperOnDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.app.Activity
    public void onEnterAnimationComplete() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onEnterAnimationComplete();
        } else {
            super.onEnterAnimationComplete();
        }
    }

    public void zeusSuperOnEnterAnimationComplete() {
        super.onEnterAnimationComplete();
    }

    @Override // android.app.Activity
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onGenericMotionEvent(motionEvent);
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public boolean zeusSuperOnGenericMotionEvent(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onGetDirectActions(cancellationSignal, consumer);
        } else {
            super.onGetDirectActions(cancellationSignal, consumer);
        }
    }

    public void zeusSuperOnGetDirectActions(CancellationSignal cancellationSignal, Consumer consumer) {
        super.onGetDirectActions(cancellationSignal, consumer);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onKeyDown(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean zeusSuperOnKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onKeyLongPress(i, keyEvent);
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    public boolean zeusSuperOnKeyLongPress(int i, KeyEvent keyEvent) {
        return super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onKeyMultiple(i, i2, keyEvent);
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean zeusSuperOnKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onKeyShortcut(i, keyEvent);
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    public boolean zeusSuperOnKeyShortcut(int i, KeyEvent keyEvent) {
        return super.onKeyShortcut(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onKeyUp(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean zeusSuperOnKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onLocalVoiceInteractionStarted() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onLocalVoiceInteractionStarted();
        } else {
            super.onLocalVoiceInteractionStarted();
        }
    }

    public void zeusSuperOnLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
    }

    @Override // android.app.Activity
    public void onLocalVoiceInteractionStopped() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onLocalVoiceInteractionStopped();
        } else {
            super.onLocalVoiceInteractionStopped();
        }
    }

    public void zeusSuperOnLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onLowMemory();
        } else {
            super.onLowMemory();
        }
    }

    public void zeusSuperOnLowMemory() {
        super.onLowMemory();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onMenuOpened(i, menu);
        }
        return super.onMenuOpened(i, menu);
    }

    public boolean zeusSuperOnMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onMultiWindowModeChanged(z);
        } else {
            super.onMultiWindowModeChanged(z);
        }
    }

    public void zeusSuperOnMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onMultiWindowModeChanged(z, configuration);
        } else {
            super.onMultiWindowModeChanged(z, configuration);
        }
    }

    public void zeusSuperOnMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onNavigateUp();
        }
        return super.onNavigateUp();
    }

    public boolean zeusSuperOnNavigateUp() {
        return super.onNavigateUp();
    }

    @Override // android.app.Activity
    public boolean onNavigateUpFromChild(Activity activity) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onNavigateUpFromChild(activity);
        }
        return super.onNavigateUpFromChild(activity);
    }

    public boolean zeusSuperOnNavigateUpFromChild(Activity activity) {
        return super.onNavigateUpFromChild(activity);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onNewIntent(intent);
        } else {
            super.onNewIntent(intent);
        }
    }

    protected void zeusSuperOnNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onOptionsItemSelected(menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean zeusSuperOnOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onOptionsMenuClosed(Menu menu) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onOptionsMenuClosed(menu);
        } else {
            super.onOptionsMenuClosed(menu);
        }
    }

    public void zeusSuperOnOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPanelClosed(i, menu);
        } else {
            super.onPanelClosed(i, menu);
        }
    }

    public void zeusSuperOnPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPause();
        } else {
            super.onPause();
        }
    }

    protected void zeusSuperOnPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer consumer) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPerformDirectAction(str, bundle, cancellationSignal, consumer);
        } else {
            super.onPerformDirectAction(str, bundle, cancellationSignal, consumer);
        }
    }

    public void zeusSuperOnPerformDirectAction(String str, Bundle bundle, CancellationSignal cancellationSignal, Consumer consumer) {
        super.onPerformDirectAction(str, bundle, cancellationSignal, consumer);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPictureInPictureModeChanged(z);
        } else {
            super.onPictureInPictureModeChanged(z);
        }
    }

    public void zeusSuperOnPictureInPictureModeChanged(boolean z) {
        super.onPictureInPictureModeChanged(z);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPictureInPictureModeChanged(z, configuration);
        } else {
            super.onPictureInPictureModeChanged(z, configuration);
        }
    }

    public void zeusSuperOnPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
    }

    @Override // android.app.Activity
    public boolean onPictureInPictureRequested() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onPictureInPictureRequested();
        }
        return super.onPictureInPictureRequested();
    }

    public boolean zeusSuperOnPictureInPictureRequested() {
        return super.onPictureInPictureRequested();
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPostCreate(bundle);
        } else {
            super.onPostCreate(bundle);
        }
    }

    protected void zeusSuperOnPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle, PersistableBundle persistableBundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPostCreate(bundle, persistableBundle);
        } else {
            super.onPostCreate(bundle, persistableBundle);
        }
    }

    public void zeusSuperOnPostCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onPostCreate(bundle, persistableBundle);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPostResume();
        } else {
            super.onPostResume();
        }
    }

    protected void zeusSuperOnPostResume() {
        super.onPostResume();
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPrepareDialog(i, dialog);
        } else {
            super.onPrepareDialog(i, dialog);
        }
    }

    protected void zeusSuperOnPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPrepareDialog(i, dialog, bundle);
        } else {
            super.onPrepareDialog(i, dialog, bundle);
        }
    }

    protected void zeusSuperOnPrepareDialog(int i, Dialog dialog, Bundle bundle) {
        super.onPrepareDialog(i, dialog, bundle);
    }

    @Override // android.app.Activity
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onPrepareNavigateUpTaskStack(taskStackBuilder);
        } else {
            super.onPrepareNavigateUpTaskStack(taskStackBuilder);
        }
    }

    public void zeusSuperOnPrepareNavigateUpTaskStack(TaskStackBuilder taskStackBuilder) {
        super.onPrepareNavigateUpTaskStack(taskStackBuilder);
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onPrepareOptionsMenu(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean zeusSuperOnPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onPreparePanel(i, view, menu);
        }
        return super.onPreparePanel(i, view, menu);
    }

    public boolean zeusSuperOnPreparePanel(int i, View view, Menu menu) {
        return super.onPreparePanel(i, view, menu);
    }

    @Override // android.app.Activity
    public void onProvideAssistContent(AssistContent assistContent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onProvideAssistContent(assistContent);
        } else {
            super.onProvideAssistContent(assistContent);
        }
    }

    public void zeusSuperOnProvideAssistContent(AssistContent assistContent) {
        super.onProvideAssistContent(assistContent);
    }

    @Override // android.app.Activity
    public void onProvideAssistData(Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onProvideAssistData(bundle);
        } else {
            super.onProvideAssistData(bundle);
        }
    }

    public void zeusSuperOnProvideAssistData(Bundle bundle) {
        super.onProvideAssistData(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onProvideKeyboardShortcuts(list, menu, i);
        } else {
            super.onProvideKeyboardShortcuts(list, menu, i);
        }
    }

    public void zeusSuperOnProvideKeyboardShortcuts(List list, Menu menu, int i) {
        super.onProvideKeyboardShortcuts(list, menu, i);
    }

    @Override // android.app.Activity
    public Uri onProvideReferrer() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onProvideReferrer();
        }
        return super.onProvideReferrer();
    }

    public Uri zeusSuperOnProvideReferrer() {
        return super.onProvideReferrer();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onRequestPermissionsResult(i, strArr, iArr);
        } else {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void zeusSuperOnRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onRestart();
        } else {
            super.onRestart();
        }
    }

    protected void zeusSuperOnRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onRestoreInstanceState(bundle);
        } else {
            super.onRestoreInstanceState(bundle);
        }
    }

    protected void zeusSuperOnRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onRestoreInstanceState(bundle, persistableBundle);
        } else {
            super.onRestoreInstanceState(bundle, persistableBundle);
        }
    }

    public void zeusSuperOnRestoreInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onRestoreInstanceState(bundle, persistableBundle);
    }

    @Override // android.app.Activity
    protected void onResume() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onResume();
        } else {
            super.onResume();
        }
    }

    protected void zeusSuperOnResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onSaveInstanceState(bundle);
        } else {
            super.onSaveInstanceState(bundle);
        }
    }

    protected void zeusSuperOnSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onSaveInstanceState(bundle, persistableBundle);
        } else {
            super.onSaveInstanceState(bundle, persistableBundle);
        }
    }

    public void zeusSuperOnSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onSearchRequested();
        }
        return super.onSearchRequested();
    }

    public boolean zeusSuperOnSearchRequested() {
        return super.onSearchRequested();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onSearchRequested(searchEvent);
        }
        return super.onSearchRequested(searchEvent);
    }

    public boolean zeusSuperOnSearchRequested(SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override // android.app.Activity
    protected void onStart() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onStart();
        } else {
            super.onStart();
        }
    }

    protected void zeusSuperOnStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onStateNotSaved();
        } else {
            super.onStateNotSaved();
        }
    }

    public void zeusSuperOnStateNotSaved() {
        super.onStateNotSaved();
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onStop();
        } else {
            super.onStop();
        }
    }

    protected void zeusSuperOnStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onTitleChanged(charSequence, i);
        } else {
            super.onTitleChanged(charSequence, i);
        }
    }

    protected void zeusSuperOnTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
    }

    @Override // android.app.Activity
    public void onTopResumedActivityChanged(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onTopResumedActivityChanged(z);
        } else {
            super.onTopResumedActivityChanged(z);
        }
    }

    public void zeusSuperOnTopResumedActivityChanged(boolean z) {
        super.onTopResumedActivityChanged(z);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean zeusSuperOnTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onTrackballEvent(motionEvent);
        }
        return super.onTrackballEvent(motionEvent);
    }

    public boolean zeusSuperOnTrackballEvent(MotionEvent motionEvent) {
        return super.onTrackballEvent(motionEvent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onTrimMemory(i);
        } else {
            super.onTrimMemory(i);
        }
    }

    public void zeusSuperOnTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    @Override // android.app.Activity
    public void onUserInteraction() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onUserInteraction();
        } else {
            super.onUserInteraction();
        }
    }

    public void zeusSuperOnUserInteraction() {
        super.onUserInteraction();
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onUserLeaveHint();
        } else {
            super.onUserLeaveHint();
        }
    }

    protected void zeusSuperOnUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override // android.app.Activity
    public void onVisibleBehindCanceled() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onVisibleBehindCanceled();
        } else {
            super.onVisibleBehindCanceled();
        }
    }

    public void zeusSuperOnVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onWindowAttributesChanged(layoutParams);
        } else {
            super.onWindowAttributesChanged(layoutParams);
        }
    }

    public void zeusSuperOnWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.onWindowFocusChanged(z);
        } else {
            super.onWindowFocusChanged(z);
        }
    }

    public void zeusSuperOnWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onWindowStartingActionMode(callback);
        }
        return super.onWindowStartingActionMode(callback);
    }

    public ActionMode zeusSuperOnWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.onWindowStartingActionMode(callback, i);
        }
        return super.onWindowStartingActionMode(callback, i);
    }

    public ActionMode zeusSuperOnWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return super.onWindowStartingActionMode(callback, i);
    }

    @Override // android.app.Activity
    public void openContextMenu(View view) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.openContextMenu(view);
        } else {
            super.openContextMenu(view);
        }
    }

    public void zeusSuperOpenContextMenu(View view) {
        super.openContextMenu(view);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.openOptionsMenu();
        } else {
            super.openOptionsMenu();
        }
    }

    public void zeusSuperOpenOptionsMenu() {
        super.openOptionsMenu();
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.overridePendingTransition(i, i2);
        } else {
            super.overridePendingTransition(i, i2);
        }
    }

    public void zeusSuperOverridePendingTransition(int i, int i2) {
        super.overridePendingTransition(i, i2);
    }

    @Override // android.app.Activity
    public void postponeEnterTransition() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.postponeEnterTransition();
        } else {
            super.postponeEnterTransition();
        }
    }

    public void zeusSuperPostponeEnterTransition() {
        super.postponeEnterTransition();
    }

    @Override // android.app.Activity
    public void recreate() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.recreate();
        } else {
            super.recreate();
        }
    }

    public void zeusSuperRecreate() {
        super.recreate();
    }

    @Override // android.app.Activity
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            super.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    public void zeusSuperRegisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        super.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override // android.app.Activity
    public void registerForContextMenu(View view) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.registerForContextMenu(view);
        } else {
            super.registerForContextMenu(view);
        }
    }

    public void zeusSuperRegisterForContextMenu(View view) {
        super.registerForContextMenu(view);
    }

    @Override // android.app.Activity
    public boolean releaseInstance() {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.releaseInstance();
        }
        return super.releaseInstance();
    }

    public boolean zeusSuperReleaseInstance() {
        return super.releaseInstance();
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.reportFullyDrawn();
        } else {
            super.reportFullyDrawn();
        }
    }

    public void zeusSuperReportFullyDrawn() {
        super.reportFullyDrawn();
    }

    @Override // android.app.Activity
    public DragAndDropPermissions requestDragAndDropPermissions(DragEvent dragEvent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.requestDragAndDropPermissions(dragEvent);
        }
        return super.requestDragAndDropPermissions(dragEvent);
    }

    public DragAndDropPermissions zeusSuperRequestDragAndDropPermissions(DragEvent dragEvent) {
        return super.requestDragAndDropPermissions(dragEvent);
    }

    @Override // android.app.Activity
    public boolean requestVisibleBehind(boolean z) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.requestVisibleBehind(z);
        }
        return super.requestVisibleBehind(z);
    }

    public boolean zeusSuperRequestVisibleBehind(boolean z) {
        return super.requestVisibleBehind(z);
    }

    @Override // android.app.Activity
    public void setActionBar(Toolbar toolbar) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setActionBar(toolbar);
        } else {
            super.setActionBar(toolbar);
        }
    }

    public void zeusSuperSetActionBar(Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    @Override // android.app.Activity
    public void setContentTransitionManager(TransitionManager transitionManager) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setContentTransitionManager(transitionManager);
        } else {
            super.setContentTransitionManager(transitionManager);
        }
    }

    public void zeusSuperSetContentTransitionManager(TransitionManager transitionManager) {
        super.setContentTransitionManager(transitionManager);
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setContentView(i);
        } else {
            super.setContentView(i);
        }
    }

    public void zeusSuperSetContentView(int i) {
        super.setContentView(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setContentView(view);
        } else {
            super.setContentView(view);
        }
    }

    public void zeusSuperSetContentView(View view) {
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setContentView(view, layoutParams);
        } else {
            super.setContentView(view, layoutParams);
        }
    }

    public void zeusSuperSetContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }

    @Override // android.app.Activity
    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setEnterSharedElementCallback(sharedElementCallback);
        } else {
            super.setEnterSharedElementCallback(sharedElementCallback);
        }
    }

    public void zeusSuperSetEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        super.setEnterSharedElementCallback(sharedElementCallback);
    }

    @Override // android.app.Activity
    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setExitSharedElementCallback(sharedElementCallback);
        } else {
            super.setExitSharedElementCallback(sharedElementCallback);
        }
    }

    public void zeusSuperSetExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        super.setExitSharedElementCallback(sharedElementCallback);
    }

    @Override // android.app.Activity
    public void setFinishOnTouchOutside(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setFinishOnTouchOutside(z);
        } else {
            super.setFinishOnTouchOutside(z);
        }
    }

    public void zeusSuperSetFinishOnTouchOutside(boolean z) {
        super.setFinishOnTouchOutside(z);
    }

    @Override // android.app.Activity
    public void setImmersive(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setImmersive(z);
        } else {
            super.setImmersive(z);
        }
    }

    public void zeusSuperSetImmersive(boolean z) {
        super.setImmersive(z);
    }

    @Override // android.app.Activity
    public void setInheritShowWhenLocked(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setInheritShowWhenLocked(z);
        } else {
            super.setInheritShowWhenLocked(z);
        }
    }

    public void zeusSuperSetInheritShowWhenLocked(boolean z) {
        super.setInheritShowWhenLocked(z);
    }

    @Override // android.app.Activity
    public void setIntent(Intent intent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setIntent(intent);
        } else {
            super.setIntent(intent);
        }
    }

    public void zeusSuperSetIntent(Intent intent) {
        super.setIntent(intent);
    }

    @Override // android.app.Activity
    public void setLocusContext(LocusId locusId, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setLocusContext(locusId, bundle);
        } else {
            super.setLocusContext(locusId, bundle);
        }
    }

    public void zeusSuperSetLocusContext(LocusId locusId, Bundle bundle) {
        super.setLocusContext(locusId, bundle);
    }

    @Override // android.app.Activity
    public void setPictureInPictureParams(PictureInPictureParams pictureInPictureParams) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setPictureInPictureParams(pictureInPictureParams);
        } else {
            super.setPictureInPictureParams(pictureInPictureParams);
        }
    }

    public void zeusSuperSetPictureInPictureParams(PictureInPictureParams pictureInPictureParams) {
        super.setPictureInPictureParams(pictureInPictureParams);
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setRequestedOrientation(i);
        } else {
            super.setRequestedOrientation(i);
        }
    }

    public void zeusSuperSetRequestedOrientation(int i) {
        super.setRequestedOrientation(i);
    }

    @Override // android.app.Activity
    public void setShowWhenLocked(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setShowWhenLocked(z);
        } else {
            super.setShowWhenLocked(z);
        }
    }

    public void zeusSuperSetShowWhenLocked(boolean z) {
        super.setShowWhenLocked(z);
    }

    @Override // android.app.Activity
    public void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTaskDescription(taskDescription);
        } else {
            super.setTaskDescription(taskDescription);
        }
    }

    public void zeusSuperSetTaskDescription(ActivityManager.TaskDescription taskDescription) {
        super.setTaskDescription(taskDescription);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTheme(i);
        } else {
            super.setTheme(i);
        }
    }

    @Override // com.bytedance.pangle.activity.b
    public void zeusSuperSetTheme(int i) {
        super.setTheme(i);
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTitle(i);
        } else {
            super.setTitle(i);
        }
    }

    public void zeusSuperSetTitle(int i) {
        super.setTitle(i);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTitle(charSequence);
        } else {
            super.setTitle(charSequence);
        }
    }

    public void zeusSuperSetTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
    }

    @Override // android.app.Activity
    public void setTitleColor(int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTitleColor(i);
        } else {
            super.setTitleColor(i);
        }
    }

    public void zeusSuperSetTitleColor(int i) {
        super.setTitleColor(i);
    }

    @Override // android.app.Activity
    public boolean setTranslucent(boolean z) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.setTranslucent(z);
        }
        return super.setTranslucent(z);
    }

    public boolean zeusSuperSetTranslucent(boolean z) {
        return super.setTranslucent(z);
    }

    @Override // android.app.Activity
    public void setTurnScreenOn(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setTurnScreenOn(z);
        } else {
            super.setTurnScreenOn(z);
        }
    }

    public void zeusSuperSetTurnScreenOn(boolean z) {
        super.setTurnScreenOn(z);
    }

    @Override // android.app.Activity
    public void setVisible(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setVisible(z);
        } else {
            super.setVisible(z);
        }
    }

    public void zeusSuperSetVisible(boolean z) {
        super.setVisible(z);
    }

    @Override // android.app.Activity
    public void setVrModeEnabled(boolean z, ComponentName componentName) throws PackageManager.NameNotFoundException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.setVrModeEnabled(z, componentName);
        } else {
            super.setVrModeEnabled(z, componentName);
        }
    }

    public void zeusSuperSetVrModeEnabled(boolean z, ComponentName componentName) {
        super.setVrModeEnabled(z, componentName);
    }

    @Override // android.app.Activity
    public boolean shouldShowRequestPermissionRationale(String str) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.shouldShowRequestPermissionRationale(str);
        }
        return super.shouldShowRequestPermissionRationale(str);
    }

    public boolean zeusSuperShouldShowRequestPermissionRationale(String str) {
        return super.shouldShowRequestPermissionRationale(str);
    }

    @Override // android.app.Activity
    public boolean shouldUpRecreateTask(Intent intent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.shouldUpRecreateTask(intent);
        }
        return super.shouldUpRecreateTask(intent);
    }

    public boolean zeusSuperShouldUpRecreateTask(Intent intent) {
        return super.shouldUpRecreateTask(intent);
    }

    @Override // android.app.Activity
    public boolean showAssist(Bundle bundle) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.showAssist(bundle);
        }
        return super.showAssist(bundle);
    }

    public boolean zeusSuperShowAssist(Bundle bundle) {
        return super.showAssist(bundle);
    }

    @Override // android.app.Activity
    public void showLockTaskEscapeMessage() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.showLockTaskEscapeMessage();
        } else {
            super.showLockTaskEscapeMessage();
        }
    }

    public void zeusSuperShowLockTaskEscapeMessage() {
        super.showLockTaskEscapeMessage();
    }

    @Override // android.app.Activity
    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startActionMode(callback);
        }
        return super.startActionMode(callback);
    }

    public ActionMode zeusSuperStartActionMode(ActionMode.Callback callback) {
        return super.startActionMode(callback);
    }

    @Override // android.app.Activity
    public ActionMode startActionMode(ActionMode.Callback callback, int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startActionMode(callback, i);
        }
        return super.startActionMode(callback, i);
    }

    public ActionMode zeusSuperStartActionMode(ActionMode.Callback callback, int i) {
        return super.startActionMode(callback, i);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivities(intentArr);
        } else {
            super.startActivities(intentArr);
        }
    }

    public void zeusSuperStartActivities(Intent[] intentArr) {
        super.startActivities(intentArr);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivities(intentArr, bundle);
        } else {
            super.startActivities(intentArr, bundle);
        }
    }

    public void zeusSuperStartActivities(Intent[] intentArr, Bundle bundle) {
        super.startActivities(intentArr, bundle);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivity(intent);
        } else {
            super.startActivity(intent);
        }
    }

    public void zeusSuperStartActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivity(intent, bundle);
        } else {
            super.startActivity(intent, bundle);
        }
    }

    public void zeusSuperStartActivity(Intent intent, Bundle bundle) {
        super.startActivity(intent, bundle);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityForResult(intent, i);
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    public void zeusSuperStartActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityForResult(intent, i, bundle);
        } else {
            super.startActivityForResult(intent, i, bundle);
        }
    }

    public void zeusSuperStartActivityForResult(Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startActivityFromChild(Activity activity, Intent intent, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityFromChild(activity, intent, i);
        } else {
            super.startActivityFromChild(activity, intent, i);
        }
    }

    public void zeusSuperStartActivityFromChild(Activity activity, Intent intent, int i) {
        super.startActivityFromChild(activity, intent, i);
    }

    @Override // android.app.Activity
    public void startActivityFromChild(Activity activity, Intent intent, int i, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityFromChild(activity, intent, i, bundle);
        } else {
            super.startActivityFromChild(activity, intent, i, bundle);
        }
    }

    public void zeusSuperStartActivityFromChild(Activity activity, Intent intent, int i, Bundle bundle) {
        super.startActivityFromChild(activity, intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityFromFragment(fragment, intent, i);
        } else {
            super.startActivityFromFragment(fragment, intent, i);
        }
    }

    public void zeusSuperStartActivityFromFragment(Fragment fragment, Intent intent, int i) {
        super.startActivityFromFragment(fragment, intent, i);
    }

    @Override // android.app.Activity
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startActivityFromFragment(fragment, intent, i, bundle);
        } else {
            super.startActivityFromFragment(fragment, intent, i, bundle);
        }
    }

    public void zeusSuperStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        super.startActivityFromFragment(fragment, intent, i, bundle);
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startActivityIfNeeded(intent, i);
        }
        return super.startActivityIfNeeded(intent, i);
    }

    public boolean zeusSuperStartActivityIfNeeded(Intent intent, int i) {
        return super.startActivityIfNeeded(intent, i);
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i, Bundle bundle) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startActivityIfNeeded(intent, i, bundle);
        }
        return super.startActivityIfNeeded(intent, i, bundle);
    }

    public boolean zeusSuperStartActivityIfNeeded(Intent intent, int i, Bundle bundle) {
        return super.startActivityIfNeeded(intent, i, bundle);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startIntentSender(IntentSender intentSender, Intent intent, int i, int i2, int i3) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSender(intentSender, intent, i, i2, i3);
        } else {
            super.startIntentSender(intentSender, intent, i, i2, i3);
        }
    }

    public void zeusSuperStartIntentSender(IntentSender intentSender, Intent intent, int i, int i2, int i3) {
        super.startIntentSender(intentSender, intent, i, i2, i3);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startIntentSender(IntentSender intentSender, Intent intent, int i, int i2, int i3, Bundle bundle) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSender(intentSender, intent, i, i2, i3, bundle);
        } else {
            super.startIntentSender(intentSender, intent, i, i2, i3, bundle);
        }
    }

    public void zeusSuperStartIntentSender(IntentSender intentSender, Intent intent, int i, int i2, int i3, Bundle bundle) {
        super.startIntentSender(intentSender, intent, i, i2, i3, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
        } else {
            super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
        }
    }

    public void zeusSuperStartIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        }
    }

    public void zeusSuperStartIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderFromChild(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4);
        } else {
            super.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4);
        }
    }

    public void zeusSuperStartIntentSenderFromChild(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        super.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderFromChild(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            super.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4, bundle);
        }
    }

    public void zeusSuperStartIntentSenderFromChild(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        super.startIntentSenderFromChild(activity, intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void startLocalVoiceInteraction(Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startLocalVoiceInteraction(bundle);
        } else {
            super.startLocalVoiceInteraction(bundle);
        }
    }

    public void zeusSuperStartLocalVoiceInteraction(Bundle bundle) {
        super.startLocalVoiceInteraction(bundle);
    }

    @Override // android.app.Activity
    public void startLockTask() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startLockTask();
        } else {
            super.startLockTask();
        }
    }

    public void zeusSuperStartLockTask() {
        super.startLockTask();
    }

    @Override // android.app.Activity
    public void startManagingCursor(Cursor cursor) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startManagingCursor(cursor);
        } else {
            super.startManagingCursor(cursor);
        }
    }

    public void zeusSuperStartManagingCursor(Cursor cursor) {
        super.startManagingCursor(cursor);
    }

    @Override // android.app.Activity
    public boolean startNextMatchingActivity(Intent intent) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startNextMatchingActivity(intent);
        }
        return super.startNextMatchingActivity(intent);
    }

    public boolean zeusSuperStartNextMatchingActivity(Intent intent) {
        return super.startNextMatchingActivity(intent);
    }

    @Override // android.app.Activity
    public boolean startNextMatchingActivity(Intent intent, Bundle bundle) {
        if (this.mTargetActivity != null) {
            return this.mTargetActivity.startNextMatchingActivity(intent, bundle);
        }
        return super.startNextMatchingActivity(intent, bundle);
    }

    public boolean zeusSuperStartNextMatchingActivity(Intent intent, Bundle bundle) {
        return super.startNextMatchingActivity(intent, bundle);
    }

    @Override // android.app.Activity
    public void startPostponedEnterTransition() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void zeusSuperStartPostponedEnterTransition() {
        super.startPostponedEnterTransition();
    }

    @Override // android.app.Activity
    public void startSearch(String str, boolean z, Bundle bundle, boolean z2) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.startSearch(str, z, bundle, z2);
        } else {
            super.startSearch(str, z, bundle, z2);
        }
    }

    public void zeusSuperStartSearch(String str, boolean z, Bundle bundle, boolean z2) {
        super.startSearch(str, z, bundle, z2);
    }

    @Override // android.app.Activity
    public void stopLocalVoiceInteraction() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.stopLocalVoiceInteraction();
        } else {
            super.stopLocalVoiceInteraction();
        }
    }

    public void zeusSuperStopLocalVoiceInteraction() {
        super.stopLocalVoiceInteraction();
    }

    @Override // android.app.Activity
    public void stopLockTask() {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.stopLockTask();
        } else {
            super.stopLockTask();
        }
    }

    public void zeusSuperStopLockTask() {
        super.stopLockTask();
    }

    @Override // android.app.Activity
    public void stopManagingCursor(Cursor cursor) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.stopManagingCursor(cursor);
        } else {
            super.stopManagingCursor(cursor);
        }
    }

    public void zeusSuperStopManagingCursor(Cursor cursor) {
        super.stopManagingCursor(cursor);
    }

    @Override // android.app.Activity
    public void takeKeyEvents(boolean z) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.takeKeyEvents(z);
        } else {
            super.takeKeyEvents(z);
        }
    }

    public void zeusSuperTakeKeyEvents(boolean z) {
        super.takeKeyEvents(z);
    }

    @Override // android.app.Activity
    public void triggerSearch(String str, Bundle bundle) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.triggerSearch(str, bundle);
        } else {
            super.triggerSearch(str, bundle);
        }
    }

    public void zeusSuperTriggerSearch(String str, Bundle bundle) {
        super.triggerSearch(str, bundle);
    }

    @Override // android.app.Activity
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            super.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    public void zeusSuperUnregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        super.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override // android.app.Activity
    public void unregisterForContextMenu(View view) {
        if (this.mTargetActivity != null) {
            this.mTargetActivity.unregisterForContextMenu(view);
        } else {
            super.unregisterForContextMenu(view);
        }
    }

    public void zeusSuperUnregisterForContextMenu(View view) {
        super.unregisterForContextMenu(view);
    }

    public boolean zeusSuperConvertToTranslucent(Activity.TranslucentConversionListener translucentConversionListener, ActivityOptions activityOptions) throws NoSuchMethodException, SecurityException {
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("convertToTranslucent", Class.forName("android.app.Activity$TranslucentConversionListener"), ActivityOptions.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this, translucentConversionListener, activityOptions)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.bytedance.pangle.activity.b
    public Plugin getPlugin() {
        return this.mPlugin;
    }

    @Override // com.bytedance.pangle.activity.b
    public void setPlugin(Plugin plugin) {
        this.mPlugin = plugin;
    }

    @Override // com.bytedance.pangle.activity.b
    public void setTargetActivity(IPluginActivity iPluginActivity) {
        this.mTargetActivity = (GeneratePluginActivity) iPluginActivity;
    }
}
