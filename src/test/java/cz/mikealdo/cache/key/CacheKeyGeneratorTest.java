package cz.mikealdo.cache.key;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

public class CacheKeyGeneratorTest {

    private static final String ENCODED_KEY = "MjAxNjEwMjgxNTMxIyMzNDUyMzQ1LTI0NS0yNDM1MjM0NTIzNDU=";
    private static final String INPUT_HASH = "3452345-245-243523452345";
    private final CacheKeyGenerator keyGenerator = new CacheKeyGenerator();

    @Test
    public void shouldProduceValidCacheKey() throws Exception {
        final DateTime currentTime = new DateTime(2016, 10, 28, 15, 31);

        final String key = keyGenerator.generateKey(new
                CacheKeyDescriptor(currentTime, CacheKeyGeneratorTest
                .INPUT_HASH));

        Assertions.assertThat(key).isEqualTo(CacheKeyGeneratorTest.ENCODED_KEY);
    }

    @Test
    public void shouldDecodeCacheKey() throws Exception {
        final CacheKeyDescriptor descriptor = keyGenerator.decodeKey
                (CacheKeyGeneratorTest.ENCODED_KEY);

        Assertions.assertThat(descriptor.getHash()).isEqualTo
                (CacheKeyGeneratorTest
                .INPUT_HASH);
    }
}
