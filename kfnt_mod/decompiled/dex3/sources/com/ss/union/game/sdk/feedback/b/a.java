package com.ss.union.game.sdk.feedback.b;

import android.app.Activity;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.feedback.LGSDKFeedbackService;
import com.ss.union.game.sdk.feedback.callback.LGUserFeedbackCallback;
import com.ss.union.game.sdk.feedback.fragment.LGUserFeedbackFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements LGSDKFeedbackService {
    private static volatile a a;

    private a() {
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    @Override // com.ss.union.game.sdk.feedback.LGSDKFeedbackService
    public void openUserFeedback(Activity activity, LGUserFeedbackCallback lGUserFeedbackCallback) {
        if (!LGAccountDataUtil.isLogined()) {
            if (lGUserFeedbackCallback != null) {
                lGUserFeedbackCallback.onOpenFail(-4001, "当前未登录，请先登录");
            }
        } else {
            LGUserFeedbackFragment.a(activity, lGUserFeedbackCallback);
            if (lGUserFeedbackCallback != null) {
                lGUserFeedbackCallback.onOpenSuccess();
            }
        }
    }
}
