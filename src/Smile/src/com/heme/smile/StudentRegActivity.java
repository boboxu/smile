package com.heme.smile;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.heme.logic.common.Constans;
import com.heme.smile.util.CityRegionItem;
import com.heme.smile.util.DBManager;
import com.heme.utils.Util;

public class StudentRegActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "StudentRegActivity";
	private DBManager dbm;
	private SQLiteDatabase db;
	private Spinner spinner1 = null;
	private Spinner spinner2=null;
	private Spinner spinner3=null;
	private String province=null;
	private String city=null;
	private String district=null;
	private Button mNextStepBtn;
	private EditText mAccount,mRealName,mStudentID,mPwd,mConfirmPwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initUI();
	}
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constans.STUDENT_REG_SUCCESS:
				dismissDialog();
				//得到服务器返回的信息后跳转到验证码界面
				startActivity(new Intent(StudentRegActivity.this, AdultRegPhoneCheckActivity.class));
				break;

			default:
				break;
			}
		};
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;
		case R.id.nextstep:
			String account = mAccount.getText().toString().trim();
			String realname = mRealName.getText().toString().trim();
			String studentid = mStudentID.getText().toString().trim();
			String pwd = mPwd.getText().toString().trim();
			String confirmpwd = mConfirmPwd.getText().toString().trim();
			if (account.equals("")||realname.equals("")||studentid.equals("")||pwd.equals("")||confirmpwd.equals("")) {
				Util.showToast(this, "还有必填内容没有填写");
				return;
			}
			if (!pwd.equals(confirmpwd)) {
				Util.showToast(this, "两次密码必须相同");
				return;
			}
			showWaitDialog("注册中,请稍候...");
			//模拟服务器注册
			mHandler.sendEmptyMessageDelayed(Constans.STUDENT_REG_SUCCESS, 3000);
			break;
		default:
			break;
		}
	}
	private void initUI(){
		setContentView(R.layout.studentreg);
		mAccount = (EditText)findViewById(R.id.account_edittext);
		mRealName = (EditText)findViewById(R.id.realname_edittext);
		mStudentID = (EditText)findViewById(R.id.studentid_edittext);
		mPwd = (EditText)findViewById(R.id.pwd_edittext);
		mConfirmPwd = (EditText)findViewById(R.id.confirmpwd_edittext);
		
		mNextStepBtn = (Button)findViewById(R.id.nextstep);
		mNextStepBtn.setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("注册");
		findViewById(R.id.backImg).setOnClickListener(this);
		spinner1=(Spinner)findViewById(R.id.province);
        spinner2=(Spinner)findViewById(R.id.city);
        spinner3=(Spinner)findViewById(R.id.region);
		spinner1.setPrompt("省");
		spinner2.setPrompt("城市");		
		spinner3.setPrompt("地区");
        initSpinner1();
	}
	public void initSpinner1(){
		dbm = new DBManager(this);
	 	dbm.openDatabase();
	 	db = dbm.getDatabase();
	 	List<CityRegionItem> list = new ArrayList<CityRegionItem>();
		
	 	try {    
	        String sql = "select * from province";  
	        Cursor cursor = db.rawQuery(sql,null);  
	        cursor.moveToFirst();
	        while (!cursor.isLast()){ 
		        String code=cursor.getString(cursor.getColumnIndex("code")); 
		        byte bytes[]=cursor.getBlob(2); 
		        String name=new String(bytes,"gbk");
		        CityRegionItem CityRegionItem=new CityRegionItem();
		        CityRegionItem.setName(name);
		        CityRegionItem.setPcode(code);
		        list.add(CityRegionItem);
		        cursor.moveToNext();
	        }
	        String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        CityRegionItem CityRegionItem=new CityRegionItem();
	        CityRegionItem.setName(name);
	        CityRegionItem.setPcode(code);
	        list.add(CityRegionItem);
	        
	    } catch (Exception e) {  
	    } 
	 	dbm.closeDatabase();
	 	db.close();	
	 	
	 	MyAdapter myAdapter = new MyAdapter(this,list);
	 	spinner1.setAdapter(myAdapter);
		spinner1.setOnItemSelectedListener(new SpinnerOnSelectedListener1());
	}
	public void initSpinner2(String pcode){
		dbm = new DBManager(this);
	 	dbm.openDatabase();
	 	db = dbm.getDatabase();
	 	List<CityRegionItem> list = new ArrayList<CityRegionItem>();
		
	 	try {    
	        String sql = "select * from city where pcode='"+pcode+"'";  
	        Cursor cursor = db.rawQuery(sql,null);  
	        cursor.moveToFirst();
	        while (!cursor.isLast()){ 
		        String code=cursor.getString(cursor.getColumnIndex("code")); 
		        byte bytes[]=cursor.getBlob(2); 
		        String name=new String(bytes,"gbk");
		        CityRegionItem CityRegionItem=new CityRegionItem();
		        CityRegionItem.setName(name);
		        CityRegionItem.setPcode(code);
		        list.add(CityRegionItem);
		        cursor.moveToNext();
	        }
	        String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        CityRegionItem CityRegionItem=new CityRegionItem();
	        CityRegionItem.setName(name);
	        CityRegionItem.setPcode(code);
	        list.add(CityRegionItem);
	        
	    } catch (Exception e) {  
	    } 
	 	dbm.closeDatabase();
	 	db.close();	
	 	
	 	MyAdapter myAdapter = new MyAdapter(this,list);
	 	spinner2.setAdapter(myAdapter);
		spinner2.setOnItemSelectedListener(new SpinnerOnSelectedListener2());
	}
	public void initSpinner3(String pcode){
		dbm = new DBManager(this);
	 	dbm.openDatabase();
	 	db = dbm.getDatabase();
	 	List<CityRegionItem> list = new ArrayList<CityRegionItem>();
		
	 	try {    
	        String sql = "select * from district where pcode='"+pcode+"'";  
	        Cursor cursor = db.rawQuery(sql,null);  
	        cursor.moveToFirst();
	        while (!cursor.isLast()){ 
		        String code=cursor.getString(cursor.getColumnIndex("code")); 
		        byte bytes[]=cursor.getBlob(2); 
		        String name=new String(bytes,"gbk");
		        CityRegionItem CityRegionItem=new CityRegionItem();
		        CityRegionItem.setName(name);
		        CityRegionItem.setPcode(code);
		        list.add(CityRegionItem);
		        cursor.moveToNext();
	        }
	        String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        CityRegionItem CityRegionItem=new CityRegionItem();
	        CityRegionItem.setName(name);
	        CityRegionItem.setPcode(code);
	        list.add(CityRegionItem);
	        
	    } catch (Exception e) {  
	    } 
	 	dbm.closeDatabase();
	 	db.close();	
	 	
	 	MyAdapter myAdapter = new MyAdapter(this,list);
	 	spinner3.setAdapter(myAdapter);
		spinner3.setOnItemSelectedListener(new SpinnerOnSelectedListener3());
	}
