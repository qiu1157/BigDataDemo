package com.jd.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qiuxiangu on 2016/6/1.
 */
public class DiffFile {


    public Set<String> readFileLine(String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            Set<String> lineSet = new HashSet<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                lineSet.add(line);
            }
            return lineSet;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void readFileLine(String path, String line) {
        boolean b = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            Set<String> lineSet = new HashSet<String>();
            String aline = null;
            while ((aline = br.readLine()) != null) {
                if (line.equals(aline)) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void diffLine(Set<String> aSet, Set<String> bSet) {
        for (String aline : aSet) {
            if (!bSet.contains(aline)) {
                System.out.println(aline);
            } else {

            }
        }
    }

    public static void main(String[] args) throws IOException {
        DiffFile diffFile = new DiffFile();
        Set<String> aSet = diffFile.readFileLine("e:\\order_uuid.txt");
        for (String line : aSet) {
            diffFile.readFileLine("e:\\keys.txt", line);
        }

    }
}
