package com.ss.union.game.sdk.core.base.account.constant;

import com.ss.union.game.sdk.account.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAccountConstant {
    public static final String MOBILE_AGREEMENT = "https://wap.cmpassport.com/resources/html/contract.html";
    public static String ONE_KEY_LOGIN_SETTING = "{\n    \"cm_config\":{\n        \"is_enable\":1,\n        \"need_data_mobile\":0,\n        \"timeout_sec\":3,\n        \"need_read_phone_permission\":0\n    },\n    \"ct_config\":{\n        \"is_enable\":1,\n        \"need_data_mobile\":1,\n        \"timeout_sec\":3,\n        \"need_read_phone_permission\":0\n    },\n    \"cu_config\":{\n        \"is_enable\":1,\n        \"need_data_mobile\":1,\n        \"timeout_sec\":3,\n        \"need_read_phone_permission\":0\n    }\n}";
    public static final String TELECOM_AGREEMENT = "https://e.189.cn/sdk/agreement/detail.do?hidetop=true";
    public static final String UNICOM_AGREEMENT = "https://opencloud.wostore.cn/authz/resource/html/disclaimer.html?fromsdk=true";

    public enum LoginType {
        LOGIN_TYPE_PHONE(0, "MOBILE_PHONE"),
        LOGIN_TYPE_GUEST(1, "GUEST"),
        LOGIN_TYPE_TT(2, a.al),
        LOGIN_TYPE_DY(3, a.am),
        LOGIN_TYPE_CLOUD_PHONE(4, a.an);

        final int a;
        final String b;

        LoginType(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public int getType() {
            return this.a;
        }

        public String getName() {
            return this.b;
        }
    }
}
