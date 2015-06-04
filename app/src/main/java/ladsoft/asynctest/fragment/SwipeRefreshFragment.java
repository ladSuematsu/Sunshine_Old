package ladsoft.asynctest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ladsoft.asynctest.R;

/**
 * Created by suematsu on 6/2/15.
 */
public class SwipeRefreshFragment extends Fragment {

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout mFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.swipe_refresh_layout, container, false);
        return mSwipeRefreshLayout;
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    public boolean isRefreshing() { return mSwipeRefreshLayout.isRefreshing();}

    public void setRefreshing(boolean refreshing) { mSwipeRefreshLayout.setRefreshing(refreshing);}

    public SwipeRefreshLayout getSwipeRefreshLayout() { return mSwipeRefreshLayout; }

    /**
     * Programatically triggers SwipeRefreshLayout onRefresh
     * in order to show the animation when loading an Fragment for the first time
     */
    protected void triggerSwipeRefresh(){
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

}
