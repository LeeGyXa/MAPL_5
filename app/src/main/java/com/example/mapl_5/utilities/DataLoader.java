package com.example.mapl_5.utilities;

import android.os.AsyncTask;

import java.io.IOException;

public class DataLoader extends AsyncTask<String, Void, String>  {

    protected String doInBackground(String... params) {
        try {
            return DataReader.getValuesFromApi(params[0]);
        } catch (IOException ex) {
            return String.format("Some error occured => %s", ex.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}