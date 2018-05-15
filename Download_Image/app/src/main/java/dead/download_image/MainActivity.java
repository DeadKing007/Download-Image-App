package dead.download_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText ImageUrl;
    private Button Download;
    private ImageView DownloadingImage;
    private final static String TAG=MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageUrl=findViewById(R.id.Image_URL_Edit_Text);
        Download=findViewById(R.id.Download);
        DownloadingImage=findViewById(R.id.Image_Download);
        Download.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id==R.id.Download){
            String imageUrl=ImageUrl.getText().toString();
            if (!imageUrl.isEmpty()){

                new DownloadImageTask(this,DownloadingImage).execute(imageUrl);
                // DownloadingImage(imageUrl);
            }else {
                //error Message
            }
        }

    }

}
