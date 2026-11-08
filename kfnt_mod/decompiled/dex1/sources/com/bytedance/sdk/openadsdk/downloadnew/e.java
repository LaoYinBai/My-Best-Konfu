package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile e f5311a;

    /* renamed from: b, reason: collision with root package name */
    private final Context f5312b;

    private e(Context context) {
        this.f5312b = context;
    }

    public static e a(Context context) {
        if (f5311a == null) {
            synchronized (e.class) {
                if (f5311a == null) {
                    f5311a = new e(context);
                }
            }
        }
        return f5311a;
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.a, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T callMethod(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 0:
                return (T) Boolean.valueOf(d.a(getActivity(map.get(TTDownloadField.TT_ACTIVITY)), e(map.get(TTDownloadField.TT_EXIT_INSTALL_LISTENER))));
            case 1:
                return (T) d.a().getSDKVersion();
            case 2:
                try {
                    return (T) Boolean.valueOf(d.a((String) map.get(TTDownloadField.TT_TAG_INTERCEPT), (String) map.get(TTDownloadField.TT_LABEL), new JSONObject((String) map.get(TTDownloadField.TT_META)), new HashMap()));
                } catch (JSONException unused) {
                    return (T) Boolean.FALSE;
                }
            case 3:
                d.a(((Integer) map.get(TTDownloadField.TT_HID)).intValue());
                return null;
            case 4:
                d.a().unbind((String) map.get(TTDownloadField.TT_DOWNLOAD_URL), ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue());
                return null;
            case 5:
                int iIntValue = ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue();
                DownloadModel downloadModelB = b(map.get(TTDownloadField.TT_DOWNLOAD_MODEL));
                d.a().bind(this.f5312b, iIntValue, a(map.get(TTDownloadField.TT_DOWNLOAD_STATUSCHANGE_LISTENER)), downloadModelB);
                return null;
            case 6:
                return (T) Boolean.valueOf(d.a(this.f5312b, (String) map.get(TTDownloadField.TT_DOWNLOAD_URL)));
            case 7:
                d.b();
                return null;
            case 8:
                d.a().cancel((String) map.get(TTDownloadField.TT_DOWNLOAD_URL), ((Boolean) map.get(TTDownloadField.TT_FORCE)).booleanValue());
                return null;
            case 9:
                d.a(((Integer) map.get("id")).intValue(), (ITTDownloadAdapter.OnEventLogHandler) map.get(TTDownloadField.TT_ONEVENT_LOG_HANDLER));
                return null;
            case 10:
                d.a((String) map.get(TTDownloadField.TT_DOWNLOAD_PATH));
                return null;
            case 11:
            default:
                return (T) super.callMethod(cls, i, map);
            case 12:
                return (T) Boolean.valueOf(d.a(this.f5312b, (Uri) map.get("uri"), b(map.get(TTDownloadField.TT_DOWNLOAD_MODEL)), c(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG)), d(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER))));
            case 13:
                int iIntValue2 = ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue();
                boolean zBooleanValue = ((Boolean) map.get(TTDownloadField.TT_IS_DISABLE_DIALOG)).booleanValue();
                d.a().getAdWebViewDownloadManager().tryStartDownload(this.f5312b, (String) map.get(TTDownloadField.TT_USERAGENT), zBooleanValue, b(map.get(TTDownloadField.TT_DOWNLOAD_MODEL)), c(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG)), d(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER)), a(map.get(TTDownloadField.TT_DOWNLOAD_STATUSCHANGE_LISTENER)), iIntValue2);
                return null;
            case 14:
                return (T) Boolean.valueOf(d.a().getAdWebViewDownloadManager().bind(this.f5312b, ((Long) map.get("id")).longValue(), (String) map.get(TTDownloadField.TT_LOG_EXTRA), (DownloadStatusChangeListener) null, ((Integer) map.get(TTDownloadField.TT_HASHCODE)).intValue()));
            case 15:
                return (T) Boolean.valueOf(d.a((Uri) map.get("uri")));
            case 16:
                d.a().action((String) map.get(TTDownloadField.TT_DOWNLOAD_URL), ((Long) map.get("id")).longValue(), ((Integer) map.get(TTDownloadField.TT_ACTION_TYPE_BUTTON)).intValue(), c(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG)), d(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER)));
                return null;
            case 17:
                d.a().action((String) map.get(TTDownloadField.TT_DOWNLOAD_URL), ((Long) map.get("id")).longValue(), ((Integer) map.get(TTDownloadField.TT_ACTION_TYPE_BUTTON)).intValue(), c(map.get(TTDownloadField.TT_DOWNLOAD_EVENT_CONFIG)), d(map.get(TTDownloadField.TT_DOWNLOAD_CONTROLLER)), f(map.get(TTDownloadField.TT_ITEM_CLICK_LISTENER)), g(map.get(TTDownloadField.TT_DOWNLOAD_BUTTON_CLICK_LISTENER)));
                return null;
        }
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.a, com.bytedance.sdk.openadsdk.TTAdBridge
    public <T> T getObj(Class<T> cls, int i, Map<String, Object> map) {
        switch (i) {
            case 0:
                return (T) d.f5296a;
            case 1:
                return (T) Boolean.valueOf(d.f5297b);
            default:
                return (T) super.getObj(cls);
        }
    }

    @Override // com.bytedance.sdk.openadsdk.downloadnew.a, com.bytedance.sdk.openadsdk.TTAdBridge
    public void init(Bundle bundle) {
        super.init(bundle);
        d.a(this.f5312b);
    }

    private DownloadStatusChangeListener a(Object obj) {
        if (obj instanceof DownloadStatusChangeListener) {
            return (DownloadStatusChangeListener) obj;
        }
        return null;
    }

    private DownloadModel b(Object obj) {
        if (obj instanceof DownloadModel) {
            return (DownloadModel) obj;
        }
        return null;
    }

    private DownloadEventConfig c(Object obj) {
        if (obj instanceof DownloadEventConfig) {
            return (DownloadEventConfig) obj;
        }
        return null;
    }

    private DownloadController d(Object obj) {
        if (obj instanceof DownloadController) {
            return (DownloadController) obj;
        }
        return null;
    }

    private Activity getActivity(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        }
        return null;
    }

    private ExitInstallListener e(Object obj) {
        if (obj instanceof ExitInstallListener) {
            return (ExitInstallListener) obj;
        }
        return null;
    }

    private OnItemClickListener f(Object obj) {
        if (obj instanceof OnItemClickListener) {
            return (OnItemClickListener) obj;
        }
        return null;
    }

    private IDownloadButtonClickListener g(Object obj) {
        if (obj instanceof IDownloadButtonClickListener) {
            return (IDownloadButtonClickListener) obj;
        }
        return null;
    }
}
