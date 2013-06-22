This project illustrates the use of the mockrunner framework to mock out a database connection.
For more information about the framework <a href="http://mockrunner.sourceforge.net" target="_blank">click here</a>

1. The jdbc part of mockrunner is used to mock out the database interaction.
* The main purpose of this framework is to write jdbc data access layer before creating the database
of even deciding which database and which jdbc driver to use.
* As long as we know which database behaviour to expect, we can completely write and test the data access object.

####How it works
When we are building an application we use separation of concerns(SoC) to divide responsibilities into distinct sections
of code.  Our sql data access class abstracts all sql database interaction away from the normal flow of the program.
Just as we unit test the business logic layer of our application, we would also like to verify and test the sql and 
the results it gives of the database access layer.

####Let's get started
Download the mockrunner bundle <a href="http://sourceforge.net/projects/mockrunner/files/" target="_blank">here</a> 
and from this add the following jars to your project:

1. 
