package com.ss.union.game.sdk.core.init.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameSDKOption {
    private long l;
    private int k = -1;
    public i a = new i();
    public g b = new g();
    public l c = new l();
    public e d = new e();
    public j e = new j();
    public c f = new c();
    public k g = new k();
    public d h = new d();
    public a i = new a();
    public f j = new f();

    private GameSDKOption() {
    }

    public static GameSDKOption a(JSONObject jSONObject) {
        try {
            GameSDKOption gameSDKOption = new GameSDKOption();
            gameSDKOption.k = jSONObject.optInt("status", -1);
            if (gameSDKOption.k != 0) {
                return null;
            }
            gameSDKOption.l = jSONObject.optLong("server_timestamp", System.currentTimeMillis());
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                return null;
            }
            gameSDKOption.a.a(jSONObjectOptJSONObject);
            gameSDKOption.b.a(jSONObjectOptJSONObject);
            gameSDKOption.c.a(jSONObjectOptJSONObject);
            gameSDKOption.d.a(jSONObjectOptJSONObject);
            gameSDKOption.e.a(jSONObjectOptJSONObject);
            gameSDKOption.f.a(jSONObjectOptJSONObject);
            gameSDKOption.g.a(jSONObjectOptJSONObject);
            gameSDKOption.h.a(jSONObjectOptJSONObject);
            gameSDKOption.i.a(jSONObjectOptJSONObject);
            gameSDKOption.j.a(jSONObjectOptJSONObject);
            return gameSDKOption;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static class i {
        private static final String a = "record_screen_switch";
        private boolean b = false;
        private int c = 0;
        private String d = "";

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(a)) == null) {
                return;
            }
            this.b = jSONObjectOptJSONObject.optBoolean("status", this.b);
            this.c = jSONObjectOptJSONObject.optInt("code", this.c);
            this.d = jSONObjectOptJSONObject.optString("message", this.d);
        }

        public boolean a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public String c() {
            return this.d;
        }
    }

    public static class g {
        private static final String a = "mv_switch";
        private boolean b = false;
        private int c = 0;
        private String d = "";

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(a)) == null) {
                return;
            }
            this.b = jSONObjectOptJSONObject.optBoolean("status", this.b);
            this.c = jSONObjectOptJSONObject.optInt("code", this.c);
            this.d = jSONObjectOptJSONObject.optString("message", this.d);
        }

        public boolean a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public String c() {
            return this.d;
        }
    }

    public static class e {
        private static final String a = "share_videos";
        private int b;
        private List<VideoShareConfig> c;

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(a)) == null) {
                return;
            }
            this.b = jSONObjectOptJSONObject.optInt("share_count");
            this.c = VideoShareConfig.a(jSONObjectOptJSONObject.optJSONArray("video_list"));
        }

        public int a() {
            return this.b;
        }

        public List<VideoShareConfig> b() {
            return this.c;
        }
    }

    public static class VideoShareConfig implements Parcelable {
        public static final Parcelable.Creator<VideoShareConfig> CREATOR = new Parcelable.Creator<VideoShareConfig>() { // from class: com.ss.union.game.sdk.core.init.bean.GameSDKOption.VideoShareConfig.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public VideoShareConfig createFromParcel(Parcel parcel) {
                return new VideoShareConfig(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public VideoShareConfig[] newArray(int i) {
                return new VideoShareConfig[i];
            }
        };
        static final String a = "video_id";
        static final String b = "video_url";
        static final String c = "cover_image";
        public static final int d = 1;
        public static final int e = 2;
        public int f;
        public String g;
        public String h;
        public int i;

        public static boolean a(int i) {
            return i == 2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        static List<VideoShareConfig> a(JSONArray jSONArray) throws JSONException {
            if (jSONArray == null) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    VideoShareConfig videoShareConfig = new VideoShareConfig();
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    videoShareConfig.f = jSONObject.optInt(a);
                    videoShareConfig.g = jSONObject.optString(b);
                    videoShareConfig.h = jSONObject.optString(c);
                    arrayList.add(videoShareConfig);
                } catch (JSONException unused) {
                }
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
            parcel.writeInt(this.i);
        }

        public VideoShareConfig() {
        }

        VideoShareConfig(Parcel parcel) {
            this.f = parcel.readInt();
            this.g = parcel.readString();
            this.h = parcel.readString();
            this.i = parcel.readInt();
        }
    }

    public static class l {
        private static final String g = "video_config";
        private static final String h = "editing_config";
        private static final String i = "effect";
        private static final String j = "bgm";
        private static final String k = "use_huawei_encoding";
        private static final String l = "sharing_topic";
        private static final String m = "download_url";
        private static final String n = "checksum";
        public boolean a = true;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(g)) == null) {
                return;
            }
            this.b = jSONObjectOptJSONObject.optString(l);
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(h);
            if (jSONObjectOptJSONObject2 != null) {
                this.a = jSONObjectOptJSONObject2.optBoolean(k, true);
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject(i);
                if (jSONObjectOptJSONObject3 != null) {
                    this.c = jSONObjectOptJSONObject3.optString("download_url");
                    this.d = jSONObjectOptJSONObject3.optString(n);
                }
                JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject2.optJSONObject(j);
                if (jSONObjectOptJSONObject3 != null) {
                    this.e = jSONObjectOptJSONObject4.optString("download_url");
                    this.f = jSONObjectOptJSONObject4.optString(n);
                }
            }
        }

        JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(l, this.b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(k, this.a);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("download_url", this.c);
                jSONObject3.put(n, this.d);
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("download_url", this.e);
                jSONObject4.put(n, this.f);
                jSONObject2.put(i, jSONObject3);
                jSONObject2.put(j, jSONObject4);
                jSONObject.put(h, jSONObject2);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public static class j {
        private static final String d = "splash_ad";
        private static final String e = "group";
        private static final String f = "enable";
        private static final String g = "frequency";
        public String a;
        public int b;
        public int c;
        private JSONObject h;
        private boolean i;

        void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(d);
                    if (jSONObjectOptJSONObject != null) {
                        this.h = jSONObjectOptJSONObject;
                        this.a = jSONObjectOptJSONObject.optString(e);
                        this.b = jSONObjectOptJSONObject.optInt(f);
                        this.c = jSONObjectOptJSONObject.optInt(g);
                        if ("A".equals(this.a) || "B1".equals(this.a) || "B2".equals(this.a) || "B3".equals(this.a)) {
                            this.i = true;
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }

        String a() {
            return this.h != null ? this.h.toString() : "";
        }

        public boolean b() {
            return this.b == 1;
        }

        public boolean c() {
            return this.i;
        }

        public a d() {
            if (TextUtils.isEmpty(this.a) || (!this.a.equals("A") && !this.a.equals("B1") && !this.a.equals("B2") && !this.a.equals("B3"))) {
                return a.A;
            }
            return a.valueOf(this.a);
        }

        public enum a {
            A("A"),
            B1("B1"),
            B2("B2"),
            B3("B3");

            String e;

            a(String str) {
                this.e = str;
            }

            public String a() {
                return this.e;
            }
        }
    }

    public static class c {
        private static final String d = "anti_addiction_config";
        public b a = new b();
        public b b = new b();
        public h c = new h();

        public void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(d)) == null) {
                return;
            }
            this.a.a(jSONObjectOptJSONObject.optJSONObject("account_config"));
            this.b.a(jSONObjectOptJSONObject.optJSONObject("device_config"));
            this.c.a(jSONObjectOptJSONObject.optJSONObject("trade_config"));
        }
    }

    public static class b {
        public boolean a = true;
        public boolean b = false;
        public int c;

        void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("identify_validate_popup_config");
                if (jSONObjectOptJSONObject != null) {
                    this.a = jSONObjectOptJSONObject.optBoolean(PointCategory.SHOW, true);
                    this.b = jSONObjectOptJSONObject.optBoolean("closable", false);
                }
                this.c = jSONObject.optInt("device_identify_link_expire", this.c);
            }
        }
    }

    public static class h {
        public boolean a = false;

        void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optBoolean("anti_addiction_enable", this.a);
            }
        }
    }

    public static class k {
        private static final String d = "vapp_config";
        public boolean a;
        public String b;
        public String c;

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(d)) == null) {
                return;
            }
            this.a = jSONObjectOptJSONObject.optBoolean(PointCategory.SHOW);
            this.c = jSONObjectOptJSONObject.optString("click_url");
            this.b = jSONObjectOptJSONObject.optString("icon");
        }
    }

    public static class d {
        public static final String a = "ApkUrl";
        public static final String b = "LandingPage";
        private static final String h = "upgrade_config";
        public String c;
        public String d;
        public String e;
        public String f;
        public boolean g;

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(h)) == null) {
                return;
            }
            this.c = jSONObjectOptJSONObject.optString(DBDefinition.SEGMENT_INFO, "");
            this.d = jSONObjectOptJSONObject.optString("version", "");
            this.e = jSONObjectOptJSONObject.optString("upgrade_type", "");
            this.f = jSONObjectOptJSONObject.optString("url", "");
            this.g = jSONObjectOptJSONObject.optBoolean("is_forced", false);
        }
    }

    public static class a {
        public String a;

        void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString("age_tips", "");
            }
        }
    }

    public static class f {
        public static final String a = "award_enable";
        public static final String b = "award_name";
        public static final String c = "award_icon";
        public static final String d = "award_custom_info";
        public boolean e;
        public String f;
        public String g;
        public String h;

        void a(JSONObject jSONObject) {
            JSONObject jSONObjectOptJSONObject;
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("identify_award_config")) == null) {
                return;
            }
            this.e = jSONObjectOptJSONObject.optBoolean(a);
            this.f = jSONObjectOptJSONObject.optString(b, "");
            this.g = jSONObjectOptJSONObject.optString(c, "");
            this.h = jSONObjectOptJSONObject.optString(d, "");
        }
    }
}
