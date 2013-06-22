This project illustrates the use of the mockrunner framework to mock out a database connection.
For more information about the framework [click here] (http://mockrunner.sourceforge.net/)

1. The jdbc part of mockrunner is used to mock out the database interaction.
* The main purpose of this framework is to write jdbc data access layer before creating the database
of even deciding which database and which jdbc driver to use.
* As long as we know which database behaviour to expect, we can completely write and test the data access object.
