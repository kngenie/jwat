package dk.netarkivet.warclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtf8 {

	private int expected_records;
	private int expected_errors;
	private String warcFile;

	@Parameters
	public static Collection<Object[]> configs() {
		return Arrays.asList(new Object[][] {
				{1, 0, "src/test/resources/test-utf8.warc"}
		});
	}

	public TestUtf8(int records, int errors, String warcFile) {
		this.expected_records = records;
		this.expected_errors = errors;
		this.warcFile = warcFile;
	}

	@Test
	public void test() {
		File file = new File(warcFile);
		InputStream in;

		int records = 0;
		int errors = 0;

		try {
			in = new FileInputStream(file);

			WarcParser parser = new WarcParser(in);
			WarcRecord record;

			while ((record = parser.nextRecord()) != null) {
				TestWarc.printRecord(record);
				TestWarc.printRecordErrors(record);

				errors = 0;
				if (record.hasErrors()) {
					errors = record.getValidationErrors().size();
				}

				if (record.warcFilename != null) {
					saveUtf8(record.warcFilename);
				}

				++records;
			}

			System.out.println("--------------");
			System.out.println("       Records: " + records);
			System.out.println("        Errors: " + errors);
			parser.close();
			in.close();
		}
		catch (FileNotFoundException e) {
			Assert.fail("Input file missing");
		}
		catch (IOException e) {
			Assert.fail("Unexpected io exception");
		}

		Assert.assertEquals(expected_records, records);
		Assert.assertEquals(expected_errors, errors);
	}

	public static void saveUtf8(String str) {
		RandomAccessFile ram = null;
		try {
			ram = new RandomAccessFile("utf8.txt", "rw");
			ram.write(str.getBytes("UTF-8"));
			ram.close();
			ram = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (ram != null) {
				try {
					ram.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}