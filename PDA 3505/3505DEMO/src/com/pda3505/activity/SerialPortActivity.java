package com.pda3505.activity;

import java.lang.reflect.Field;

import com.pda3505.R;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.serialport.api.SerialPortClass;
import android.serialport.api.SerialPortDataReceivedOther;
import android.serialport.api.SerialPortDataReceivedPrint;
import android.serialport.api.SerialPortDataReceivedScan;
import android.serialport.api.SerialPortHelper;
import android.serialport.api.SerialPortParam;
import android.serialport.api.SerialPortClass.SERIALPORT;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SerialPortActivity extends Activity {
	private static final String TAG = "MainActivity";
	SerialPortHelper serialport = new SerialPortHelper();
	private EditText revText;
	StringBuffer text;
	private EditText editText_send;
	private Button btn_send;
	CheckBox checkbox_rec, checkbox_send;
	TextView textViewInfo, textViewCount;
	int countRev = 0, countSend = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serialport);

		text = new StringBuffer();
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		revText = (EditText) findViewById(R.id.tvRev);
		setEditTextReadOnly(revText);

		checkbox_rec = (CheckBox) findViewById(R.id.checkBox_rec);
		checkbox_rec.setChecked(true);
		checkbox_send = (CheckBox) findViewById(R.id.checkBox_send);
		checkbox_send.setChecked(true);

		textViewCount = (TextView) findViewById(R.id.textViewCount);

		editText_send = (EditText) findViewById(R.id.editText_send);
		editText_send.setText("81");
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				byte[] buffer = null;
				if (checkbox_send.isChecked()) {
					buffer = hexStringToBytes(editText_send.getText()
							.toString());
					serialport.Write(buffer);
					countSend += buffer.length;
					textViewCount.setText("R:" + countRev + ",S:" + countSend);
				} else {
					serialport.Write(editText_send.getText().toString());
				}
			}
		});

		getOverflowMenu();

		initSerialPortParam();

		btn_send.setEnabled(false);
		serialport.setOnserialportDataReceivedOhter(new SerialPortDataReceivedOther() {			
			@Override
			public void onDataReceivedListener(final byte[] buffer, int size) {
				if (text.length() > 3000) {
					//text.delete(0, text.length() - 1000);
					text=new StringBuffer();
				}
				revText.post(new Runnable() {
					@Override
					public void run() {
						if (checkbox_rec.isChecked()) {
							text.append(byteToString(buffer, buffer.length));
						} else {
							text.append(new String(buffer, 0, buffer.length));
						}
						revText.setText(text);
						revText.setSelection(revText.length());
						countRev += buffer.length;
						textViewCount.setText("R:" + countRev + ",S:"
								+ countSend);
					}
				});
			}
		});
	}

	private void initSerialPortParam() {
		try {
			SharedPreferences sp = getSharedPreferences(
					"com.example.serialtest_preferences",
					Application.MODE_PRIVATE);

			SerialPortParam.Path = sp.getString("path", "");
			SerialPortParam.Baudrate = Integer.parseInt(sp.getString(
					"baudValues", ""));
			SerialPortParam.DataBits = Integer.parseInt(sp.getString(
					"databits", ""));
			SerialPortParam.StopBits = Integer.parseInt(sp
					.getString("stop", ""));
			SerialPortParam.Parity = Integer.parseInt(sp.getString(
					"parityvalue", ""));
			SerialPortParam.SpaceTime = Integer.parseInt(sp.getString(
					"spacetime", ""));
			SerialPortParam.Flowcontrol = Integer.parseInt(sp.getString(
					"flowcontrol", ""));

			textViewInfo
					.setText(SerialPortParam.Path
							+ ","
							+ SerialPortParam.Baudrate
							+ ","
							+ SerialPortParam.DataBits
							+ ","
							+ SerialPortParam.StopBits
							+ ","
							+ intTochar(SerialPortParam.Parity)
							+ ","
							+ SerialPortSettingActivity.flowcontrol[SerialPortParam.Flowcontrol]
							+ "," + SerialPortParam.SpaceTime);
		} catch (Exception ex) {
			Log.e(TAG, ex.getMessage());
		}
	}

	private void getOverflowMenu() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, R.string.str_menu_serialport_open);
		menu.add(0, 2, 2, R.string.str_menu_serialport_set);
		menu.add(0, 3, 3, R.string.str_menu_serialport_clean);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		if (item.getItemId() == 1) {
			if (item.getTitle().equals(
					getResources()
							.getString(R.string.str_menu_serialport_close))) {
				item.setTitle(R.string.str_menu_serialport_open);
				serialport.CloseSerialPort();
				btn_send.setEnabled(false);
			} else {
				item.setTitle(R.string.str_menu_serialport_close);
				
				if(!SerialPortClass.serialPortName.equals(SERIALPORT.comOutSide))
				{
					SerialPortClass.serialPortName=SERIALPORT.comOutSide;
					SerialPortClass.serialPortHelper.Write(SerialPortClass.bt_outside);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				if (serialport.OpenSerialPort(SerialPortParam.Path,
						SerialPortParam.Baudrate)) {

					textViewInfo
							.setText(SerialPortParam.Path
									+ ","
									+ SerialPortParam.Baudrate
									+ ","
									+ SerialPortParam.DataBits
									+ ","
									+ SerialPortParam.StopBits
									+ ","
									+ intTochar(SerialPortParam.Parity)
									+ ","
									+ SerialPortSettingActivity.flowcontrol[SerialPortParam.Flowcontrol]
									+ "," + SerialPortParam.SpaceTime);
					btn_send.setEnabled(true);
				}
			}
		} else if (item.getItemId() == 2) {
			Intent intent = new Intent();
			intent.setClass(SerialPortActivity.this,
					SerialPortSettingActivity.class);
			startActivityForResult(intent, 1);
		} else if (item.getItemId() == 3) {
			countRev = 0;
			countSend = 0;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		if (serialport != null) {
			serialport.CloseSerialPort();
		}
		super.onDestroy();
	}

	public static String byteToString(byte[] b, int size) {
		byte high, low;
		byte maskHigh = (byte) 0xf0;
		byte maskLow = 0x0f;

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < size; i++) {
			high = (byte) ((b[i] & maskHigh) >> 4);
			low = (byte) (b[i] & maskLow);
			buf.append(findHex(high));
			buf.append(findHex(low));
			buf.append(" ");
		}

		return buf.toString();
	}

	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;

		if ((0 <= t) && (t <= 9)) {
			return (char) (t + '0');
		}

		return (char) (t - 10 + 'A');
	}

	/**
	 * ���ַ���ʽ��ʾ��ʮ�������ת��Ϊbyte����
	 */
	public static byte[] hexStringToBytes(String hexString) {
		hexString = hexString.toLowerCase();
		String[] hexStrings = hexString.split(" ");
		byte[] bytes = new byte[hexStrings.length];
		for (int i = 0; i < hexStrings.length; i++) {
			char[] hexChars = hexStrings[i].toCharArray();
			bytes[i] = (byte) (charToByte(hexChars[0]) << 4 | charToByte(hexChars[1]));
		}
		return bytes;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789abcdef".indexOf(c);
	}

	public static void setEditTextReadOnly(EditText view) {
		if (view instanceof android.widget.EditText) {
			view.setCursorVisible(false); // ����������еĹ�겻�ɼ�
			view.setFocusable(false); // �޽���
			view.setFocusableInTouchMode(false); // ����ʱҲ�ò�������
		}
	}

	public static char intTochar(int backnum) {
		char strChar = (char) backnum;
		return strChar;
	}
}
