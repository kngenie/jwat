package dk.netarkivet.warclib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class WarcInputStream extends PushbackInputStream {

    /** Offset relative to beginning of stream. */
    protected long consumed = 0;

    public WarcInputStream(InputStream in, int size) {
		super(in, size);
	}

    /**
     * Retrieve the number of consumed bytes by this stream.
     * @return current byte offset in this stream
     */
    public long getConsumed() {
        return consumed;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public synchronized void mark(int readlimit) {
    }

    @Override
    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b != -1) {
            ++consumed;
        }
        return b;
    }

    @Override
	public int read(byte[] b) throws IOException {
        int n = super.read(b);
        if (n > 0) {
            consumed += n;
        }
        return n;
	}

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = super.read(b, off, len);
        if (n > 0) {
            consumed += n;
        }
        return n;
    }

	@Override
    public long skip(long n) throws IOException {
        n = super.skip(n);
        this.consumed += n;
        return n;
    }

	@Override
	public void unread(int b) throws IOException {
		super.unread(b);
		--consumed;
	}

	@Override
	public void unread(byte[] b) throws IOException {
		super.unread(b);
		consumed -= b.length;
	}

	@Override
	public void unread(byte[] b, int off, int len) throws IOException {
		super.unread(b, off, len);
		consumed -= len;
	}

    /**
     * Read a single line into a string.
     * @return single string line
     * @throws IOException io exception while reading line
     */
    public String readLine() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(128);
        int b;
        while (true) {
            b = this.read();
            if (b == -1) {
                return null;    //Unexpected EOF
            }
            if (b == '\n'){
                break;
            }
            if (b != '\r') {
                bos.write(b);
            }
        }
        return bos.toString("US-ASCII");
    }

}
