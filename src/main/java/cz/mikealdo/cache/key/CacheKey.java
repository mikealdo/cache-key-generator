/**
 * Copyright (c) 2016, cache-key-generator for fotbal-cz-api.cz
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: no conditions.
 */
package cz.mikealdo.cache.key;

import java.nio.charset.Charset;

/**
 * Interface with common constants used for encoding/decoding keys.
 *
 * @author Michal Davidek (mdavidek1@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface CacheKey {

    /**
     * Default charset for given encoded keys.
     */
    Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    /**
     * Desired date format of string passed for key generation.
     */
    String DEFAULT_DATE_FMT = "YYYYMMdHmm";
    /**
     * Delimiter of values used for key generation.
     */
    String DELIMITER = "##";

    /**
     * Returns current encoded key.
     *
     * @return Encoded key.
     */
    String getKey();

    /**
     * Returns current key description.
     *
     * @return Current key description.
     */
    KeyDescription getDescription();
}
