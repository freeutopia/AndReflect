package com.utopia.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;

import com.utopia.reflecct.Reflect;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private StorageManager storageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
    }

    public void testInvok1(View view) {
        try {
            Object result = Reflect.on(StorageManager.class).method("getVolumePaths").call(storageManager);//hide方法
            if (result != null && result instanceof String[]) {
                String[] pathes = (String[]) result;
                for (String path : pathes) {
                    Log.e("test", "path:" + path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void test2(View view) {
        /*        *//** @hide *//*
        @SystemApi
        public long getAllocatableBytes(@NonNull UUID storageUuid,@RequiresPermission @AllocateFlags int flags) throws IOException;*/
        try {
            Object result = Reflect
                    .on(StorageManager.class)
                    .method("getAllocatableBytes", UUID.class, int.class)
                    .call(storageManager,StorageManager.UUID_DEFAULT,0);

            if (result instanceof Long) {
                Log.e("test", "result:" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}