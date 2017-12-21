package a.talenting.com.talenting.util;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import a.talenting.com.talenting.R;

/**
 * Created by daeho on 2017. 12. 14..
 */

public class TempUtil {
    public static File createTempImage() {
        // 임시파일명 생성
        String tempFileName = "Temp_" + System.currentTimeMillis();

        // 임시파일 저장용 디렉토리 생성
        File tempDir = new File(Environment.getExternalStorageDirectory() + File.separator + R.xml.file_path + File.separator);

        // 생성체크
        if(!tempDir.exists()) tempDir.mkdirs();

        //실제 임시파일을 생성
        File tempFile = null;
        try {
            tempFile = File.createTempFile(tempFileName, ".jpg", tempDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile;
    }

    public static File createTempImage(ContentResolver contentResolver, Uri imageUri) {
        File file = createTempImage();

        final int chunkSize = 1024;
        byte[] imageData = new byte[chunkSize];


        try {
            InputStream in = contentResolver.openInputStream(imageUri);
            OutputStream out = new FileOutputStream(file);

            int bytesRead;
            while ((bytesRead = in.read(imageData)) > 0) {
                out.write(Arrays.copyOfRange(imageData, 0, Math.max(0, bytesRead)));
            }

            in.close();
            out.close();

            return file;
        }
        catch (Exception e) {
            Log.e("Something went wrong.", e.getMessage());
        }

        return file;
    }

}
