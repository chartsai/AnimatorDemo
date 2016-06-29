package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For Multiple properties in same animator
 */
public class SixthFragment extends AnimatorFragment {

    private ValueAnimator anim;

    private Paint paint;

    @Override
    protected Animator prepareAnimator(int width, int height) {
        anim = new ValueAnimator();
        PropertyValuesHolder pX = PropertyValuesHolder.ofInt("x", width, 0);
        PropertyValuesHolder pY = PropertyValuesHolder.ofInt("y", 0, height);
        anim.setValues(pX, pY);

        paint = new Paint();
        paint.setColor(Color.DKGRAY);

        anim.setDuration(5000);

        return anim;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        int x = (int) anim.getAnimatedValue("x");
        int y = (int) anim.getAnimatedValue("y");

        canvas.drawCircle(x, y, 50, paint);
    }
}
