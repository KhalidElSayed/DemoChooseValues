package com.michael.choosevalues;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

/**
 * 类似QQ通讯录中的选择联系人，使用的是GridView
 * 
 * 
 * */
public class MainActivity extends Activity 
{

	private GridView gvPanel;
	private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gvPanel = (GridView)findViewById(R.id.gv_panel);
        listView = (ListView)findViewById(R.id.listview);
        
        final List<String> data = this.getData();
        final List<String> panelArrayList = new ArrayList<String>();
        final ChoosePanelAdapter adapter = new ChoosePanelAdapter(MainActivity.this, panelArrayList);
        
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
        		android.R.layout.simple_expandable_list_item_1, data));
        
        listView.setOnItemClickListener(new OnItemClickListener() 
        {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
				panelArrayList.add(data.get(position));
				adapter.notifyDataSetChanged();
			}
		});
        
        gvPanel.setAdapter(adapter);
    }

    /**
     * DataSource
     * 
     * 
     * */
    private List<String> getData()
    {
    	List<String> list = new ArrayList<String>();
    	list.add("张三");
    	list.add("李四");
    	list.add("王五");
    	list.add("赵六");
    	list.add("乔丹");
    	list.add("爱斯基摩");
    	list.add("维也纳");
    	list.add("天线宝宝");
    	return list;
    }
}
























