package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class CoolAnimationFragment extends AnimatorFragment {

    private ValueAnimator innerAngleAnim;
    private ValueAnimator midAngleAnim;
    private ValueAnimator outerAngleAnim;

    private Paint centerPaint;
    private Paint innerPaint;
    private Paint midPaint;
    private Paint outerPaint;

    private Paint whiltePaint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        innerAngleAnim = new ValueAnimator();
        innerAngleAnim.setIntValues(0, 359);
        innerAngleAnim.setRepeatMode(ValueAnimator.RESTART);
        innerAngleAnim.setRepeatCount(ValueAnimator.INFINITE);
        innerAngleAnim.setDuration(1500);
        innerAngleAnim.setInterpolator(new LinearInterpolator());

        midAngleAnim = new ValueAnimator();
        midAngleAnim.setIntValues(359, 0);
        midAngleAnim.setRepeatMode(ValueAnimator.RESTART);
        midAngleAnim.setRepeatCount(ValueAnimator.INFINITE);
        midAngleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        midAngleAnim.setDuration(2500);

        outerAngleAnim = new ValueAnimator();
        outerAngleAnim.setIntValues(0, 359);
        outerAngleAnim.setRepeatMode(ValueAnimator.RESTART);
        outerAngleAnim.setRepeatCount(ValueAnimator.INFINITE);
        outerAngleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        outerAngleAnim.setDuration(3500);

        AnimatorSet as = new AnimatorSet();
        as.play(innerAngleAnim).with(midAngleAnim);
        as.play(innerAngleAnim).with(outerAngleAnim);

        centerPaint = new Paint();
        centerPaint.setColor(Color.RED);
        centerPaint.setStyle(Paint.Style.FILL);

        innerPaint = new Paint();
        innerPaint.setColor(Color.YELLOW);
        innerPaint.setStyle(Paint.Style.FILL);

        midPaint = new Paint();
        midPaint.setColor(Color.BLUE);
        midPaint.setStyle(Paint.Style.FILL);

        outerPaint = new Paint();
        outerPaint.setColor(Color.GREEN);
        outerPaint.setStyle(Paint.Style.FILL);

        whiltePaint = new Paint();
        whiltePaint.setColor(Color.WHITE);
        whiltePaint.setStyle(Paint.Style.FILL);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {

        int centerCircleRadius = canvas.getWidth() / 5;
        int innerRadius = centerCircleRadius + centerCircleRadius / 3;
        int midRadius = innerRadius + centerCircleRadius / 3;
        int outerRadius = midRadius + centerCircleRadius / 3;

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        int innerAngle = (int) innerAngleAnim.getAnimatedValue();
        int midAngle = (int) midAngleAnim.getAnimatedValue();
        int outerAngle = (int) outerAngleAnim.getAnimatedValue();

        drawArcHelper(canvas, centerX, centerY, outerRadius, outerAngle, 225, outerPaint);
        canvas.drawCircle(centerX, centerY, midRadius, whiltePaint);

        drawArcHelper(canvas, centerX, centerY, midRadius, midAngle, 225, midPaint);
        canvas.drawCircle(centerX, centerY, innerRadius, whiltePaint);

        drawArcHelper(canvas, centerX, centerY, innerRadius, innerAngle, 225, innerPaint);
        canvas.drawCircle(centerX, centerY, centerCircleRadius, centerPaint);
    }

    private void drawArcHelper(Canvas c, int cx, int cy, int r, int angle, int sweep, Paint p) {
        RectF rect = new RectF(cx - r, cy - r, cx + r, cy + r);
        c.drawArc(rect, angle, sweep, true, p);
    }
}
