/**
 * Copyright (c) 2016, cache-key-generator for fotbal-cz-api.cz
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: no conditions.
 */
package cz.mikealdo.cache.key;

import org.joda.time.DateTime;

/**
 * Descriptor used for cache key generator.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CacheKeyDescriptor {
    /**
     * Time is used for checking if the cache should be invalidated or not.
     */
    private final DateTime time;
    /**
     * Descriptor of competition being stored.
     */
    private final String hash;

    /**
     * Constructor for key descriptor.
     * @param time Current time
     * @param hash String with hash under the value will be stored.
     */
    public CacheKeyDescriptor(final DateTime time, final String hash) {
        this.time = time;
        this.hash = hash;
    }

    /**
     * Returns time used for key generation.
     * @return Current time
     */
    public DateTime getTime() {
        return this.time;
    }

    /**
     * Returns hash (aka competition hash) used for key generation.
     * @return Hash used for key generation
     */
    public String getHash() {
        return this.hash;
    }
}
