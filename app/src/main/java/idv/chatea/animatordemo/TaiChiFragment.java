package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Chris on 16/7/1.
 */
public class TaiChiFragment extends AnimatorFragment {

    private Paint mWhitePaint, mBlackPaint;

    private int radius;
    private float angle;

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @NonNull
    @Override
    protected Animator prepareAnimator(int width, int height) {

        mWhitePaint = new Paint();
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setStyle(Paint.Style.FILL);

        mBlackPaint = new Paint();
        mBlackPaint.setColor(Color.BLACK);
        mBlackPaint.setStyle(Paint.Style.FILL);

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(this, "angle", 0f, 360f);
        rotateAnim.setDuration(3000);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.setRepeatMode(ValueAnimator.INFINITE);
        rotateAnim.setInterpolator(new LinearInterpolator());

        ObjectAnimator scaleAnim = ObjectAnimator.ofInt(this, "radius", 0, width / 3);
        scaleAnim.setDuration(3000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnim.setInterpolator(new LinearInterpolator());

        AnimatorSet as = new AnimatorSet();
        as.play(rotateAnim).with(scaleAnim);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int outRadius = radius;
        int midRadius = outRadius / 2;
        int innerRadius = outRadius / 5;

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        canvas.rotate(angle, centerX, centerY);

        RectF rect = new RectF(centerX - outRadius, centerY - outRadius, centerX + outRadius, centerY + outRadius);
        canvas.drawArc(rect, 90, 180, true, mBlackPaint);
        canvas.drawArc(rect, -90, 180, true, mWhitePaint);

        canvas.drawCircle(centerX, centerY - midRadius, midRadius, mWhitePaint);
        canvas.drawCircle(centerX, centerY + midRadius, midRadius, mBlackPaint);

        canvas.drawCircle(centerX, centerY - midRadius, innerRadius, mBlackPaint);
        canvas.drawCircle(centerX, centerY + midRadius, innerRadius, mWhitePaint);
    }
}
