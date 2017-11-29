package com.example.cm.testrv.persistence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm.testrv.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by cm on 2017/10/25.
 *
 */

public class TestFileActivity extends AppCompatActivity {

    EditText mEditText;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestFileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_test_file_layout);
        initView();
    }

    private void initView() {
        mEditText = ((EditText) findViewById(R.id.my_edit_text));
        String input = loadMyData();
        if (!TextUtils.isEmpty(input)) {
            mEditText.setText(input);
            mEditText.setSelection(input.length());
            Toast.makeText(this, "Restoring Succeeded !", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData(String input) {
        FileOutputStream out = null;

        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(out);
            writer = new BufferedWriter(osw);
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveMyData(String input) {
        BufferedWriter bw = null;
        String path = getFilePath(this);
        Log.d("George", "File path :" + path);

        try {
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getFilePath(Context context) {
        return context.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "test_data.log";
    }

    private String loadMyData() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String path = getFilePath(this);
        File file = new File(path);
        Log.d("George", "load .. path :" + path);
        if (file.exists()) {
            try {

                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String input = mEditText.getText().toString();
        saveMyData(input);
    }
}
