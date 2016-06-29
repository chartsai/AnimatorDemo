package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;

/**
 * Demo the Heart Shock
 */
public class HeartShockFragment extends AnimatorFragment {

    private Paint redPaint;
    private int radius;

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    protected Animator prepareAnimator(int width, int height) {
        ObjectAnimator anim = ObjectAnimator.ofInt(this, "radius", width / 6, width/ 5);
        anim.setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setInterpolator(new AccelerateInterpolator(2f));

        redPaint = new Paint();
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setColor(Color.RED);

        return anim;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int cx = canvas.getWidth() / 2;
        int cy = canvas.getHeight() / 2;

        int r = radius;

        canvas.rotate(45, cx, cy);

        RectF rectangle = new RectF(cx - r, cy -r, cx + r, cy + r);
        canvas.drawRect(rectangle, redPaint);
        canvas.drawCircle(cx - r, cy, r, redPaint);
        canvas.drawCircle(cx, cy - r, r, redPaint);
    }
}
