package a.talenting.com.talenting.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormatUtil {
    /**
     * longTime을 지정한 format String 으로 변환
     * @param time
     * @param format ex)yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String longTimeToFormatedString(long time, String format){
        DateFormat df = new SimpleDateFormat(format);
        String str_time = df.format(time);
        return str_time;
    }

    public static Uri decodeUri(Context c, Uri uri, final int requiredSize) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;

        Bitmap result = BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
        return getImageUri(c, rotateBitmap(result, 90));
    }

    private static Bitmap rotateBitmap(Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "temp", null);
        return Uri.parse(path);
    }
}
