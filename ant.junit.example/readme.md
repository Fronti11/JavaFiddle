###ANT Basics with Junit Testing
We are going to create a project which has a couple of tests.  The tests are plain junit tests and mockrunner jdbc unit tests.
In this project I am going to illustrate how we can use ant to compile the project and run unit tests as part of a build process.

####Create the Project
Open eclipse and create a java project with file > New > Java Project
1. Create a libs
* Create the build or bin folder if it doesn't exist.
* Add external jars to libs, including junit jar.
* Use eclipse to add jars to build path.
* Create unit test class.
* Create mockrunner jdbc unit test class with methods starting with "test".
* Test these test classes by running the test from within eclipse.

###Create the Build Script

1. Within the base folder of the project create a file called the build.xml
