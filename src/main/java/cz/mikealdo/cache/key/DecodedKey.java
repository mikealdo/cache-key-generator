/**
 * Copyright (c) 2016, cache-key-generator for fotbal-cz-api.cz
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: no conditions.
 */
package cz.mikealdo.cache.key;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.crypto.codec.Base64;

/**
 * Return key description from given encoded hash.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class DecodedKey implements CacheKey {

    /**
     * Formatter for parsing date used by decoding generated key.
     */
    private final DateTimeFormatter formatter;

    /**
     * Description of the key.
     */
    private final String key;

    /**
     * Constructor with sensible defaults for date format.
     *
     * @param key Given encoded key.
     */
    public DecodedKey(final String key) {
        this.formatter = DateTimeFormat.forPattern(CacheKey.DEFAULT_DATE_FMT);
        this.key = key;
    }

    @Override
    public KeyDescription getDescription() {
        final byte[] bytes = Base64.decode(
            this.key.getBytes(DecodedKey.DEFAULT_CHARSET)
        );
        final String decoded = new String(
            bytes,
            CacheKey.DEFAULT_CHARSET
        );
        final String[] split = decoded.split(CacheKey.DELIMITER);
        return
            new KeyDescription(
                DateTime.parse(split[0], this.formatter),
                split[1]
            );
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
