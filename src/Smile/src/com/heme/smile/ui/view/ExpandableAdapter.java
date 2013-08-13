package com.heme.smile.ui.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.heme.smile.R;
import com.heme.smile.testdata.ChatGroup;
import com.heme.smile.testdata.Contacter;
public class ExpandableAdapter extends BaseExpandableListAdapter{
    private Context context;
    LinkedList<ChatGroup> groups;
    List<Contacter> contacters;
    private boolean showCheckBox;
    LinkedHashMap<Integer,LinkedList<Contacter>> groupContacterMap = new LinkedHashMap<Integer, LinkedList<Contacter>>();
    public  Map<Integer,Boolean> mCheckFlagMap = new HashMap<Integer, Boolean>();
    public Map<Integer, Boolean> mGroupCheckMap = new HashMap<Integer, Boolean>();
    void initCheckFlagMap(){
		for (int i = 0; i < contacters.size(); i++) {
			Contacter entity = contacters.get(i);
			mCheckFlagMap.put(entity.serverId, false);
		}
		for (ChatGroup group : groups) {
			mGroupCheckMap.put(group.id, false);
		}
	}
    public ExpandableAdapter(Context context,LinkedList<ChatGroup> group,List<Contacter> contacter){
        this.context = context;
        this.groups = group;
        this.contacters = contacter;
        initGroupContacterMap();
        initCheckFlagMap();
    }
    public void showCheckBox(boolean show){
    	this.showCheckBox = show;
    }
    private void initGroupContacterMap(){
    	for (ChatGroup group : groups) {
			LinkedList<Contacter> list = new LinkedList<Contacter>();
			groupContacterMap.put(group.id, list);
		}
    	for (Contacter contacter : contacters) {
			if (groupContacterMap.containsKey(contacter.groupId)) {
				LinkedList<Contacter> list = groupContacterMap.get(contacter.groupId);
				if (list!=null) {
					list.add(contacter);
				}
			}
		}
    }
    int[] logos = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher};
    //子视图图片
    public int[][] generallogos = new int[][] {
            { R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher },
            { R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher },
            { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher } };
    
    //自己定义一个获得文字信息的方法
    TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 84);
        TextView textView = new TextView(
                context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        textView.setPadding(36, 0, 200, 0);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        return textView;
    }
    public int getCheckedCount(){
		int i = 0;
		for (Integer integer : mCheckFlagMap.keySet()) {
			if (mCheckFlagMap.get(integer)) {
				i++;
			}
		}
		return i;
	}
    @Override
    public Contacter getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
    	ChatGroup group = (ChatGroup)getGroup(groupPosition);
    	Contacter object = null;
    	object = groupContacterMap.get(group.id).get(childPosition);
    	return object;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition,final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
    	
    	final Contacter contacter = getChild(groupPosition, childPosition);
		ChildViewHolder childViewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, null);
			childViewHolder = new ChildViewHolder();
			childViewHolder.tvCatalog = (TextView)convertView.findViewById(R.id.contactitem_catalog);
			childViewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.contactitem_avatar_iv);
			childViewHolder.tvNick = (TextView)convertView.findViewById(R.id.contactitem_nick);
			childViewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
			convertView.setTag(childViewHolder);
		}else{
			childViewHolder = (ChildViewHolder)convertView.getTag();
		}
		convertView.findViewById(R.id.contactitem_catalog).setVisibility(View.GONE);
		if (showCheckBox) {
			childViewHolder.checkBox.setVisibility(View.VISIBLE);
			childViewHolder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					ChatGroup group = getGroup(groupPosition);
					if(isChecked){
						mCheckFlagMap.put(getChild(groupPosition, childPosition).serverId, true);
					}else{
						mGroupCheckMap.put(group.id, false);
						mCheckFlagMap.put(getChild(groupPosition, childPosition).serverId, false);
						mGroupCheckMap.put(getGroup(groupPosition).id, false);
					}
//					mStartGroupChatBtn.setText("开始 ("+getCheckedCount()+")");
					notifyDataSetChanged();
				}
			});
		}else {
			childViewHolder.checkBox.setVisibility(View.GONE);
		}
		childViewHolder.checkBox.setChecked(mCheckFlagMap.get(getChild(groupPosition, childPosition).serverId));
		childViewHolder.ivAvatar.setImageResource(R.drawable.default_avatar);
		childViewHolder.tvNick.setText(contacter.name);
		return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
    	ChatGroup group = (ChatGroup)getGroup(groupPosition);
        return groupContacterMap.get(group.id).size();
    }

    @Override
    public ChatGroup getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return this.groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return this.groups.size();
    }
    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }
    public class ChildViewHolder{
    	public TextView tvCatalog;//目录
    	public ImageView ivAvatar;//头像
    	public TextView tvNick;//昵称
		public CheckBox checkBox;
    }
    public class GroupViewHolder{
    	ImageView expandImg;
		ImageView ivAvatar;
		TextView name;
		CheckBox checkBox;
		ImageView rightImg;
    }
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	GroupViewHolder groupViewHolder = null;
    	if (convertView==null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.expand_group_item, null);
			groupViewHolder = new GroupViewHolder();
			groupViewHolder.expandImg = (ImageView)convertView.findViewById(R.id.expand_tag);
			groupViewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.group_avatar_iv);
			groupViewHolder.name = (TextView)convertView.findViewById(R.id.group_name);
			groupViewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
			groupViewHolder.rightImg = (ImageView)convertView.findViewById(R.id.right_img);
			convertView.setTag(groupViewHolder);
		}else {
			groupViewHolder = (GroupViewHolder)convertView.getTag();
		}
    	groupViewHolder.ivAvatar.setImageResource(logos[groupPosition]);
    	groupViewHolder.name.setText(((ChatGroup)getGroup(groupPosition)).name);
    	if (showCheckBox) {
    		groupViewHolder.expandImg.setVisibility(View.VISIBLE);
    		groupViewHolder.rightImg.setVisibility(View.GONE);
    		groupViewHolder.checkBox.setVisibility(View.VISIBLE);
//    		groupViewHolder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					ChatGroup group = getGroup(groupPosition);
//					LinkedList<Contacter> contacters = groupContacterMap.get(group.id);
//					if (isChecked) {
//						for (Contacter contacter : contacters) {
//							mCheckFlagMap.put(contacter.serverId, true);
//						}
//					}else {
//						for (Contacter contacter : contacters) {
//							mCheckFlagMap.put(contacter.serverId, false);
//						}
//					}
//					notifyDataSetChanged();
//				}
//			});
    		groupViewHolder.checkBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Boolean isChecked =((CheckBox)v).isChecked();
					if (isChecked) {
						mGroupCheckMap.put(getGroup(groupPosition).id, true);
						for (Contacter contacter : contacters) {
							if (contacter.groupId==getGroup(groupPosition).id) {
								mCheckFlagMap.put(contacter.serverId, true);
							}
						}
					}else {
						mGroupCheckMap.put(getGroup(groupPosition).id, false);
						for (Contacter contacter : contacters) {
							if (contacter.groupId==getGroup(groupPosition).id) {
								mCheckFlagMap.put(contacter.serverId, false);
							}
						}
					}
					notifyDataSetChanged();
				}
			});
    		if (isExpanded) {
                groupViewHolder.expandImg.setBackgroundResource(R.drawable.ibtn_gallery_zoom_out);
            } else {
            	groupViewHolder.expandImg.setBackgroundResource(R.drawable.ibtn_gallery_zoom_in);
            }
    		groupViewHolder.checkBox.setChecked(mGroupCheckMap.get(getGroup(groupPosition).id));
		}else {
			groupViewHolder.rightImg.setVisibility(View.VISIBLE);
    		groupViewHolder.checkBox.setVisibility(View.GONE);
			groupViewHolder.expandImg.setVisibility(View.GONE);
		}
    	return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

}
