package com.kwad.sdk.api.core.fragment;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
abstract class DelegateDialogFragment extends DialogFragment implements IDelegateFragment {
    private IDialogFragmentLifecycle mBase;

    DelegateDialogFragment(KsDialogFragment ksDialogFragment) {
        this.mBase = ksDialogFragment;
    }

    @Override // com.kwad.sdk.api.core.fragment.IDelegateFragment
    public Activity getActivity2() {
        return getActivity();
    }

    @Override // com.kwad.sdk.api.core.fragment.IDelegateFragment
    public KsFragment getBase() {
        return (KsFragment) this.mBase;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mBase.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mBase.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBase.onAttach(activity);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mBase.onAttach(context);
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof IDelegateFragment) {
            this.mBase.onAttachFragment(((IDelegateFragment) fragment).getBase());
        } else {
            if (fragment == null) {
                this.mBase.onAttachFragment(null);
                return;
            }
            throw new RuntimeException(fragment + " is not a DelegateFragment or DelegateDialogFragment");
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.mBase.onCancel(dialogInterface);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mBase.onConfigurationChanged(configuration);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return this.mBase.onContextItemSelected(menuItem);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mBase.onCreate(bundle);
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return this.mBase.onCreateAnimation(i, z, i2);
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return this.mBase.onCreateAnimator(i, z, i2);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        this.mBase.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        return this.mBase.onCreateDialog(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        this.mBase.onCreateOptionsMenu(menu, menuInflater);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return this.mBase.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBase.onDestroy();
    }

    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        this.mBase.onDestroyOptionsMenu();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBase.onDestroyView();
    }

    public void onDetach() {
        super.onDetach();
        this.mBase.onDetach();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mBase.onDismiss(dialogInterface);
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        return super.onGetLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.mBase.onHiddenChanged(z);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.mBase.onInflate(activity, attributeSet, bundle);
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        this.mBase.onInflate(context, attributeSet, bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mBase.onLowMemory();
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.mBase.onMultiWindowModeChanged(z);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.mBase.onOptionsItemSelected(menuItem);
    }

    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        this.mBase.onOptionsMenuClosed(menu);
    }

    public void onPause() {
        super.onPause();
        this.mBase.onPause();
    }

    public void onPictureInPictureModeChanged(boolean z) {
        super.onPictureInPictureModeChanged(z);
        this.mBase.onPictureInPictureModeChanged(z);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.mBase.onPrepareOptionsMenu(menu);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.mBase.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onResume() {
        super.onResume();
        this.mBase.onResume();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBase.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        this.mBase.onStart();
    }

    public void onStop() {
        super.onStop();
        this.mBase.onStop();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBase.onViewCreated(view, bundle);
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mBase.onViewStateRestored(bundle);
    }

    void setBase(KsDialogFragment ksDialogFragment) {
        this.mBase = ksDialogFragment;
    }

    void superOnCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
    }

    Dialog superOnCreateDialog(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    void superOnDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
    }
}
