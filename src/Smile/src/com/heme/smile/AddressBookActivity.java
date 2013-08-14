package com.heme.smile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.heme.smile.AddressBookActivity.ContactAdapter.ViewHolder;
import com.heme.smile.testdata.ChatGroup;
import com.heme.smile.testdata.Contacter;
import com.heme.smile.ui.view.ExpandableAdapter;
import com.heme.smile.ui.view.ExpandableAdapter.ChildViewHolder;
import com.heme.smile.ui.view.SideBar;
import com.heme.smile.util.MySearchAdapter;
import com.heme.smile.util.PinyinComparator;
import com.heme.utils.Util;

public class AddressBookActivity extends BaseActivity {
    /** Called when the activity is first created. */
	public static final String SHOWCHECKBOX = "showcheckbox";
	public static final String SELECTCARD = "showcard";
	private ListView lvContact;
	private SideBar indexBar;
	private WindowManager mWindowManager;
	private TextView mDialogText;
	private boolean mShowCheckbox,mSelectCard;
	private ContactAdapter mAdapter;
	private RelativeLayout mTitleLayout;
	private Button mStartGroupChatBtn;
	private LinkedList<String> mGroupSelectList = new LinkedList<String>();
 	private AutoCompleteTextView mAutoCompleteTextView;
 	private MySearchAdapter mSearchAdapter;
 	private Button mRightBtn;
 	private PopupWindow mMorePopupWindow;
 	private View mMoreView;
 	private RelativeLayout mGroupLayout,mShequLayout;
	private void initEmulatedDatas(){
		initGroupData();
		initContacters();
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEmulatedDatas();
        mShowCheckbox = getIntent().getBooleanExtra(SHOWCHECKBOX, false);
        mSelectCard = getIntent().getBooleanExtra(AddressBookActivity.SELECTCARD, false);
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        initUI();
    }
    
