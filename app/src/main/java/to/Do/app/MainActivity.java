package to.Do.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener, MyRecyclerViewAdapter.ItemClickListener {

    private static final String TAG = "MainActivity";
    private String mInput;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatinActionButton);

        ArrayList<String> items = new ArrayList<>();

        items.add("First CardView Item");
        items.add("Second CardView Item");
        items.add("Third CardView Item");
        items.add("Fourth CardView Item");
        items.add("Fifth CardView Item");
        items.add("Sixth CardView Item");


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(this, items);
        recyclerViewAdapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(recyclerViewAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getSupportFragmentManager(), null);
            }
        });

    }

    @Override
    public void sendInput(String input) {
        mInput = input;
        Log.d(TAG, "sendInput: got the input: " + input);

    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked ", Toast.LENGTH_SHORT)
                .show();
    }
}