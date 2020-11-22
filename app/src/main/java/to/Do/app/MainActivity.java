package to.Do.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener {

    private static final String TAG = "MainActivity";
    private Button mOpenDialog;
    private TextView mInputDisplay;
    private String mInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenDialog = findViewById(R.id.openDialog);
        mInputDisplay = findViewById(R.id.inputDialog);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: opening dialog.");
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getSupportFragmentManager(),"fsag");
            }
        });

    }

    @Override
    public void sendInput(String input) {

        Log.d(TAG,"sendInput: got the input: " + input);
        mInput = input;
        setInputToTextView();
    }

    private void setInputToTextView() {
        mInputDisplay.setText(mInput);
    }

}