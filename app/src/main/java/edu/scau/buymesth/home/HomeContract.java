package edu.scau.buymesth.home;

import java.util.List;

import base.BasePresenter;
import edu.scau.buymesth.data.bean.Request;
import rx.Observable;

/**
 * Created by John on 2016/8/5.
 */

public interface HomeContract {
    interface  View{
        void onLoadMoreSuccess(List<Request>list);
        void showError(String msg);
        void onRefreshComplete(List<Request>list);
        void onRefreshFail();
    }

    interface Model{
        interface GetRequestListener{
            void onSuccess(List<Request>list);
        }
        List<Request> getDatas();
        void setDatas(List<Request>list);
        void resetPage();
        Observable<List<Request>> getRxRequests();
    }
}
