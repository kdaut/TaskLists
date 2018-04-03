package com.example.dautovic.tasklist;

import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dautovic.tasklist.database.DBhelper;
import com.example.dautovic.tasklist.ExitDialog.ExitDialog;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    DBhelper dBhelper;
    ArrayAdapter<String> myAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        dBhelper = new DBhelper(this);

        enteringToast();
        loadTask();
    }

    public void loadTask() {
        ArrayList<String> taskList = dBhelper.getList();
        if (myAdapter == null) {
            myAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.tvTxt, taskList);
            listView.setAdapter(myAdapter);
        } else {
            myAdapter.clear();
            myAdapter.addAll(taskList);
            myAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTask:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.add_task)
                        .setMessage("").setView(taskEditText).setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dBhelper.makeTask(task);
                                loadTask();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null).create();
                dialog.show();
                return true;

            case R.id.Exite:
                ExitApp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) findViewById(R.id.tvTxt);
        String task = String.valueOf(taskTextView.getText());
        dBhelper.removeTask(task);
        loadTask();

    }

    public void enteringToast() {
        for (int i = 0; i < 2; i++) {
            Toast toast = Toast.makeText(this, R.string.toast, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    public void ExitApp() {
        ExitDialog dialog = new ExitDialog();
        dialog.show(getFragmentManager(), "ExitDialog");
    }



    @Override
    protected void onResume() {
        super.onResume();

    }
}
