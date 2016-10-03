package barcode.postoffice.com.postoffice;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview ;
    private String HOME_URL = "http://54.69.174.4/html/index.php";
    private String ABOUT_URL = "http://54.69.174.4/html/about.php";
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        mWebview .loadUrl(HOME_URL);
        setContentView(mWebview );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                showAboutPage();
            case R.id.createNew:
                showHome();
        }
        return false;
    }

    private void showHome() {
        mWebview .loadUrl(HOME_URL);
        setContentView(mWebview );
    }

    private void showAboutPage() {
        mWebview .loadUrl(ABOUT_URL);
        setContentView(mWebview );
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        showHome();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
