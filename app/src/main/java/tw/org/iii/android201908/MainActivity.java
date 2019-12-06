package tw.org.iii.android201908;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private File sdroot, approot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    9487);
        }else{
            init();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
        }else {
            finish();
        }
    }

    private void init(){
        sdroot = Environment.getExternalStorageDirectory();
        approot = new File(sdroot, "Android/data/" + getPackageName());
        if (!approot.exists()){
            approot.mkdir();
        }
    }

    public void test1(View view){
        File file1 = new File(sdroot, "bradiii.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello,Brad\n".getBytes());
            fout.flush();
            fout.close();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }

    public void test2(View view) {
        File file1 = new File(approot, "bradiii.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello,Brad\n".getBytes());
            fout.flush();
            fout.close();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }
    public void test3(View view) {
        File file1 = new File(sdroot, "Android/data/tw.org.iii.appps.brad07");
        File file2 = new File(sdroot, "bradiii.txt");
        if (file1.delete()){
            Log.v("brad","ok1");
        }
        if (file2.delete()){
            Log.v("brad", "OK2");
        }
    }
}
