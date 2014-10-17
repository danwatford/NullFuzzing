Experiments with a Null Fuzzer - a method of substituting a null value for each of a constructor's 
arguments, in turn, to ensure correct error handling - i.e. throwing of IllegalArgumentException.

This isn't anything usable at the moemnt, its just me trying a few things out.

Why
---
I often come across legacy sources which do have plenty of tests (yay!) but they seem very repetitive and difficult to maintain (boo!).
One particular pattern I keep finding is a class which takes a number of dependencies in its constructor and will throw an
IllegalArgumentException if any of those dependencies is null. (Perhaps those classes should be throwing NullPointerException instead).

I then find associated test code where an individual test has been written to exercise calling the constructor with a null value
for each of the dependencies. This test code becomes difficult to maintain when it turns out you need to alter the constructor's 
parameter list, necessitating many test changes. 

There must be a better way!

ScalaCheck's generators look like they would provide a way to exercise this constructor, passing in null or valid instances of the 
various dependencies, but it might not be possible to bring in Scala based tools.

JUnit has the @Theory annotation for theories, a type of test which is called repeatedly for different combinations of @DataPoints.
Within the theory we can have assumptions filter out the invalid tests - i.e. those where none
of the dependencies is null, and then call the constructor and assert that the appropriate exception was thrown.

JUnit-QuickCheck (https://github.com/pholser/junit-quickcheck) gives us Generators for use with Theories. This project has a
MockGenerator to generate Mockito mocks for a theory's arguments. See src/test/java/ContribTheoryWithGeneratorsTest and
src/test/java/MockGenerator. This implementation depends on using the @ForAll and the @From annotations provided by JUnit-QuickCheck.

Using the MockGenerator with JUnit-QuickCheck can replace all those individual tests mentioned earlier, reducing the amount 
of code to be maintained.

The next step could be a Null Fuzzing annotation for a theory which could act as an alias for the @ForAll and @From annotation 
needed on each of a Theory's parameters.

Is this a good test
-------------------
Regardless of whether you think it is worth 'fuzzing' constructor arguments with null to prove null checking is working, there is
so much of this sort of test code out there that it will be worth finding a way to continue to exercise the null checking in a
Java only environment while reducing the amount of test code needed to do it. 
