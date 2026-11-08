package com.huawei.hms.update.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {

    public static class b extends a {
        public b() {
            super();
        }

        @Override // com.huawei.hms.update.ui.d.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_update_nettype");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int i() {
            return ResourceLoaderUtil.getStringId("hms_update_continue");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int j() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }
    }

    public static class c extends a {
        public c() {
            super();
        }

        @Override // com.huawei.hms.update.ui.d.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_download_retry");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int i() {
            return ResourceLoaderUtil.getStringId("hms_retry");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int j() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }
    }

    /* renamed from: com.huawei.hms.update.ui.d$d, reason: collision with other inner class name */
    public static class C0010d extends a {
        public C0010d() {
            super();
        }

        @Override // com.huawei.hms.update.ui.d.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_abort_message");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int i() {
            return ResourceLoaderUtil.getStringId("hms_abort");
        }

        @Override // com.huawei.hms.update.ui.d.a
        protected int j() {
            return ResourceLoaderUtil.getStringId("hms_cancel");
        }
    }

    private static abstract class a extends com.huawei.hms.update.ui.b {
        protected abstract int h();

        protected abstract int i();

        protected abstract int j();

        private a() {
        }

        @Override // com.huawei.hms.update.ui.b
        public AlertDialog a() {
            AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
            builder.setMessage(h());
            builder.setPositiveButton(i(), new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.d.a.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.e();
                }
            });
            builder.setNegativeButton(j(), new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.d.a.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.b();
                }
            });
            return builder.create();
        }
    }
}
