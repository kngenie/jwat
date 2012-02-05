package org.jwat.arc;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ARC Reader base class.
 *
 * @author nicl
 */
public abstract class ArcReader {

    /** Compliance status for records parsed up to now. */
    protected boolean bIsCompliant = true;

    /** Number of bytes consumed by this reader. */
    protected long consumed = 0;

    /** Aggregated number of errors encountered while parsing. */
    protected int errors = 0;

    /** Block Digest enabled/disabled. */
    protected boolean bBlockDigest = false;

    /** Optional block digest algorithm to use. */
    protected String blockDigestAlgorithm;

    /** Encoding scheme used to encode block digest into a string. */
    protected String blockDigestEncoding = "base32";

    /** Payload Digest enabled/disabled. */
    protected boolean bPayloadDigest = false;

    /** Optional payload digest algorithm to use. */
    protected String payloadDigestAlgorithm;

    /** Encoding scheme used to encode payload digest into a string. */
    protected String payloadDigestEncoding = "base32";

    /** Current ARC version block object. */
    protected ArcVersionBlock versionBlock = null;

    /** Current ARC record object. */
    protected ArcRecord arcRecord = null;

    /** Previous record of either kind. */
    protected ArcRecordBase previousRecord = null;

    /** Exception thrown while using the iterator. */
    protected Exception iteratorExceptionThrown;

    /**
     * Returns a boolean indicating whether the reader has only parsed
     * compliant records up to now.
     * @return a boolean indicating all compliant records parsed to far
     */
    public boolean isCompliant() {
        return bIsCompliant;
    }

    /**
     * Is this reader assuming GZip compressed input.
     * @return boolean indicating the assumption of GZip compressed input
     */
    public abstract boolean isCompressed();

    /**
     * Get the readers block digest on/off status.
     * @return boolean indicating block digest on/off
     */
    public boolean getBlockDigestEnabled() {
        return bBlockDigest;
    }

    /**
     * Set the readers block digest on/off status. Digest, however,
     * will only be computed if an algorithm has also been chosen.
     * @param enabled boolean indicating block digest on/off
     */
    public void setBlockDigestEnabled(boolean enabled) {
        bBlockDigest = enabled;
    }

    /**
     * Get the readers payload digest on/off status.
     * @return boolean indicating payload digest on/off
     */
    public boolean getPayloadDigestEnabled() {
        return bPayloadDigest;
    }

    /**
     * Set the readers payload digest on/off status. Digest, however,
     * will only be computed if an algorithm has also been chosen.
     * @param enabled boolean indicating payload digest on/off
     */
    public void setPayloadDigestEnabled(boolean enabled) {
        bPayloadDigest = enabled;
    }

    /**
     * Get the optional block digest algorithm.
     * @return optional block digest algorithm
     */
    public String getBlockDigestAlgorithm() {
        return blockDigestAlgorithm;
    }

    /**
     * Set the optional block digest algorithm.
     * @param digestAlgorithm block digest algorithm
     * (null means optional block digest is disabled)
     * @throws NoSuchAlgorithmException occurs in case the algorithm can not
     * be identified
     */
    public void setBlockDigestAlgorithm(String digestAlgorithm)
                                            throws NoSuchAlgorithmException {
        if (digestAlgorithm != null) {
            MessageDigest.getInstance(digestAlgorithm);
        }
        blockDigestAlgorithm = digestAlgorithm;
    }

    /**
     * Get the optional payload digest algorithm.
     * @return optional payload digest algorithm
     */
    public String getPayloadDigestAlgorithm() {
        return payloadDigestAlgorithm;
    }

    /**
     * Set the optional payload digest algorithm.
     * @param digestAlgorithm payload digest algorithm
     * (null means optional payload digest is disabled)
     * @throws NoSuchAlgorithmException occurs in case the algorithm can not
     * be identified
     */
    public void setPayloadDigestAlgorithm(String digestAlgorithm)
            throws NoSuchAlgorithmException {
        if (digestAlgorithm != null) {
            MessageDigest.getInstance(digestAlgorithm);
        }
        payloadDigestAlgorithm = digestAlgorithm;
    }

    /**
     * Get the optional block digest encoding scheme.
     * @return optional block digest encoding scheme
     */
    public String getBlockDigestEncoding() {
        return blockDigestEncoding;
    }

