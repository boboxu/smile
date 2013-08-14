package com.heme.smile;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.heme.smile.util.AsyncImageLoader;
import com.heme.smile.util.AsyncImageLoader.ImageCallback;
import com.heme.smile.util.DateComparator;
import com.heme.smile.util.ViewCache;

public class NotificationActivity extends BaseActivity implements OnClickListener{
	private static final String TAG = "NotificationActivity";
	private ListView mListView;
	private MyAdapter mAdapter;
	private LinkedList<NotificationData> mNotifications = new LinkedList<NotificationActivity.NotificationData>();
	
	private void emulateData(){
		NotificationData data = new NotificationData();
		data.date = System.currentTimeMillis();
		data.message = "请各位小伙伴们注意我的通知，速回";
		data.imgUrl = "http://t3.baidu.com/it/u=1986029191,1759192348&fm=21&gp=0.jpg";
		data.weburl = "http://3g.qq.com";
		mNotifications.add(data);
		NotificationData data2 = new NotificationData();
		data2.date = System.currentTimeMillis();
		data2.message = "我和我的小伙伴们都吓尿了";
		data2.weburl = "http://sina.cn";
		data2.imgUrl = "http://t1.baidu.com/it/u=197600486,2285323441&fm=21&gp=0.jpg";
		mNotifications.add(data2);
		
		NotificationData data3 = new NotificationData();
		data3.date = System.currentTimeMillis()-17457278929l;
		data3.message = "最美的不是下雨天,是与你一起躲过雨的屋檐.";
		data3.imgUrl = "http://t2.baidu.com/it/u=2212918111,20697924&fm=11&gp=0.jpg";
		data3.weburl = "http://sohu.com";
		mNotifications.add(data3);
		
		
		NotificationData data4 = new NotificationData();
		data4.date = System.currentTimeMillis()-75442674788l;
		data4.message = "放心,我一定会治好你的脑残的";
		data4.imgUrl = "http://t1.baidu.com/it/u=4164381868,3966555663&fm=11&gp=0.jpg";
		data4.weburl = "http://3g.cn";
		mNotifications.add(data4);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		emulateData();
		initUI();
	}
	
	private void initUI(){
		setContentView(R.layout.notification);
		findViewById(R.id.backImg).setOnClickListener(this);
		((TextView)findViewById(R.id.titleTextView)).setText("公告信息");
		mListView = (ListView)findViewById(R.id.listview);
		mAdapter = new MyAdapter(this, mNotifications);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				NotificationData entity = mNotifications.get(arg2);
				gotoWebview(entity.message, entity.weburl);
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backImg:
			finish();
			break;

		default:
			break;
		}
	}
	private String getTimeText(long date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = sdf.format(date);
		return dateString;
	}
	public class NotificationData{
		public long date;//消息日期
		public String message;//消息内容
		public String imgUrl;  //图片url
		public String weburl;
	}
	class MyAdapter extends BaseAdapter{
		private AsyncImageLoader asyncImageLoader;
		private Context ctx;
		private Handler handler;
		public void setHandler(Handler handler){
			this.handler = handler;
		}
		private static final int ITEMCOUNT = 2;// 消息类型的总数
		private List<NotificationData> coll;// 消息对象数组
		private LayoutInflater mInflater;

		public MyAdapter(Context context, List<NotificationData> coll) {
			this.ctx = context;
			this.coll = coll;
			asyncImageLoader = new AsyncImageLoader();
			mInflater = LayoutInflater.from(context);
			Collections.sort(this.coll,new DateComparator());
		}

		public int getCount() {
			return coll.size();
		}

		public Object getItem(int position) {
			return coll.get(position);
		}

		public long getItemId(int position) {
			return position;
		}


		/**
		 * Item类型的总数
		 */
		public int getViewTypeCount() {
			return ITEMCOUNT;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {

			final NotificationData entity = coll.get(position);
			View rowView = convertView;
	        ViewCache viewCache;
	        if (rowView == null) {
	            rowView = LayoutInflater.from(this.ctx).inflate(R.layout.notification_item, null);
	            viewCache = new ViewCache(rowView);
	            rowView.setTag(viewCache);
	        } else {
	            viewCache = (ViewCache) rowView.getTag();
	        }
	        TextView timeView = viewCache.getTimeView();
	        String catalog = getTimeText(entity.date);
			if(position == 0){
				timeView.setVisibility(View.VISIBLE);
				timeView.setText(catalog);
			}else{
				String lastCatalog = getTimeText(this.coll.get(position-1).date);
				if(catalog.equals(lastCatalog)){
					timeView.setVisibility(View.GONE);
				}else{
					timeView.setVisibility(View.VISIBLE);
					timeView.setText(catalog);
				}
			}
	        String imageUrl = entity.imgUrl;
	        ImageView imageView = viewCache.getImageView();
	        imageView.setTag(imageUrl);
	        Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
	            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
	                ImageView imageViewByTag = (ImageView) mListView.findViewWithTag(imageUrl);
	                if (imageViewByTag != null) {
	                    imageViewByTag.setImageDrawable(imageDrawable);
	                }
	            }
	        });
			if (cachedImage == null) {
				imageView.setImageResource(R.drawable.ic_launcher);
			}else{
				imageView.setImageDrawable(cachedImage);
			}
	        // Set the text on the TextView
	        TextView textView = viewCache.getTextView();
	        textView.setText(entity.message);

	        return rowView;
		}

		class ViewHolder {
			public ImageView ivAvatar;
			public TextView tvContent;
		}
	}
	
}
