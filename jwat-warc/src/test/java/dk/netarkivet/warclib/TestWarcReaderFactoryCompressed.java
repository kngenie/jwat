package dk.netarkivet.warclib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dk.netarkivet.common.RandomAccessFileInputStream;

@RunWith(Parameterized.class)
public class TestWarcReaderFactoryCompressed {

	private int expected_records;
	private String warcFile;

	@Parameters
	public static Collection<Object[]> configs() {
		return Arrays.asList(new Object[][] {
				{822, "IAH-20080430204825-00000-blackbook.warc.gz"}
		});
	}

	public TestWarcReaderFactoryCompressed(int records, String warcFile) {
		this.expected_records = records;
		this.warcFile = warcFile;
	}

	@Test
	public void test() {
		boolean bDebugOutput = System.getProperty("jwat.debug.output") != null;

        URL url;
        File file;
        RandomAccessFile ram;
		InputStream in;

		WarcReader reader;
		WarcRecord record;

        int records = 0;
        int errors = 0;

		try {
			List<WarcEntry> entries = indexWarcFile();
			WarcEntry entry;

			/*
			 * getReaderUncompressed() / nextRecordFrom(in).
			 */

	        records = 0;
	        errors = 0;

	        url = this.getClass().getClassLoader().getResource(warcFile);
	        file = new File(url.getFile());
	        ram = new RandomAccessFile(file, "r");
	        in = new RandomAccessFileInputStream(ram);

			reader = WarcReaderFactory.getReaderCompressed();

			for (int i=0; i<entries.size(); ++i) {
				entry = entries.get(i);

				ram.seek(entry.offset);

				if ((record = reader.getNextRecordFrom(in)) != null) {
					if (bDebugOutput) {
						RecordDebugBase.printRecord(record);
						RecordDebugBase.printRecordErrors(record);
					}

					record.close();

					++records;

					if (record.hasErrors()) {
						errors += record.getValidationErrors().size();
					}

					if (record.warcRecordIdUri.compareTo(entry.recordId) != 0) {
						Assert.fail("Wrong record");
					}
				}
				else {
					Assert.fail("Location incorrect");
				}
			}

			reader.close();
			in.close();
			ram.close();

			if (bDebugOutput) {
				RecordDebugBase.printStatus(records, errors);
			}

	        Assert.assertEquals(expected_records, records);
	        Assert.assertEquals(0, errors);

			/*
			 * getReaderUncompressed(in) / nextRecordFrom(in, buffer_size).
			 */

	        records = 0;
	        errors = 0;

	        url = this.getClass().getClassLoader().getResource(warcFile);
	        file = new File(url.getFile());
	        ram = new RandomAccessFile(file, "r");
	        in = new RandomAccessFileInputStream(ram);

			reader = WarcReaderFactory.getReaderCompressed(in);

			for (int i=0; i<entries.size(); ++i) {
				entry = entries.get(i);

				ram.seek(entry.offset);

				if ((record = reader.getNextRecordFrom(in, 8192)) != null) {
					if (bDebugOutput) {
						RecordDebugBase.printRecord(record);
						RecordDebugBase.printRecordErrors(record);
					}

					record.close();

					++records;

					if (record.hasErrors()) {
						errors += record.getValidationErrors().size();
					}

					if (record.warcRecordIdUri.compareTo(entry.recordId) != 0) {
						Assert.fail("Wrong record");
					}
				}
				else {
					Assert.fail("Location incorrect");
				}
			}

			reader.close();
			in.close();

			if (bDebugOutput) {
				RecordDebugBase.printStatus(records, errors);
			}

	        Assert.assertEquals(expected_records, records);
	        Assert.assertEquals(0, errors);

	        /*
			 * getReaderUncompressed(in, buffer_size) / nextRecordFrom(in).
			 */

	        records = 0;
	        errors = 0;

	        url = this.getClass().getClassLoader().getResource(warcFile);
	        file = new File(url.getFile());
	        ram = new RandomAccessFile(file, "r");
	        in = new RandomAccessFileInputStream(ram);

			reader = WarcReaderFactory.getReaderCompressed(in, 8192);

			for (int i=0; i<entries.size(); ++i) {
				entry = entries.get(i);

				ram.seek(entry.offset);

				if ((record = reader.getNextRecordFrom(in)) != null) {
					if (bDebugOutput) {
						RecordDebugBase.printRecord(record);
						RecordDebugBase.printRecordErrors(record);
					}

					record.close();

					++records;

					if (record.hasErrors()) {
						errors += record.getValidationErrors().size();
					}

					if (record.warcRecordIdUri.compareTo(entry.recordId) != 0) {
						Assert.fail("Wrong record");
					}
				}
				else {
					Assert.fail("Location incorrect");
				}
			}

			reader.close();
			in.close();

			if (bDebugOutput) {
				RecordDebugBase.printStatus(records, errors);
			}

	        Assert.assertEquals(expected_records, records);
	        Assert.assertEquals(0, errors);
		}
		catch (IOException e) {
			Assert.fail("Unexpected io exception");
		}
	}

	class WarcEntry {
		URI recordId;
		long offset;
	}

	public List<WarcEntry> indexWarcFile() {
		boolean bDebugOutput = System.getProperty("jwat.debug.output") != null;

		List<WarcEntry> warcEntries = new ArrayList<WarcEntry>();
		WarcEntry warcEntry;

		int records = 0;
        int errors = 0;

        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(warcFile);

    		WarcReader reader = WarcReaderFactory.getReader(in);
    		Iterator<WarcRecord> recordIterator = reader.iterator();
    		WarcRecord record;

    		while (recordIterator.hasNext()) {
    			record = recordIterator.next();
    			++records;

				if (record.warcRecordIdUri == null) {
    				Assert.fail("Invalid warc-record-id");
    			}

    			warcEntry = new WarcEntry();
    			warcEntry.recordId = record.warcRecordIdUri;
    			warcEntry.offset = record.offset;
    			warcEntries.add(warcEntry);

				record.close();

				if (bDebugOutput) {
	    			System.out.println("0x" + Long.toString(warcEntry.offset, 16) + "(" + warcEntry.offset + ") - " + warcEntry.recordId);
				}

				if (record.hasErrors()) {
    				errors += record.getValidationErrors().size();
    			}
    		}

    		reader.close();
    		in.close();
        }
        catch (IOException e) {
			Assert.fail("Unexpected io exception");
        }

        Assert.assertEquals(expected_records, records);
        Assert.assertEquals(0, errors);

        return warcEntries;
	}

}
