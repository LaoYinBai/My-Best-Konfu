package com.mbridge.msdk.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.y;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.widget.custom.DownloadMessageDialog;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBAlertDialog extends Dialog {
    private a a;
    private TextView b;
    private TextView c;
    private Button d;
    private Button e;
    private int f;
    private int g;

    public MBAlertDialog(Context context, final a aVar) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        requestWindowFeature(1);
        View viewInflate = LayoutInflater.from(context).inflate(m.a(context, "mbridge_cm_alertview", TtmlNode.TAG_LAYOUT), (ViewGroup) null);
        this.a = aVar;
        if (viewInflate != null) {
            setContentView(viewInflate);
            try {
                this.b = (TextView) viewInflate.findViewById(m.a(context, "mbridge_video_common_alertview_titleview", "id"));
            } catch (Exception e) {
                s.a("MBAlertDialog", e.getMessage());
            }
            try {
                this.c = (TextView) viewInflate.findViewById(m.a(context, "mbridge_video_common_alertview_contentview", "id"));
                this.d = (Button) viewInflate.findViewById(m.a(context, "mbridge_video_common_alertview_confirm_button", "id"));
                this.e = (Button) viewInflate.findViewById(m.a(context, "mbridge_video_common_alertview_cancel_button", "id"));
            } catch (Exception e2) {
                s.a("MBAlertDialog", e2.getMessage());
            }
        }
        if (this.e != null) {
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.dialog.MBAlertDialog.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (aVar != null) {
                        aVar.a();
                    }
                    MBAlertDialog.this.cancel();
                    MBAlertDialog.this.clear();
                }
            });
        }
        if (this.d != null) {
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.widget.dialog.MBAlertDialog.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (aVar != null) {
                        aVar.b();
                    }
                    MBAlertDialog.this.cancel();
                    MBAlertDialog.this.clear();
                }
            });
        }
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void clear() {
        if (this.a != null) {
            this.a = null;
        }
    }

    public a getListener() {
        return this.a;
    }

    public void setTitle(String str) {
        if (this.b != null) {
            this.b.setText(str);
        }
    }

    public void setContent(String str) {
        if (this.c != null) {
            this.c.setText(str);
        }
    }

    public void setConfirmText(String str) {
        if (this.d != null) {
            this.d.setText(str);
        }
    }

    public void setCancelText(String str) {
        if (this.e != null) {
            this.e.setText(str);
        }
    }

    private void a(String str, String str2, String str3, String str4) {
        setTitle(str);
        setContent(str2);
        setConfirmText(str3);
        setCancelText(str4);
    }

    public void makeRVAlertView(String str) {
        try {
            String string = y.b(getContext(), "MBridge_ConfirmTitle" + str, "").toString();
            String string2 = y.b(getContext(), "MBridge_ConfirmContent" + str, "").toString();
            String string3 = y.b(getContext(), "MBridge_CancelText" + str, "").toString();
            String string4 = y.b(getContext(), "MBridge_ConfirmText" + str, "").toString();
            com.mbridge.msdk.b.a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
            if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3) && TextUtils.isEmpty(string4)) {
                if (aVarB != null) {
                    a(aVarB.W(), aVarB.X(), aVarB.Y(), aVarB.Z());
                    return;
                }
                String language = Locale.getDefault().getLanguage();
                if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                    setTitle("确认关闭？");
                    setContent("关闭后您将不会获得任何奖励噢~ ");
                    setConfirmText("确认关闭");
                    setCancelText("继续观看");
                    return;
                }
                setTitle("Confirm to close? ");
                setContent("You will not be rewarded after closing the window");
                setConfirmText("Close it");
                setCancelText("Continue");
                return;
            }
            String language2 = Locale.getDefault().getLanguage();
            if (TextUtils.isEmpty(string)) {
                if (aVarB != null) {
                    string = aVarB.W();
                } else if (!TextUtils.isEmpty(language2) && language2.equals("zh")) {
                    setTitle("确认关闭？");
                } else {
                    setTitle("Confirm to close? ");
                }
            }
            if (TextUtils.isEmpty(string2)) {
                if (aVarB != null) {
                    string2 = aVarB.X();
                } else if (!TextUtils.isEmpty(language2) && language2.equals("zh")) {
                    setContent("关闭后您将不会获得任何奖励噢~ ");
                } else {
                    setContent("You will not be rewarded after closing the window");
                }
            }
            if (TextUtils.isEmpty(string4)) {
                if (aVarB != null) {
                    string4 = aVarB.Y();
                } else if (!TextUtils.isEmpty(language2) && language2.equals("zh")) {
                    setConfirmText("确认关闭");
                } else {
                    setConfirmText("Close it");
                }
            }
            if (TextUtils.isEmpty(string3)) {
                if (aVarB != null) {
                    string3 = aVarB.Z();
                } else if (!TextUtils.isEmpty(language2) && language2.equals("zh")) {
                    setCancelText("继续观看");
                } else {
                    setCancelText("Continue");
                }
            }
            a(string, string2, string4, string3);
        } catch (Exception e) {
            s.a("MBAlertDialog", e.getMessage());
        }
    }

    public void makeInstallAlert(String str) {
        setCancelable(false);
        String language = Locale.getDefault().getLanguage();
        if (!TextUtils.isEmpty(language) && language.equals("zh")) {
            setTitle("提示");
            if (TextUtils.isEmpty(str)) {
                setContent("应用已经安装完成，是否现在打开？");
            } else {
                setContent(str + " 已完成安装，是否现在打开？");
            }
            setConfirmText("取消");
            setCancelText("打开");
            return;
        }
        setTitle("Notification");
        if (TextUtils.isEmpty(str)) {
            setContent("app has been installed. Open it right now?");
        } else {
            setContent(str + " has been installed. Open it right now?");
        }
        setConfirmText("Cancel");
        setCancelText("Open");
    }

    public void makeDownloadAlert(String str) {
        setCancelable(true);
        String language = Locale.getDefault().getLanguage();
        if (!TextUtils.isEmpty(language) && language.equals("zh")) {
            setTitle("提示");
            if (TextUtils.isEmpty(str)) {
                setContent("确认下载吗？");
            } else {
                setContent("确认下载\"" + str + "\"吗？");
            }
            setConfirmText("取消");
            setCancelText("确认下载");
            return;
        }
        setTitle("Notification");
        if (TextUtils.isEmpty(str)) {
            setContent("Confirm to download?");
        } else {
            setContent("Confirm to download\"" + str + "\"?");
        }
        setConfirmText("Cancel");
        setCancelText("Confirm");
    }

    public void makePlayableAlertView() {
        com.mbridge.msdk.b.a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB != null) {
            a(aVarB.W(), aVarB.X(), aVarB.Y(), aVarB.aa());
            return;
        }
        String language = Locale.getDefault().getLanguage();
        if (!TextUtils.isEmpty(language) && language.equals("zh")) {
            setTitle("确认关闭？");
            setContent("关闭后您将不会获得任何奖励噢~ ");
            setConfirmText("确认关闭");
            setCancelText("继续试玩");
            return;
        }
        setTitle("Confirm to close? ");
        setContent("You will not be rewarded after closing the window");
        setConfirmText("Close it");
        setCancelText("Continue");
    }

    public void makeIVAlertView(int i, String str) {
        try {
            String string = y.b(getContext(), "MBridge_ConfirmTitle" + str, "").toString();
            String string2 = y.b(getContext(), "MBridge_ConfirmContent" + str, "").toString();
            String string3 = y.b(getContext(), "MBridge_CancelText" + str, "").toString();
            String string4 = y.b(getContext(), "MBridge_ConfirmText" + str, "").toString();
            if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2) || !TextUtils.isEmpty(string3) || !TextUtils.isEmpty(string4)) {
                a(string, string2, string3, string4);
            } else {
                String language = Locale.getDefault().getLanguage();
                if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                    setTitle(i == com.mbridge.msdk.foundation.same.a.v ? "确认关闭？" : "提示");
                    setContent(i == com.mbridge.msdk.foundation.same.a.v ? "如果你选择继续，结束后将会获得奖励。确认关闭吗？" : "如果你选择继续，结束后将会获得奖励。是否继续？");
                    setConfirmText(i == com.mbridge.msdk.foundation.same.a.v ? "确认关闭" : "取消");
                    setCancelText("继续");
                } else {
                    setTitle(i == com.mbridge.msdk.foundation.same.a.v ? "Confirm" : "Tips");
                    setContent(i == com.mbridge.msdk.foundation.same.a.v ? "If you choose to continue, you will receive a reward after the end. Confirm closed?" : "If you choose to continue, you will receive a reward after the end. Whether to continue?");
                    setConfirmText(i == com.mbridge.msdk.foundation.same.a.v ? "Close" : "Cancel");
                    setCancelText("Continue");
                }
            }
        } catch (Exception e) {
            s.a("MBAlertDialog", e.getMessage());
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        try {
            getWindow().setFlags(8, 8);
            super.show();
            hideNavigationBar(getWindow());
            getWindow().clearFlags(8);
        } catch (Exception e) {
            s.d("MBAlertDialog", e.getMessage());
            super.show();
        }
    }

    public void onlyShow() {
        super.show();
    }

    public void hideNavigationBar(Window window) {
        if (window != null) {
            window.setFlags(1024, 1024);
            if (Build.VERSION.SDK_INT >= 19) {
                window.addFlags(67108864);
                window.getDecorView().setSystemUiVisibility(Message.MESSAGE_APP);
            } else {
                window.getDecorView().setSystemUiVisibility(2);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setLayout(-1, -1);
            window.setGravity(17);
        }
    }

    public void setDialogWidthAndHeight(float f, float f2) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        if (DownloadMessageDialog.isScreenOrientationPortrait(getContext())) {
            this.g = displayMetrics.widthPixels;
            this.f = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = (int) (this.f * f2);
            attributes.gravity = 80;
            getWindow().setAttributes(attributes);
            return;
        }
        this.g = displayMetrics.heightPixels;
        this.f = displayMetrics.widthPixels;
        WindowManager.LayoutParams attributes2 = getWindow().getAttributes();
        attributes2.width = (int) (this.f * f);
        attributes2.height = -1;
        attributes2.gravity = 17;
        getWindow().setAttributes(attributes2);
    }
}
