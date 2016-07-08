package idv.chatea.animatordemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Chris on 16/7/4.
 */
public class LotteryFragment extends AnimatorFragment {

    private float angle;
    private int radius;
    private Paint mWhitePaint, mBlackPaint, textPaint, mOuterPaint;

    ObjectAnimator mAnim;

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public static final String[] item = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    boolean isPlay = false;

    @NonNull
    @Override
    protected Animator prepareAnimator(int width, int height) {

        return isPlay ? stopAnimator(width, height) : initAnimator(width, height);
    }

    @Override
    protected void onDrawAnimation(Canvas canvas) {
        initDrawAnimation(canvas);
    }

    private Animator initAnimator(int width, int height) {

        isPlay = true;

        mOuterPaint = new Paint();
        mOuterPaint.setShader(new LinearGradient(0, 0, width, 0,
                new int[]{Color.RED, Color.rgb(255, 165, 0), Color.YELLOW, Color.GREEN, Color.rgb(0, 255, 255), Color.BLUE, Color.CYAN},
                null, Shader.TileMode.MIRROR));
        mOuterPaint.setStyle(Paint.Style.FILL);

        mWhitePaint = new Paint();
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setStyle(Paint.Style.FILL);

        mBlackPaint = new Paint();
        mBlackPaint.setColor(Color.BLACK);
        mBlackPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setTextSize(100);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setShader(new LinearGradient(0, 0, width, 0,
                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN},
                null, Shader.TileMode.MIRROR));

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(this, "angle", -90f, 270f);
        rotateAnim.setDuration(2000);

        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.setRepeatMode(ValueAnimator.INFINITE);
        rotateAnim.setInterpolator(new LinearInterpolator());

        return rotateAnim;
    }

    private Animator stopAnimator(int width, int height) {

        isPlay = false;

        mAnim = ObjectAnimator.ofFloat(this, "angle", angle, angle + 360f);
        mAnim.setDuration(7000);
        mAnim.setRepeatCount(0);
        mAnim.setRepeatMode(ValueAnimator.INFINITE);
        mAnim.setInterpolator(new DecelerateInterpolator());

        Message msg = new Message();
        msg.what = 1;
        mHandler.sendMessageDelayed(msg, 6000);

        return mAnim;
    }

    private void initDrawAnimation(Canvas canvas) {
        radius = canvas.getWidth() / 2;
        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        int textOffset = canvas.getWidth() / (item.length);
        int outerOffset = canvas.getWidth() / (item.length * 3);

        float fixAngle = 360 / (item.length);

        int drawLength = Math.round(360 / fixAngle);

        canvas.drawCircle(centerX, centerY, radius + outerOffset, mOuterPaint);

        canvas.rotate(angle, centerX, centerY);

        RectF innerRect = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        RectF rectText = new RectF(centerX - radius + textOffset, centerY - radius + textOffset, centerX + radius - textOffset, centerY + radius - textOffset);

        for (int i = 0; i < drawLength; i++) {

            if (i % 2 == 0) {
                canvas.drawArc(innerRect, fixAngle * i, fixAngle, true, mBlackPaint);

                Path path = new Path();
                path.addArc(rectText, fixAngle * i, fixAngle);
                canvas.drawTextOnPath(item[i], path, 0, 20, textPaint);
            } else {
                canvas.drawArc(innerRect, fixAngle * i, fixAngle, true, mWhitePaint);

                Path path = new Path();
                path.addArc(rectText, fixAngle * i, fixAngle);
                canvas.drawTextOnPath(item[i], path, 0, 20, textPaint);
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    mAnim.pause();
            }
        }
    };
}
