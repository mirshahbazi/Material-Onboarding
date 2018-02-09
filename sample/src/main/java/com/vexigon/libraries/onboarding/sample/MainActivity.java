package com.vexigon.libraries.onboarding.sample;

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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vexigon.libraries.onboarding.obj.benefits.Page;
import com.vexigon.libraries.onboarding.obj.selfselect.BundledListItem;
import com.vexigon.libraries.onboarding.obj.selfselect.GridViewItem;
import com.vexigon.libraries.onboarding.obj.selfselect.SelectionPage;
import com.vexigon.libraries.onboarding.obj.selfselect.User;
import com.vexigon.libraries.onboarding.obj.selfselect.UserPage;
import com.vexigon.libraries.onboarding.sampleapp.R;
import com.vexigon.libraries.onboarding.ui.models.SelfSelectModel;
import com.vexigon.libraries.onboarding.ui.models.TopUserBenefitsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_benefits_demo:
                new TopUserBenefitsModel(this)
                        .setupSlides(
                                new Page("Title 1", "Subtitle 1", R.mipmap.ic_red),
                                new Page("Title 2", "Subtitle 2", R.mipmap.ic_green),
                                new Page("Title 3", "Subtitle 3", "Custom Button Text", R.mipmap.ic_blue)
                        )
                        .launch();
                break;
            case R.id.self_select_demo_bundled:
                ArrayList<User> users1 = new ArrayList<>();
                users1.add(new User("Andrew Quebe", "andrewquebe@vexigon.com", R.mipmap.ic_red));
                users1.add(new User("John Doe", "john@domain.com", R.mipmap.ic_green));
                users1.add(new User("Jane Doe", "jane@domain.com", R.mipmap.ic_blue));

                ArrayList<BundledListItem> bundledListItems = new ArrayList<>();
                for (int i = 1; i <= 100; i++) {
                    bundledListItems.add(new BundledListItem("Option " + i, "Shown individually",
                            "Bundled", R.mipmap.ic_red));
                }

                new SelfSelectModel(this)
                        .setupSlides(
                                new UserPage(R.mipmap.ic_launcher, users1),
                                new SelectionPage("Streamline your laboratory", "Bundle hydrocarbons you want to see together. " +
                                        "Unbundled hydrocarbons appear in your feed individually.",
                                        bundledListItems, null, null)
                        )
                        .launch();
                break;
            case R.id.self_select_demo_grid:
                ArrayList<User> users2 = new ArrayList<>();
                users2.add(new User("Andrew Quebe", "andrewquebe@vexigon.com", R.mipmap.ic_red));
                users2.add(new User("John Doe", "john@domain.com", R.mipmap.ic_green));
                users2.add(new User("Jane Doe", "jane@domain.com", R.mipmap.ic_blue));

                ArrayList<GridViewItem> gridViewItems = new ArrayList<>();
                for (int i = 1; i <= 96; i++) {
                    gridViewItems.add(new GridViewItem("Option " + i, R.drawable.blue));
                }

                new SelfSelectModel(this)
                        .setupSlides(
                                new UserPage(R.mipmap.ic_launcher, users2),
                                new SelectionPage("Streamline your laboratory", "Bundle hydrocarbons you want to see together. " +
                                        "Unbundled hydrocarbons appear in your feed individually.",
                                        null, gridViewItems, null)
                        )
                        .launch();
                break;
            case R.id.self_select_demo_list:
                Toast.makeText(this, "Soon.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
