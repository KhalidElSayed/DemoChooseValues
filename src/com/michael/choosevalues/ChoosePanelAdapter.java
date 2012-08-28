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
	 * 让所有的子控件的选中状态都变为false
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