class SpinnerOnSelectedListener1 implements OnItemSelectedListener{
		
		public void onItemSelected(AdapterView<?> adapterView, View view, int position,
				long id) {
			province=((CityRegionItem) adapterView.getItemAtPosition(position)).getName();
			String pcode =((CityRegionItem) adapterView.getItemAtPosition(position)).getPcode();
			
			initSpinner2(pcode);
			initSpinner3(pcode);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}		
	}
	class SpinnerOnSelectedListener2 implements OnItemSelectedListener{
		
		public void onItemSelected(AdapterView<?> adapterView, View view, int position,
				long id) {
			city=((CityRegionItem) adapterView.getItemAtPosition(position)).getName();
			String pcode =((CityRegionItem) adapterView.getItemAtPosition(position)).getPcode();

			initSpinner3(pcode);
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}		
	}
	
	class SpinnerOnSelectedListener3 implements OnItemSelectedListener{
		
		public void onItemSelected(AdapterView<?> adapterView, View view, int position,
				long id) {
			district=((CityRegionItem) adapterView.getItemAtPosition(position)).getName();
		}

		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}		
	}
	class MyAdapter extends BaseAdapter {
		
		private Context context;
		private List<CityRegionItem> myList;
		
		public MyAdapter(Context context, List<CityRegionItem> myList) { 
			this.context = context; 
			this.myList = myList;
		}
		
		public int getCount() {
			return myList.size(); 
		} 
		public Object getItem(int position) {
			return myList.get(position);
		} 
		public long getItemId(int position) {
			return position;
		} 
		
		public View getView(int position, View convertView, ViewGroup parent)
		{ 
			CityRegionItem CityRegionItem = myList.get(position); 
			return new MyAdapterView(this.context, CityRegionItem ); 
		}

		class MyAdapterView extends LinearLayout { 
			public static final String LOG_TAG = "MyAdapterView";
			
			public MyAdapterView(Context context, CityRegionItem CityRegionItem ) { 
			super(context);
			this.setOrientation(HORIZONTAL); 
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
			params.setMargins(1, 1, 1, 1); 
			
			TextView name = new TextView(context); 
			name.setText( CityRegionItem.getName() ); 
			addView( name, params); 
			
			LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
			params2.setMargins(1, 1, 1, 1); 
			
			TextView pcode = new TextView(context); 
			pcode.setText(CityRegionItem.getPcode()); 
			addView( pcode, params2); 
			pcode.setVisibility(GONE);

			}		 

			}

	}
}
