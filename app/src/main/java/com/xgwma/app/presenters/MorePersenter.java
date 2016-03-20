package com.xgwma.app.presenters;

import android.content.Context;
import android.widget.TextView;

import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import com.xgwma.app.task.FileCalculateAsyncTask;
import com.xgwma.app.task.FileDeleteAsyncTask;
import com.xgwma.app.task.OnResponseListener;
import com.xgwma.app.utils.CacheManager;
import com.xgwma.app.utils.FileUtils;
import com.xgwma.app.utils.NetUtil;
import com.xgwma.app.utils.ToastUtil;
import com.xgwma.app.views.BaseDialog;
import com.xgwma.app.views.IMoreView;
import com.xgwma.app.views.LoadingDialog;
import com.xgwma.app.views.SelectionDialog;

import java.io.File;

/**
 *更多
 */
public class MorePersenter{
    /**
     * SD卡缓存大小
     */
    private long mExternCacheSize;
    /**
     * 外部缓存目录
     */
    private File mExternalCacheFile;
    IMoreView moreView;
    private Context context;
    public  MorePersenter(Context context){
        this.context=context;
    }
    /**
     * 更新数据
     */
    public void updateData(final TextView tvCath){
        // TODO Auto-generated method stub
        FileCalculateAsyncTask task = new FileCalculateAsyncTask(context);
        mExternalCacheFile = new File(CacheManager.getExternalCachePath(context));
        task.execute(mExternalCacheFile);
        task.setOnResponseListener(new OnResponseListener() {

            @Override
            public void onResponse(String resultString) {
                // TODO Auto-generated method stub
                try {
                    mExternCacheSize = Long.valueOf(resultString);
                    tvCath.setText(FileUtils.formatSize(mExternCacheSize));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 清除缓存
     */
    public void clearCache(final TextView tvCath){
        // TODO Auto-generated method stub
        SelectionDialog dialog = new SelectionDialog(context, "确定清除" + FileUtils.formatSize(mExternCacheSize) + "缓存吗？");
        dialog.setOnConfirmListener(new BaseDialog.OnConfirmListener() {

            @Override
            public void onConfirm(String result) {
                // TODO Auto-generated method stub

                final LoadingDialog dialog = new LoadingDialog(context, "正在清理缓存");
                dialog.show();

                FileDeleteAsyncTask task = new FileDeleteAsyncTask(context);
                task.execute(mExternalCacheFile);
                task.setOnResponseListener(new OnResponseListener() {

                    @Override
                    public void onResponse(String resultString) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        ToastUtil.showCenter(context, "清理成功");
                        updateData(tvCath);
                    }
                });
            }
        });
        dialog.show();
    }

    /**
     * 检查更新
     */
    public void checkUpgrade(){
        // TODO Auto-generated method stub
        if (NetUtil.isNetAvailable(context)) {
            UmengUpdateAgent.forceUpdate(context);
            UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
                @Override
                public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                    switch (updateStatus) {
                        case UpdateStatus.Yes:
                            ToastUtil.show(context, "软件有更新");
                            UmengUpdateAgent.update(context);
                            break;

                        case UpdateStatus.No:
                            ToastUtil.show(context, "没有更新");
                            break;

                        case UpdateStatus.NoneWifi:
                            ToastUtil.show(context, "没有wifi连接， 只在wifi下更新");
                            break;

                        case UpdateStatus.Timeout:
                            ToastUtil.show(context, "连接超时");
                            break;
                    }
                }
            });
        }
    }
}
