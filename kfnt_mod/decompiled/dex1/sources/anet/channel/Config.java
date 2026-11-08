package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.security.ISecurity;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Config {

    /* renamed from: b, reason: collision with root package name */
    private String f364b;

    /* renamed from: c, reason: collision with root package name */
    private String f365c;
    private ENV d = ENV.ONLINE;
    private ISecurity e;

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Config> f363a = new HashMap();
    public static final Config DEFAULT_CONFIG = new Builder().setTag("[default]").setAppkey("[default]").setEnv(ENV.ONLINE).build();

    protected Config() {
    }

    public static Config getConfigByTag(String str) {
        Config config;
        synchronized (f363a) {
            config = f363a.get(str);
        }
        return config;
    }

    public static Config getConfig(String str, ENV env) {
        synchronized (f363a) {
            for (Config config : f363a.values()) {
                if (config.d == env && config.f365c.equals(str)) {
                    return config;
                }
            }
            return null;
        }
    }

    public String getTag() {
        return this.f364b;
    }

    public String getAppkey() {
        return this.f365c;
    }

    public ENV getEnv() {
        return this.d;
    }

    public ISecurity getSecurity() {
        return this.e;
    }

    public String toString() {
        return this.f364b;
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private String f366a;

        /* renamed from: b, reason: collision with root package name */
        private String f367b;

        /* renamed from: c, reason: collision with root package name */
        private ENV f368c = ENV.ONLINE;
        private String d;
        private String e;

        public Builder setTag(String str) {
            this.f366a = str;
            return this;
        }

        public Builder setAppkey(String str) {
            this.f367b = str;
            return this;
        }

        public Builder setEnv(ENV env) {
            this.f368c = env;
            return this;
        }

        public Builder setAuthCode(String str) {
            this.d = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.e = str;
            return this;
        }

        public Config build() {
            if (!TextUtils.isEmpty(this.f367b)) {
                for (Config config : Config.f363a.values()) {
                    if (config.d == this.f368c && config.f365c.equals(this.f367b)) {
                        ALog.w("awcn.Config", "duplicated config exist!", null, "appkey", this.f367b, "env", this.f368c);
                        if (!TextUtils.isEmpty(this.f366a)) {
                            synchronized (Config.f363a) {
                                Config.f363a.put(this.f366a, config);
                            }
                        }
                        return config;
                    }
                }
                Config config2 = new Config();
                config2.f365c = this.f367b;
                config2.d = this.f368c;
                if (TextUtils.isEmpty(this.f366a)) {
                    config2.f364b = StringUtils.concatString(this.f367b, "$", this.f368c.toString());
                } else {
                    config2.f364b = this.f366a;
                }
                if (!TextUtils.isEmpty(this.e)) {
                    config2.e = anet.channel.security.c.a().createNonSecurity(this.e);
                } else {
                    config2.e = anet.channel.security.c.a().createSecurity(this.d);
                }
                synchronized (Config.f363a) {
                    Config.f363a.put(config2.f364b, config2);
                }
                return config2;
            }
            throw new RuntimeException("appkey can not be null or empty!");
        }
    }
}
