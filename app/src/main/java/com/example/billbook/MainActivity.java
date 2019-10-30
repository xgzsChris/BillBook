package com.example.billbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.litepal.LitePal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String countType ;
    private TextView tvInfo;
    private Log log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // LitePal.initialize(this);
        //SQLiteDatabase db = LitePal.getDatabase();
        LitePal.getDatabase();
        final EditText editdesc = (EditText) findViewById(R.id.editdesc);
        final EditText editcount= (EditText) findViewById(R.id.editcount);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        Button btnAdd = (Button) findViewById(R.id.record);
        Button btnSel=(Button) findViewById(R.id.situation);
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillBook bill = new BillBook();
                long time = System. currentTimeMillis ();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = format.format(new Date(time));
                bill.setDate(str);
                bill.setMoney(Double. parseDouble (editcount.getText().toString()));
                bill.setDescribe(editdesc.getText().toString());
                bill.setType(countType + "");
                bill.save();
                resetInfo();
            }
        });
        btnSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    public void resetInfo(){
        Double out=getResult("支出");
        Double in=getResult("收入");
        Double all=in-out;
        tvInfo.setText("总计支出："+out+"  总计收入："+in+" \n 结余：" +all+"。");
        // List<BillBook> book= LitePal.where("type==").find(BillBook.class);
    }
    public double getResult(String type)
    {
        Double result=0.0;
        List<BillBook> billbooks=LitePal.findAll(BillBook.class);
        //BillBook billBook=new BillBook();
        for(int i=0;i<billbooks.size();i++) {
            if (billbooks.get(i).getType().equals(type))
                result += billbooks.get(i).getMoney();
            System.out.println(result);
        }
        //Cursor c = LitePal.findBySQL("select id,count,type,date,describe from "+ type ,null);
        //for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            //if (c.getInt(2) == type)
                //result += c.getFloat(1);
       // }
        //c.close();
        //System.out.println("结果"+result);
        return result;
    }
}
