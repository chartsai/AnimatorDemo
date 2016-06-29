package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For Repeat Count
 */
public class SecondFragment extends AnimatorFragment {

    private ValueAnimator x1Animator;
    private ValueAnimator x2Animator;
    private ValueAnimator x3Animator;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        x1Animator = new ValueAnimator();
        x1Animator.setIntValues(0, width);
        x1Animator.setRepeatCount(0);

        x2Animator = new ValueAnimator();
        x2Animator.setIntValues(0, width);
        x2Animator.setRepeatCount(1);

        x3Animator = new ValueAnimator();
        x3Animator.setIntValues(0, width);
        x3Animator.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet as = new AnimatorSet();
        as.play(x1Animator).with(x2Animator);
        as.play(x1Animator).with(x3Animator);

        as.setDuration(5000);

        paint = new Paint();
        paint.setColor(Color.RED);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int y1 = canvas.getHeight() / 4;
        int y2 = y1 * 2;
        int y3 = y1 * 3;

        int x1 = (int) x1Animator.getAnimatedValue();
        int x2 = (int) x2Animator.getAnimatedValue();
        int x3 = (int) x3Animator.getAnimatedValue();

        canvas.drawCircle(x1, y1, 50, paint);
        canvas.drawCircle(x2, y2, 50, paint);
        canvas.drawCircle(x3, y3, 50, paint);
    }
}
