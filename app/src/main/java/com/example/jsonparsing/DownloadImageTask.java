package com.example.jsonparsing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {

    ImageView imageView;
    public DownloadImageTask(ImageView imageView)
    {
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(strings[0]);
            InputStream in = url.openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }
        catch (IOException exception)
        {
            Log.d("HttpError",exception.getMessage());
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
