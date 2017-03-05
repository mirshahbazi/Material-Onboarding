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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vexigon.libraries.onboarding.sampleapp.R;
import com.vexigon.libraries.onboarding.ui.models.TopUserBenefitsModel;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.demo:
                new TopUserBenefitsModel(this)
                        .setTitleText(new String[]{
                                "Title 1",
                                "Title 2",
                                "Title 3"
                        })
                        .setSubtitles(new String[]{
                                "Subtitle 1",
                                "Subtitle 2",
                                "Subtitle 3"
                        })
                        .setIllustrations(new int[]{
                                R.mipmap.ic_launcher,
                                R.mipmap.ic_launcher,
                                R.mipmap.ic_launcher
                        })
                        .setButtonText(new String[]{
                                "Button 1",
                                "Button 2",
                                "Button 3"
                        })
                        .launch();
                break;
        }
    }
}
