package kaptenpotato.pilihpilih;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SlideAdapter slideAdapter;

    private Button mNextBtn;
    private Button mBackBtn;
    private Button mFinishBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.backBtn);
        mFinishBtn = (Button) findViewById(R.id.finishBtn);

        slideAdapter = new SlideAdapter(this);

        mSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                mSlideViewPager.setCurrentItem(mCurrentPage + 1);

            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);

            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                startActivity(new Intent(StartActivity.this, MainActivity.class));

            }
        });
    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i <mDots.length; i++) {

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= 23) {
                mDots[i].setTextColor(ContextCompat.getColor(this, R.color.transparentwhite));
            } else {
                mDots[i].setTextColor(getResources().getColor(R.color.transparentwhite));
            }
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            if (Build.VERSION.SDK_INT >= 23) {
                mDots[position].setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                mDots[position].setTextColor(getResources().getColor(R.color.white));
            }

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if(i == 0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mFinishBtn.setEnabled(false);

                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.INVISIBLE);
                mFinishBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");
                mFinishBtn.setText("");

            }else if(i == mDots.length - 1){
                mNextBtn.setEnabled(false);
                mBackBtn.setEnabled(true);
                mFinishBtn.setEnabled(true);

                mNextBtn.setVisibility(View.INVISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
                mFinishBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("");
                mBackBtn.setText("Back");
                mFinishBtn.setText("Let's Go");

            }else{
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mFinishBtn.setEnabled(false);

                mNextBtn.setVisibility(View.VISIBLE);
                mBackBtn.setVisibility(View.VISIBLE);
                mFinishBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
                mFinishBtn.setText("");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
