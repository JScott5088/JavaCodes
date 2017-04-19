package com.example.crystalball;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.example.crystalball:
//            CrystalBall, ShakeDetector

public class MainActivity extends ActionBarActivity
{

    public static final String TAG = com/example/crystalball/MainActivity.getSimpleName();
    private Sensor mAccelerometer;
    private TextView mAnswerLabel;
    private CrystalBall mCrystalBall;
    private ImageView mCrystalBallImage;
    private SensorManager mSensorManager;
    private ShakeDetector mShakeDetector;

    public MainActivity()
    {
        mCrystalBall = new CrystalBall();
    }

    private void animateAnswer()
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(0.0F, 1.0F);
        alphaanimation.setDuration(1500L);
        alphaanimation.setFillAfter(true);
        mAnswerLabel.setAnimation(alphaanimation);
    }

    private void animateCrystalBall()
    {
        mCrystalBallImage.setImageResource(0x7f02006f);
        AnimationDrawable animationdrawable = (AnimationDrawable)mCrystalBallImage.getDrawable();
        if (animationdrawable.isRunning())
        {
            animationdrawable.stop();
        }
        animationdrawable.start();
    }

    private void handleNewAnswer()
    {
        String s = mCrystalBall.getAnAnswer();
        mAnswerLabel.setText(s);
        animateCrystalBall();
        animateAnswer();
        playSound();
    }

    private void playSound()
    {
        MediaPlayer mediaplayer = MediaPlayer.create(this, 0x7f050000);
        mediaplayer.start();
        mediaplayer.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {

            final MainActivity this$0;

            public void onCompletion(MediaPlayer mediaplayer1)
            {
                mediaplayer1.release();
            }

            
            {
                this$0 = MainActivity.this;
                super();
            }
        });
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030017);
        mAnswerLabel = (TextView)findViewById(0x7f06003e);
        mCrystalBallImage = (ImageView)findViewById(0x7f06003c);
        mSensorManager = (SensorManager)getSystemService("sensor");
        mAccelerometer = mSensorManager.getDefaultSensor(1);
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {

            final MainActivity this$0;

            public void onShake()
            {
                handleNewAnswer();
            }

            
            {
                this$0 = MainActivity.this;
                super();
            }
        });
        Log.d(TAG, "We're logging from the onCreate() method!");
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f0d0000, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem.getItemId() == 0x7f060040)
        {
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }

    public void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
    }

    public void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, 2);
    }


}
