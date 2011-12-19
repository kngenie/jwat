package dk.netarkivet.warclib;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;

/**
 * 
 *
 * @author nicl
 */
public abstract class WarcWriter {

    /** WARC <code>DateFormat</code> as specified by the WARC ISO standard. */
    protected DateFormat warcDateFormat = WarcDateParser.getWarcDateFormat();

    /** Block Digesting enabled/disabled. */
    protected boolean bDigestBlock = false;

    /**
     * Is this writer producing compressed output.
     * @return boolean indicating whether compressed output is produced
     */
    public abstract boolean isCompressed();

    /**
     * Is this writer set to block digest payload.
     * @return boolean indicating payload block digesting
     */
    public boolean digestBlock() {
        return bDigestBlock;
    }

    /**
     * Set the writers payload block digest mode
     * @param enabled boolean indicating digest on/off
     */
    public void setDigestBlock(boolean enabled) {
        bDigestBlock = enabled;
    }

    /**
     * Close current record resource(s) and input stream(s).
     */
    public abstract void close();

    public abstract void write(WarcRecord record) throws IOException;

	public abstract long transfer(InputStream in, long length) throws IOException;

	public abstract void closeRecord() throws IOException;

}
