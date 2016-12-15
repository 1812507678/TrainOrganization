package hainu.com.trainorganization.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import hainu.com.trainorganization.R;
import hainu.com.trainorganization.activity.HomeActivity;
import hainu.com.trainorganization.application.MyApplication;

/**
 * Created by Administrator on 2016/7/7.
 */
public class HomePageFragment  extends Fragment {

    private static final String TAG = "StrategyPageFragment";
    private View inflate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frgament_page_home, new LinearLayout(getActivity()),false);

        initView();



        return inflate;
    }

    private void initView() {


    }


}