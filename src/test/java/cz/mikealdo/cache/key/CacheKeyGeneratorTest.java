package cz.mikealdo.cache.key;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CacheKeyGeneratorTest {
    
    private CacheKeyGenerator cacheKeyGenerator;// = new CacheKeyGenerator();

    @Test
    public void shouldProduceValidCacheKey() throws Exception {
        DateTime currentTime = new DateTime(2016, 10, 28, 15, 31);
        String competitionHash = "3452345-245-243523452345";

        String key = cacheKeyGenerator.generateKey(new CacheKeyDescriptor(currentTime, competitionHash));

        assertThat(key).isEqualTo("MjAxNjEwMjgxNTMxIyMzNDUyMzQ1LTI0NS0yNDM1MjM0NTIzNDU=");
    }

    @Test
    public void shouldDecodeCacheKey() throws Exception {
        String decodedKey = "MjAxNjEwMjgxNTMxIyMzNDUyMzQ1LTI0NS0yNDM1MjM0NTIzNDU=";

        CacheKeyDescriptor descriptor = cacheKeyGenerator.decodeKey(decodedKey);

        assertThat(descriptor.getCompetitionHash()).isEqualTo("3452345-245-243523452345");
        assertThat(descriptor.getCurrentTime()).isEqualTo(new DateTime(2016, 10, 28, 15, 31));
    }
}
