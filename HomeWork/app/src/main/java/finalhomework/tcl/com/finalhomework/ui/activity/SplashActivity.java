package finalhomework.tcl.com.finalhomework.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.LockViewUtil;



public class SplashActivity extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        if(LockViewUtil.getISFIRST(this)){
//            Intent intent = new Intent(this, WelcomeActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LockViewUtil.setIsfirst(this, false);
    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        if(LockViewUtil.getISFIRST(this)){
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }

        /* you don't have to override every property */

        //Customize Circular Reveal
        //设置主题颜色
        configSplash.setBackgroundColor(R.color.startColor);

        //configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(3000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_TOP); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.start); //or any other drawable
        configSplash.setAnimLogoSplashDuration(500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInUp); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash.setTitleSplash("好好记账");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
//        configSplash.setTitleFont("fonts/volatire.ttf"); //provide string to your font located in assets/fonts/

    }

    /**
     * 监听动画完成事件
     */
    @Override
    public void animationsFinished() {

        //transit to another activity here
        //or do whatever you want
        Intent intent  = new Intent(this,UesrLoginActivity.class);
        startActivityForResult(intent,0);
        finish();
    }
}
