package com.ss.union.game.sdk.nick.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.account.callback.INickNameUpdate;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.StringUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.nick.a.a;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UpdateNickNameFragment extends BaseFragment {
    public static final String a = "key_from";
    private static final int b = 14;
    private a c;
    private INickNameUpdate d;
    private int e = 1;
    private TextView f;
    private EditText g;
    private ImageView h;
    private TextView i;
    private TextView j;
    private ImageView k;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_update_nick_name";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void a(int i, a aVar) {
        new OperationBuilder(a(i, aVar, null)).show();
    }

    public static void a(int i, INickNameUpdate iNickNameUpdate) {
        new OperationBuilder(a(i, null, iNickNameUpdate)).cancelable(true).show();
    }

    private static UpdateNickNameFragment a(int i, a aVar, INickNameUpdate iNickNameUpdate) {
        UpdateNickNameFragment updateNickNameFragment = new UpdateNickNameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(a, i);
        updateNickNameFragment.setArguments(bundle);
        updateNickNameFragment.a(iNickNameUpdate);
        updateNickNameFragment.a(aVar);
        return updateNickNameFragment;
    }

    private void a(a aVar) {
        this.c = aVar;
    }

    private void a(INickNameUpdate iNickNameUpdate) {
        this.d = iNickNameUpdate;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        this.e = bundle.getInt(a, this.e);
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.f = (TextView) findViewById("lg_nick_name_update_description_tv");
        this.g = (EditText) findViewById("lg_input_nick_et");
        this.h = (ImageView) findViewById("lg_nick_name_random_btn");
        this.i = (TextView) findViewById("lg_nick_name_confirm");
        this.j = (TextView) findViewById("lg_nick_name_title_tv");
        this.k = (ImageView) findViewById("lg_nick_name_close_iv");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        boolean zIsVisitor = LGAccountDataUtil.isVisitor();
        if (e()) {
            this.j.setText(ResourceUtils.getString("lg_nick_name_update"));
            this.k.setVisibility(0);
            this.f.setText(ResourceUtils.getString("lg_nick_name_update_description"));
            c(LGAccountDataUtil.getCurrentUserLocal().nick_name);
        } else {
            this.k.setVisibility(8);
            this.f.setText(ResourceUtils.getString(zIsVisitor ? "lg_nick_name_update_visitor_description" : "lg_nick_name_update_description"));
            if (zIsVisitor) {
                c(LGAccountDataUtil.getCurrentUserLocal().nick_name);
            }
        }
        a(this.g.getText().toString());
        com.ss.union.game.sdk.nick.b.a.a(this.e);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UpdateNickNameFragment.this.c();
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UpdateNickNameFragment.this.d();
            }
        });
        this.g.addTextChangedListener(new TextWatcher() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String string = editable.toString();
                if (StringUtils.getStrCharactersLength(string) > 14) {
                    int indexByMaxCharLimit = StringUtils.getIndexByMaxCharLimit(string, 14);
                    UpdateNickNameFragment.this.g.setText(string.subSequence(0, indexByMaxCharLimit));
                    Selection.setSelection(UpdateNickNameFragment.this.g.getText(), indexByMaxCharLimit);
                }
                UpdateNickNameFragment.this.a(string);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UpdateNickNameFragment.this.back();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.i.setEnabled(str.length() > 0);
        a();
    }

    private void a() {
        if (this.i.isEnabled()) {
            this.i.setTextColor(Color.parseColor("#000000"));
        } else {
            this.i.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private String b() {
        return this.g.getText().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (getActivity() == null) {
            com.ss.union.game.sdk.nick.a.a("randomNickName activity is null...");
            return;
        }
        showLoading();
        com.ss.union.game.sdk.nick.b.a.b(this.e);
        com.ss.union.game.sdk.nick.c.a.a(new CoreNetCallback<String>() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.5
            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            public void onError(int i, String str) {
                UpdateNickNameFragment.this.hideLoading();
                com.ss.union.game.sdk.nick.a.a("code : " + i + "--message = " + str);
                ToastUtils.getInstance().toast(str);
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("onSuccess is MainThread = ");
                sb.append(Looper.myLooper() == Looper.getMainLooper());
                com.ss.union.game.sdk.nick.a.a(sb.toString());
                UpdateNickNameFragment.this.hideLoading();
                UpdateNickNameFragment.this.c(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        String strB = b();
        if (b(strB)) {
            return;
        }
        showLoading();
        com.ss.union.game.sdk.nick.b.a.c(this.e);
        com.ss.union.game.sdk.nick.c.a.a(strB, new CoreNetCallback<String>() { // from class: com.ss.union.game.sdk.nick.fragment.UpdateNickNameFragment.6
            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            public void onError(int i, String str) {
                UpdateNickNameFragment.this.hideLoading();
                com.ss.union.game.sdk.nick.a.a("code : " + i + "--message = " + str);
                ToastUtils.getInstance().toast(str);
                if (UpdateNickNameFragment.this.d != null) {
                    UpdateNickNameFragment.this.d.onFail(i, str);
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                UpdateNickNameFragment.this.hideLoading();
                ToastUtils.getInstance().toast(ResourceUtils.getString("lg_update_success"));
                UpdateNickNameFragment.this.back();
                if (UpdateNickNameFragment.this.d != null) {
                    UpdateNickNameFragment.this.d.onSuccess(str);
                }
            }
        });
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_nick_warning"));
            return true;
        }
        if (!Pattern.matches("[a-zA-Z0-9一-龥]*", str)) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_nick_name_combination_rule"));
            return true;
        }
        if (StringUtils.getStrCharactersLength(str) <= 14) {
            return false;
        }
        ToastUtils.getInstance().toast(ResourceUtils.getString("lg_nick_name_char_length_limit"));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        try {
            if (getActivity() == null || getActivity().isFinishing() || this.g == null) {
                return;
            }
            this.g.setText(str);
            Selection.setSelection(this.g.getText(), this.g.getText().length());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public void back() {
        super.back();
        if (this.c != null) {
            this.c.a();
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        if (e()) {
            return super.onBackPressed();
        }
        return true;
    }

    private boolean e() {
        return 4 == this.e;
    }
}
