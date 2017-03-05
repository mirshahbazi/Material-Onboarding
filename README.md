#Material-Onboarding [![](https://jitpack.io/v/Vexigon/Material-Onboarding.svg)](https://jitpack.io/#Vexigon/Material-Onboarding)

![Sample image](https://github.com/Vexigon/Material-Onboarding/raw/master/art/onboarding_sample.png)

<p align="center">
  (Image is of sample usage from an unreleased app)
</p>

A short and simple library which allows easy replication of several app onboarding techniqies found [here](https://material.io/guidelines/growth-communications/onboarding.html).

##Background

The Material Design guidelines list a lot of different techniques for onboarding users in your apps. The main concept is to remain as simple as possible, and do away with complicated introductions to your app.

##*What's Included

For now, this library only allows the creation of the [TopUserBenefitsModel](https://material.io/guidelines/growth-communications/onboarding.html#onboarding-top-user-benefits) technique. Later on, it will make use of the other techniques as well.
 
##Enough Talking. How do I use this?

First off, add the Gradle dependency to your app:

```groovy
// Add this in your root build.gradle file
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
// And add this to your module build.gradle file
dependencies {
    // ...
    compile 'com.github.Vexigon:Material-Onboarding:v1.0'
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
```

##Code Overview

Below, you'll find info for each of the methods for setting up your onboarding activity.

###new TopUserBenefitsModel(Activity activity)

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

###setTitleText()

This method takes a String array of titles for each slide. The items in the array correspond to the title on each slide. (Array position 0 sets the title of the first slide, and so on.)

```java
// Sample
new TopUserBenefitsModel(Activity activity)
    .setTitleText(new String[]{
        "Title 1",
        "Title 2",
        "Title 3"
    });
```

###setSubtitles()

Similar to `setTitletext()`, this method sets the subtitles for the onboarding activity.

```java
new TopUserBenefitsModel(Activity activity)
    .setSubtitles(new String[]{
        "Subtitle 1",
        "Subtitle 2",
        "Subtitle 3"
    });
```

###setIllustrations()

This method takes and array of integer resource values for each slide.

```java
new TopUserBenefitsModel(this)
    .setIllustrations(new int[]{
            R.drawable.drawable1,
            R.drawable.drawable2,
            R.drawable.drawable3
    });
```

###setButtonText()

This method takes an array of Strings and sets the button text for each slide.

<b>WARNING: overriding these fields technically violates the Material Design Guidelines, as they state that this technique should have a button that explicitly says "Get Started". Use at your own discretion. Defaults to "Get Started" if method not called.</b>

```java
new TopUserBenefitsModel(this)
    .setButtonText(new String[]{
       "Get Started",
       "Get Started",
       "Get Started"
   });
```

###launch()

This method launches the activity. No parameters are required.

##Sample App

Review the sample app code [here](https://github.com/Andrew-Quebe/Material-Onboarding/tree/master/sample), and download the APK [here](https://github.com/Andrew-Quebe/Material-Onboarding/blob/master/apks/samplev1.apk?raw=true).

##Thanks

This library makes use of some other libraries made by some awesome developers around the Github community.

* [InkPagerIndicator](https://github.com/DavidPacioianu/InkPageIndicator) by David Pacioianu ([@DavidPacioianu](https://github.com/DavidPacioianu))
* [Glide](https://github.com/bumptech/glide) by Sam Judd ([@sjudd](https://github.com/sjudd))

Thank you!

##License

See [LICENSE.md](https://github.com/Vexigon/Material-Onboarding/blob/master/LICENSE.md)

##Developed By

Andrew Quebe

[Github](https://github.com/Andrew-Quebe) | [Google+](https://google.com/+AndrewQuebe) | [Twitter](https://twitter.com/andrew_quebe)

