package edu.uco.hsung.m07_viewpost;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Bitmap bitmap;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        final Button button = (Button) findViewById(R.id.loadButton);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
            }
        });

        final Button buttonClear = (Button) findViewById(R.id.clearButton);
        buttonClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Other Button is pressed!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadIcon() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // simulate 5sec delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                bitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.cs);

                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        t.start();
    }
}