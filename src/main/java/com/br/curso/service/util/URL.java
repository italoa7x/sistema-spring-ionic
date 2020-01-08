package com.br.curso.service.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public abstract class URL {

	public static List<Integer> decodeIntList(String cat){
		String[] decode = cat.split(",");
		List<Integer> listInteger = new ArrayList<Integer>();
		for(String x : decode) {
			listInteger.add(Integer.parseInt(x));
		}
		return listInteger;
	}
	
	public static String decodeParam(String n) {
		try {
			return URLDecoder.decode(n, "UTF=8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
