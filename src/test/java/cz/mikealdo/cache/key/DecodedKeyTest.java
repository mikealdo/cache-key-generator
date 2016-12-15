package cz.mikealdo.cache.key;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DecodedKeyTest {

    private static final String ENCODED_KEY = "MjAxNjEwMjgxNTMxIyMzNDUyMzQ1LTI0NS0yNDM1MjM0NTIzNDU=";
    private static final String INPUT_HASH = "3452345-245-243523452345";


    @Test
    public void shouldDecodeCacheKey() throws Exception {
        final KeyDescription description =
            new DecodedKey(
                DecodedKeyTest.ENCODED_KEY
            ).getDescription();

        Assertions.assertThat(description.getHash()).isEqualTo
            (DecodedKeyTest
                 .INPUT_HASH);
    }
}
