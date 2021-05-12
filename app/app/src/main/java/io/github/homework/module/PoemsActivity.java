package io.github.homework.module;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.github.homework.R;
import io.github.homework.constant.Constant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PoemsActivity extends AppCompatActivity {
    private TextView textView;
    private String poems;
    private OkHttpClient client;
    private Request request = new Request.Builder()
            .url(Constant.MY_API)
            .build();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                textView.setText(poems);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poems);


        client = new OkHttpClient();
        textView = findViewById(R.id.tv_poems);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    Log.d("==>", result);
                    JSONObject jsonObject = new JSONObject(result);
                    poems = jsonObject.getString("content");
                    Log.d("==>", "content = " + poems);

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
