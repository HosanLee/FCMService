package org.androidtown.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hosan on 2016-12-16.
 */

public class CustomAdapter extends BaseAdapter{

    private ArrayList<ItemData> itemDatas = null;
    private LayoutInflater layoutInflater = null;

    public CustomAdapter(ArrayList<ItemData> itemDatas, Context ctx){
        this.itemDatas = itemDatas;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItemData(ArrayList<ItemData> itemDatas){
        this.itemDatas = itemDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (itemDatas != null) ? itemDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (itemDatas != null && (0 <= position && position < itemDatas.size()) ? itemDatas.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return (itemDatas != null && (0 <= position && position < itemDatas.size()) ? position : 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item, parent ,false);

            viewHolder.textView_Title = (TextView)convertView.findViewById(R.id.txtTitle_item);
            viewHolder.textView_Description = (TextView)convertView.findViewById(R.id.txtDescription_item);

            convertView.setTag(viewHolder);
        }

        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        ItemData itemData = itemDatas.get(position);

        viewHolder.textView_Title.setText(itemData.Title);
        viewHolder.textView_Description.setText(itemData.Description);

        return convertView;
    }
}
