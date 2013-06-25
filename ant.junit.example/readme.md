###ANT Basics with Junit Testing
In this project I am going to illustrate how we can use ant to compile the project and run unit tests as 
part of a build process.

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
Within the base folder of the project create a file called the build.xml.  The aim of the build file is to compile 
and run unit test if there are any within the project.

```xml
 <project default="main" name="Testing ant buildscript">
```
 
 This first line just names the build process and tells the process which target to kick off first.  This shows the "main"
 target will be kicked off first.
 
```xml
  <target name="main" depends="clean, runjunit"></target>
```
 
 We see the main target just calls the clean, which just deleted the build folder and the runjunit targets
 
```xml
     <target name="runjunit" depends="compile" >
      <junit printsummary="yes">
    	    
    		<!--We search through the whole project for classes with Test in them-->
    		<batchtest fork="yes">
    	    	<fileset dir="${build.dir}" includes="**/*Test*"/>
    	    </batchtest>
    		
    		<!--We run tests on the class files so other than the jars we need to include them as well-->
    		<classpath>
    			<path refid="jarpath"></path>
    			<pathelement location="${build.dir}"/>
    		</classpath>
    		
    	</junit>
        
    </target>
```
We can see here this target just looks for all tests with the name Test in it and runs it using the jars in the jarpath.
It is very important to add the build folder path as this is where the unit test class files are.

The complete file is as follows:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project default="main" name="Testing ant buildscript">
  
	<!--A couple of re-usable properties declared-->
	<property name="build.dir" value="bin" />
	<property name="lib.dir" value="libs" />
	
	<!--Include all jar files in libs folder-->
    <path id="jarpath">
        <fileset dir="${lib.dir}" includes="**/*.jar"/>
    </path>
	
	
    <target name="runjunit" depends="compile" >
    	<junit printsummary="yes">
    	    
    		<!--We search through the whole project for classes with Test in them-->
    		<batchtest fork="yes">
    	    	<fileset dir="${build.dir}" includes="**/*Test*"/>
    	    </batchtest>
    		
    		<!--We run tests on the class files so other than the jars we need to include them as well-->
    		<classpath>
    			<path refid="jarpath"></path>
    			<pathelement location="${build.dir}"/>
    		</classpath>
    		
    	</junit>
        
    </target>
	
    <!--We have to compile the code to a build dir before we can run tests-->
    <target name="compile">
    	<mkdir dir="${build.dir}"/>
    	
    	<!--Compile all source code to the build folder-->
    	<!--We include the jarpath as external libs are referenced/imported in the source-->
        <javac srcdir="./src" 
        	includeantruntime="false" 
        	destdir="${build.dir}" 
        	classpathref="jarpath" />
    </target>
	
	 <target name="clean">
	        <delete dir="${build.dir}"/>
	 </target>
	
	<target name="main" depends="clean, runjunit"></target>
    
</project>
```
