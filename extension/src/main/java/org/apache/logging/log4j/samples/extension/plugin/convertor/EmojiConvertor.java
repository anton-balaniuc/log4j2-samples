package org.apache.logging.log4j.samples.extension.plugin.convertor;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

import java.util.concurrent.ThreadLocalRandom;

@Plugin(name = "EmojiConvertor", category = PatternConverter.CATEGORY)
@ConverterKeys({ "emoji" })
public class EmojiConvertor extends LogEventPatternConverter{

        /**
         * Singleton.
         */
        private static final EmojiConvertor INSTANCE =
                new EmojiConvertor(new String[]{"Emoji","emoji"});

        /**
         * Private constructor.
         *
         * @param options options, may be null.
         */
        private EmojiConvertor(final String[] options) {
            super(options[0], options[1]);
        }

        /**
         * Obtains an instance of pattern converter.
         *
         * @param options options, may be null.
         * @return instance of pattern converter.
         */
        public static EmojiConvertor newInstance(
                final String[] options) {
            if (options == null || options.length == 0) {
                return INSTANCE;
            }

            return new EmojiConvertor(options);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void format(final LogEvent event, final StringBuilder toAppendTo) {
            toAppendTo.append(Character.toChars(ThreadLocalRandom.current().nextInt(0x1F601, 0x1F64F)));
        }
}
