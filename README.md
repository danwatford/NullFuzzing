Experiments with a Null Fuzzer - a method of substituting a null value for each of a constructor's 
arguments, in turn, to ensure correct error handling - i.e. throwing of IllegalArgumentException.

This isn't at the stage of being a usable library, its just me trying a few things out.

Why
---
I often come across legacy sources which do have plenty of tests (yay!) but they seem very repetitive and difficult to maintain.
One particular pattern I keep finding is a class which takes a number of dependencies in its constructor and will throw an
IllegalArgumentException if any of those dependencies is null.

I then find associated test code where an individual test has been written to exercise calling the constructor with a null value
for each of the dependencies. This test code becomes difficult to maintain when it turns out you need to alter the constructor's 
parameter list, necessitating many test changes. 

There must be a better way!

ScalaCheck's generators look like they would provide a way to exercise this constructor, passing in null or valid instances of the 
various dependencies, but it might not be possible to bring in Scala based tools.

JUnit has the @Theory annotation for tests, with the test called repeatedly for different combinations of @DataPoints. Within
the theory we can have assumptions filter out the invalid tests - i.e. those where none
of the dependencies is null, and then call the constructor and assert that the appropriate exception was thrown.

I would like to go one step further and have Mockito mocks generated for each for each of the dependency types.

When a theory fails it will be nice if JUnit can show us which combination of null arguments to the constructor caused the problem.

Is this a good test
-------------------
Regardless of whether you think it is worth 'fuzzing' constructor arguments with null to prove null checking is working, there is
so much of this sort of test code out there that it will be worth finding a way to continue to exercise the null checking in a
Java only environment while reducing the amount of test code needed to do it. 


Run using:
	sbt run
	
Test using:
	sbt test
	Looks like there is a problem here. How do we get sbt to use JUnit to run tests rather than ScalaTest. It appears that ScalaTest doesn't
	exercise JUnit Theories, essentially skipping the tests in this project.
	
	For now try running the tests as a JUnit test from within eclipse.

Eclipse:
To create project files to enable importing into Scala IDE or Eclipse with Scala plugins, run:
	sbt "eclipse with-source=true"
