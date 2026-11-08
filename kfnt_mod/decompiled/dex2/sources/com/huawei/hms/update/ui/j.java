package com.huawei.hms.update.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class j {

    public static class b extends a {
        public b() {
            super();
        }

        @Override // com.huawei.hms.update.ui.j.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.j.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_check_failure");
        }
    }

    public static class c extends a {
        public c() {
            super();
        }

        @Override // com.huawei.hms.update.ui.j.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.j.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_download_failure");
        }
    }

    public static class d extends a {
        public d() {
            super();
        }

        @Override // com.huawei.hms.update.ui.j.a, com.huawei.hms.update.ui.b
        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        @Override // com.huawei.hms.update.ui.j.a
        protected int h() {
            return ResourceLoaderUtil.getStringId("hms_download_no_space");
        }
    }

    private static abstract class a extends com.huawei.hms.update.ui.b {
        protected abstract int h();

        private a() {
        }

        @Override // com.huawei.hms.update.ui.b
        public AlertDialog a() {
            AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
            builder.setMessage(h());
            builder.setPositiveButton(i(), new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.j.a.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    a.this.e();
                }
            });
            return builder.create();
        }

        protected int i() {
            return ResourceLoaderUtil.getStringId("hms_confirm");
        }
    }
}
