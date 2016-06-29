package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * For ObjectAnimator
 */
public class SeventhFragment extends AnimatorFragment {

    private int x, y;

    private Paint paint;

    // used by ObjectAnimator
    public void setX(int newX) {
        this.x = newX;
    }

    // used by ObjectAnimator
    public void setY(int newY) {
        this.y = newY;
    }

    @Override
    protected Animator prepareAnimator(int width, int height) {

        // Use the variable name to create animator
        // Must have the public setter for the variable name.
        Animator xAnim = ObjectAnimator.ofInt(this, "x", 0, width);
        Animator yAnim = ObjectAnimator.ofInt(this, "y", 0, height);

        paint = new Paint();
        paint.setColor(Color.CYAN);

        AnimatorSet as = new AnimatorSet();
        as.play(xAnim).with(yAnim);

        as.setDuration(5000);

        return as;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        canvas.drawCircle(x, y, 50, paint);
    }
}
