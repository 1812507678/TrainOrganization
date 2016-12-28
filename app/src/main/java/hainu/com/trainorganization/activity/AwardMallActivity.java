package hainu.com.trainorganization.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import hainu.com.trainorganization.R;
import hainu.com.trainorganization.bean.GoodInfo;

public class AwardMallActivity extends BaseActivity {
    private static final String TAG = "AwardMallFragment";
    private GridView gv_awardmall_item;
    private List<GoodInfo> goodInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_mall);

        initView();
    }

    private void initView() {
        gv_awardmall_item = (GridView) findViewById(R.id.gv_awardmall_item);
        goodInfoList = new ArrayList<>();

        initHeadView();
        tv_base_centerText = (TextView) findViewById(R.id.tv_base_centerText);
        tv_base_centerText.setText("金豆商城");
        setLeftImage(R.drawable.bg_back);
        getIv_base_leftimage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        innitViewData();

    }

    private void innitViewData() {
        //获取数据
        BmobQuery<GoodInfo> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(AwardMallActivity.this, new FindListener<GoodInfo>() {
            @Override
            public void onSuccess(List<GoodInfo> list) {
                goodInfoList = list;
                Toast.makeText(AwardMallActivity.this,"获取数据成功:",Toast.LENGTH_SHORT).show();
                Log.i(TAG,"goodInfoList"+goodInfoList.size());

                //给LisetVIew添加adapter
                gv_awardmall_item.setAdapter(new AwardmallAdapter());

            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(AwardMallActivity.this,"失败:"+s,Toast.LENGTH_SHORT).show();
            }
        });


    }


    class AwardmallAdapter extends BaseAdapter {

        private final BitmapUtils bitmapUtils;

        public AwardmallAdapter() {
            bitmapUtils = new BitmapUtils(AwardMallActivity.this);
        }

        @Override
        public int getCount() {
            return goodInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GoodInfo goodInfo1 = goodInfoList.get(position);

            View inflate = View.inflate(AwardMallActivity.this, R.layout.listitem_awardmall, null);

            ImageView iv_awardlist_icon1 = (ImageView) inflate.findViewById(R.id.iv_awardlist_icon1);
            TextView tv_awardlist_name1 = (TextView) inflate.findViewById(R.id.tv_awardlist_name1);
            TextView tv_awardlist_point1 = (TextView) inflate.findViewById(R.id.tv_awardlist_point1);

            bitmapUtils.display(iv_awardlist_icon1,goodInfo1.getIconurl());
            tv_awardlist_name1.setText(goodInfo1.getName());
            tv_awardlist_point1.setText(goodInfo1.getPoints()+"积分");


            return inflate;
        }
    }
}
