package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For Live Demo
 */
public class LiveFragment extends AnimatorFragment {

    private int r;

    private Paint redPaint;

    public void setR(int r) {
        this.r = r;
    }

    @Override
    protected Animator prepareAnimator(int width, int height) {
        // TODO Live demo
        ObjectAnimator anim = ObjectAnimator.ofInt(this, "r", width / 6, width / 5);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setDuration(500);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);

        return anim;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {

        int cx = canvas.getWidth() / 2;
        int cy = canvas.getHeight() / 2;

        canvas.rotate(45, cx, cy);

        int r = this.r;

        canvas.drawRect(cx - r, cy - r, cx + r, cy + r, redPaint);
        canvas.drawCircle(cx - r, cy, r, redPaint);
        canvas.drawCircle(cx, cy - r, r, redPaint);

        // TODO live demo
    }
}
