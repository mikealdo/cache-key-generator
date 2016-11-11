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
 * Generator of keys used for saving/retrieving results
 * from results-storage module.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CacheKeyGenerator {

    /**
     * Desired date format of string passed for key generation.
     */
    private static final String DATE_FMT = "YYYYMMdHmm";
    /**
     * Formatter for parsing date used by decoding generated key.
     */
    private static final DateTimeFormatter FORMATTER;
    /**
     * Delimiter of values used for key generation.
     */
    private static final String DELIMITER = "##";

    static {
        FORMATTER = DateTimeFormat.forPattern(DATE_FMT);
    }

    /**
     * This class is containing only static methods.
     */
    private CacheKeyGenerator() {
    }

    /**
     * Used for generation of the key by given descriptor.
     * @param descriptor Descriptor used as input parameter.
     * @return Hashed generated key
     */
    public static String generateKey(final CacheKeyDescriptor descriptor) {
        final StringBuilder hash = new StringBuilder();
        hash.append(descriptor.getCurrentTime().toString(DATE_FMT));
        hash.append(DELIMITER);
        hash.append(descriptor.getCompetitionHash());
        return new String(Base64.encode(hash.toString().getBytes()));
    }

    /**
     * Used for backward retrieve parameters from hashed generated key.
     * @param key Hashed generated key.
     * @return Parameters used for generation of the key.
     */
    public static CacheKeyDescriptor decodeKey(final String key) {
        final byte[] bytes = Base64.decode(key.getBytes());
        final String decoded = new String(bytes);
        final String[] split = decoded.split(DELIMITER);
        return new CacheKeyDescriptor(resolveDateTimeFrom(split[0]), split[1]);
    }

    /**
     * Parse text with given formatter.
     * @param text String containing date in Formatter pattern.
     * @return DateTime from string.
     */
    private static DateTime resolveDateTimeFrom(final String text) {
        return DateTime.parse(text, FORMATTER);
    }
}
