package rnd.com.technodhaka.android.myapplication.connect.Animation;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import rnd.com.technodhaka.android.myapplication.connect.MaterialDesign.Elevation;


public class PageTransitions {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    int height;
    Context mContext;
    Layout mLayout;
    int setDuration = 500;
    View view;
    int width;

    class C03001 implements AnimatorUpdateListener {
        C03001() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PageTransitions.this.view.setTranslationY(((Float) animation.getAnimatedValue()).floatValue());
        }
    }

    class C03012 implements AnimatorUpdateListener {
        C03012() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PageTransitions.this.view.setTranslationY(((Float) animation.getAnimatedValue()).floatValue());
        }
    }

    class C03023 implements AnimatorUpdateListener {
        C03023() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PageTransitions.this.view.setTranslationX(((Float) animation.getAnimatedValue()).floatValue());
        }
    }

    class C03034 implements AnimatorUpdateListener {
        C03034() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PageTransitions.this.view.setTranslationX(((Float) animation.getAnimatedValue()).floatValue());
        }
    }

    class C03045 implements AnimatorUpdateListener {
        C03045() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            PageTransitions.this.view.setRotation(((Float) animation.getAnimatedValue()).floatValue());
        }
    }

    public PageTransitions(Context mContext, View view) {
        this.mContext = mContext;
        this.view = view;
        this.displayMetrics = mContext.getResources().getDisplayMetrics();
        this.width = this.displayMetrics.widthPixels;
        this.height = this.displayMetrics.widthPixels;
    }

    public void pageTransitionBottomToTop() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{(float) this.height, 0.0f});
        valueAnimator.addUpdateListener(new C03001());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration((long) this.setDuration);
        Elevation.setElevation(this.view, 10.0f);
        valueAnimator.start();
    }

    public void pageTransitionTopToBottom() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{(float) (-this.height), 0.0f});
        valueAnimator.addUpdateListener(new C03012());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration((long) this.setDuration);
        Elevation.setElevation(this.view, 10.0f);
        valueAnimator.start();
    }

    public void pageTransitionLeftToRight() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{(float) (-this.width), 0.0f});
        valueAnimator.addUpdateListener(new C03023());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration((long) this.setDuration);
        Elevation.setElevation(this.view, 10.0f);
        valueAnimator.start();
    }

    public void pageTransitionRightToLeft() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{(float) this.width, 0.0f});
        valueAnimator.addUpdateListener(new C03034());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration((long) this.setDuration);
        Elevation.setElevation(this.view, 10.0f);
        valueAnimator.start();
    }

    public void pageTransitionSpin() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 360.0f});
        valueAnimator.addUpdateListener(new C03045());
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(600);
        Elevation.setElevation(this.view, 10.0f);
        valueAnimator.start();
    }
}
