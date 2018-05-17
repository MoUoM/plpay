package com.javen.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;


public class XmlUtil<T> {

	private static final String ROOT_START = "<root>";
	private static final String ROOT_END = "</root>";

	public Object getObjectToXml(List<T> list) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<?xml version=\"1.0\" encoding=\"" + "UTF-8" + "\" ?>");
		stringBuffer.append(ROOT_START);
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				try {
					String name = fields[j].getName();
					java.lang.reflect.Method method = object.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1), new Class[] {});
					Object result = method.invoke(object, new Object[] {});

					if (result != null && result.toString() != "") {

						stringBuffer.append("<" + name);
						stringBuffer.append(">");

						// stringBuffer.append(result);

						if ("sign".equals(name) || "goods_name".equals(name) || "goodsTitle".equals(name) 
								|| "service_stime".equals(name) || "service_etime".equals(name)) {
							stringBuffer.append(result);
//							stringBuffer.append("![CDATA[" + result + "]]");
						} else {
							stringBuffer.append(result);
						}
						// if (result.toString().matches("^[0-9.]$")) {
						// stringBuffer.append(result);
						// } else {
						// stringBuffer.append("![CDATA[" + result + "]]");
						// }
						stringBuffer.append("</" + name);
						stringBuffer.append(">");

					}
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		}
		stringBuffer.append(ROOT_END);
		return stringBuffer;
	}
	
	public Map<String, String> getObjectToXmlWithOutRoot(List<T> list) {
		StringBuffer stringBuffer = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		stringBuffer.append("<xml>");
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				try {
					String name = fields[j].getName();
					java.lang.reflect.Method method = object.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1), new Class[] {});
					Object result = method.invoke(object, new Object[] {});

					if (result != null && result.toString() != "") {

						stringBuffer.append("<" + name);
						stringBuffer.append(">");

						if ("goods_name".equals(name) || "goodsTitle".equals(name) 
								|| "service_stime".equals(name) || "service_etime".equals(name)) {
							stringBuffer.append(result);
						} else {
							stringBuffer.append(result);
						}
						stringBuffer.append("</" + name);
						stringBuffer.append(">");
						map.put(name, result.toString());
					}
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		}
		stringBuffer.append("</xml>");
		return map;
	}

	public Map<String, String> xmlElementsToMap(String xmlDoc) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		StringReader read = new StringReader(xmlDoc);
		InputSource source = new InputSource(read);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(source);
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			String name = element.getName();
			String value = element.getText();
			map.put(name, value);
		}
		return map;
	}

	/*
	 * ascii码从小到大排序
	 */
	public List<String> ColnumSort(List<String> listString) {
		List<String> list = new ArrayList<String>();
		List<String> lists = new ArrayList<String>();
		list.add("retcode");
		list.add("trade_state");
		Map<String, String> map = new HashMap<String, String>();
		List<Integer> ints = new ArrayList<Integer>();
		int s = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length(); j++) {
				int ss = list.get(i).charAt(j);
				s += ss;
			}
			ints.add(s);
			map.put("" + s + "", list.get(i));
		}
		Arrays.sort(ints.toArray());
		for (int i = 0; i < ints.size(); i++) {
			lists.add(map.get("" + ints.get(i) + ""));
		}
		return lists;

	}
	
	/**
	 * 解析XML
	 * @param xml_obj
	 * @return
	 * @throws ServiceException 
	 */
	public static Map<String, String> parserXml(String xml_obj, List<String> ele) throws ServiceException {
		// TODO Auto-generated method stub
		String result = null;
		Map<String, String> map = new HashMap<String, String>();
		try{
			Document document = DocumentHelper.parseText(xml_obj);
			//获取根节点元素对象  
	        Element node = document.getRootElement();  
	        //获取element中的元素节点对象  
	        for (String string : ele) {
	        	Element element = node.element(string);  
				if(element != null){
					result = element.getText();
					map.put(string, result);
				}
			}
		}catch(Exception ex){
			throw new ServiceException(String.format("XML解析异常"));
		}
		return map;
	}
}
