package com.gon.coin.demotradingcoin.api;

import com.gon.coin.demotradingcoin.service.UpbitCoinService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class UpbitCoinApiController {
    private final UpbitCoinService upbitCoinService;
    private static String categoryURL="https://api.upbit.com/v1/market/all";

    @GetMapping("/api/v1/getcoinname")
    public String getdatas(){
        return upbitCoinService.callURL(categoryURL);
    }
}