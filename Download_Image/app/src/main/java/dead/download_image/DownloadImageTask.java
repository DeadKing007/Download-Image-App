package dead.download_image;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;
    ProgressDialog progressDialog;
    DownloadImageTask(Context context, ImageView imageView){
        this.imageView=imageView;
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Downloading");
        progressDialog.setMessage("Downloading , please wait");
        progressDialog.setIndeterminate(true);

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return DownloadingImage(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (progressDialog.isShowing())
        progressDialog.dismiss();
        imageView.setImageBitmap(bitmap);
    }
    private Bitmap DownloadingImage(String image_url){

        try {
            URL Image_URL=new URL(image_url);

            HttpURLConnection connection= (HttpURLConnection) Image_URL.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);//millisecond;
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.connect();

            int responsecode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK==responsecode){

                InputStream inputStream=connection.getInputStream();

                if (null!=inputStream) {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    return bitmap;
                }

            }

        }catch (MalformedURLException exception){
            Log.e(TAG,"Error "+exception.getLocalizedMessage());
        }catch (IOException e){
            Log.e(TAG,"Error "+e.getLocalizedMessage());
        }

        return null;
    }
}
