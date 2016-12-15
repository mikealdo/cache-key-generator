package cz.mikealdo.cache.key;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class EncodedKeyTest {

    private static final String ENCODED_KEY = "MjAxNjEwMjgxNTMxIyMzNDUyMzQ1LTI0NS0yNDM1MjM0NTIzNDU=";
    private static final String INPUT_HASH = "3452345-245-243523452345";


    @Test
    public void shouldProduceValidCacheKey() throws Exception {
        final DateTime currentTime = new DateTime(2016, 10, 28, 15, 31);
        final EncodedKey keyGenerator =
            new EncodedKey(
                new KeyDescription(
                    currentTime,
                    EncodedKeyTest.INPUT_HASH
                )
            );

        final String key = keyGenerator.getKey();

        Assertions.assertThat(key).isEqualTo(EncodedKeyTest.ENCODED_KEY);
    }

}
