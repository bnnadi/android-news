package com.bisikennadi.androidNews.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BNnadi on 12/2/2015.
 * com.bisikennadi.androidNews.utils
 */
public class DownloadUtils {
    private static final String DOWNLOAD = "";
    public static void downloadFile(String fileName) {
        URL urlApk;
        try {
            urlApk = new URL(DOWNLOAD+fileName);
            HttpURLConnection c = (HttpURLConnection) urlApk.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory() + "/download/";
            File folder = new File(PATH);
            boolean success = true;
            if(!folder.exists())
                success = folder.mkdirs();

            if(success) {
                File outputFile = new File(folder, fileName);
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }
                fos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
