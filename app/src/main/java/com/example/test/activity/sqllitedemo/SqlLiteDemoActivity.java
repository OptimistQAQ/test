package com.example.test.activity.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.bean.People;
import com.example.test.utils.sqllitedemo.DBAdapter;

/**
 * @author 65667
 */
public class SqlLiteDemoActivity extends Activity {
    /** Called when the activity is first created. */
    private DBAdapter dbAdepter ;

    private EditText nameText;
    private EditText ageText;
    private EditText heightText;
    private EditText idEntry;

    private TextView labelView;
    private TextView displayView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_demo);

        nameText = (EditText)findViewById(R.id.name);
        ageText = (EditText)findViewById(R.id.age);
        heightText = (EditText)findViewById(R.id.height);
        idEntry = (EditText)findViewById(R.id.id_entry);

        labelView = (TextView)findViewById(R.id.label);
        displayView = (TextView)findViewById(R.id.display);



        Button addButton = (Button)findViewById(R.id.add);
        Button queryAllButton = (Button)findViewById(R.id.query_all);
        Button clearButton = (Button)findViewById(R.id.clear);
        Button deleteAllButton = (Button)findViewById(R.id.delete_all);

        Button queryButton = (Button)findViewById(R.id.query);
        Button deleteButton = (Button)findViewById(R.id.delete);
        Button updateButton = (Button)findViewById(R.id.update);


        addButton.setOnClickListener(addButtonListener);
        queryAllButton.setOnClickListener(queryAllButtonListener);
        clearButton.setOnClickListener(clearButtonListener);
        deleteAllButton.setOnClickListener(deleteAllButtonListener);

        queryButton.setOnClickListener(queryButtonListener);
        deleteButton.setOnClickListener(deleteButtonListener);
        updateButton.setOnClickListener(updateButtonListener);

        dbAdepter = new DBAdapter(this);
        dbAdepter.open();
    }

    View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            People people = new People();
            people.Name = nameText.getText().toString();
            people.Age = Integer.parseInt(ageText.getText().toString());
            people.Height = Float.parseFloat(heightText.getText().toString());
            long colunm = dbAdepter.insert(people);
            if (colunm == -1 ){
                labelView.setText("添加过程错误！");
            } else {
                labelView.setText("成功添加数据，ID："+String.valueOf(colunm));
                nameText.setText("");
                ageText.setText("");
                heightText.setText("");

            }
        }
    };

    View.OnClickListener queryAllButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            People[] peoples = dbAdepter.queryAllData();
            if (peoples == null){
                labelView.setText("数据库中没有数据");
                displayView.setText("");
                return;
            }
            labelView.setText("数据库：");
            String msg = "";
            for (int i = 0 ; i<peoples.length; i++){
                msg += peoples[i].toString()+"\n";
            }
            displayView.setText(msg);
        }
    };

    View.OnClickListener clearButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            displayView.setText("");
        }
    };

    View.OnClickListener deleteAllButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbAdepter.deleteAllData();
            String msg = "数据全部删除";
            labelView.setText(msg);
            displayView.setText("");
        }
    };

    View.OnClickListener queryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int id = Integer.parseInt(idEntry.getText().toString());
                People[] peoples = dbAdepter.queryOneData(id);

                if (peoples == null){
                    labelView.setText("数据库中没有ID为"+String.valueOf(id)+"的数据");
                    return;
                }
                labelView.setText("数据库：");
                displayView.setText(peoples[0].toString());
            } catch (Exception e) {
                Toast.makeText(SqlLiteDemoActivity.this, "ID不能为空", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    };

    View.OnClickListener deleteButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                long id = Integer.parseInt(idEntry.getText().toString());
                long result = dbAdepter.deleteOneData(id);
                String msg = "删除ID为"+idEntry.getText().toString()+"的数据" + (result>0?"成功":"失败");
                labelView.setText(msg);
            } catch (Exception e) {
                Toast.makeText(SqlLiteDemoActivity.this, "ID不能为空", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener updateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                People people = new People();
                people.Name = nameText.getText().toString();
                people.Age = Integer.parseInt(ageText.getText().toString().trim());
                people.Height = Float.parseFloat(heightText.getText().toString().trim());
                Log.e("id", idEntry.getText().toString().trim());
                long id = Integer.parseInt(idEntry.getText().toString().trim());
                long count = dbAdepter.updateOneData(id, people);
                if (count == -1 ){
                    labelView.setText("更新错误！");
                } else {
                    labelView.setText("更新成功，更新数据"+String.valueOf(count)+"条");

                }
            } catch (Exception e) {
                Toast.makeText(SqlLiteDemoActivity.this, "ID不能为空", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    };

}