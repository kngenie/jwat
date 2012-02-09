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

import java.util.Collection;
import java.util.Iterator;

import org.jwat.warc.WarcRecord;
import org.jwat.warc.WarcValidationError;

public class RecordDebugBase {

    private RecordDebugBase() {
    }

    public static void printRecord(WarcRecord record) {
        System.out.println("--------------");
        System.out.println("       Version: " + record.bMagicIdentified + " " + record.bVersionParsed + " " + record.major + "." + record.minor);
        System.out.println("       TypeIdx: " + record.warcTypeIdx);
        System.out.println("          Type: " + record.warcTypeStr);
        System.out.println("      Filename: " + record.warcFilename);
        System.out.println("     Record-ID: " + record.warcRecordIdUri);
        System.out.println("          Date: " + record.warcDate);
        System.out.println("Content-Length: " + record.contentLength);
        System.out.println("  Content-Type: " + record.contentType);
        System.out.println("     Truncated: " + record.warcTruncatedStr);
        System.out.println("   InetAddress: " + record.warcInetAddress);
        System.out.println("  ConcurrentTo: " + record.warcConcurrentToUriList);
        System.out.println("      RefersTo: " + record.warcRefersToUri);
        System.out.println("     TargetUri: " + record.warcTargetUriUri);
        System.out.println("   WarcInfo-Id: " + record.warcWarcInfoIdUri);
        System.out.println("   BlockDigest: " + record.warcBlockDigest);
        System.out.println(" PayloadDigest: " + record.warcPayloadDigest);
        System.out.println("IdentPloadType: " + record.warcIdentifiedPayloadType);
        System.out.println("       Profile: " + record.warcProfileStr);
        System.out.println("      Segment#: " + record.warcSegmentNumber);
        System.out.println(" SegmentOrg-Id: " + record.warcSegmentOriginIdUrl);
        System.out.println("SegmentTLength: " + record.warcSegmentTotalLength);
    }

    public static void printStatus(int records, int errors) {
        System.out.println("--------------");
        System.out.println("       Records: " + records);
        System.out.println("        Errors: " + errors);
    }

    public static void printRecordErrors(WarcRecord record) {
        if (record.hasErrors()) {
            Collection<WarcValidationError> errorCol = record.getValidationErrors();
            if (errorCol != null && errorCol.size() > 0) {
                Iterator<WarcValidationError> iter = errorCol.iterator();
                while (iter.hasNext()) {
                    WarcValidationError error = iter.next();
                    System.out.println( error.error );
                    System.out.println( error.field );
                    System.out.println( error.value );
                }
            }
        }
    }

}
