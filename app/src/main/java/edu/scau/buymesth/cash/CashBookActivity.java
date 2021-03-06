package edu.scau.buymesth.cash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.util.SpaceItemDecoration;
import base.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import edu.scau.buymesth.R;
import edu.scau.buymesth.adapter.CashBookAdapter;
import edu.scau.buymesth.data.bean.CashBook;
import edu.scau.buymesth.data.bean.User;

/**
 * Created by Jammy on 2016/10/7.
 */
public class CashBookActivity extends BaseActivity {
    User user;
    @Bind(R.id.rv)
    RecyclerView rv;
    CashBookAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cashbook;
    }

    @Override
    public void initView() {
        user = (User) getIntent().getSerializableExtra("user");
        adapter = new CashBookAdapter(new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_6)));
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(adapter);

        showLoadingDialog();
        BmobQuery<CashBook> query = new BmobQuery<>();
        query.addWhereEqualTo("user", user.getObjectId());
        query.include("user,toUser,toOrder");
        query.order("-updatedAt");
        query.findObjects(new FindListener<CashBook>() {
            @Override
            public void done(List<CashBook> list, BmobException e) {
                if(e==null) {
                    adapter.setNewData(list);
                    closeLoadingDialog();
                }
                else{
                    Toast.makeText(CashBookActivity.this,"请求失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean canSwipeBack() {
        return true;
    }

    public static void navigate(Activity activity, User user) {
        Intent intent = new Intent(activity, CashBookActivity.class);
        intent.putExtra("user", user);
        activity.startActivity(intent);
    }

    @Override
    public int getStatusColorResources() {
        return R.color.colorPrimaryDark;
    }


    @Override
    protected int getToolBarId() {
        return R.id.toolbar;
    }
}
