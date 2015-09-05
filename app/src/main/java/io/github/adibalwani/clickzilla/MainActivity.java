package io.github.adibalwani.clickzilla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    int clickCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_logout) {
            ParseUser.logOut();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonClicked(View v) {
        clickCounter++;
        ParseUser.getCurrentUser().put("clickCount",clickCounter);
        ParseUser.getCurrentUser().saveInBackground();
        Toast.makeText(getApplicationContext(), "The Button has been clicked " + clickCounter + " times", Toast.LENGTH_SHORT).show();
    }

    public void launchDecrementActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), DecrementActivity.class);
        intent.putExtra("counterValue", clickCounter);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                this.clickCounter = data.getIntExtra("counterValue",0);
            }
        }
    }
}
