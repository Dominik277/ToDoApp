package to.Do.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener {

    private static final String TAG = "MainActivity";
    private TextView uneseniTekst;
    private String mInput;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uneseniTekst = findViewById(R.id.uneseniTekst);
        floatingActionButton = findViewById(R.id.floatinActionButton);
        recyclerView = findViewById(R.id.recyclerView);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getSupportFragmentManager(),null);
            }
        });

    }

    @Override
    public void sendInput(String input) {
        mInput = input;
        setInputToTextView();
        Log.d(TAG,"sendInput: got the input: " + input);

    }

    private void setInputToTextView() {
        uneseniTekst.setText(mInput);
    }



}