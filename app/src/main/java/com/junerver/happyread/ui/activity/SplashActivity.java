package com.junerver.happyread.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.junerver.happyread.R;
import com.junerver.happyread.utils.DensityUtils;
import com.junerver.happyread.utils.PicassoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.ivImage)
    ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.textView)
    public void onClick() {
        //测试工具类
        PicassoUtils.loadCircleImageViewCrop(this,"http://ww4.sinaimg.cn/large/610dc034jw1f9469eoojtj20u011hdjy.jpg",mIvImage);
    }
}
