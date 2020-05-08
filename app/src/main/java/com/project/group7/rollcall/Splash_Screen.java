package com.project.group7.rollcall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3300;
   private ImageView imageView;

   Animation fromtop_image,fromtop_text,from_bottom_cpy;
   TextView textView,cpyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        setStatusBarTransparent();

        imageView=(ImageView)findViewById(R.id.imageView);
        textView=(TextView)findViewById(R.id.text_view_splash);
        cpyText =(TextView)findViewById(R.id.copyright_text);

        fromtop_image = AnimationUtils.loadAnimation(this,R.anim.from_top_image);
        fromtop_text = AnimationUtils.loadAnimation(this,R.anim.from_top_text_text);
        from_bottom_cpy =AnimationUtils.loadAnimation(this,R.anim.from_bottom_cpy);

        imageView.setAnimation(fromtop_image);
        textView.setAnimation(fromtop_text);
        cpyText.setAnimation(from_bottom_cpy);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }

    private void setStatusBarTransparent(){

        if(Build.VERSION.SDK_INT >=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window =getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
