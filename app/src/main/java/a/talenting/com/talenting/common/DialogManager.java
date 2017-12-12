package a.talenting.com.talenting.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.talenting.com.talenting.BuildConfig;
import a.talenting.com.talenting.R;
import a.talenting.com.talenting.custom.domain.detailItem.TextContentItem;
import a.talenting.com.talenting.custom.domain.detailItem.TitleAndValueItem;
import a.talenting.com.talenting.util.PermissionUtil;

import static a.talenting.com.talenting.common.Constants.CAMERA_PERMISSION_REQ;
import static a.talenting.com.talenting.common.Constants.CAMERA_REQ;
import static a.talenting.com.talenting.common.Constants.GALLERY_REQ;
import static android.app.Activity.RESULT_OK;

/**
 * Created by daeho on 2017. 12. 11..
 */

public class DialogManager {
    public static void showTextDialog(Context context, TitleAndValueItem item, IDialogStringEvent event){
        showTextDialog(context, item.title, item.value, event);
    }
    public static void showTextDialog(Context context, String title, String value, IDialogStringEvent event){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input_text, null, false);
        EditText editText = v.findViewById(R.id.editText);
        editText.setHint(R.string.hosting_input);
        editText.setText(value);

        new MaterialDialog.Builder(context)
                .title(title)
                .customView(v, true)
                .positiveText("Save")
                .onAny((@NonNull MaterialDialog dialog, @NonNull DialogAction which) -> {
                    event.callback(editText.getText().toString());
                })
                .show();
    }

    public static void showMultiLineTextDialog(Context context, String title, TextContentItem item, IDialogStringEvent event){
        showMultiLineTextDialog(context, title, item.content, event);
    }
    public static void showMultiLineTextDialog(Context context, String title, String value, IDialogStringEvent event){
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_input_multi_line_text, null, false);
        EditText editText = v.findViewById(R.id.editText);
        editText.setHint(R.string.hosting_input);
        editText.setText(value);

        new MaterialDialog.Builder(context)
                .title(title)
                .customView(v, true)
                .positiveText("Save")
                .onAny((@NonNull MaterialDialog dialog, @NonNull DialogAction which) -> {
                    event.callback(editText.getText().toString());
                })
                .show();
    }

    public static void showTypeListDialog(Context context, String title, Map<Integer, String> data, IDialogTypeEvent event){
        int[] ids = new int[data.size()];
        List<String> items = new ArrayList<>();
        int index = 0;
        for(int key : data.keySet()){
            ids[index++] = key;
            items.add(data.get(key));
        }

        new MaterialDialog.Builder(context)
                .title(title)
                .items(items)
                .itemsIds(ids)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        event.callback(view.getId() + "", text.toString());
                    }
                })
                .show();
    }

    public static void showCameraDialog(Activity activity, ActivityResultManager resultManager, IDialogStringEvent event){
        View v = LayoutInflater.from(activity).inflate(R.layout.dialog_camera, null, false);

        //region camera event
        v.findViewById(R.id.btnCamera).setOnClickListener(view -> {
            String[] Permission = new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };

            PermissionUtil pUtil = new PermissionUtil(CAMERA_PERMISSION_REQ, Permission);
            pUtil.check(activity, new PermissionUtil.IPermissionGrant() {
                @Override
                public void run() {
                    ActivityResultItem resultItem = new ActivityResultItem(CAMERA_REQ, (int resultCode, Intent data) -> {
                        Uri uri = null;
                        if(resultCode == RESULT_OK){
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) uri = fileUri;
                            else uri = data.getData();
                        }

                        if(uri != null) event.callback(uri.toString());
                    });
                    resultManager.addItem(resultItem);

                    camera(activity);
                }

                @Override
                public void fail() {

                }
            });
        });
        //endregion
        //region gallery event
        v.findViewById(R.id.btnGallery).setOnClickListener(view -> {
            String[] Permission = new String[] { Manifest.permission.READ_EXTERNAL_STORAGE };

            PermissionUtil pUtil = new PermissionUtil(CAMERA_PERMISSION_REQ, Permission);
            pUtil.check(activity, new PermissionUtil.IPermissionGrant() {
                @Override
                public void run() {
                    ActivityResultItem resultItem = new ActivityResultItem(GALLERY_REQ, (int resultCode, Intent data) -> {
                        Uri uri = null;
                        if(resultCode == RESULT_OK) uri = data.getData();

                        if(uri != null) event.callback(uri.toString());
                    });
                    resultManager.addItem(resultItem);

                    gallery(activity);
                }

                @Override
                public void fail() {

                }
            });
        });
        //endregion

        new MaterialDialog.Builder(activity).customView(v, true).show();
    }
    //region camera / gallery functions
    private static Uri fileUri = null;
    private static void camera(Activity activity){
        // 1. Intent 만들기
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 2. 호환성 처리 버전체크 - 롤리팝 이상
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // 3. 실제 파일이 저장되는 파일 객체 < 빈 파일을 생성해 둔다
            File photoFile = null;

            // 3.1 실제 파일이 저장되는 곳에 권한이 부여되어 있어야 한다
            //     롤리팝 부터는 File Provider를 선언해 줘야만한다 > Manifest에
            try {
                photoFile = createFile();

                // 갤러리에서 나오지 않을때
                refreshMedia(activity, photoFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", photoFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            activity.startActivityForResult(intent, CAMERA_REQ);
        }
        else activity.startActivityForResult(intent, CAMERA_REQ);
    }
    private static void gallery(Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, GALLERY_REQ);
    }
    private static void refreshMedia(Activity activity, File file){
        MediaScannerConnection.scanFile(activity, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }
    private static File createFile() throws IOException {
        // 임시파일명 생성
        String tempFileName = "Temp_" + System.currentTimeMillis();

        // 임시파일 저장용 디렉토리 생성
        File tempDir = new File(Environment.getExternalStorageDirectory() + File.separator + "tempPicture" + File.separator);

        // 생성체크
        if(!tempDir.exists()) tempDir.mkdirs();

        //실제 임시파일을 생성
        File tempFile = File.createTempFile(tempFileName, ".jpg", tempDir);

        return tempFile;
    }
    //endregion
}
