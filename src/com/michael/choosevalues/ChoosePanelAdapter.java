package com.michael.choosevalues;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * This is the adapter of GridView. 
 * Make every Item of GridView setOnClickListener in the method of getView(),
 * when Item clicked,get the background drawable of this Item.
 * If it's normal drawable,then go through all the items of GridView and set normal drawable.
 * If it's selected drawable,delete the data of arrayList by Item id,and call notifyDataSetChanged()
 * 
 * 这个是GridView的数据适配器。
 * 在getView()中让每一个Item都设置了OnClickListener,在点击事件触发的时候，判断下当前Item的背景图片
 * 如果是normal，则遍历所有的Item，将背景设置为normal,将当前被点击的Item的背景图片设置成selected，
 * 如果是selected，就根据索引删除当前的Item的数据源，并调用notifyDataSetChanged()，刷新。
 * 
 * @author MichaelYe
 * @since 2012-8-28
 * */
public class ChoosePanelAdapter extends BaseAdapter
{
	private Context context;
	private List<String> arrayList;
	private Drawable iconDefalut;
	private Drawable iconSelected;
	public ChoosePanelAdapter(Context context, List<String> arrayList)
	{
		this.context = context;
		this.arrayList = arrayList;
		iconDefalut = context.getResources().getDrawable(R.drawable.icon_default);
		iconSelected = context.getResources().getDrawable(R.drawable.icon_selected);
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		Button button;
        if (convertView == null)
        {
        	button = new Button(context);
	    }
	    else
	    {
	    	button = (Button) convertView;
	    }
        button.setText(arrayList.get(position));
        button.setBackgroundDrawable(iconDefalut);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getBackground() == iconSelected)//判断当前的背景是哪个
				{
					arrayList.remove(position);
					ChoosePanelAdapter.this.notifyDataSetChanged();
				}
				else
				{
					ChoosePanelAdapter.this.setButtonDefaultState(v);
					v.setBackgroundDrawable(iconSelected);
				}
				v.invalidate();
			}
		});
	    return button;
	}
	
	/**
	 * set all the buttons state normal
	 * 
	 * 设置按钮背景图片为默认状态
	 * 
	 * */
	private void setButtonDefaultState(View v)
	{
		GridView gvParent = (GridView)v.getParent();
		int childCount = gvParent.getChildCount();
		for(int i = 0; i < childCount; i++)
		{
			gvParent.getChildAt(i).setBackgroundDrawable(iconDefalut);
		}
		gvParent.invalidate();
	}
}











