package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For AnimatorSet
 */
public class AnimatorSetFragment extends AnimatorFragment {

    private ValueAnimator xAnimator;
    private ValueAnimator yAnimator;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        xAnimator = new ValueAnimator();
        xAnimator.setIntValues(0, width);

        yAnimator = new ValueAnimator();
        yAnimator.setIntValues(0, height);

        AnimatorSet as = new AnimatorSet();
        as.play(xAnimator).with(yAnimator);

        as.setDuration(5000);

        paint = new Paint();
        paint.setColor(Color.BLACK);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int x = (int) xAnimator.getAnimatedValue();
        int y = (int) yAnimator.getAnimatedValue();

        canvas.drawCircle(x, y, 50, paint);
    }
}
