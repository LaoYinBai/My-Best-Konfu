package com.kwad.sdk.reward;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.widget.RewardTaskStepView;
import com.kwad.sdk.utils.t;
import com.kwad.sdk.utils.w;
import com.kwad.sdk.widget.KSCornerImageView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j extends com.kwad.sdk.h.e {
    public static j a = null;
    private static String d = "奖励还差 %s 步到手，\n确认放弃吗？";
    private AdTemplate b;
    private b c;

    public static class a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public String b;
        public String c;
        public String d;
        public com.kwad.sdk.reward.b.a.a e;
        public com.kwad.sdk.reward.b.kwai.a f;
        public String g;

        private a() {
        }

        static a a(AdInfo adInfo) {
            a aVar = new a();
            aVar.a = 3;
            aVar.b = com.kwad.sdk.core.response.a.a.aD(adInfo);
            aVar.g = com.kwad.sdk.core.response.a.a.aE(adInfo);
            return aVar;
        }

        static a a(com.kwad.sdk.reward.b.a.a aVar, AdTemplate adTemplate) {
            a aVar2 = new a();
            aVar2.a = 1;
            aVar2.e = aVar;
            aVar2.g = com.kwad.sdk.core.response.a.a.au(com.kwad.sdk.core.response.a.d.j(adTemplate));
            return aVar2;
        }

        static a a(com.kwad.sdk.reward.b.kwai.a aVar, AdTemplate adTemplate) {
            a aVar2 = new a();
            aVar2.a = 2;
            aVar2.f = aVar;
            aVar2.g = com.kwad.sdk.core.response.a.a.au(com.kwad.sdk.core.response.a.d.j(adTemplate));
            return aVar2;
        }

        static a a(String str) {
            a aVar = new a();
            aVar.a = 0;
            aVar.b = str;
            aVar.c = "关闭广告";
            aVar.d = "继续观看";
            return aVar;
        }

        static a b(AdInfo adInfo) {
            a aVar = new a();
            aVar.a = 4;
            AdProductInfo adProductInfoAK = com.kwad.sdk.core.response.a.a.aK(adInfo);
            aVar.b = com.kwad.sdk.core.response.a.a.s(adInfo);
            aVar.g = adProductInfoAK.getIcon();
            return aVar;
        }

        public static a b(String str) {
            a aVar = new a();
            aVar.a = 0;
            aVar.b = str;
            aVar.c = "奖励不要了";
            aVar.d = "返回";
            return aVar;
        }

        public static a c(String str) {
            a aVar = new a();
            try {
                aVar.parseJson(new JSONObject(str));
            } catch (JSONException unused) {
            }
            return aVar;
        }

        public int a() {
            return this.a;
        }

        @Override // com.kwad.sdk.core.response.kwai.a
        public void afterParseJson(@Nullable JSONObject jSONObject) {
            super.afterParseJson(jSONObject);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("mLaunchAppTask");
            if (jSONObjectOptJSONObject != null) {
                if (this.e == null) {
                    this.e = new com.kwad.sdk.reward.b.a.a();
                }
                this.e.parseJson(jSONObjectOptJSONObject);
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("mLandPageOpenTask");
            if (jSONObjectOptJSONObject2 != null) {
                if (this.f == null) {
                    this.f = new com.kwad.sdk.reward.b.kwai.a();
                }
                this.f.parseJson(jSONObjectOptJSONObject2);
            }
        }

        @Override // com.kwad.sdk.core.response.kwai.a
        public void afterToJson(JSONObject jSONObject) throws JSONException {
            super.afterToJson(jSONObject);
            if (this.e != null) {
                t.a(jSONObject, "mLaunchAppTask", this.e);
            }
            if (this.f != null) {
                t.a(jSONObject, "mLandPageOpenTask", this.f);
            }
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return TextUtils.isEmpty(this.c) ? "关闭广告" : this.c;
        }

        public String d() {
            return TextUtils.isEmpty(this.d) ? "继续观看" : this.d;
        }

        public com.kwad.sdk.reward.b.a.a e() {
            return this.e;
        }

        public com.kwad.sdk.reward.b.kwai.a f() {
            return this.f;
        }

        public String g() {
            return this.g;
        }
    }

    public interface b {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    public static class c implements b {
        @Override // com.kwad.sdk.reward.j.b
        public void a() {
        }

        @Override // com.kwad.sdk.reward.j.b
        public void b() {
        }

        @Override // com.kwad.sdk.reward.j.b
        public void c() {
        }

        @Override // com.kwad.sdk.reward.j.b
        public void d() {
        }

        @Override // com.kwad.sdk.reward.j.b
        public void e() {
        }
    }

    private static View a(final DialogFragment dialogFragment, LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar, AdTemplate adTemplate, final b bVar) throws Resources.NotFoundException {
        View viewInflate = layoutInflater.inflate(R.layout.ksad_reward_task_launch_app_dialog, viewGroup, false);
        com.kwad.sdk.reward.b.a.a aVarE = aVar.e();
        com.kwad.sdk.reward.b.a.a.a(aVarE, viewInflate.getContext(), adTemplate);
        ((RewardTaskStepView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_steps)).a(aVarE.m());
        KSImageLoader.loadAppIcon((ImageView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_icon), aVar.g(), adTemplate, 12);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_abandon);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_continue);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_title);
        String str = aVarE.l() + "";
        String str2 = String.format(d, str);
        int iIndexOf = str2.indexOf(str);
        if (iIndexOf < 0) {
            textView3.setText(str2);
        } else {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(viewInflate.getContext().getResources().getColor(R.color.ksad_reward_main_color));
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(foregroundColorSpan, iIndexOf, iIndexOf + 1, 17);
            textView3.setText(spannableString);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.b();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.c();
                }
            }
        });
        return viewInflate;
    }

    private static View a(final DialogFragment dialogFragment, LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar, final b bVar) {
        View viewInflate = layoutInflater.inflate(R.layout.ksad_video_close_dialog, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.ksad_title)).setText(aVar.b());
        TextView textView = (TextView) viewInflate.findViewById(R.id.ksad_close_btn);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.ksad_continue_btn);
        textView.setText(aVar.c());
        textView2.setText(aVar.d());
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.b();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.c();
                }
            }
        });
        return viewInflate;
    }

    public static a a(AdTemplate adTemplate, @Nullable String str, @Nullable com.kwad.sdk.reward.b.a.a aVar, @Nullable com.kwad.sdk.reward.b.kwai.a aVar2) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        return (!com.kwad.sdk.core.response.a.d.u(adTemplate) || aVar == null) ? (!com.kwad.sdk.core.response.a.d.v(adTemplate) || aVar2 == null) ? (!com.kwad.sdk.core.response.a.a.aH(adInfoJ) || com.kwad.sdk.core.response.a.a.aA(adInfoJ)) ? com.kwad.sdk.core.response.a.a.aI(adInfoJ) ? a.b(adInfoJ) : com.kwad.sdk.core.response.a.a.as(adInfoJ) ? a.b(str) : a.a(str) : a.a(adInfoJ) : a.a(aVar2, adTemplate) : a.a(aVar, adTemplate);
    }

    public static void a(Activity activity, AdTemplate adTemplate, a aVar, b bVar) {
        a = new j();
        Bundle bundle = new Bundle();
        bundle.putString("key_params_json", aVar.toJson().toString());
        bundle.putString("key_template_json", adTemplate.toJson().toString());
        a.setArguments(bundle);
        a.a(bVar);
        a.show(activity.getFragmentManager(), "videoCloseDialog");
    }

    private void a(b bVar) {
        this.c = bVar;
    }

    public static boolean a() {
        return a != null && a.b();
    }

    private static View b(final DialogFragment dialogFragment, LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar, AdTemplate adTemplate, final b bVar) throws Resources.NotFoundException {
        View viewInflate = layoutInflater.inflate(R.layout.ksad_reward_task_launch_app_dialog, viewGroup, false);
        com.kwad.sdk.reward.b.kwai.a aVarF = aVar.f();
        ((RewardTaskStepView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_steps)).a(aVarF.j());
        KSImageLoader.loadAppIcon((ImageView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_icon), aVar.g(), adTemplate, 12);
        TextView textView = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_abandon);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_continue);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.ksad_reward_task_dialog_title);
        String str = aVarF.i() + "";
        String str2 = String.format(d, str);
        int iIndexOf = str2.indexOf(str);
        if (iIndexOf < 0) {
            textView3.setText(str2);
        } else {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(viewInflate.getContext().getResources().getColor(R.color.ksad_reward_main_color));
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(foregroundColorSpan, iIndexOf, iIndexOf + 1, 17);
            textView3.setText(spannableString);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.b();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.c();
                }
            }
        });
        return viewInflate;
    }

    private static View b(final DialogFragment dialogFragment, LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar, final b bVar) throws Resources.NotFoundException {
        View viewInflate = layoutInflater.inflate(R.layout.ksad_reward_follow_dialog, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.ksad_reward_follow_dialog_title)).setText(aVar.b());
        viewInflate.findViewById(R.id.ksad_reward_follow_dialog_btn_close).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.c();
                }
            }
        });
        viewInflate.findViewById(R.id.ksad_reward_follow_dialog_btn_follow).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (bVar != null) {
                    bVar.d();
                }
            }
        });
        viewInflate.findViewById(R.id.ksad_reward_follow_dialog_btn_deny).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.b();
                }
            }
        });
        KSImageLoader.loadCircleIcon((ImageView) viewInflate.findViewById(R.id.ksad_reward_follow_dialog_icon), aVar.g(), viewInflate.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon));
        return viewInflate;
    }

    private static View c(final DialogFragment dialogFragment, LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar, AdTemplate adTemplate, final b bVar) {
        View viewInflate = layoutInflater.inflate(R.layout.ksad_reward_order_dialog, viewGroup, false);
        KSImageLoader.loadImage((KSCornerImageView) viewInflate.findViewById(R.id.ksad_reward_order_dialog_icon), aVar.g, adTemplate);
        ((TextView) viewInflate.findViewById(R.id.ksad_reward_order_dialog_desc)).setText(aVar.b());
        viewInflate.findViewById(R.id.ksad_reward_order_dialog_btn_close).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.c();
                }
            }
        });
        viewInflate.findViewById(R.id.ksad_reward_order_dialog_btn_view_detail).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (bVar != null) {
                    bVar.e();
                }
            }
        });
        viewInflate.findViewById(R.id.ksad_reward_order_dialog_btn_deny).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.reward.j.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialogFragment.dismiss();
                if (bVar != null) {
                    bVar.b();
                }
            }
        });
        return viewInflate;
    }

    @Override // com.kwad.sdk.h.e
    @Nullable
    public View a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) throws Resources.NotFoundException {
        View viewA;
        com.kwad.sdk.widget.c cVar;
        getDialog().requestWindowFeature(1);
        Bundle arguments = getArguments();
        String string = arguments.getString("key_params_json");
        try {
            String string2 = arguments.getString("key_template_json");
            this.b = new AdTemplate();
            this.b.parseJson(new JSONObject(string2));
        } catch (Throwable unused) {
        }
        a aVarC = a.c(string);
        switch (aVarC.a()) {
            case 1:
                viewA = a(this, layoutInflater, viewGroup, aVarC, this.b, this.c);
                break;
            case 2:
                viewA = b(this, layoutInflater, viewGroup, aVarC, this.b, this.c);
                break;
            case 3:
                viewA = b(this, layoutInflater, viewGroup, aVarC, this.c);
                cVar = new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aJ());
                w.a(cVar, (ViewGroup) viewA);
                break;
            case 4:
                viewA = c(this, layoutInflater, viewGroup, aVarC, this.b, this.c);
                cVar = new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aL());
                w.a(cVar, (ViewGroup) viewA);
                break;
            default:
                viewA = a(this, layoutInflater, viewGroup, aVarC, this.c);
                break;
        }
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.kwad.sdk.reward.j.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getAction() == 0;
            }
        });
        return viewA;
    }

    public boolean b() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        getDialog().setCanceledOnTouchOutside(false);
        window.setLayout(-1, -1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (this.c != null) {
            this.c.a();
        }
    }
}
