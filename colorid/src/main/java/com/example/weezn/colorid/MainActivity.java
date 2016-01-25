package com.example.weezn.colorid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements PreViewSurface.OnColorlisnter {

    private  CricleCrossView cricleCrossView;
    private View preview;
    private PreViewSurface preViewSurface;
    private PreViewSurface.OnColorlisnter colorlisnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        cricleCrossView=(CricleCrossView)findViewById(R.id.CricleCross);
//        cricleCrossView.setColor(Color.RED);
//        cricleCrossView.refresh();
//
//        preview=(PreViewSurface)findViewById(R.id.preview);

        cricleCrossView=(CricleCrossView)findViewById(R.id.cross_view);
        preViewSurface=(PreViewSurface)findViewById(R.id.preview);

        preViewSurface.setOnColorListener(this);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onColor(int color) {
        cricleCrossView.setColor(color);
        cricleCrossView.refresh();
    }
}
