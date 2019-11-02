package com.example.billbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.litepal.LitePal;

public class MainActivity3 extends AppCompatActivity {
    private EditText editdesc=null;
    private EditText editcount=null;
    private EditText editdate=null;
    private String countType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Intent intent=getIntent();
        final BillBook billBook=(BillBook) getIntent().getSerializableExtra("billbook");
        //init();
        editdesc = (EditText) findViewById(R.id.editdesc);
        editcount= (EditText) findViewById(R.id.editcount);
        editdate = (EditText) findViewById(R.id.editdate);
        editdesc.setText(billBook.getDescribe());
        editcount.setText(String.valueOf(billBook.getMoney()));
        editdate.setText(billBook.getDate());
        final Button btnSub = (Button) findViewById(R.id.submit);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id. radioOut :
                        countType = "支出" ;
                        break;
                    case R.id. radioIn :
                        countType = "收入" ;
                        break;
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BillBook bill=new BillBook();
                LitePal.deleteAll(BillBook.class,"date=?",billBook.getDate());
                billBook.setDate(editdate.getText().toString());
                billBook.setMoney(Double. parseDouble(editcount.getText().toString()));
                billBook.setDescribe(editdesc.getText().toString());
                billBook.setType(countType+"");
                billBook.save();
                Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        //String type=intent.getStringExtra("type");
        //String money=intent.getStringExtra("money");
        //String date=intent.getStringExtra("date");
        //String describe=intent.getStringExtra("describe");

        //((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
    }
}
