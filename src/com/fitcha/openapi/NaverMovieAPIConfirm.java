package com.fitcha.openapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NaverMovieAPIConfirm {
	public static void main(String[] args) {

		String id = SecretKey.getNaverMovieId();
		String secret = SecretKey.getNaverMovieKey();

		try {
			NaverMovieAPI nmapi = new NaverMovieAPI();
			String url = URLEncoder.encode("애니메이션", "UTF-8");

			String response = nmapi.search(id, secret, url);
			System.out.println(response);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
