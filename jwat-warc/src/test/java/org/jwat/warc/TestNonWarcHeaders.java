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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.jwat.common.HeaderLine;

@RunWith(Parameterized.class)
public class TestNonWarcHeaders {

    private int expected_records;
    private int expected_errors;
    private String warcFile;

    @Parameters
    public static Collection<Object[]> configs() {
        return Arrays.asList(new Object[][] {
                {1, 0, "test-non-warc-headers.warc"}
        });
    }

    public TestNonWarcHeaders(int records, int errors, String warcFile) {
        this.expected_records = records;
        this.expected_errors = errors;
        this.warcFile = warcFile;
    }

    @Test
    public void test_non_warc_headers() {
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

                HeaderLine header1 = record.getHeader("header1");
                HeaderLine header2 = record.getHeader("HEADER2");

                Assert.assertNotNull(header1);
                Assert.assertNotNull(header2);

                Assert.assertEquals("domination", header1.value);
                Assert.assertEquals("world", header2.value);

                List<HeaderLine> headers = record.getHeaderList();

                String[][] headerRef = {
                        {"Header1", "hello"},
                        {"hEADER2", "world"},
                        {"header1", "domination"}
                };

                Assert.assertEquals(headers.size(), headerRef.length);

                for (int i=0; i<headerRef.length; ++i) {
                    Assert.assertEquals(headerRef[i][0], headers.get(i).name);
                    Assert.assertEquals(headerRef[i][1], headers.get(i).value);
                }

                errors = 0;
                if (record.hasErrors()) {
                    errors = record.getValidationErrors().size();
                }

                ++records;
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
        Assert.assertEquals(expected_errors, errors);
    }

}
