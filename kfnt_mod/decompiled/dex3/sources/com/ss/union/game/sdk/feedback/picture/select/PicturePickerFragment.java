package com.ss.union.game.sdk.feedback.picture.select;

import android.os.Bundle;
import android.view.View;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PicturePickerFragment extends BaseFragment {
    private a a;
    private View b;
    private View c;
    private View d;
    private View e;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_picture_picker_fragment";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isShowStatusBar() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean isStatusBarDarkMode() {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void a(a aVar) {
        PicturePickerFragment picturePickerFragmentA = a((Bundle) null);
        picturePickerFragmentA.b(aVar);
        new OperationBuilder(picturePickerFragmentA).animation(OperationBuilder.Animation.BOTTOM).cancelable(true).show();
    }

    public static PicturePickerFragment a(Bundle bundle) {
        PicturePickerFragment picturePickerFragment = new PicturePickerFragment();
        picturePickerFragment.setArguments(bundle);
        return picturePickerFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.e = findViewById(ResourceUtils.getIdByName("lg_picture_picker_root"));
        this.c = findViewById(ResourceUtils.getIdByName("lg_picture_album"));
        this.b = findViewById(ResourceUtils.getIdByName("lg_picture_take"));
        this.d = findViewById(ResourceUtils.getIdByName("lg_picture_cancel"));
    }

    public PicturePickerFragment b(a aVar) {
        this.a = aVar;
        return this;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.PicturePickerFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PicturePickerFragment.this.a != null) {
                    PicturePickerFragment.this.a.a();
                }
                PicturePickerFragment.this.close();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.PicturePickerFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PicturePickerFragment.this.a != null) {
                    PicturePickerFragment.this.a.b();
                }
                PicturePickerFragment.this.close();
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.PicturePickerFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PicturePickerFragment.this.close();
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.PicturePickerFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PicturePickerFragment.this.close();
            }
        });
    }
}
