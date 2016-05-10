### Java TestNG examples with the [Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

These examples are running two tests written in Java and using TestNG. The Surefire Plugin is in charge of passing
the parallelism options to TestNG.

The [Threads](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng/threads)
implementation is running the tests in two threads and only one browser/platform combination.

The [Threads and Browsers](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng
/threads-and-browsers) implementation runs the tests in five different browser/platform combinations.

Both implementations are made to run on [SauceLabs](https://saucelabs.com/)

#### Environment Setup:

1. Global Dependencies
    * [Install Maven](https://maven.apache.org/install.html)
    * Or Install Maven with [Homebrew](http://brew.sh/)
    ```
    $ brew install maven
    ```
1. Sauce Credentials
    * In the terminal export your Sauce Labs Credentials as environmental variables:
    ```
    $ export SAUCE_USERNAME=<your Sauce Labs username>
    $ export SAUCE_ACCESS_KEY=<your Sauce Labs access key>
    ```

#### Steps to run it:

1. Clone the repo:

    ```
    $ git clone https://github.com/diemol/frontend_testing.git
    ```
1. Change directory to execute the examples:

    [Threads](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng/threads)
    example:

    ```
    $ cd more-examples-parallel/java-testng/threads
    ```
    [Threads and Browsers](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng
    /threads-and-browsers) example:

    ```
    $ cd more-examples-parallel/java-testng/threads-and-browsers
    ```
1. Execute the code

	```
	$ mvn test
	```

Afterwards you can check the executed test in your [Sauce Labs Dashboard](https://saucelabs.com/beta/dashboard/)


#### How does parallelism work in this example?

The parallelism configuration for [TestNG](http://testng.org/doc/index.html) is configured via the [Surefire
Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/), for the Threads example it can be seen [here]
(https://github.com/diemol/frontend_testing/blob/master/more-examples-parallel/java-testng/threads/pom.xml#L52). For
the Threads and Browsers example can be seen [here](https://github
.com/diemol/frontend_testing/blob/master/more-examples-parallel/java-testng/threads-and-browsers/pom.xml#L54).

Something very important to be noted is how the `webDriver` variable is created, different from the single-threaded
examples, the `webDriver` variable needs to be threadsafe because each session must be exclusive to avoid
interference between tests. This example uses a `ThreadLocal<WebDriver>`, which is a common practice to keep the
WebDriver session threadsafe.

_[Threads](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng/threads) example:_

In this example, `methods` and two threads are configured for parallelism, which means that each test in the
example will be executed in its own thread.


_[Threads and Browsers](https://github.com/diemol/frontend_testing/tree/master/more-examples-parallel/java-testng
/threads-and-browsers) example:_

In this example, a dataProvider is configured with five different browser/platform configurations, which means that
each test will be executed in each platform/browser configuration. The parameters used for `parallel` and
`dataproviderthreadcount` are `methods` and `5` respectively. This means that five threads will be used to execute
the tests generated by the combinations given by the dataProvider. A great explanation of this feature in TestNG can
be seen [here](http://beust.com/weblog2/archives/000513.html).


You can play around with the `classes`, `methods`, `classesAndMethods` and `dataproviderthreadcount` parameters in
the `<parallel>` tag to find a tuned configuration for your test suite.
