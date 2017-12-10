package com.algha.otkurname;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActivitySettings  extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_settings);
		
		TextView textView = (TextView)findViewById(R.id.title);
        textView.setTypeface(App.TYPE_FACE);
        
        TextView text_1 = (TextView)findViewById(R.id.text_1);
        text_1.setTypeface(App.TYPE_FACE);
        TextView text_2 = (TextView)findViewById(R.id.text_2);
        text_2.setTypeface(App.TYPE_FACE);
        TextView text_3 = (TextView)findViewById(R.id.text_3);
        text_3.setTypeface(App.TYPE_FACE);
        
        findViewById(R.id.about).setOnClickListener(new clickListener());
        findViewById(R.id.blog).setOnClickListener(new clickListener());
        findViewById(R.id.baha).setOnClickListener(new clickListener());
		
	}
	
	public class clickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.about:

				Intent intent1 = new Intent(ActivitySettings.this,ActivityBrowser.class);
				intent1.putExtra("url", "file:///android_asset/html/info.htm");
				intent1.putExtra("title", "ئەپ ھەققىدە");
				startActivity(intent1);
				
				break;
				
			case R.id.blog:
				
				Intent intent = new Intent(ActivitySettings.this,ActivityBrowser.class);
				intent.putExtra("url", "http://www.uyghurbeg.cn/");
				intent.putExtra("title", "ئۇيغۇربەگ بلوگى");
				startActivity(intent);
				
				break;
				
			case R.id.baha:
				Intent data = new Intent(Intent.ACTION_SENDTO); 
				data.setData(Uri.parse("mailto:algha@outlook.com")); 
				data.putExtra(Intent.EXTRA_SUBJECT, "ئۆتكۈرنامەدىن پىكىر"); 
				data.putExtra(Intent.EXTRA_TEXT, "پىكىر مەزمۇنى..."); 
				startActivity(data); 
				break;

			default:
				break;
			}
		}
		
	}

}
