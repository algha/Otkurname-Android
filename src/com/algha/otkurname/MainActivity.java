package com.algha.otkurname;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	private ListView listview;
	private ArrayList<String> arrayList;
	private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        inflater = LayoutInflater.from(MainActivity.this);
        
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setTypeface(App.TYPE_FACE);
        
        View btn = (View)findViewById(R.id.settings);
        btn.setOnClickListener(new clickListener());
        
        listview = (ListView)findViewById(R.id.listview);
        
        arrayList = new ArrayList<String>();
        
        String string = getFromAssets("titles.txt");
        for (String str : string.split("-")) {
        	arrayList.add(str);
		}
        
        adapter m_adapter = new adapter();
        listview.setAdapter(m_adapter);
        
        listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO Auto-generated method stub

				Intent intent1 = new Intent(MainActivity.this,ActivityBrowser.class);
				intent1.putExtra("url", "file:///android_asset/html/s"+title(position+1)+".htm");
				intent1.putExtra("title", "مەزمۇنى");
				startActivity(intent1);
			}
		});
    }
    
    public String title(int mid){
    	if (mid > 9) {
			return ""+mid;
		}
    	return "0"+mid;
    }

    public class clickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intent = new Intent(MainActivity.this,ActivitySettings.class);
			startActivity(intent);
			
		}
    	
    }
    
    public String getFromAssets(String fileName){ 
        try { 
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName)); 
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
		return "";
    } 
    
    public class adapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arrayList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arrayList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item, null);
			}
			
			TextView textView = (TextView)convertView.findViewById(R.id.text);
			textView.setTypeface(App.TYPE_FACE);
			textView.setText(arrayList.get(position));
			
			return convertView;
		}
    	
    }
        
}
