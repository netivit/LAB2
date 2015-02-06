package com.example.admin.lab2;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class WeaTherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wea_ther);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button but = (Button)findViewById(R.id.Find);

        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String api_call = "http://api.openweathermap.org/data/2.5/weather?q=";
                EditText qText = (EditText)findViewById(R.id.editText);
                String qMessage = qText.getText().toString();
                try {
                    URL url = new URL(api_call+qMessage);
                    Scanner sc = new Scanner(url.openStream());
                    StringBuffer buf = new StringBuffer();
                    while(sc.hasNext()){
                        buf.append(sc.next());
                    }
                    JSONObject jsonObject = new JSONObject(buf.toString());
                    JSONObject sysObj = jsonObject.getJSONObject("sys");
                    String city = sysObj.getString("country");

                    TextView cityText = (TextView)findViewById(R.id.CityText);
                    cityText.setText(city);

                    sysObj = jsonObject.getJSONObject("main");
                    String pressure = sysObj.getString("pressure");
                    TextView pressureText = (TextView)findViewById(R.id.PressureText);
                    pressureText.setText(pressure);

                    String humidity = sysObj.getString("humidity");
                    TextView humidityText = (TextView)findViewById(R.id.HumidityText);
                    humidityText.setText(humidity);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wea_ther, menu);
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
}
