package com.ss.union.game.sdk.feedback.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.ui.BorderImageView;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.base.view.CardView;
import com.ss.union.game.sdk.feedback.picture.select.PicturePickerFragment;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.ss.union.game.sdk.feedback.picture.select.e;
import com.ss.union.game.sdk.feedback.picture.wall.PictureWallFragment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AddPhotoCard extends CardView implements com.ss.union.game.sdk.feedback.picture.wall.a {
    protected static final int MARGIN_START_OF_PHOTO = 8;
    protected static final int MAX_PHOTO_COUNT = 3;
    private List<a> compressResultListeners;
    private List<c> imageInfos;
    protected int maxPhotoCount;
    protected LinearLayout mllContainer;

    public interface a {
        void a(List<File> list);

        void b(List<Throwable> list);
    }

    enum b {
        COMPRESSING,
        SUCCESS,
        FAIL
    }

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public String getLayoutName() {
        return "lg_feedback_card_add_photo";
    }

    public AddPhotoCard(Context context) {
        this(context, null);
    }

    public AddPhotoCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddPhotoCard(Context context, AttributeSet attributeSet, int i) throws Throwable {
        super(context, attributeSet, i);
        this.maxPhotoCount = 3;
        this.imageInfos = new CopyOnWriteArrayList();
        this.compressResultListeners = new ArrayList();
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes;
        if (attributeSet != null) {
            TypedArray typedArray = null;
            try {
                try {
                    typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ResourceUtils.getStyleableInts("styleable", "AddPhotoCard"), i, 0);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                typedArrayObtainStyledAttributes = typedArray;
            }
            try {
                this.maxPhotoCount = typedArrayObtainStyledAttributes.getInt(ResourceUtils.getStyleableInt("styleable", "AddPhotoCard_maxPhotoCount"), 3);
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } catch (Exception e2) {
                e = e2;
                typedArray = typedArrayObtainStyledAttributes;
                e.printStackTrace();
                if (typedArray != null) {
                    typedArray.recycle();
                }
            } catch (Throwable th2) {
                th = th2;
                if (typedArrayObtainStyledAttributes != null) {
                    typedArrayObtainStyledAttributes.recycle();
                }
                throw th;
            }
        }
    }

    private ArrayList<String> getPaths() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (c cVar : this.imageInfos) {
            if (cVar.e != null) {
                arrayList.add(cVar.e.getAbsolutePath());
            } else if (cVar.d != null) {
                arrayList.add(cVar.d.getAbsolutePath());
            }
        }
        return arrayList;
    }

    private int getImageInfoIndex(c cVar) {
        for (int i = 0; i < this.imageInfos.size(); i++) {
            if (this.imageInfos.get(i) == cVar) {
                return i;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImageInfoClick(final c cVar) {
        View rootView = getRootView();
        if (rootView instanceof ViewGroup) {
            KeyboardUtils.hideKeyboard(getContext(), ((ViewGroup) rootView).getFocusedChild());
        }
        if (cVar.h) {
            ArrayList<String> paths = getPaths();
            if (paths.isEmpty()) {
                ToastUtils.getInstance().toast("请先选择图片");
                return;
            }
            com.ss.union.game.sdk.feedback.picture.wall.b bVar = new com.ss.union.game.sdk.feedback.picture.wall.b();
            bVar.a = paths;
            bVar.c = getImageInfoIndex(cVar);
            bVar.b = true;
            if (ActivityUtils.unwrap(getContext()) != null) {
                showPictureWallDialog(bVar, this);
                return;
            }
            return;
        }
        ActivityUtils.unwrap(getContext());
        showPictureSelectDialog(new com.ss.union.game.sdk.feedback.picture.select.a() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.1
            @Override // com.ss.union.game.sdk.feedback.picture.select.a
            public void a() {
                AddPhotoCard.this.getCommonLoader(cVar).b();
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.a
            public void b() {
                AddPhotoCard.this.getCommonLoader(cVar).a();
            }
        });
    }

    public static void showPictureWallDialog(com.ss.union.game.sdk.feedback.picture.wall.b bVar, com.ss.union.game.sdk.feedback.picture.wall.a aVar) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(PictureWallFragment.d, bVar.b);
        bundle.putInt(PictureWallFragment.c, bVar.c);
        bundle.putStringArrayList(PictureWallFragment.b, bVar.a);
        PictureWallFragment pictureWallFragmentA = PictureWallFragment.a(bundle);
        pictureWallFragmentA.a(aVar);
        new OperationBuilder(pictureWallFragmentA).animation(OperationBuilder.Animation.RIGHT).transparentBackground().cancelable(true).show();
    }

    public static void showPictureSelectDialog(com.ss.union.game.sdk.feedback.picture.select.a aVar) {
        PicturePickerFragment.a(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        com.ss.union.game.sdk.c.a.d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public e getCommonLoader(final c cVar) {
        return e.a(cVar.c).a(new com.ss.union.game.sdk.feedback.picture.select.b() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.2
            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void a(File file) {
                super.a(file);
                cVar.d = file;
                AddPhotoCard.this.d("加载原图成功:" + file.getAbsolutePath());
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void a(String str, @Nullable Throwable th) {
                super.a(str, th);
                if (!TextUtils.isEmpty(str)) {
                    ToastUtils.getInstance().toast(str);
                }
                AddPhotoCard.this.d("加载原图失败:" + str);
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void a() {
                super.a();
                cVar.h = true;
                AddPhotoCard.this.addPhotoView();
                AddPhotoCard.this.d("展示图片成功:");
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void a(Throwable th) {
                super.a(th);
                ToastUtils.getInstance().toast(th.getMessage());
                AddPhotoCard.this.d("展示图片失败:" + th.getMessage());
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void b() {
                cVar.f = true;
                super.b();
                AddPhotoCard.this.d("压缩开始:");
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void b(File file) {
                super.b(file);
                cVar.e = file;
                cVar.f = false;
                cVar.g = true;
                AddPhotoCard.this.d("压缩成功:" + file.getAbsolutePath());
                AddPhotoCard.this.checkCompressFinished();
            }

            @Override // com.ss.union.game.sdk.feedback.picture.select.b
            public void b(Throwable th) {
                super.b(th);
                cVar.f = false;
                cVar.g = false;
                AddPhotoCard.this.d("压缩失败:");
                AddPhotoCard.this.checkCompressFinished();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCompressFinished() {
        if (getCompressResult() == b.SUCCESS) {
            onCompressSuccess(getCompressFiles());
        } else {
            onCompressFail(getCompressExceptions());
        }
    }

    private void notifyDataChanged() {
        for (int i = 0; i < this.imageInfos.size(); i++) {
            final c cVar = this.imageInfos.get(i);
            cVar.c.setOnClickListener(antiShake(new View.OnClickListener() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        AddPhotoCard.this.onImageInfoClick(cVar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    private void removePhotoView(c cVar) {
        this.mllContainer.removeView(cVar.b);
        boolean z = true;
        if (this.imageInfos.size() > 0 && this.imageInfos.get(this.imageInfos.size() - 1).h) {
            z = false;
        }
        this.imageInfos.remove(cVar);
        if (this.imageInfos.size() > 0) {
            setMarginStart(this.imageInfos.get(0).b, 0);
        }
        if (z) {
            return;
        }
        addPhotoView();
    }

    protected void setMarginStart(View view, int i) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(UIUtils.dip2Px(i));
                view.setLayoutParams(layoutParams);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPhotoView() {
        if (this.imageInfos.size() >= this.maxPhotoCount) {
            return;
        }
        int size = this.imageInfos.size();
        ImageView imageView = getImageView(getContext(), true);
        if (size != 0) {
            setMarginStart(imageView, 8);
        }
        this.imageInfos.add(new c(imageView));
        this.mllContainer.addView(imageView);
        notifyDataChanged();
    }

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public void initData() {
        super.initData();
        this.imageInfos = new CopyOnWriteArrayList();
    }

    @Override // com.ss.union.game.sdk.core.base.view.CardView
    public void initView() {
        super.initView();
        this.mllContainer = (LinearLayout) findViewById(ResourceUtils.getIdByName("lg_feedback_photo_container"));
        addPhotoView();
    }

    public b getCompressResult() {
        boolean z = false;
        for (c cVar : this.imageInfos) {
            if (cVar.h) {
                if (cVar.f) {
                    return b.COMPRESSING;
                }
                z |= cVar.g;
            }
        }
        if (z) {
            return b.SUCCESS;
        }
        return b.FAIL;
    }

    public List<File> getCompressFiles() {
        ArrayList arrayList = new ArrayList();
        if (getCompressResult() != b.SUCCESS) {
            return arrayList;
        }
        for (c cVar : this.imageInfos) {
            if (cVar.h) {
                if (cVar.g) {
                    arrayList.add(cVar.e);
                } else if (cVar.d != null && cVar.d.length() < d.f) {
                    arrayList.add(cVar.d);
                }
            }
        }
        return arrayList;
    }

    public List<Throwable> getCompressExceptions() {
        ArrayList arrayList = new ArrayList();
        if (getCompressResult() != b.FAIL) {
            return arrayList;
        }
        for (c cVar : this.imageInfos) {
            if (cVar.h && !cVar.g && !cVar.f) {
                arrayList.add(cVar.i);
            }
        }
        return arrayList;
    }

    public void addCompressListener(a aVar) {
        if (aVar != null) {
            this.compressResultListeners.add(aVar);
        }
    }

    public boolean containsPicture() {
        for (c cVar : this.imageInfos) {
            if (cVar != null && cVar.h) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.4
            @Override // java.lang.Runnable
            public void run() {
                AddPhotoCard.this.updateData(new ArrayList());
            }
        });
    }

    public void asyncWaitCompressResult(@NonNull final a aVar) {
        b compressResult = getCompressResult();
        if (compressResult == b.SUCCESS) {
            aVar.a(getCompressFiles());
        } else if (compressResult == b.FAIL) {
            aVar.b(getCompressExceptions());
        } else {
            addCompressListener(new a() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.5
                @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
                public void a(List<File> list) {
                    try {
                        AddPhotoCard.this.compressResultListeners.remove(this);
                    } catch (Exception unused) {
                    }
                    aVar.a(list);
                }

                @Override // com.ss.union.game.sdk.feedback.view.AddPhotoCard.a
                public void b(List<Throwable> list) {
                    try {
                        AddPhotoCard.this.compressResultListeners.remove(this);
                    } catch (Exception unused) {
                    }
                    aVar.b(list);
                }
            });
        }
    }

    public void onCompressSuccess(final List<File> list) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    for (a aVar : (a[]) AddPhotoCard.this.compressResultListeners.toArray(new a[0])) {
                        aVar.a(list);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public void onCompressFail(final List<Throwable> list) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.view.AddPhotoCard.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    for (a aVar : (a[]) AddPhotoCard.this.compressResultListeners.toArray(new a[0])) {
                        aVar.b(list);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // com.ss.union.game.sdk.feedback.picture.wall.a
    public void onResult(List<String> list, List<String> list2) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        if (list == null || list2.isEmpty()) {
            return;
        }
        for (c cVar : this.imageInfos) {
            String absolutePath = cVar.d != null ? cVar.d.getAbsolutePath() : "";
            String absolutePath2 = cVar.e != null ? cVar.e.getAbsolutePath() : "";
            if (list.contains(absolutePath) || list.contains(absolutePath2)) {
                copyOnWriteArrayList.add(cVar);
            }
        }
        updateData(copyOnWriteArrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateData(List<c> list) {
        this.imageInfos = list;
        this.mllContainer.removeAllViews();
        if (list == null || list.isEmpty()) {
            addPhotoView();
            return;
        }
        for (int i = 0; i < this.imageInfos.size(); i++) {
            c cVar = this.imageInfos.get(i);
            if (i == 0) {
                setMarginStart(cVar.b, 0);
            } else {
                setMarginStart(cVar.b, 8);
            }
            this.mllContainer.addView(cVar.b);
        }
        addPhotoView();
    }

    public ImageView getImageView(Context context, boolean z) {
        BorderImageView borderImageView = new BorderImageView(context);
        borderImageView.setLayoutParams(new LinearLayout.LayoutParams(UIUtils.dip2Px(59.0f), UIUtils.dip2Px(59.0f)));
        borderImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (z) {
            borderImageView.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_feedback_image_add"));
        } else {
            borderImageView.showBorder();
        }
        return borderImageView;
    }

    class c {
        private View b;
        private ImageView c;
        private File d;
        private File e;
        private boolean f = false;
        private boolean g = false;
        private boolean h = false;
        private Throwable i = null;

        c(View view) {
            this.b = view;
            this.c = (ImageView) view;
        }
    }
}
