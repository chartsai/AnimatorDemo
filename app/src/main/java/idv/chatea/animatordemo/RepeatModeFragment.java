package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For Repeat Mode
 */
public class RepeatModeFragment extends AnimatorFragment {

    private ValueAnimator x1Animator;
    private ValueAnimator x2Animator;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        x1Animator = new ValueAnimator();
        x1Animator.setIntValues(0, width);
        x1Animator.setRepeatCount(ValueAnimator.INFINITE);
        x1Animator.setRepeatMode(ValueAnimator.RESTART);

        x2Animator = new ValueAnimator();
        x2Animator.setIntValues(0, width);
        x2Animator.setRepeatCount(ValueAnimator.INFINITE);
        x2Animator.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet as = new AnimatorSet();
        as.play(x1Animator).with(x2Animator);

        as.setDuration(5000);

        paint = new Paint();
        paint.setColor(Color.BLUE);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int y1 = canvas.getHeight() / 3;
        int y2 = y1 * 2;

        int x1 = (int) x1Animator.getAnimatedValue();
        int x2 = (int) x2Animator.getAnimatedValue();

        canvas.drawCircle(x1, y1, 50, paint);

        canvas.drawCircle(x2, y2, 50, paint);
    }
}
