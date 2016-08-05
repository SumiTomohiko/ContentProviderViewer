package neko_daisuki.java_conf.gr.jp.contentprovierviewer;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri queryUri = MediaStore.Files.getContentUri("external");
        String[] projection = new String[]{
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.TITLE
        };
        CursorLoader cursorLoader = new CursorLoader(
                this,
                queryUri,
                projection,
                null,
                null, // Selection args (none).
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        );

        Cursor cursor = cursorLoader.loadInBackground();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String data = cursor.getString(1);
            String mediaType = cursor.getString(3);
            String mimeType = cursor.getString(4);
            String title = cursor.getString(5);
            Log.d("test", String.format("id=%d, data=%s, mediaType=%s, mimeType=%s, title=%s", id, data, mediaType, mimeType, title));
        }
    }
}