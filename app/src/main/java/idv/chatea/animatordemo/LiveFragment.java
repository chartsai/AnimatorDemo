package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;

/**
 * For Live Demo
 */
public class LiveFragment extends AnimatorFragment {

    @Override
    protected Animator prepareAnimator(int width, int height) {
        // TODO Live demo
        return ValueAnimator.ofInt(0);
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        // TODO live demo
    }
}
