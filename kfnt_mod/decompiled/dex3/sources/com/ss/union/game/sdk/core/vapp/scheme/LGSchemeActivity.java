package com.ss.union.game.sdk.core.vapp.scheme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGSchemeActivity extends FragmentActivity {
    public static final int a = 1001;
    public static final int b = 9999;
    private a c;

    private boolean b() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = new a(getIntent());
        if (!this.c.a((Context) this)) {
            b.a("非法的intent，return");
            finish();
            return;
        }
        if (c()) {
            this.c.b(this);
            finish();
            return;
        }
        if (this.c.c(this)) {
            if (!b()) {
                Log.e("LGScheme", "游戏没有正常初始化，拉起失败，请打开游戏后重试");
                Intent intent = new Intent();
                intent.putExtra("status", 9999);
                intent.putExtra("msg", "游戏没有正常初始化，拉起失败，请打开游戏后重试");
                setResult(-1, intent);
                a();
                return;
            }
            LGSDKCore.addSdkInitCallback(new LGSdkInitCallback() { // from class: com.ss.union.game.sdk.core.vapp.scheme.LGSchemeActivity.1
                @Override // com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback
                public void onInitSuccess(String str, String str2, String str3, String str4) {
                    Log.e("LGScheme", "游戏初始化成功");
                    LGSchemeActivity.this.c.b(LGSchemeActivity.this);
                    LGSchemeActivity.this.finish();
                }

                @Override // com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback
                public void onInitFailed(int i, String str) {
                    Log.e("LGScheme", "游戏初始化失败");
                    if (i == -3) {
                        LGSchemeActivity.this.c.b(LGSchemeActivity.this);
                    }
                    LGSchemeActivity.this.finish();
                }
            });
        }
    }

    private void a() {
        finish();
        Log.e("LGScheme", "当前cp没有初始化sdk，准备销毁进程");
        Process.killProcess(Process.myPid());
    }

    private boolean c() {
        try {
            return LGSDKCore.isSdkInitSuccess();
        } catch (Exception unused) {
            return false;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            setResult(i2, intent);
            finish();
        }
    }
}
