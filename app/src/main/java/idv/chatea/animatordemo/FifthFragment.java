package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * For Interpolator
 */
public class FifthFragment extends AnimatorFragment {

    private ValueAnimator x1Animator;
    private ValueAnimator x2Animator;
    private ValueAnimator x3Animator;
    private ValueAnimator x4Animator;
    private ValueAnimator x5Animator;
    private ValueAnimator x6Animator;
    private ValueAnimator x7Animator;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        int offset = width / 8;
        int start = offset;
        int end = width - offset;

        x1Animator = new ValueAnimator();
        x1Animator.setIntValues(start, end);
        // default interpolator is AccelerateDecelerateInterpolator

        x2Animator = new ValueAnimator();
        x2Animator.setIntValues(start, end);
        x2Animator.setInterpolator(new LinearInterpolator());

        x3Animator = new ValueAnimator();
        x3Animator.setIntValues(start, end);
        x3Animator.setInterpolator(new AccelerateInterpolator());

        x4Animator = new ValueAnimator();
        x4Animator.setIntValues(start, end);
        x4Animator.setInterpolator(new DecelerateInterpolator());

        x5Animator = new ValueAnimator();
        x5Animator.setIntValues(start, end);
        x5Animator.setInterpolator(new OvershootInterpolator());

        x6Animator = new ValueAnimator();
        x6Animator.setIntValues(start, end);
        x6Animator.setInterpolator(new AnticipateInterpolator());

        x7Animator = new ValueAnimator();
        x7Animator.setIntValues(start, end);
        x7Animator.setInterpolator(new AnticipateOvershootInterpolator());

        AnimatorSet as = new AnimatorSet();
        as.play(x1Animator).with(x2Animator);
        as.play(x1Animator).with(x3Animator);
        as.play(x1Animator).with(x4Animator);
        as.play(x1Animator).with(x5Animator);
        as.play(x1Animator).with(x6Animator);
        as.play(x1Animator).with(x7Animator);

        as.setDuration(5000);

        paint = new Paint();
        paint.setColor(Color.MAGENTA);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int y1 = canvas.getHeight() / 8;
        int y2 = y1 * 2;
        int y3 = y1 * 3;
        int y4 = y1 * 4;
        int y5 = y1 * 5;
        int y6 = y1 * 6;
        int y7 = y1 * 7;

        int x1 = (int) x1Animator.getAnimatedValue();
        int x2 = (int) x2Animator.getAnimatedValue();
        int x3 = (int) x3Animator.getAnimatedValue();
        int x4 = (int) x4Animator.getAnimatedValue();
        int x5 = (int) x5Animator.getAnimatedValue();
        int x6 = (int) x6Animator.getAnimatedValue();
        int x7 = (int) x7Animator.getAnimatedValue();

        canvas.drawCircle(x1, y1, 50, paint);
        canvas.drawCircle(x2, y2, 50, paint);
        canvas.drawCircle(x3, y3, 50, paint);
        canvas.drawCircle(x4, y4, 50, paint);
        canvas.drawCircle(x5, y5, 50, paint);
        canvas.drawCircle(x6, y6, 50, paint);
        canvas.drawCircle(x7, y7, 50, paint);
    }
}
