package org.jwat.warc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;

@RunWith(Parameterized.class)
public class TestContentTypeRecommended {

    private int expected_records;
    private int expected_recommended;
    private String warcFile;

    @Parameters
    public static Collection<Object[]> configs() {
        return Arrays.asList(new Object[][] {
                {1, 1, "test-contenttype-warcinfo-recommended.warc"},
                {7, 1, "test-contenttype-recommended.warc"},
                {1, 0, "test-contenttype-continuation.warc"}
        });
    }

    public TestContentTypeRecommended(int records, int recommended, String warcFile) {
        this.expected_records = records;
        this.expected_recommended = recommended;
        this.warcFile = warcFile;
    }

    @Test
    public void test() {
        boolean bDebugOutput = System.getProperty("jwat.debug.output") != null;

        InputStream in;

        int records = 0;
        int errors = 0;

        try {
            in = this.getClass().getClassLoader().getResourceAsStream(warcFile);

            WarcReader reader = WarcReaderFactory.getReader(in);
            WarcRecord record;

            while ((record = reader.getNextRecord()) != null) {
                if (bDebugOutput) {
                    RecordDebugBase.printRecord(record);
                    RecordDebugBase.printRecordErrors(record);
                }

                record.close();

                ++records;

                errors = 0;
                if (record.hasErrors()) {
                    errors = record.getValidationErrors().size();
                }

                Assert.assertEquals(expected_recommended, errors);
            }

            reader.close();
            in.close();

            if (bDebugOutput) {
                RecordDebugBase.printStatus(records, errors);
            }
        }
        catch (FileNotFoundException e) {
            Assert.fail("Input file missing");
        }
        catch (IOException e) {
            Assert.fail("Unexpected io exception");
        }

        Assert.assertEquals(expected_records, records);
    }

}