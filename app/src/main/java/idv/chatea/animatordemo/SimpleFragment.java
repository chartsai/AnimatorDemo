package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For Simple Sample
 */
public class SimpleFragment extends AnimatorFragment {

    private ValueAnimator xAnimator;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        xAnimator = new ValueAnimator();
        xAnimator.setIntValues(0, width);
        xAnimator.setDuration(5000);

        paint = new Paint();
        paint.setColor(Color.GREEN);

        return xAnimator;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int y = canvas.getHeight() / 2;

        int x = (int) xAnimator.getAnimatedValue();

        canvas.drawCircle(x, y, 50, paint);
    }
}