    /**
     * Set the optional block digest encoding scheme.
     * @param encodingScheme encoding scheme
     * (null means optional block digest is not encoded)
     */
    public void setBlockDigestEncoding(String encodingScheme) {
        if (encodingScheme != null && encodingScheme.length() > 0) {
            blockDigestEncoding = encodingScheme.toLowerCase();
        } else {
            blockDigestEncoding = null;
        }
    }

    /**
     * Get the optional payload digest encoding scheme.
     * @return optional payload digest encoding scheme
     */
    public String getPayloadDigestEncoding() {
        return payloadDigestEncoding;
    }

    /**
     * Set the optional payload digest encoding scheme.
     * @param encodingScheme encoding scheme
     * (null means optional payload digest is not encoded)
     */
    public void setPayloadDigestEncoding(String encodingScheme) {
        if (encodingScheme != null && encodingScheme.length() > 0) {
            payloadDigestEncoding = encodingScheme.toLowerCase();
        } else {
            payloadDigestEncoding = null;
        }
    }

    /**
     * Close current record resource(s) and input stream(s).
     */
    public abstract void close();

    /**
     * Get the currect offset in the ARC <code>InputStream</code>.
     * @return offset in ARC <code>InputStream</code>
     * @see ArcRecordBase#getOffset()
     */
    @Deprecated
    public abstract long getOffset();

    /** Get number of bytes consumed by this reader.
     * @return number of bytes consumed by this reader
     */
    public long getConsumed() {
        return consumed;
    }

    /**
     * Parses and gets the version block of the ARC file.
     * @return the version block of the ARC file
     * @throws IOException io exception in reading process
     */
    public abstract ArcVersionBlock getVersionBlock() throws IOException;

    /**
     * Parses and gets the version block of an ARC file from the supplied
     * <code>InputStream</code>.
     * @param in input stream from which to read version block
     * @return the version block of the ARC file
     * @throws IOException io exception in reading process
     */
    public abstract ArcVersionBlock getVersionBlock(InputStream in)
            throws IOException;

    /**
     * Parses and gets the next ARC record.
     * @return the next ARC record
     * @throws IOException io exception in reading process
     */
    public abstract ArcRecord getNextRecord() throws IOException;

    /**
     * Parses and gets the next ARC record.
     * @param in ARC record <code>InputStream</code>
     * @param offset offset provided by caller
     * @return the next ARC record
     * @throws IOException io exception in reading process
     */
    public abstract ArcRecord getNextRecordFrom(InputStream in, long offset)
            throws IOException;

    /**
     * Parses and gets the next ARC record.
     * @param in ARC record <code>InputStream</code>
     * @param buffer_size size of buffer used to wrap <code>InputStream</code>
     * @param offset offset provided by caller
     * @return the next ARC record
     * @throws IOException io exception in reading process
     */
    public abstract ArcRecord getNextRecordFrom(InputStream in,
            int buffer_size, long offset) throws IOException;

    /**
     * Gets an exception thrown in the iterator if any or null.
     * @return exception thrown in the iterator if any or null
     */
    public Exception getIteratorExceptionThrown() {
        return iteratorExceptionThrown;
    }

    /**
     * Returns an <code>Iterator</code> over the records as they are being
     * parsed. Any exception thrown during parsing is accessible through the
     * <code>getIteratorExceptionThrown</code> method.
     * @return <code>Iterator</code> over the <code>WARC</code> records
     */
    public Iterator<ArcRecord> iterator() {
        return new Iterator<ArcRecord>() {

            private ArcRecord next;

            private ArcRecord current;

            @Override
            public boolean hasNext() {
                if (next == null) {
                    iteratorExceptionThrown = null;
                    try {
                        next = getNextRecord();
                    } catch (IOException e) {
                        iteratorExceptionThrown = e;
                    }
                }
                return (next != null);
            }

            @Override
            public ArcRecord next() {
                if (next == null) {
                    iteratorExceptionThrown = null;
                    try {
                        next = getNextRecord();
                    } catch (IOException e) {
                        iteratorExceptionThrown = e;
                    }
                }
                if (next == null) {
                    throw new NoSuchElementException();
                }
                current = next;
                next = null;
                return current;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
