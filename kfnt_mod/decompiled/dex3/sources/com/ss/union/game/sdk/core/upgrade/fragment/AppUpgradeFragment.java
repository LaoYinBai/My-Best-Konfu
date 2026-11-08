package com.ss.union.game.sdk.core.upgrade.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.download.GameDownloadManager;
import com.ss.union.game.sdk.common.download.GameDownloadShortInfo;
import com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestPermissionCallback;
import com.ss.union.game.sdk.core.init.bean.GameSDKOption;
import com.ss.union.game.sdk.core.init.fragment.PermissionFragment;
import com.ss.union.game.sdk.core.upgrade.a.a;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.umeng.message.MsgConstant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppUpgradeFragment extends BaseFragment<a, BasePresenter> {
    public static final String a = "AppUpgradeFragment";
    public static final String b = "key_content";
    public static final String c = "key_type";
    public static final String d = "key_is_force";
    public static final String e = "key_new_version";
    public static final String f = "key_url";
    private View g;
    private TextView h;
    private View i;
    private View j;
    private View k;
    private View l;
    private ProgressBar m;
    private TextView n;
    private View o;
    private View p;
    private boolean q;
    private String r;
    private String s;
    private String t;
    private String u;
    private long v;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_app_upgrade";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void a(String str, String str2, String str3, String str4, boolean z, a aVar) {
        new OperationBuilder(b(str, str2, str3, str4, z, aVar)).animation(OperationBuilder.Animation.NONE).show();
    }

    private static AppUpgradeFragment b(String str, String str2, String str3, String str4, boolean z, a aVar) {
        AppUpgradeFragment appUpgradeFragment = new AppUpgradeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key_content", str2);
        bundle.putString(c, str);
        bundle.putString(f, str3);
        bundle.putBoolean(d, z);
        bundle.putString(e, str4);
        appUpgradeFragment.setArguments(bundle);
        appUpgradeFragment.setCallback(aVar);
        return appUpgradeFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle != null) {
            this.q = bundle.getBoolean(d, false);
            this.r = bundle.getString("key_content", "");
            this.s = bundle.getString(c);
            this.t = bundle.getString(f);
            this.u = bundle.getString(e);
        }
        return !TextUtils.isEmpty(this.t);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.g = findViewById("id_lg_app_upgrade_container");
        this.h = (TextView) findViewById("id_lg_app_upgrade_content");
        this.h.setMovementMethod(new LinkMovementMethod());
        this.i = findViewById("id_lg_app_upgrade_cancel");
        this.j = findViewById("id_lg_app_upgrade_update");
        this.k = findViewById("id_lg_app_upgrade_update_force");
        this.l = findViewById("id_lg_app_upgrade_progress_container");
        this.m = (ProgressBar) findViewById("id_lg_app_upgrade_progress_bar");
        this.n = (TextView) findViewById("id_lg_app_upgrade_progress_download_size");
        this.o = findViewById("id_lg_app_upgrade_failure_container");
        this.p = findViewById("id_lg_app_upgrade_retry");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        com.ss.union.game.sdk.core.upgrade.b.a.a(this.q);
        this.v = Math.abs(this.t.hashCode());
        if (this.q) {
            this.k.setVisibility(0);
            this.j.setVisibility(8);
            this.i.setVisibility(8);
        } else {
            this.k.setVisibility(8);
            this.j.setVisibility(0);
            this.i.setVisibility(0);
        }
        this.h.setText(this.r);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogDevKitUtils.logUpgrade("user click cancel");
                com.ss.union.game.sdk.core.upgrade.b.a.d();
                AppUpgradeFragment.this.back();
                if (AppUpgradeFragment.this.getCallback() != null) {
                    ((a) AppUpgradeFragment.this.getCallback()).a();
                }
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogDevKitUtils.logUpgrade("user click update");
                com.ss.union.game.sdk.core.upgrade.b.a.c();
                AppUpgradeFragment.this.a(false);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogDevKitUtils.logUpgrade("user click force update");
                com.ss.union.game.sdk.core.upgrade.b.a.b();
                AppUpgradeFragment.this.a(true);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogDevKitUtils.logUpgrade("user click retry");
                AppUpgradeFragment.this.a(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (GameDownloadManager.hasDownloadComponent()) {
            if (GameSDKOption.d.a.equals(this.s)) {
                LogDevKitUtils.logUpgrade("start download apk");
                final String packageName = getContext().getPackageName();
                final long jAbs = Math.abs(packageName.hashCode());
                GameDownloadManager.bind(getActivity(), this.v, this.t, "", ConfigManager.AppConfig.appName(), this.u, z ? new GameDownloadStatusChangeListener() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.5
                    float a = 0.0f;

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onIdle() {
                        LogDevKitUtils.logUpgrade("onIdle");
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onDownloadStart() {
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onDownloadStart ");
                        AppUpgradeFragment.this.m.setProgress(0);
                        AppUpgradeFragment.this.n.setText("");
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onDownloadActive(GameDownloadShortInfo gameDownloadShortInfo, int i) {
                        float fA;
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onDownloadActive percent:" + i);
                        AppUpgradeFragment.this.a();
                        if (gameDownloadShortInfo != null) {
                            if (this.a == 0.0f) {
                                this.a = a((gameDownloadShortInfo.totalBytes / 1024.0f) / 1024.0f);
                            }
                            fA = a((gameDownloadShortInfo.currentBytes / 1024.0f) / 1024.0f);
                        } else {
                            fA = 0.0f;
                        }
                        if (this.a > 0.0f) {
                            AppUpgradeFragment.this.n.setText(fA + "MB/" + this.a + "MB");
                        }
                        AppUpgradeFragment.this.m.setProgress(i);
                    }

                    private float a(float f2) {
                        return new BigDecimal(f2).setScale(1, RoundingMode.HALF_UP).floatValue();
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onDownloadPaused(GameDownloadShortInfo gameDownloadShortInfo, int i) {
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onDownloadPaused");
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onDownloadFailed(GameDownloadShortInfo gameDownloadShortInfo) {
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onDownloadFailed");
                        AppUpgradeFragment.this.b();
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onInstalled(GameDownloadShortInfo gameDownloadShortInfo) {
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onInstalled");
                    }

                    @Override // com.ss.union.game.sdk.common.download.GameDownloadStatusChangeListener
                    public void onDownloadFinished(GameDownloadShortInfo gameDownloadShortInfo) {
                        LogDevKitUtils.logUpgrade("DownloadListenerForBtn onDownloadFinished");
                    }
                } : null);
                GameDownloadManager.setAutoInstall(getActivity(), true);
                PermissionFragment.a(true, new String[]{"android.permission.READ_EXTERNAL_STORAGE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE}, new LGRequestPermissionCallback() { // from class: com.ss.union.game.sdk.core.upgrade.fragment.AppUpgradeFragment.6
                    @Override // com.ss.union.game.sdk.core.base.init.callback.LGRequestPermissionCallback
                    public void onRequestPermissionResult(List<String> list, List<String> list2) {
                        if (list2 == null || list2.size() <= 0) {
                            GameDownloadManager.download(AppUpgradeFragment.this.getActivity(), AppUpgradeFragment.this.v, jAbs, 0L, -1L, "", packageName);
                        }
                    }
                });
            } else {
                a(getContext(), this.t);
            }
        } else {
            LogDevKitUtils.logUpgrade("start go to browser");
            a(getContext(), this.t);
        }
        if (!z) {
            LogDevKitUtils.logUpgrade("close upgrade dialog , isForce = false");
            back();
            if (getCallback() != null) {
                getCallback().b();
                return;
            }
            return;
        }
        LogDevKitUtils.logUpgrade("keep show upgrade dialog , isForce = true");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.g.setVisibility(8);
        this.o.setVisibility(8);
        this.l.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.g.setVisibility(8);
        this.o.setVisibility(0);
        this.l.setVisibility(8);
    }

    private void a(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            ToastUtils.getInstance().toast("启动浏览器失败");
        }
    }
}
