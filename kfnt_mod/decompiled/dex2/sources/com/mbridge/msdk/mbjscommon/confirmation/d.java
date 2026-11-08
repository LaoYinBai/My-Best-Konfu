package com.mbridge.msdk.mbjscommon.confirmation;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.tools.ab;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.out.IDownloadListener;
import com.mbridge.msdk.widget.custom.baseview.MBButton;
import java.lang.reflect.InvocationTargetException;

/* compiled from: NativeProgressController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    MBButton a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private int g;
    private int h;
    private IDownloadListener i;

    public d(MBButton mBButton) {
        this.a = mBButton;
        c();
    }

    public final void a(int i) {
        this.d = i;
        if (this.c == 2) {
            c(1);
        }
    }

    private void c(final int i) {
        this.b = i;
        if (this.h == 1 || i == 0) {
            this.a.post(new Runnable() { // from class: com.mbridge.msdk.mbjscommon.confirmation.d.1
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    switch (i) {
                        case 1:
                            d.b(d.this);
                            break;
                        case 2:
                            d dVar = d.this;
                            if (dVar.a != null) {
                                dVar.a.setText(m.a(dVar.a.getContext(), "mbridge_cm_progress_status_descri_pause", "string"));
                                break;
                            }
                            break;
                        case 3:
                            d dVar2 = d.this;
                            if (dVar2.a != null) {
                                dVar2.a.setProgress(100);
                                try {
                                    Context context = dVar2.a.getContext();
                                    dVar2.a.setText(context.getResources().getString(m.a(context, "mbridge_cm_progress_status_descri_ins", "string")));
                                    break;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            break;
                        case 4:
                            d dVar3 = d.this;
                            if (dVar3.a != null) {
                                dVar3.a.setProgress(100);
                                try {
                                    Context context2 = dVar3.a.getContext();
                                    dVar3.a.setText(context2.getResources().getString(m.a(context2, "mbridge_cm_progress_status_descri_open", "string")));
                                    break;
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    return;
                                }
                            }
                            break;
                        default:
                            d.a(d.this);
                            break;
                    }
                }
            });
        }
    }

    public final void b(int i) {
        this.g = i;
    }

    public final void a(String str) {
        this.f = str;
        c();
    }

    public final void b(String str) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("1".equals(ab.a(str, "ctaldtype"))) {
            this.h = 1;
            if (!TextUtils.isEmpty(this.f)) {
                this.a.setProgress(50);
            }
            if (this.i == null) {
                this.i = new IDownloadListener() { // from class: com.mbridge.msdk.mbjscommon.confirmation.d.3
                    @Override // com.mbridge.msdk.out.IDownloadListener
                    public final void onEnd(int i, int i2, String str2) {
                    }

                    @Override // com.mbridge.msdk.out.IDownloadListener
                    public final void onStart() {
                    }

                    @Override // com.mbridge.msdk.out.IDownloadListener
                    public final void onProgressUpdate(int i) {
                        d.this.a(i);
                    }

                    @Override // com.mbridge.msdk.out.IDownloadListener
                    public final void onStatus(int i) {
                        if (d.this.c != i) {
                            d.this.c = i;
                            d.this.d(i);
                        }
                    }
                };
            }
            if (TextUtils.isEmpty(this.f)) {
                return;
            }
            try {
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                cls.getMethod("addDownloadListener", String.class, IDownloadListener.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.f, this.i);
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                return;
            }
        }
        this.h = 0;
    }

    public final void a() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.g == 3) {
            c();
            if (this.b != 2 || TextUtils.isEmpty(this.f)) {
                return;
            }
            try {
                String strB = com.mbridge.msdk.foundation.db.e.a(i.a(this.a.getContext())).b(this.f);
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                cls.getMethod("start", String.class, String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.f, strB);
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
    }

    private void c() {
        d(d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        switch (i) {
            case -1:
                this.b = 0;
                break;
            case 1:
                this.b = 3;
                break;
            case 2:
                this.b = 1;
                break;
            case 3:
                this.b = 0;
                break;
            case 5:
            case 6:
                this.b = 2;
                break;
            case 9:
                this.b = 4;
                break;
        }
        c(this.b);
    }

    private int d() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (!TextUtils.isEmpty(this.f)) {
            try {
                Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
                return ((Integer) cls.getMethod("getTaskStatusByUniqueKey", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.f)).intValue();
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

    public final void b() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (TextUtils.isEmpty(this.f)) {
            return;
        }
        try {
            Class<?> cls = Class.forName("com.mbridge.msdk.mbdownload.manager.ADownloadManager");
            cls.getMethod("deleteDownloadListener", String.class, IDownloadListener.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), this.f, this.i);
            this.i = null;
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

    static /* synthetic */ void a(d dVar) {
        if (dVar.a != null) {
            if (TextUtils.isEmpty(dVar.e)) {
                dVar.a.setText(m.a(dVar.a.getContext(), "mbridge_cm_progress_status_descri_default", "string"));
            } else {
                dVar.a.setText(dVar.e);
            }
        }
    }

    static /* synthetic */ void b(d dVar) throws Resources.NotFoundException {
        if (dVar.a != null) {
            dVar.a.setIndeterminateProgressMode(false);
            dVar.a.post(new Runnable() { // from class: com.mbridge.msdk.mbjscommon.confirmation.d.2
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.a.setProgress(d.this.d);
                }
            });
            if (dVar.d == 100) {
                try {
                    Context context = dVar.a.getContext();
                    dVar.a.setText(context.getResources().getString(m.a(context, "mbridge_cm_progress_status_descri_ins", "string")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
