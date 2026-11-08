package com.mbridge.msdk.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.foundation.db.e;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.tools.ab;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.out.IDownloadListener;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBDownloadProgressBar extends RelativeLayout {
    public static final int PROGRESS_STATUS_DEFAULT = 0;
    public static final int PROGRESS_STATUS_DOING = 1;
    public static final int PROGRESS_STATUS_INT = 3;
    public static final int PROGRESS_STATUS_OPEN = 4;
    public static final int PROGRESS_STATUS_PAUSE = 2;
    private int a;
    private int b;
    private int c;
    private ProgressBar d;
    private ImageView e;
    private TextView f;
    private String g;
    private String h;
    private int i;
    private int j;
    private IDownloadListener k;

    public MBDownloadProgressBar(Context context) {
        this(context, null);
    }

    public MBDownloadProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MBDownloadProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(m.a(context, "mbridge_same_download_progress_bar_layout", TtmlNode.TAG_LAYOUT), (ViewGroup) null);
        addView(viewGroup);
        this.d = (ProgressBar) viewGroup.findViewById(m.a(context, "mbridge_same_download_mbprogress_progress", "id"));
        this.e = (ImageView) viewGroup.findViewById(m.a(context, "mbridge_same_download_mbprogress_status_icon", "id"));
        this.f = (TextView) viewGroup.findViewById(m.a(context, "mbridge_same_download_mbprogress_status_desc", "id"));
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        super.onAttachedToWindow();
        if (this.a == 0 || this.j != 1) {
            return;
        }
        e();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        super.onDetachedFromWindow();
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            cls.getMethod("deleteDownloadListener", String.class, IDownloadListener.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.h, this.k);
            this.k = null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    public void setProgress(int i) {
        this.c = i;
        if (this.b == 2) {
            a(1);
        }
    }

    private void a(final int i) {
        this.a = i;
        if (this.j == 1 || i == 0) {
            post(new Runnable() { // from class: com.mbridge.msdk.widget.MBDownloadProgressBar.1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 1:
                            MBDownloadProgressBar.b(MBDownloadProgressBar.this);
                            break;
                        case 2:
                            MBDownloadProgressBar.c(MBDownloadProgressBar.this);
                            break;
                        case 3:
                            MBDownloadProgressBar.d(MBDownloadProgressBar.this);
                            break;
                        case 4:
                            MBDownloadProgressBar.e(MBDownloadProgressBar.this);
                            break;
                        default:
                            MBDownloadProgressBar.this.a();
                            break;
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.e != null && this.e.getVisibility() != 8) {
            this.e.setVisibility(8);
        }
        if (this.d != null) {
            this.d.setProgress(0);
        }
        if (this.f != null) {
            if (TextUtils.isEmpty(this.g)) {
                this.f.setText(m.a(getContext(), "mbridge_cm_progress_status_descri_default", "string"));
            } else {
                this.f.setText(this.g);
            }
        }
    }

    public int getmStatus() {
        return this.a;
    }

    public void setText(String str) {
        this.g = str;
        a();
    }

    public void setLinkType(int i) {
        this.i = i;
    }

    public void setUniqueKey(String str) {
        this.h = str;
        b();
    }

    public void setCtaldtypeUrl(String str) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("1".equals(ab.a(str, "ctaldtype"))) {
            this.j = 1;
            e();
        } else {
            this.j = 0;
        }
    }

    public boolean NotProgressBarInterceptedClick() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.i == 3) {
            b();
            if (this.a == 2) {
                c();
                return true;
            }
            if (this.a == 1) {
                return true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public boolean performClick() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.i == 3) {
            e();
            b();
            if (this.a == 2) {
                c();
            }
        }
        return super.performClick();
    }

    private void b() {
        b(d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        switch (i) {
            case -1:
                this.a = 0;
                break;
            case 1:
                this.a = 3;
                break;
            case 2:
                this.a = 1;
                break;
            case 3:
                this.a = 0;
                break;
            case 5:
            case 6:
                this.a = 2;
                break;
            case 9:
                this.a = 4;
                break;
        }
        a(this.a);
    }

    private void c() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        try {
            String strB = e.a(i.a(getContext())).b(this.h);
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            cls.getMethod("start", String.class, String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.h, strB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    private int d() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (!TextUtils.isEmpty(this.h)) {
            try {
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                return ((Integer) cls.getMethod("getTaskStatusByUniqueKey", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.h)).intValue();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        return -1;
    }

    private void e() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.k == null) {
            this.k = new IDownloadListener() { // from class: com.mbridge.msdk.widget.MBDownloadProgressBar.2
                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onEnd(int i, int i2, String str) {
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onStart() {
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onProgressUpdate(int i) {
                    MBDownloadProgressBar.this.setProgress(i);
                }

                @Override // com.mbridge.msdk.out.IDownloadListener
                public final void onStatus(int i) {
                    if (MBDownloadProgressBar.this.b != i) {
                        MBDownloadProgressBar.this.b = i;
                        MBDownloadProgressBar.this.b(i);
                    }
                }
            };
            f();
        }
    }

    private void f() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.h)) {
            return;
        }
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            cls.getMethod("addDownloadListener", String.class, IDownloadListener.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.h, this.k);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }

    static /* synthetic */ void b(MBDownloadProgressBar mBDownloadProgressBar) {
        if (mBDownloadProgressBar.e != null && mBDownloadProgressBar.e.getVisibility() != 0) {
            mBDownloadProgressBar.e.setVisibility(0);
        }
        if (mBDownloadProgressBar.d != null) {
            mBDownloadProgressBar.d.setProgress(mBDownloadProgressBar.c);
        }
        if (mBDownloadProgressBar.f != null) {
            mBDownloadProgressBar.f.setText(mBDownloadProgressBar.c + "%");
        }
    }

    static /* synthetic */ void c(MBDownloadProgressBar mBDownloadProgressBar) {
        if (mBDownloadProgressBar.e != null && mBDownloadProgressBar.e.getVisibility() != 0) {
            mBDownloadProgressBar.e.setVisibility(0);
        }
        if (mBDownloadProgressBar.f != null) {
            mBDownloadProgressBar.f.setText(m.a(mBDownloadProgressBar.getContext(), "mbridge_cm_progress_status_descri_pause", "string"));
        }
    }

    static /* synthetic */ void d(MBDownloadProgressBar mBDownloadProgressBar) {
        if (mBDownloadProgressBar.e != null && mBDownloadProgressBar.e.getVisibility() != 8) {
            mBDownloadProgressBar.e.setVisibility(8);
        }
        if (mBDownloadProgressBar.d != null) {
            mBDownloadProgressBar.d.setProgress(100);
        }
        if (mBDownloadProgressBar.f != null) {
            mBDownloadProgressBar.f.setText(m.a(mBDownloadProgressBar.getContext(), "mbridge_cm_progress_status_descri_ins", "string"));
        }
    }

    static /* synthetic */ void e(MBDownloadProgressBar mBDownloadProgressBar) {
        if (mBDownloadProgressBar.e != null && mBDownloadProgressBar.e.getVisibility() != 8) {
            mBDownloadProgressBar.e.setVisibility(8);
        }
        if (mBDownloadProgressBar.d != null) {
            mBDownloadProgressBar.d.setProgress(100);
        }
        if (mBDownloadProgressBar.f != null) {
            mBDownloadProgressBar.f.setText(m.a(mBDownloadProgressBar.getContext(), "mbridge_cm_progress_status_descri_open", "string"));
        }
    }
}
