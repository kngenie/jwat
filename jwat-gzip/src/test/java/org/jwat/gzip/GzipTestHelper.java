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
package org.jwat.gzip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import org.jwat.common.Diagnosis;
import org.jwat.common.DiagnosisType;
import org.jwat.common.Diagnostics;

public class GzipTestHelper {

    public static boolean containsError(Diagnostics<Diagnosis> diagnostics, DiagnosisType type, String entity, int infos) {
        Iterator<Diagnosis> iter = diagnostics.getErrors().iterator();
        Diagnosis diagnosis;
        while (iter.hasNext()) {
            diagnosis = iter.next();
            if (diagnosis.type == type && diagnosis.entity.equals(entity) && diagnosis.information.length == infos) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsWarning(Diagnostics<Diagnosis> diagnostics, DiagnosisType type, String entity, int infos) {
        Iterator<Diagnosis> iter = diagnostics.getWarnings().iterator();
        Diagnosis diagnosis;
        while (iter.hasNext()) {
            diagnosis = iter.next();
            if (diagnosis.type == type && diagnosis.entity.equals(entity) && diagnosis.information.length == infos) {
                return true;
            }
        }
        return false;
    }

    public static void storeStream(String filename, byte[] bytes) {
        try {
            RandomAccessFile raf = new RandomAccessFile("gzip_" + filename + ".gz", "rw");
            raf.seek(0);
            raf.setLength(0);
            raf.write(bytes);
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
