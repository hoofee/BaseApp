package com.hoofee.everything.main.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {
	
	/***
	 * xml读取转换成model
	 * @param fis
	 * @param clazz
	 * @return model
	 * @serialData :
	 <arrays>
	    <array name="sex">
	         <item value="1">男</item>
	        <item value="0">女</item>
	    </array>
	    <array name="bloodtype">
	        <item value="A">A型</item>
	        <item value="B">B型</item>
	    </array>
	</arrays>
	 * @example: 
	 *   XMLAttributesModel model=(XMLAttributesModel)XmlUtils.xml2List(isStream, XMLAttributesModel.class);
		 Iterator<Map<String,String>> it=model.getSex().listIterator();
		 while(it.hasNext()){
			Map<String,String> map=it.next();
			Set<String> set=map.keySet();
			Iterator<String> itset=set.iterator();
			while(itset.hasNext()){
				String key=itset.next();
				String value=map.get(key);
				KeyValueModel keymodel=new KeyValueModel();
				keymodel.key=key;
				keymodel.value=value;
				keyvaluelist.add(keymodel);
			}
		 }
	 */
	public static Object xml2List(InputStream fis,Class clazz){
		Field[] fields=clazz.getDeclaredFields();
		Object obj = null;
		try {
			obj=clazz.newInstance();
		}
		catch (InstantiationException e1) {
			e1.printStackTrace();
		}
		catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		List[] listArr=new List[fields.length];
		
		List<Map<String,String>> list=null;
		Map<String,String> map=null;
		int flag=0;
		String fieldName="";
		String key="";
		String value="";
				
		XmlPullParser parser = Xml.newPullParser();
		try{
			// 指定解析的文件和编码格式
			parser.setInput(fis, "utf-8");
			int eventType = parser.getEventType(); 		// 获得事件类型
			String tagName="";
			while(eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
					case XmlPullParser.START_TAG: 		// 当前等于开始节点  <array>
						tagName = parser.getName();	// 获得当前节点的名称
						if("array".equals(tagName.trim())){
							list=new ArrayList<Map<String,String>>();
							for(Field field:fields){
								if(field.getName().equals(parser.getAttributeValue(0))){
									fieldName=field.getName();
									map=new HashMap<String, String>();
									break;
								}
							}
						}
						else if(tagName.trim().equals("item")){
							key=parser.getAttributeValue(0);
						}
						break;
					case XmlPullParser.TEXT:
						value=parser.getText().trim();
						if(tagName.trim().equals("item")&&value.length()>0){
							map.put(key, value);
						}
						break;
					case XmlPullParser.END_TAG:		// </item>
						tagName = parser.getName();
						if("array".equals(tagName.trim())){
							list.add(map);
							for(Field field:fields){
								if(field.getName().equals(fieldName)){
									field.set(obj, list);
								}
							}
						}
						break;
					default:
						break;
					}
					eventType = parser.next();		// 获得下一个事件类型
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
