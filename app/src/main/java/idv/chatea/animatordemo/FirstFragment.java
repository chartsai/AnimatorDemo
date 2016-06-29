package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Simple demo
 */
public class FirstFragment extends AnimatorFragment {

    private ValueAnimator xAnimator;
    private ValueAnimator yAnimator;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        xAnimator = new ValueAnimator();
        xAnimator.setIntValues(0, width);

        yAnimator = new ValueAnimator();
        yAnimator.setIntValues(0, height);

        AnimatorSet as = new AnimatorSet();
        as.play(xAnimator).with(yAnimator);

        as.setDuration(5000);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        Paint p = new Paint(Color.BLACK);

        int x = (int) xAnimator.getAnimatedValue();
        int y = (int) yAnimator.getAnimatedValue();

        canvas.drawCircle(x, y, 20, p);
    }
}
