package edu.scau.buymesth.notice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import edu.scau.buymesth.R;
import edu.scau.buymesth.data.bean.Evaluate;
import edu.scau.buymesth.data.bean.Notificate;
import edu.scau.buymesth.data.bean.Order;

/**
 * Created by Jammy on 2016/10/6.
 */
public class RejectActivity extends BaseActivity {

    public static final int REJECT_SUCCESS = 10087;
    Order order;
    @Bind(R.id.btn_go)
    Button btnGo;
    @Bind(R.id.et)
    EditText et;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reject;
    }

    @Override
    public void initView() {
        order = (Order) getIntent().getSerializableExtra("order");
        btnGo.setOnClickListener(v -> {
            if(et.getText().toString().equals("")){
                Toast.makeText(RejectActivity.this,"请填写理由",Toast.LENGTH_SHORT).show();
                return ;
            }
            order.setStatus(Order.STATUS_SELLER_REJECT);
            order.setRejectReason(et.getText().toString());
            order.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Notificate notificate = new Notificate();
                        notificate.setUser(order.getBuyer());
                        notificate.setOrder(order);
                        notificate.setStatus(order.getStatus());
                        notificate.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {

                            }
                        });
                    } else {
                        Toast.makeText(mContext, "请重试", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
        @Override
        public boolean canSwipeBack () {
            return true;
        }

        @Override
        protected int getToolBarId () {
            return R.id.toolbar;
        }

    public static void navigateForResult(Activity activity, Order order) {
        Intent intent = new Intent(activity, ReplyActivity.class);
        intent.putExtra("order", order);
        activity.startActivityForResult(intent, REJECT_SUCCESS);
    }

}
