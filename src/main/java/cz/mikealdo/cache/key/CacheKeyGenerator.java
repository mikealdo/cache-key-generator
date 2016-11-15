/**
 * Copyright (c) 2016, cache-key-generator for fotbal-cz-api.cz
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: no conditions.
 */
package cz.mikealdo.cache.key;

import java.nio.charset.Charset;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.crypto.codec.Base64;

/**
 * Generator of keys used for saving/retrieving results
 * from results-storage module.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CacheKeyGenerator {

    /**
     * Default charset for given encoded keys.
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    /**
     * Desired date format of string passed for key generation.
     */
    private static final String DEFAULT_DATE_FMT = "YYYYMMdHmm";
    /**
     * Delimiter of values used for key generation.
     */
    private static final String DELIMITER = "##";
    /**
     * Formatter for parsing date used by decoding generated key.
     */
    private final DateTimeFormatter formatter;

    /**
     * Format of date provided in cache key descriptor.
     */
    private final String format;

    /**
     * Constructor with sensible defaults for date format.
     */
    public CacheKeyGenerator() {
        this(CacheKeyGenerator.DEFAULT_DATE_FMT);
    }

    /**
     * DateFormat of given date included in descriptor.
     *
     * @param format Format for given date.
     */
    public CacheKeyGenerator(final String format) {
        this.format = format;
        this.formatter = DateTimeFormat.forPattern(this.format);
    }

    /**
     * Used for generation of the key by given descriptor.
     *
     * @param descriptor Descriptor used as input parameter.
     * @return Hashed generated key
     */
    public String generateKey(final CacheKeyDescriptor descriptor) {
        final StringBuilder hash = new StringBuilder();
        hash
            .append(descriptor.getTime().toString(this.format))
            .append(CacheKeyGenerator.DELIMITER)
            .append(descriptor.getHash());
        return
            new String(
                Base64.encode(
                    hash.toString().getBytes(CacheKeyGenerator.DEFAULT_CHARSET)
                ),
                CacheKeyGenerator.DEFAULT_CHARSET
            );
    }

    /**
     * Used for backward retrieve parameters from hashed generated key.
     *
     * @param key Hashed generated key.
     * @return Parameters used for generation of the key.
     */
    public CacheKeyDescriptor decodeKey(final String key) {
        final byte[] bytes = Base64.decode(
            key.getBytes(CacheKeyGenerator.DEFAULT_CHARSET)
        );
        final String decoded = new String(
            bytes,
            CacheKeyGenerator.DEFAULT_CHARSET
        );
        final String[] split = decoded.split(CacheKeyGenerator.DELIMITER);
        return
            new CacheKeyDescriptor(
                DateTime.parse(split[0], this.formatter),
                split[1]
            );
    }

}
