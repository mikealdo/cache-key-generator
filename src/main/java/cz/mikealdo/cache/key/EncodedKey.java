/**
 * Copyright (c) 2016, cache-key-generator for fotbal-cz-api.cz
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: no conditions.
 */
package cz.mikealdo.cache.key;

import org.springframework.security.crypto.codec.Base64;

/**
 * Make a hash from given key description.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class EncodedKey implements CacheKey {

    /**
     * Format of date provided in cache key descriptor.
     */
    private final String format;

    /**
     * Description of the key.
     */
    private final KeyDescription description;

    /**
     * Constructor with sensible defaults for date format.
     *
     * @param description Given key description.
     */
    public EncodedKey(final KeyDescription description) {
        this.format = CacheKey.DEFAULT_DATE_FMT;
        this.description = description;
    }

    @Override
    public String getKey() {
        final StringBuilder hash = new StringBuilder();
        hash
            .append(this.description.getTime().toString(this.format))
            .append(CacheKey.DELIMITER)
            .append(this.description.getHash());
        return
            new String(
                Base64.encode(
                    hash.toString().getBytes(CacheKey.DEFAULT_CHARSET)
                ),
                CacheKey.DEFAULT_CHARSET
            );
    }

    @Override
    public KeyDescription getDescription() {
        return this.description;
    }
}
