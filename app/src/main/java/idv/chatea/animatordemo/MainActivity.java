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
import android.view.View;

public class MainActivity extends AppCompatActivity {

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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private Fragment[] caches = new Fragment[getCount()];

        @Override
        public Fragment getItem(int position) {
            if (caches[position] == null) {
                switch (position) {
                    case 0:
                        caches[position] = new LiveFragment();
                        break;
                    case 1:
                        caches[position] = new FirstFragment();
                        break;
                    case 2:
                        caches[position] = new SecondFragment();
                        break;
                    case 3:
                        caches[position] = new ThirdFragment();
                        break;
                    case 4:
                        caches[position] = new FourthFragment();
                        break;
                    default:
                        caches[position] = new CoolFragment();
                        break;
                }
            }

            return caches[position];
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Live Demo";
                case 1:
                    return "Demo 1";
                case 2:
                    return "Demo 2";
                case 3:
                    return "Demo 3";
                case 4:
                    return "Demo 4";
                case 5:
                    return "Demo 5";
            }
            return null;
        }
    }
}
