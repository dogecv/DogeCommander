# DogeCommander
A new way to write code for FTC bots! 

## What is this?
This is a Command library.

What does that mean? That means it gives you a convienient way to modularly 
program FTC robots. Your code is split into 2 main parts: `Subsystem`s, and 
`Command`s. `Subsystem`s represent a physical part of the robot, while 
`Command`s represent an action a `Subsystem` can take. `Command`s can also be 
easily run parallely.

## How do I install it?
1. Add the Jitpack repository at the end of your root `build.release.gradle` at the end of repositories:
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' } // this line!
  }
}
```
2. Add the line `implementation 'com.github.dogecv:dogecommander:v1.0.0'` to TeamCode's `build.gradle`, inside the `dependencies` block. See the [DogeQuickStart](https://github.com/dogecv/DogeQuickStart/blob/6783d597d9b6f6dc9fb1c841033498a61658bd13/TeamCode/build.release.gradle#L10)
3. Run a gradle build, and ensure it works
4. Your install should be complete!

For more details, check out the [JitPack page for DogeCommander](https://jitpack.io/#dogecv/dogecommander/).

## How do I use it?
There are two main documentation sources; the [examples](https://github.com/dogecv/DogeQuickStart/tree/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/dogecommander), and the [JavaDocs](https://dogecv.github.io/DogeCommander/)
