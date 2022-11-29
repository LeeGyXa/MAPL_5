package com.example.mapl_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapl_5.utilities.Constants;
import com.example.mapl_5.utilities.DataLoader;
import com.example.mapl_5.utilities.DataReader;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.status = findViewById(R.id.textView);



    }

    public void onBtnGetDataClick(View view) {
        this.status.setText(R.string.loading_data);
        getDataByThread();
        Toast.makeText(this, R.string.msg_using_thread, Toast.LENGTH_LONG).show();

    }

    public void getDataByThread() {
        this.status.setText(R.string.loading_data);
        Runnable getDataAndDisplayRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    final String result = DataReader.getValuesFromApi(Constants.ECB_URL);
                    Runnable updateUIRunnable = new Runnable() {
                        @Override
                        public void run() {
                            status.setText(getString(R.string.data_loaded) + result);
                        }
                    };
                    runOnUiThread(updateUIRunnable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(getDataAndDisplayRunnable);
        thread.start();


    }
}

