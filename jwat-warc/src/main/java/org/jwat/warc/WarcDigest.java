/**
 * Java Web Archive Toolkit - Software to read and validate ARC, WARC
 * and GZip files. (http://jwat.org/)
 * Copyright 2011-2012 Netarkivet.dk (http://netarkivet.dk/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jwat.warc;

import org.jwat.common.Digest;

/**
 * This class represents the parsed and format validated information provided
 * from a WARC digest header value.
 *
 * @author nicl
 */
public class WarcDigest extends Digest {

	/**
	 * Package level constructor.
	 */
	protected WarcDigest() {
	}

	/**
     * Construct an object with the supplied parameters.
     * @param algorithm digest algorithm
     * @param digestValue digest value in encoded format. (base16/32/64)
     */
    protected WarcDigest(String algorithm, String digestValue) {
        this.algorithm = algorithm;
        this.digestString = digestValue;
    }

    /**
     * Parse and validate the format of a WARC digest header value.
     * @param labelledDigest WARC digest header value
     * @return <code>WarcDigest</code> object or <code>null</code>
     */
    public static WarcDigest parseWarcDigest(String labelledDigest) {
        if (labelledDigest == null || labelledDigest.length() == 0) {
            return null;
        }
        String algorithm;
        String digestValue;
        int cIdx = labelledDigest.indexOf(':');
        if (cIdx != -1) {
            algorithm = labelledDigest.substring(0, cIdx).trim().toLowerCase();
            digestValue = labelledDigest.substring(cIdx + 1).trim();
            if (algorithm.length() > 0 && digestValue.length() > 0) {
                return new WarcDigest(algorithm, digestValue);
            }
        }
        return null;
    }

	/**
     * Create an object with the supplied parameters.
     * @param algorithm digest algorithm
     * @param digestBytes digest in byte form
     * @param encoding encoding used
     * @param digestValue digest value in encoded form.
     */
	public static WarcDigest createWarcDigest(String algorithm, byte[] digestBytes, String encoding, String digestValue) {
		WarcDigest digest = new WarcDigest();
		if (algorithm != null) {
			algorithm = algorithm.toLowerCase();
		}
		if (encoding != null) {
			encoding = encoding.toLowerCase();
		}
		digest.algorithm = algorithm;
		digest.digestBytes = digestBytes;
		digest.encoding = encoding;
		digest.digestString = digestValue;
        return digest;
	}

    /**
     * Returns a header representation of the class state.
     * @return header representation of the class state
     */
    @Override
    public String toString() {
        return (algorithm + ":" + digestString);
    }

    public String toStringFull() {
    	return (algorithm + ":" + encoding + ":" + digestString);
    }

}
