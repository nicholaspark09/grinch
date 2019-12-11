## The Grinch
An android library project meant to get the boilerplate out of instrumented testing out of the way.

### Architecture
This library is built with kotlin using interfaces when possible. The inherent structure
of instrumented testing makes it very difficult to structure the library in a decoupled
way.

### Example
In order to create instrumented tests,
users are encouraged to extend `BaseActivityTest<MainActivity>(MainActivity::class.java, true)`
The constructor allows the user to choose the screen they want to test as well as enable screenshots.

### How to declare in your gradle file
In the project gradle file (not app), please add jitpack

`buildscript {
    ext.kotlin_version = '1.3.50'
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
     }
     dependencies {...}
}`

In your app build.gradle:

`androidTestImplementation 'com.github.nicholaspark09:grinch:0.1.0'`