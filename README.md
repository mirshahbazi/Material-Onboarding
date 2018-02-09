# Material-Onboarding
[![Download](https://api.bintray.com/packages/vexigon/maven/Material-Onboarding/images/download.svg?version=1.2) ](https://bintray.com/vexigon/maven/Material-Onboarding/1.2/link)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9a2960e6b0554b9ba21a8105098b6c2a)](https://www.codacy.com/app/Andrew-Quebe/Material-Onboarding?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Vexigon/Material-Onboarding&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Vexigon/Material-Onboarding.svg?branch=master)](https://travis-ci.org/Vexigon/Material-Onboarding)

![Sample image](https://github.com/Vexigon/Material-Onboarding/raw/master/art/onboarding_sample.png)

<p align="center">
  (Image is of sample usage from an unreleased app)
</p>

A short and simple library which allows easy replication of several app onboarding techniqies found [here](https://material.io/guidelines/growth-communications/onboarding.html).

## Background

The Material Design guidelines list a lot of different techniques for onboarding users in your apps. The main concept is to remain as simple as possible, and do away with complicated introductions to your app.

## What's Included

For now, this library only allows the creation of the [TopUserBenefitsModel](https://material.io/guidelines/growth-communications/onboarding.html#onboarding-top-user-benefits) technique. Later on, it will make use of the other techniques as well.
 
## Enough Talking. How do I use this?

First off, add the Gradle dependency to your app:

```groovy
// Add the following lines in your apps build.gradle file
repositories {
    maven {
        url  "https://dl.bintray.com/vexigon/maven"
    }
}

dependencies {
    // ...
    compile 'com.vexigon:Material-Onboarding:1.2'
}
```

Next, add a manifest activity declaration, and set its theme. <b>NOTE: Make sure your theme's parent is `Sometheme.NoActionBar` otherwise your onboarding activity will have a toolbar.</b>

```xml
<activity
    android:name="com.vexigon.libraries.onboarding.ui.activity.UserBenefitsActivity"
    android:theme="@style/Onboarding" />
```

And now, the fun part! Create a new TopUserBenefitsModel instance like this:

```java
new TopUserBenefitsModel(this)
    .setupSlides(
            new Page("Title 1", "Subtitle 1", R.mipmap.ic_launcher),
            new Page("Title 2", "Subtitle 2", R.mipmap.ic_launcher),
            new Page("Title 3", "Subtitle 3", "Custom Button Text", R.mipmap.ic_launcher)
    )
    .launch();
```

## Code Overview

Below, you'll find info for each of the methods for setting up your onboarding activity.

### new TopUserBenefitsModel(Activity activity)

The constructor takes an activity as its parameter. 

```java
// For launching from activity...
@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.demo:
            new TopUserBenefitsModel(this)
                    ...
            break;
    }
}
```

### setupSlides()

This method takes a String array of titles for each slide. The items in the array correspond to the title on each slide. (Array position 0 sets the title of the first slide, and so on.)

```java
// Sample
new TopUserBenefitsModel(Activity activity)
    .setupSlides(
            new Page("Title 1", "Subtitle 1", R.mipmap.ic_launcher),
            new Page("Title 2", "Subtitle 2", R.mipmap.ic_launcher),
            new Page("Title 3", "Subtitle 3", "Custom Button Text", R.mipmap.ic_launcher)
    );
```

#### Page object
The SSPage object takes 4 parameters total. See below:

```java
// 3 required
new Page(String pageTitle, String pageSubtitle, int drawableResource);

// 1 optional
new Page(String pageTitle, String pageSubtitle, String buttonText, int drawableResource);
```

### launch()

This method launches the activity. No parameters are required.

## Sample App

Review the sample app code [here](https://github.com/Andrew-Quebe/Material-Onboarding/tree/master/sample), and download the APK [here](https://github.com/Vexigon/Material-Onboarding/tree/master/apks).

## Thanks

This library makes use of some other libraries made by some awesome developers around the Github community.

* [InkPagerIndicator](https://github.com/DavidPacioianu/InkPageIndicator) by David Pacioianu ([@DavidPacioianu](https://github.com/DavidPacioianu))
* [Glide](https://github.com/bumptech/glide) by Sam Judd ([@sjudd](https://github.com/sjudd))

Thank you!

## License

See [LICENSE.md](https://github.com/Vexigon/Material-Onboarding/blob/master/LICENSE.md)

## Developed By

Andrew Quebe

[Github](https://github.com/Andrew-Quebe) | [Google+](https://google.com/+AndrewQuebe) | [Twitter](https://twitter.com/andrew_quebe)

