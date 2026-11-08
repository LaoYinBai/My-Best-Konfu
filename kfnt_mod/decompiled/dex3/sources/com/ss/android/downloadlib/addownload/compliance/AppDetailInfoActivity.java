package com.ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.model.AppInfoX;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.union.game.sdk.core.base.R;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppDetailInfoActivity extends Activity {
    private long cid;
    private long infoId;
    private ImageView ivBack;
    private LinearLayout llDownload;
    private List<Pair<String, String>> permissions;
    private RecyclerView recyclerView;
    private TextView tvEmpty;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttdownloader_activity_app_detail_info);
        if (initData()) {
            initView();
        } else {
            AppDownloadUtils.safeFinish(this);
        }
    }

    public static void start(Activity activity, long j) {
        Intent intent = new Intent(activity, (Class<?>) AppDetailInfoActivity.class);
        intent.putExtra(TTDelegateActivity.APP_INFO_ID, j);
        activity.startActivity(intent);
    }

    private boolean initData() {
        this.infoId = getIntent().getLongExtra(TTDelegateActivity.APP_INFO_ID, 0L);
        AppInfoX appInfoX = AppInfoCache.getInstance().getAppInfoX(this.infoId);
        if (appInfoX == null) {
            return false;
        }
        this.cid = appInfoX.cid;
        this.permissions = appInfoX.permissions;
        return true;
    }

    private void initView() {
        this.ivBack = (ImageView) findViewById(R.id.iv_detail_back);
        this.tvEmpty = (TextView) findViewById(R.id.tv_empty);
        this.recyclerView = findViewById(R.id.permission_list);
        this.llDownload = (LinearLayout) findViewById(R.id.ll_download);
        if (this.permissions.isEmpty()) {
            this.recyclerView.setVisibility(8);
            this.tvEmpty.setVisibility(0);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(1);
            this.recyclerView.setLayoutManager(linearLayoutManager);
            this.recyclerView.setAdapter(new RecyclerAdapter());
        }
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DETAIL_CLICK_CLOSE, AppDetailInfoActivity.this.cid);
                AppDetailInfoActivity.this.finish();
            }
        });
        this.llDownload.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DETAIL_CLICK_DOWNLOAD, AppDetailInfoActivity.this.cid);
                AdLpComplianceManager.getInstance().startDownload(AppDetailInfoActivity.this.cid);
                AppDownloadUtils.safeFinish(AppDetailInfoActivity.this);
                AppDownloadUtils.safeFinish(AdLpComplianceManager.getInstance().popDialogActivity());
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_DETAIL_CLICK_CLOSE, this.cid);
        super.onBackPressed();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private RecyclerAdapter() {
        }

        @NonNull
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ttdownloader_item_permission, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            recyclerViewHolder.tvTitle.setText((CharSequence) ((Pair) AppDetailInfoActivity.this.permissions.get(i)).first);
            recyclerViewHolder.tvDescription.setText((CharSequence) ((Pair) AppDetailInfoActivity.this.permissions.get(i)).second);
            if (i == getItemCount() - 1) {
                recyclerViewHolder.dashLine.setVisibility(8);
            }
        }

        public int getItemCount() {
            return AppDetailInfoActivity.this.permissions.size();
        }
    }

    private static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public View dashLine;
        public TextView tvDescription;
        public TextView tvTitle;

        public RecyclerViewHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_permission_title);
            this.tvDescription = (TextView) view.findViewById(R.id.tv_permission_description);
            this.dashLine = view.findViewById(R.id.dash_line);
        }
    }
}
