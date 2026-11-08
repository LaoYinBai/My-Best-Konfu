package com.ss.union.game.sdk.core.vapp;

import com.ss.union.game.sdk.core.base.event.PageStater;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    public static void a() throws JSONException {
        a("close_floating", "close_floating_ball");
    }

    public static void b() throws JSONException {
        a("click_floating", "click_floating_ball");
    }

    public static void c() throws JSONException {
        a("click_floating", "click_floating_ball_ad");
    }

    public static void d() throws JSONException {
        a("ad_free", "click_ad_free");
    }

    public static void e() throws JSONException {
        a("ad_free", "click_close_floating_ball");
    }

    public static void f() {
        a("binding_window", "binding_window_show_gv");
    }

    public static void g() {
        a("binding_window", "binding_window_show_vg");
    }

    public static void h() {
        a("click_think", "click_think_gv");
    }

    public static void i() {
        a("click_think", "click_think_vg");
    }

    public static void j() {
        a("click_confirm", "click_confirm_gv");
    }

    public static void k() {
        a("click_confirm", "click_confirm_vg");
    }

    public static void l() {
        a("binding_window", "already_binding_window");
    }

    private static void a(String str, String str2) throws JSONException {
        PageStater.V1.onEvent(str, str2);
    }
}
