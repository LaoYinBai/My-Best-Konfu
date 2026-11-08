package com.ss.union.game.sdk.pay.b;

import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "ohayoo_sdk_pay";

    public static void a() throws JSONException {
        PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.b, "cashier_desk_window");
    }

    public static void b() throws JSONException {
        PageStater.V1.onEvent("create_order", "call_server_create_order");
    }

    public static void a(int i) throws JSONException {
        PageStater.V1.onEvent("create_order_result", "client_create_order_result", "fail", i);
    }

    public static void a(int i, int i2) throws JSONException {
        PageStater.V1.onEvent("create_order_result", "client_create_order_result", "fail", i, i2);
    }

    public static void c() throws JSONException {
        PageStater.V1.onEvent("create_order_result", "client_create_order_result", "success");
    }

    public static void d() throws JSONException {
        PageStater.V1.onEvent("click_button", "click_cancel_pay");
    }

    public static void a(String str, int i) throws JSONException {
        PageStater.V1.onEvent("click_button", "click_cancel_pay", str, i);
    }

    public static void b(String str, int i) throws JSONException {
        PageStater.V1.onEvent("pay_result", "client_pay_result", str, i);
    }

    public static void a(String str) {
        HashMap map = new HashMap();
        map.put("pay_request", str);
        PageStater.onEvent(a, map);
    }

    public static void e() {
        HashMap map = new HashMap();
        map.put("pay_show", "go_certify_show");
        PageStater.onEvent(a, map);
    }

    public static void f() {
        HashMap map = new HashMap();
        map.put("pay_show", "realname_certify_show");
        PageStater.onEvent(a, map);
    }

    public static void g() {
        HashMap map = new HashMap();
        map.put("pay_show", "pay_fail_show");
        PageStater.onEvent(a, map);
    }

    public static void h() {
        HashMap map = new HashMap();
        map.put("pay_click", "cancel_go_certify");
        PageStater.onEvent(a, map);
    }

    public static void i() {
        HashMap map = new HashMap();
        map.put("pay_click", "click_go_certify");
        PageStater.onEvent(a, map);
    }

    public static void j() {
        HashMap map = new HashMap();
        map.put("pay_click", "certify_return");
        PageStater.onEvent(a, map);
    }
}
