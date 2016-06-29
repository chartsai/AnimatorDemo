package idv.chatea.animatordemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AndroidRuntimeException;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final class Demo {
        String name;
        Class clazz;
        Demo(String name, Class clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }

    private static final Demo[] DEMO_LIST = {
            new Demo("Live Coding Demo", LiveFragment.class),
            new Demo("Demo 1: Simple", SimpleFragment.class),
            new Demo("Demo 2: Repeat Count", RepeatCountFragment.class),
            new Demo("Demo 3: Repeat Mode", RepeatModeFragment.class),
            new Demo("Demo 4: AnimatorSet", AnimatorSetFragment.class),
            new Demo("Demo 5: Interpolator", InterpolatorFragment.class),
            new Demo("Demo 6: Multiple Property", MultiplePropertyFragment.class),
            new Demo("Demo 7: Object Animator", ObjectAnimatorFragment.class),
            new Demo("Demo 8: Type Evaluator", TypeEvaluatorFragment.class),
            new Demo("Demo 9: Cool Animation ", CoolAnimationFragment.class),
    };

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setTitle(mSectionsPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playAnimation()) {
                    Snackbar.make(view, "This page cannot be play", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(mSectionsPagerAdapter.getPageTitle(mViewPager.getCurrentItem()));
    }

    private boolean playAnimation() {
        Fragment f = mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem());
        if (f instanceof AnimatorFragment) {
            ((AnimatorFragment) f).playAnimation();
            return true;
        }
        return false;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private Fragment[] caches = new Fragment[getCount()];

        @Override
        public Fragment getItem(int position) {
            if (caches[position] == null) {
                Class clazz = DEMO_LIST[position].clazz;
                try {
                    caches[position] = (Fragment) clazz.newInstance();
                } catch (Exception e) {
                    throw new AndroidRuntimeException("Cannot create Fragment");
                }
            }
            return caches[position];
        }

        @Override
        public int getCount() {
            return DEMO_LIST.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return DEMO_LIST[position].name;
        }
    }
}
