/*
 * 10+20=
 */



package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tvScreen;
	private List<Item> items=new ArrayList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvScreen=(TextView) findViewById(R.id.tvScreen);
		findViewById(R.id.btn0).setOnClickListener(this);
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
		findViewById(R.id.btn3).setOnClickListener(this);
		findViewById(R.id.btn4).setOnClickListener(this);
		findViewById(R.id.btn5).setOnClickListener(this);
		findViewById(R.id.btn6).setOnClickListener(this);
		findViewById(R.id.btn7).setOnClickListener(this);
		findViewById(R.id.btn8).setOnClickListener(this);
		findViewById(R.id.btn9).setOnClickListener(this);
		findViewById(R.id.btnAdd).setOnClickListener(this);
		findViewById(R.id.btnSub).setOnClickListener(this);
		findViewById(R.id.btnMult).setOnClickListener(this);
		findViewById(R.id.btnDiv).setOnClickListener(this);
		findViewById(R.id.btnResult).setOnClickListener(this);
		findViewById(R.id.btnClear).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private int clickCount=0;
	private long lastClickTime=0;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
//		if(clickCount++<1) Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
//		else finish();
		if(lastClickTime<=0){
			Toast.makeText(this, "Press again to quit", Toast.LENGTH_LONG).show();
			lastClickTime=System.currentTimeMillis();
		}else{
			long currentClickTime=System.currentTimeMillis();
			if(currentClickTime-lastClickTime<1000){
				finish();
			}else{
				Toast.makeText(this, "Press again to quit", Toast.LENGTH_LONG).show();
				lastClickTime=currentClickTime;
			}
		}	
	}
	
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn0:
			tvScreen.append("0");
			break;
		case R.id.btn1:
			tvScreen.append("1");
			break;
		case R.id.btn2:
			tvScreen.append("2");
			break;
		case R.id.btn3:
			tvScreen.append("3");
			break;
		case R.id.btn4:
			tvScreen.append("4");
			break;
		case R.id.btn5:
			tvScreen.append("5");
			break;
		case R.id.btn6:
			tvScreen.append("6");
			break;
		case R.id.btn7:
			tvScreen.append("7");
			break;
		case R.id.btn8:
			tvScreen.append("8");
			break;
		case R.id.btn9:
			tvScreen.append("9");
			break;
		case R.id.btnAdd:
			Double temp1=Double.parseDouble(tvScreen.getText().toString());
			items.add(new Item(temp1, Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.ADD));
			tvScreen.setText("");
			break;
		case R.id.btnSub:
			Double temp2=Double.parseDouble(tvScreen.getText().toString());
			items.add(new Item(temp2, Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.SUB));
			tvScreen.setText("");
			break;
		case R.id.btnMult:
			Double temp3=Double.parseDouble(tvScreen.getText().toString());
			items.add(new Item(temp3, Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.MULT));
			tvScreen.setText("");
			break;
		case R.id.btnDiv:
			Double temp4=Double.parseDouble(tvScreen.getText().toString());
			items.add(new Item(temp4, Types.NUM));
			checkAndCompute();
			items.add(new Item(0, Types.DIV));
			tvScreen.setText("");
			break;
		case R.id.btnClear:
			tvScreen.setText("");
			items.clear();
			break;
		case R.id.btnResult:
			Double temp5=0.0;
			try{
				temp5=Double.parseDouble(tvScreen.getText().toString());
				items.add(new Item(temp5, Types.NUM));
				checkAndCompute();
				tvScreen.setText(items.get(0).value+"");
				items.clear();
			}catch(Exception exception){
				Toast.makeText(this, "Problem, please input valid number..", Toast.LENGTH_SHORT).show();
			}
			break;


		default:
			break;
		}
	}

	public void checkAndCompute(){
		if(items.size()>=3){
			double a=items.get(0).value;
			double b=items.get(2).value;
			int opt=items.get(1).type;
			items.clear();
			
			switch (opt) {
			case Types.ADD:
				items.add(new Item(a+b, Types.NUM));
				break;
			case Types.SUB:
				items.add(new Item(a-b, Types.NUM));
				break;
			case Types.MULT:
				items.add(new Item(a*b, Types.NUM));
				break;
			case Types.DIV:
				items.add(new Item(a/b, Types.NUM));
				break;

			default:
				break;
			}
		}
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.About:
			new AlertDialog.Builder(this).setTitle("About").setMessage("This is one of my Android exercise, together with the basic calculating algorithm, realized the functions of calculating").setPositiveButton("Close", null).show();
			break;
		
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
