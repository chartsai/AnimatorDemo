package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;

/**
 * For TypeEvaluator
 */
public class TypeEvaluatorFragment extends AnimatorFragment {

    private ValueAnimator stringAnimator;

    private Paint paint;

    private static final TypeEvaluator STRING_TYPE_EVALUATOR = new TypeEvaluator<String>() {
        @Override
        public String evaluate(float fraction, String unused, String endValue) {
            int length = Math.round(fraction * endValue.length());
            return endValue.substring(0, length);
        }
    };

    @Override
    protected Animator prepareAnimator(int width, int height) {
        stringAnimator = new ValueAnimator();
        stringAnimator.setObjectValues("Hello, Android Taipei!");
        stringAnimator.setEvaluator(STRING_TYPE_EVALUATOR);

        stringAnimator.setDuration(5000);

        paint = new Paint();
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setShader(new LinearGradient(0, 0, width, 0,
                new int[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN},
                null, Shader.TileMode.MIRROR));

        return stringAnimator;
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        String str = (String) stringAnimator.getAnimatedValue();
        int x = canvas.getWidth() / 10;
        int y = canvas.getHeight() / 2;

        canvas.drawText(str, x, y, paint);
    }
}
