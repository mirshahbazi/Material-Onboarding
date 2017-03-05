package com.vexigon.libraries.onboarding.ui.models;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.vexigon.libraries.onboarding.obj.Page;
import com.vexigon.libraries.onboarding.ui.activity.UserBenefitsActivity;
import com.vexigon.libraries.onboarding.util.Keys;

import java.util.ArrayList;

/**
 * Created by Andrew Quebe on 3/2/2017.
 */

public class TopUserBenefitsModel {

    private String[] titleText, subtitleText, buttonText;
    private int[] illustrationRes;
    private Context context;

    private ArrayList<Page> pages = new ArrayList<>();

    private String[] backgroundColorRes;

    /**
     * Creates a new instance of the TopUserBenefitsModel
     *
     * @param context your activity context
     */
    public TopUserBenefitsModel(@NonNull Activity context) {
        this.context = context;
    }

    /**
     * Sets the title text for the TopUserBenefitsModel
     * @param titleText an array of Strings for each slide.
     * @return TopUserBenefitsModel
     * @deprecated use {@link #setupPages(Page, Page, Page)} instead
     */
    @Deprecated
    public TopUserBenefitsModel setTitleText(String[] titleText) {
        this.titleText = titleText;
        return this;
    }

    /**
     * Sets the subtitle text for the TopUserBenefitsModel
     * @param subtitleText an array of Strings for each slide.
     * @return TopUserBenefitsModel
     * @deprecated use {@link #setupPages(Page, Page, Page)} instead
     */
    @Deprecated
    public TopUserBenefitsModel setSubtitles(String[] subtitleText) {
        this.subtitleText = subtitleText;
        return this;
    }

    /**
     * Sets the button text for the TopUserBenfitsModel
     * @param buttonText an array of Strings for each slide.
     * @return TopUserBenefitsModel
     * @deprecated use {@link #setupPages(Page, Page, Page)} instead
     */
    @Deprecated
    public TopUserBenefitsModel setButtonText(String[] buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    /**
     * Sets the illustrations for the TopUserBenefitsModel
     * @param illustrationRes an array of image resources for each slide.
     * @return TopUserBenefitsModel
     * @deprecated use {@link #setupPages(Page, Page, Page)} instead
     */
    @Deprecated
    public TopUserBenefitsModel setIllustrations(int[] illustrationRes) {
        this.illustrationRes = illustrationRes;
        return this;
    }

    public TopUserBenefitsModel setupPages(Page page1, Page page2, Page page3) {
        this.pages.add(0, page1);
        this.pages.add(1, page2);
        this.pages.add(2, page3);
        return this;
    }

    /**
     * NOTE: private for now as more work is needed for status bar coloring.
     * Sets the background colors of each slide in the TopUserBenefitsModel
     * @param backgroundColorRes an array of Color Hex Strings for each slide.
     * @return TopUserBenefitsModel
     */
    private TopUserBenefitsModel setBackgroundColors(String[] backgroundColorRes) {
        this.backgroundColorRes = backgroundColorRes;
        return this;
    }

    /**
     * Checks text and image resources for null values, and returns an intent that stores the non null values.
     * @return Intent
     */
    private Intent getIntent() {
        if (titleText == null)
            throw new RuntimeException("Title text was null. Ensure setTitles() is called.");
        else if (subtitleText == null)
            throw new RuntimeException("Subtitle text was null. Ensure setSubtitles() is called.");
        else if (illustrationRes == null)
            throw new RuntimeException("Illustration resources were not set. Ensure setIllustrations() is called.");
        else {
            return new Intent(context, UserBenefitsActivity.class)
                    .putExtra(Keys.TITLE_TEXT, titleText)
                    .putExtra(Keys.SUBTITLE_TEXT, subtitleText)
                    .putExtra(Keys.BUTTON_TEXT, buttonText)
                    .putExtra(Keys.ILLUSTRATION_RES, illustrationRes)
                    .putExtra(Keys.BACKGROUND_COLOR_RES, backgroundColorRes);
        }
    }

    /**
     * Launches the activity with the getIntent() data stored.
     */
    public void launch() {
        context.startActivity(getIntent());
    }
}