    private void initUI(){
    	mMoreView = LayoutInflater.from(this).inflate(R.layout.addressbook_more_view, null);
    	mMoreView.findViewById(R.id.addfriend_rl).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMorePopupWindow.dismiss();
				startActivity(new Intent(AddressBookActivity.this, SearchUserActivity.class));
			}
		});
    	mMorePopupWindow = new PopupWindow(mMoreView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
    	mMorePopupWindow.setBackgroundDrawable(new BitmapDrawable());
    	mMorePopupWindow.setTouchable(true);
    	mMorePopupWindow.setFocusable(true);
    	setContentView(R.layout.addressbook);
    	mGroupLayout = (RelativeLayout)findViewById(R.id.my_group_rl);
    	mGroupLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AddressBookActivity.this, GroupListActivity.class));
			}
		});
    	mShequLayout = (RelativeLayout)findViewById(R.id.my_shequ_rl);
    	mShequLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AddressBookActivity.this, ShequListActivity.class));
			}
		});
    	mRightBtn = (Button)findViewById(R.id.rightBtn);
    	mRightBtn.setVisibility(View.VISIBLE);
    	mRightBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMorePopupWindow.setOutsideTouchable(true);
				mMorePopupWindow.showAsDropDown(mRightBtn,0,15);
				mMorePopupWindow.update();
			}
		});
    	mRightBtn.setText("更多");
    	
    	mAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.search_auto_complete);
    	
    	mTitleLayout = (RelativeLayout)findViewById(R.id.public_title_bar);
    	mTitleLayout.setVisibility(View.VISIBLE);
    	if (mShowCheckbox) {
    		((TextView)findViewById(R.id.titleTextView)).setText("选择联系人");
    		findViewById(R.id.backImg).setVisibility(View.VISIBLE);
    		findViewById(R.id.search_ll).setVisibility(View.GONE);
			findViewById(R.id.backImg).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AddressBookActivity.this.finish();
				}
			});
			mStartGroupChatBtn = (Button)findViewById(R.id.rightBtn);
			mStartGroupChatBtn.setVisibility(View.VISIBLE);
			mStartGroupChatBtn.setText("开始");
			mStartGroupChatBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (mAdapter.getCheckedCount()==0) {
						Util.showToast(AddressBookActivity.this, "最少选择一个联系人才能发起群聊");
						return;
					}
					Util.showToast(AddressBookActivity.this, "添加群聊成功，向服务器发送群聊请求");
					AddressBookActivity.this.finish();
				}
			});
		}else {
			((TextView)findViewById(R.id.titleTextView)).setText("通讯录");
			findViewById(R.id.backImg).setVisibility(View.GONE);
			findViewById(R.id.search_ll).setVisibility(View.VISIBLE);
		}
    	lvContact = (ListView)this.findViewById(R.id.lvContact);
    	mAdapter = new ContactAdapter(this,contacters);
    	mAdapter.showCheckbox(mShowCheckbox);
    	lvContact.setAdapter(mAdapter);
    	lvContact.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
				
				menu.add(Menu.NONE,0,0,"删除好友");
				menu.add(Menu.NONE,0,1,"取消");
				
			}
		
		});
    	lvContact.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    				long arg3) {
					if (!mSelectCard) {
						String nickname = nicks[arg2];
		    			if (nickname!=null&&!nickname.trim().equals("")) {
							Intent intent = new Intent(AddressBookActivity.this, SingleChatActivity.class);
							intent.putExtra(SingleChatActivity.SINGLE_CHAT_NICKNAME, nickname);
							startActivity(intent);
						}
					}else {
						Intent cardIntent = new Intent(AddressBookActivity.this, SendBusinessCardConfirmActivity.class);
						cardIntent.putExtra(SendBusinessCardConfirmActivity.SELECT_CARD_ID, nicks[arg2]);
						startActivityForResult(cardIntent,SingleChatActivity.REQUEST_CARD);
						finish();
					}
    		}
    	});
    	
    	lvContact.setItemsCanFocus(false);   
    	lvContact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); 
    	indexBar = (SideBar) findViewById(R.id.sideBar);  
        indexBar.setListView(lvContact); 
        mDialogText = (TextView) LayoutInflater.from(this).inflate(R.layout.addressbook_item, null);
        mDialogText.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        indexBar.setTextView(mDialogText);
        mAutoCompleteTextView.setThreshold(1);
        mSearchAdapter = new MySearchAdapter<String>(this,
    			android.R.layout.simple_dropdown_item_1line, nicks,MySearchAdapter.ALL);//速度优先
        mAutoCompleteTextView.setAdapter(mSearchAdapter);//
    }
    @Override
	public boolean onContextItemSelected(MenuItem item) {
		int position = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
		if (item.getOrder()==1) {
			return true;
		}
		contacters.remove(position);
		mAdapter.notifyDataSetChanged();
		return true;
	}
    class ContactAdapter extends BaseAdapter implements SectionIndexer {  
    	private Context mContext;
    	private boolean mShowCheckbox;
    	public  Map<Integer,Boolean> mCheckFlagMap = null;
    	private List<Contacter> mContacterList = null;
    	@SuppressWarnings("unchecked")
    	void init(){
    		for (int i = 0; i < contacters.size(); i++) {
    			mCheckFlagMap.put(i, false);
    		}
    	}
		public ContactAdapter(Context mContext,List<Contacter> c){
    		this.mContext = mContext;
    		mCheckFlagMap = new HashMap<Integer, Boolean>();
    		this.mContacterList = c;
    		init();
    		//排序(实现了中英文混排)
    		Collections.sort(mContacterList,new PinyinComparator());
    	}
		@Override
		public int getCount() {
			return this.mContacterList.size();
		}
		public void showCheckbox(boolean show){
			this.mShowCheckbox = show;
		}
		@Override
		public Contacter getItem(int position) {
			return this.mContacterList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		private int getCheckedCount(){
			int i = 0;
			for (Integer integer : mCheckFlagMap.keySet()) {
				if (mCheckFlagMap.get(integer)) {
					i++;
				}
			}
			return i;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final String nickName = this.mContacterList.get(position).name;
			ViewHolder viewHolder = null;
			if(convertView == null){
				convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null);
				viewHolder = new ViewHolder();
				viewHolder.tvCatalog = (TextView)convertView.findViewById(R.id.contactitem_catalog);
				viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.contactitem_avatar_iv);
				viewHolder.tvNick = (TextView)convertView.findViewById(R.id.contactitem_nick);
				viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder)convertView.getTag();
			}
			viewHolder.checkBox.setVisibility(View.GONE);
			String catalog = converterToFirstSpell(nickName).substring(0, 1);
			if(position == 0){
				viewHolder.tvCatalog.setVisibility(View.VISIBLE);
				viewHolder.tvCatalog.setText(catalog);
			}else{
				String lastCatalog = converterToFirstSpell(this.mContacterList.get(position-1).name).substring(0, 1);
				if(catalog.equals(lastCatalog)){
					viewHolder.tvCatalog.setVisibility(View.GONE);
				}else{
					viewHolder.tvCatalog.setVisibility(View.VISIBLE);
					viewHolder.tvCatalog.setText(catalog);
				}
			}
			viewHolder.checkBox.setChecked(mCheckFlagMap.get(position));
			viewHolder.ivAvatar.setImageResource(R.drawable.default_avatar);
			viewHolder.tvNick.setText(nickName);
			return convertView;
		}
    	
		class ViewHolder{
			TextView tvCatalog;//目录
			ImageView ivAvatar;//头像
			TextView tvNick;//昵称
			CheckBox checkBox;
		}
 
		@Override
		public int getPositionForSection(int section) {
			for (int i = 0; i < this.mContacterList.size(); i++) {  
	            String l = converterToFirstSpell(this.mContacterList.get(i).name).substring(0, 1);  
	            char firstChar = l.toUpperCase().charAt(0);  
	            if (firstChar == section) {  
	                return i;  
	            }  
	        } 
			return -1;
		}
		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}
		@Override
		public Object[] getSections() {
			return null;
		}
    }
    
    /**
     * 昵称
     */
    private static String[] nicks = {"阿雅","北风","张山","李四","欧阳锋","郭靖","黄蓉","杨过","凤姐","芙蓉姐姐","移联网","樱木花道","风清扬","张三丰","梅超风"};
    ArrayList<Contacter> contacters = new ArrayList<Contacter>();
    LinkedList<ChatGroup> groups = new LinkedList<ChatGroup>();
    private void initContacters(){
    	for (int i = 0; i < nicks.length; i++) {
			Contacter entity = new Contacter();
			entity.id = i+1;
			entity.serverId = i+1;
			entity.name = nicks[i];
			entity.groupId = (i%3)+1;
			contacters.add(entity);
		}
    }
    private void initGroupData(){
    	ChatGroup group1 = new ChatGroup();
    	group1.id = 1;
    	group1.serverId = 1;
    	group1.name = "老师群";
    	groups.add(group1);
    	ChatGroup group2 = new ChatGroup();
    	group2.id = 2;
    	group2.serverId = 2;
    	group2.name = "学生群";
    	groups.add(group2);
    	ChatGroup group3 = new ChatGroup();
    	group3.id = 3;
    	group3.serverId = 3;
    	group3.name = "家长群";
    	groups.add(group3);
    }
    /**  
     * 汉字转换位汉语拼音首字母，英文字符不变  
     * @param chines 汉字  
     * @return 拼音  
     */     
    public static String converterToFirstSpell(String chines){             
         String pinyinName = "";      
        char[] nameChar = chines.toCharArray();      
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();      
         defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);      
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);      
        for (int i = 0; i < nameChar.length; i++) {      
            if (nameChar[i] > 128) {      
                try {      
                     pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);      
                 } catch (BadHanyuPinyinOutputFormatCombination e) {      
                     e.printStackTrace();      
                 }      
             }else{      
                 pinyinName += nameChar[i];      
             }      
         }      
        return pinyinName;      
     }  
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode==SingleChatActivity.REQUEST_CARD) {
			if (data != null) {
				setResult(requestCode, data);
				this.finish();
			}
		}
    	super.onActivityResult(requestCode, resultCode, data);
    }
}