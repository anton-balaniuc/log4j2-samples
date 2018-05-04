## Simple extension which add emoji to the log message.  

This is just an example how extend log4j2 using custom `PatternConvertor`. Please see official [documentation](https://logging.apache.org/log4j/2.x/manual/extending.html#PatternConverters) for more
 details. There is a custom [`EmojiConvertor`](/Users/anton/Documents/workspace/log4j2-samples/extension/src/main/java/org/apache/logging/log4j/samples/extension/plugin/convertor/EmojiConvertor.java) which transforms `%emoji` pattern into a random emoji.   

## Code Example

``` 
@Plugin(name = "EmojiConvertor", category = PatternConverter.CATEGORY)
@ConverterKeys({ "emoji" })
public class EmojiConvertor extends LogEventPatternConverter{
 ...
     /**
     * {@inheritDoc}
     */
     @Override
     public void format(final LogEvent event, final StringBuilder toAppendTo) {
         toAppendTo.append(Character.toChars(ThreadLocalRandom.current().nextInt(0x1F601, 0x1F64F)));
     }
}        
```

We are interested in `EmojiConvertor#format(LogEvent,StringBuilder)` method. This method is in charge of converting 
custom pattern into a random emoji. The custom pattern is `%emoji`, `ThreadLocalRandom.current().nextInt(0x1F601, 
0x1F64F)` produces a random emoji starting from `0x1F601` - [GRINNING FACE WITH SMILING EYES](https://apps.timwhitlock.info/unicode/inspect/hex/1F601)  ending `0x1F64F` - [PERSON  FOLDED HANDS](https://apps.timwhitlock.info/unicode/inspect/hex/1F64F). 


## Configuration
Now inside log4j2.properties we just need to add a new pattern convertor:
```
appender.stdout_to_file.layout.pattern = %emoji %d [%p] - %m%n
```

and the final step is to execute App.java. You should be able to see something similar in the output:
```
😑 2018-05-04 23:07:18,680 [INFO] - message 0
😘 2018-05-04 23:07:18,683 [INFO] - message 1
😐 2018-05-04 23:07:18,683 [INFO] - message 2
🙄 2018-05-04 23:07:18,683 [INFO] - message 3
😈 2018-05-04 23:07:18,683 [INFO] - message 4
😤 2018-05-04 23:07:18,683 [INFO] - message 5
```