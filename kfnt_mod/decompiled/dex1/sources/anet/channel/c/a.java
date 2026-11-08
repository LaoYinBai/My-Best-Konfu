package anet.channel.c;

import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.strategy.c;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import com.taobao.orange.OrangeConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements anetwork.channel.b.a {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f412a = false;

    static {
        try {
            Class.forName("com.taobao.orange.OrangeConfig");
            f412a = true;
        } catch (Exception unused) {
            f412a = false;
        }
    }

    @Override // anetwork.channel.b.a
    public void register() {
        if (!f412a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", null, new Object[0]);
            return;
        }
        try {
            OrangeConfig.getInstance().registerListener(new String[]{"networkSdk"}, new b(this));
            getConfig("networkSdk", "network_empty_scheme_https_switch", "true");
        } catch (Exception e) {
            ALog.e("awcn.OrangeConfigImpl", "register fail", null, e, new Object[0]);
        }
    }

    @Override // anetwork.channel.b.a
    public void unRegister() {
        if (!f412a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", null, new Object[0]);
        } else {
            OrangeConfig.getInstance().unregisterListener(new String[]{"networkSdk"});
        }
    }

    public String getConfig(String... strArr) {
        if (!f412a) {
            ALog.w("awcn.OrangeConfigImpl", "no orange sdk", null, new Object[0]);
            return null;
        }
        try {
            return OrangeConfig.getInstance().getConfig(strArr[0], strArr[1], strArr[2]);
        } catch (Exception e) {
            ALog.e("awcn.OrangeConfigImpl", "get config failed!", null, e, new Object[0]);
            return null;
        }
    }

    public void onConfigUpdate(String str) {
        if ("networkSdk".equals(str)) {
            ALog.i("awcn.OrangeConfigImpl", "onConfigUpdate", null, "namespace", str);
            try {
                c.a.f541a.a(Boolean.valueOf(getConfig(str, "network_empty_scheme_https_switch", "true")).booleanValue());
            } catch (Exception unused) {
            }
            try {
                anetwork.channel.b.b.a(Boolean.valueOf(getConfig(str, "network_spdy_enable_switch", "true")).booleanValue());
            } catch (Exception unused2) {
            }
            try {
                anetwork.channel.b.b.b(Boolean.valueOf(getConfig(str, "network_http_cache_switch", "true")).booleanValue());
            } catch (Exception unused3) {
            }
            try {
                String config = getConfig(str, "network_http_cache_flag", null);
                if (config != null) {
                    anetwork.channel.b.b.a(Long.valueOf(config).longValue());
                }
            } catch (Exception unused4) {
            }
            try {
                AwcnConfig.setHttpsSniEnable(Boolean.valueOf(getConfig(str, "network_https_sni_enable_switch", "true")).booleanValue());
            } catch (Exception unused5) {
            }
            try {
                AwcnConfig.setAccsSessionCreateForbiddenInBg(Boolean.valueOf(getConfig(str, "network_accs_session_bg_switch", "true")).booleanValue());
            } catch (Exception unused6) {
            }
            try {
                anetwork.channel.b.b.a(Integer.valueOf(getConfig(str, "network_request_statistic_sample_rate", "10000")).intValue());
            } catch (Exception unused7) {
            }
            try {
                String config2 = getConfig(str, "network_request_forbidden_bg", null);
                if (!TextUtils.isEmpty(config2)) {
                    anetwork.channel.b.b.c(Boolean.valueOf(config2).booleanValue());
                }
            } catch (Exception unused8) {
            }
            try {
                anetwork.channel.b.b.a(getConfig(str, "network_url_white_list_bg", null));
            } catch (Exception unused9) {
            }
            try {
                String config3 = getConfig(str, "network_amdc_preset_hosts", null);
                if (!TextUtils.isEmpty(config3)) {
                    anetwork.channel.b.b.b(config3);
                }
            } catch (Exception unused10) {
            }
            try {
                AwcnConfig.setHorseRaceEnable(Boolean.valueOf(getConfig(str, "network_horse_race_switch", "true")).booleanValue());
            } catch (Exception unused11) {
            }
            try {
                AwcnConfig.setTnetHeaderCacheEnable(Boolean.valueOf(getConfig(str, "tnet_enable_header_cache", "true")).booleanValue());
            } catch (Exception unused12) {
            }
            try {
                String config4 = getConfig(str, "network_quic_enable_switch", null);
                if (!TextUtils.isEmpty(config4)) {
                    AwcnConfig.setQuicEnable(Boolean.valueOf(config4).booleanValue());
                }
            } catch (Exception unused13) {
            }
            try {
                anetwork.channel.b.b.d(Boolean.valueOf(getConfig(str, "network_response_buffer_switch", "true")).booleanValue());
            } catch (Exception unused14) {
            }
            try {
                String config5 = getConfig(str, "network_get_session_async_switch", null);
                if (!TextUtils.isEmpty(config5)) {
                    anetwork.channel.b.b.e(Boolean.valueOf(config5).booleanValue());
                }
            } catch (Exception unused15) {
            }
            try {
                String config6 = getConfig(str, "network_bg_forbid_request_threshold", null);
                if (!TextUtils.isEmpty(config6)) {
                    int iIntValue = Integer.valueOf(config6).intValue();
                    if (iIntValue < 0) {
                        iIntValue = 0;
                    }
                    anetwork.channel.b.b.b(iIntValue);
                }
            } catch (Exception unused16) {
            }
            try {
                String config7 = getConfig(str, "network_normal_thread_pool_executor_size", null);
                if (!TextUtils.isEmpty(config7)) {
                    ThreadPoolExecutorFactory.setNormalExecutorPoolSize(Integer.valueOf(config7).intValue());
                }
            } catch (Exception unused17) {
            }
            try {
                String config8 = getConfig(str, "network_idle_session_close_switch", null);
                if (!TextUtils.isEmpty(config8)) {
                    AwcnConfig.setIdleSessionCloseEnable(Boolean.valueOf(config8).booleanValue());
                }
            } catch (Exception unused18) {
            }
            try {
                String config9 = getConfig(str, "network_monitor_requests", null);
                if (!TextUtils.isEmpty(config9)) {
                    anetwork.channel.b.b.c(config9);
                }
            } catch (Exception unused19) {
            }
            try {
                String config10 = getConfig(str, "network_session_preset_hosts", null);
                if (!TextUtils.isEmpty(config10)) {
                    AwcnConfig.registerPresetSessions(config10);
                }
            } catch (Exception unused20) {
            }
            try {
                String config11 = getConfig(str, "network_ipv6_blacklist_switch", null);
                if (!TextUtils.isEmpty(config11)) {
                    AwcnConfig.setIpv6BlackListEnable(Boolean.valueOf(config11).booleanValue());
                }
            } catch (Exception unused21) {
            }
            try {
                String config12 = getConfig(str, "network_ipv6_blacklist_ttl", null);
                if (!TextUtils.isEmpty(config12)) {
                    AwcnConfig.setIpv6BlackListTtl(Long.valueOf(config12).longValue());
                }
            } catch (Exception unused22) {
            }
            try {
                String config13 = getConfig(str, "network_url_degrade_list", null);
                if (!TextUtils.isEmpty(config13)) {
                    anetwork.channel.b.b.d(config13);
                }
            } catch (Exception unused23) {
            }
            try {
                String config14 = getConfig(str, "network_delay_retry_request_no_network", null);
                if (TextUtils.isEmpty(config14)) {
                    return;
                }
                anetwork.channel.b.b.f(Boolean.valueOf(config14).booleanValue());
            } catch (Exception unused24) {
            }
        }
    }
}
