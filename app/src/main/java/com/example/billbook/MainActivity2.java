package com.example.billbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private ListView listview = null;
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    private List<BillBook> billBookList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LitePal.getDatabase();
        initBillBook();
        BillBookAdapter adapter=new BillBookAdapter(MainActivity2.this,R.layout.list,billBookList);
        listview=(ListView)findViewById(R.id.list_view);
        SimpleAdapter adapter1=getAdapter();
        listview.setAdapter(adapter1);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> m = (HashMap<String, Object>) adapterView.getItemAtPosition(i);
                Integer pid=(Integer)m.get("date");
                //BillBook billBook=billBookList.get(i);
                //String id=(String)billBook.getDate();
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                //BillBook b= (BillBook) LitePal.where("date=?",id).find(BillBook.class);
                //intent.putExtra("type",billBook.getType());
                //intent.putExtra("money",String.valueOf(billBook.getMoney()));
                //intent.putExtra("date",billBook.getDate());
                //intent.putExtra("describe",billBook.getDescribe());
                BillBook billBook=LitePal.find(BillBook.class,pid);
                intent.putExtra("billbook",billBook);
                startActivity(intent);
            }
        });
    }
    private void initBillBook(){
        billBookList= LitePal.findAll(BillBook.class);
        //BillBook billBook=new BillBook(150,"支出","1","2019");
        //billBookList.add(billBook);
    }

    private SimpleAdapter getAdapter(){
        List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
        for(BillBook billBook : billBookList){
            HashMap<String, Object> m = new HashMap<String, Object>();
            m.put("date", billBook.getDate());
            m.put("type", billBook.getType());
            m.put("money", billBook.getMoney());
            m.put("describe", billBook.getDescribe());
            list1.add(m);
        }i
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list1, R.layout.list,
                new String[]{"类型", "金额","时间", "描述"},
                //xml文件中的字段名
                new int[]{R.id.booktype,R.id.bookmoney,R.id.bookdate,R.id.bookdescribe}
        );
        return simpleAdapter;
    }

  /*  private List<Map<String, Object>> getData() {

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String credit = cursor.getString(2);
            //这里同样是查询，只不过把查询到的数据添加到list集合，可以参照ListView的用法
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_id", id); //获取_id
            map.put("name", name);    //获取name
            map.put("credit", credit); //获取credit学分
            list.add(map);
            //System.out.println("query--->" + id + "," + name + "," + credit);
        }
        return list;
    }*/
}
