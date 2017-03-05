package com.vexigon.libraries.onboarding.ui.activity;

/*
 * Copyright 2017 Vexigon, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.vexigon.libraries.onboarding.R;
import com.vexigon.libraries.onboarding.ui.adapters.UserBenefitsViewpagerAdapter;

/**
 * Created by Andrew Quebe on 3/2/2017.
 */

public class UserBenefitsActivity extends AppCompatActivity {

    ViewPager viewPager;
    InkPageIndicator inkPageIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_benefits_activity);

        viewPager = (ViewPager) findViewById(R.id.userBenefitsPager);
        viewPager.setAdapter(new UserBenefitsViewpagerAdapter(getSupportFragmentManager()));

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                stop();
                return false;
            }
        });

        inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(viewPager);

        start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    private boolean running = false;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    break;
                case 1:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    break;
                case 2:
                    viewPager.setCurrentItem(0, true);
                    break;
            }

            if (running)
                start();
        }
    };

    /*
     * A maximum of three illustrations should auto-rotate every two to three seconds and display pagination navigation.
     * https://material.io/guidelines/growth-communications/onboarding.html#onboarding-top-user-benefits
     */
    private void start() {
        running = true;
        handler.postDelayed(runnable, 5000);
    }

    /*
     * The auto-advance feature should be disabled if the user touches the carousel.
     * https://material.io/guidelines/growth-communications/onboarding.html#onboarding-top-user-benefits
     */
    private void stop() {
        running = false;
        handler.removeCallbacks(runnable);
    }
}
