package idv.chatea.animatordemo;

import android.animation.Animator;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


abstract public class AnimatorFragment extends Fragment {

    protected View mView;
    private boolean mPlaying;

    public AnimatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = new View(getContext()) {
            @Override
            protected void onDraw(Canvas canvas) {
                if (isPlaying()) {
                    onDrawAnimation(canvas);
                    postInvalidateDelayed(10);
                }
            }
        };
        return mView;
    }

    public boolean isPlaying() {
        return mPlaying;
    }

    final public void playAnimation() {
        Animator a = prepareAnimator(mView.getWidth(), mView.getHeight());
        startAnimation(a);
    }

    abstract protected Animator prepareAnimator(int width, int height);

    final private void startAnimation(Animator animator) {
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mPlaying = true;
                mView.invalidate();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mPlaying = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mPlaying = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mPlaying = true;
                mView.invalidate();
            }
        });
        animator.start();
    }

    abstract protected void onDrawAnimation(Canvas canvas);
}
