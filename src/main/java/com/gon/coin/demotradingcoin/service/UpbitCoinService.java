package com.gon.coin.demotradingcoin.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gon.coin.demotradingcoin.domain.upbitcoin.dto.UpbitCoinSaveNameDto;
import com.gon.coin.demotradingcoin.repository.UpbitCoinRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UpbitCoinService {
    private final UpbitCoinRepository upbitCoinRepository;
    public String callURLToString(String myURL) {
        //System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        //error : Caused by: javax.net.ssl.SSLPeerUnverifiedException: Hostname not verified:
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //특정 hostname만 승인을 해주는 형태
                return true;
            }
        };
        //
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:"+ myURL, e);
        }
        return sb.toString();
    }
    @Transactional
    public void saveCoinNameURL(String url){
        try{
            URL postUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection)postUrl.openConnection();
            Object obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));
            JSONArray jObj = (JSONArray) obj;
            ObjectMapper om = new ObjectMapper();
            System.out.println("jObj.size() = " + jObj.size());
            for (int i = 0; i < jObj.size(); i++) {
                String data=jObj.get(i).toString();
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(data);
                String market=(String) jsonObject.get("market");
                String koreanName=(String)jsonObject.get("korean_name");
                String englishName=(String) jsonObject.get("english_name");
                //System.out.println("market = " + market +" koreanName:"+koreanName+" englishName:"+englishName);
                UpbitCoinSaveNameDto upbitCoinSaveNameDto=new UpbitCoinSaveNameDto(market,koreanName,englishName);
                upbitCoinRepository.save(upbitCoinSaveNameDto.toEntity());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
