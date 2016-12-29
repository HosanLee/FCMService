package org.androidtown.listviewsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemData> itemDatas = null;

    Button btnSubmit;
    EditText editTitle, editDescription;
    RadioGroup radioGroup, diag_radioGroup;
    ListView listView;
    TextView diag_txtTitle, diag_txtDescription;


    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initModel();
        aboutView();
        makeList();
    }

    private void initModel() {
        itemDatas = new ArrayList<ItemData>();
    }

    private void aboutView() {

        initView();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(editTitle.getText())
                        || TextUtils.isEmpty(editDescription.getText())) {
                    Toast.makeText(getApplicationContext(), "데이터를 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else {

                    ItemData itemData = new ItemData();
                    itemData.Title = editTitle.getText().toString();
                    itemData.Description = editDescription.getText().toString();


                    itemDatas.add(0, itemData);
                    adapter.notifyDataSetChanged();

                    editTitle.setText("");
                    editDescription.setText("");

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final ItemData itemData_temp = (ItemData) adapter.getItem(position);
                LayoutInflater layoutInflater = (LayoutInflater)getLayoutInflater();
                View dialogLayout = layoutInflater.inflate(R.layout.dialog_layout, null);
                diag_txtTitle = (TextView)dialogLayout.findViewById(R.id.diag_txtTitle);
                diag_txtDescription =(TextView)dialogLayout.findViewById(R.id.diag_txtDescription);
                diag_txtTitle.setText(itemData_temp.Title);
                diag_txtDescription.setText(itemData_temp.Description);

                diag_txtDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri uri = Uri.parse("http://" + (String) diag_txtDescription.getText())     ;
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("상세보기");
                builder.setView(dialogLayout);



                AlertDialog alertDialog = builder.create();
                alertDialog.show();






            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("삭제");
                alert.setMessage("정말로 삭제하시겠습까?");

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemDatas.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });


                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();



                return true;
            }
        });

    }

    private void makeList() {
        adapter = new CustomAdapter(itemDatas, getApplicationContext());
        listView.setAdapter(adapter);
    }


    private void initView() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editDescription = (EditText) findViewById(R.id.editDescription);
        listView = (ListView) findViewById(R.id.listView);
    }
}
