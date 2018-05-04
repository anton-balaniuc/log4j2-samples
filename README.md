## How to write System.out and System.err to a file with log4j2 

This is just an example how to write System.out and System.err to a file with log4j2. There is a [log4j-iostreams](https://logging.apache.org/log4j/2.0/log4j-iostreams/index.html) 
module which really simplify this task. All we need to do is just to obtain a `PrintStream` using [`IoBuilder`](https://logging.apache.org/log4j/2.0/log4j-iostreams/apidocs/org/apache/logging/log4j/io/IoBuilder.html) and set
 `System.setOut` to that `PrintStream`. Please see an example below. 

## Code Example

```         
// redirect system.Out to a file
System.setOut(IoBuilder.forLogger(LogManager.getLogger("system.out")).buildPrintStream());

// redirect system.Err to a file
System.setErr(IoBuilder.forLogger(LogManager.getLogger("system.err")).buildPrintStream());
```

## API Reference

For the more detailed example please see [App.java](iostreams/src/main/java/org/apache/logging/log4j/samples/iostreams/App.java)

## Tests

There are 2 tests: 
* [ConfigTest](iostreams/src/test/java/org/apache/logging/log4j/samples/iostreams/ConfigTest.java) 
which verifies if log4j2 is configured property for unit tests. 
* [SystemTest](iostreams/src/test/java/org/apache/logging/log4j/samples/iostreams/SystemTest.java) 
which verifies if `System.out` and `System.err` actually write to a file. 


see also [extension](extension/README.md)