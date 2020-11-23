package to.Do.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TaskDatabaseHelper taskHelper;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskHelper = new TaskDatabaseHelper(this);
        listView = findViewById(R.id.listView);

        azurirajUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.gumbActionBar:
                final EditText editText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Dodaj novi podsjetnik")
                        .setMessage("Što želite napraviti sljedeće?")
                        .setView(editText)
                        .setPositiveButton("Dodaj",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String zadatak = String.valueOf(editText.getText());
                                SQLiteDatabase db = taskHelper.getWritableDatabase();
                                ContentValues vrijednosti = new ContentValues();
                                vrijednosti.put(TaskContract.TaskEntry.COL_TASK_TITLE, zadatak);
                                db.insertWithOnConflict(TaskContract.TaskEntry.TABLE, null, vrijednosti, SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                azurirajUI();
                            }
                        })
                        .setNegativeButton("Poništi",null).create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void obrisiPodsjetnik(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.tekstPodsjetnika);
        String zadatak = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = taskHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE,TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",new String[]{zadatak});
        db.close();
        azurirajUI();
    }

    private void azurirajUI(){
        ArrayList<String> zadatciLista = new ArrayList<>();
        SQLiteDatabase db = taskHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID,TaskContract.TaskEntry.COL_TASK_TITLE},
                null,null,null,null,null);
        while (cursor.moveToNext()){
                int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
                zadatciLista.add(cursor.getString(idx));
        }

        if (arrayAdapter == null){
            arrayAdapter = new ArrayAdapter<>(this,R.layout.todo_task,R.id.tekstPodsjetnika,zadatciLista);
            listView.setAdapter(arrayAdapter);
        }else {
            arrayAdapter.clear();
            arrayAdapter.addAll(zadatciLista);
            arrayAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

}