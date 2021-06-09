package com.gon.coin.demotradingcoin.api;

import com.gon.coin.demotradingcoin.service.UpbitCoinService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@Controller
@RequiredArgsConstructor
public class UpbitCoinApiController {
    private final UpbitCoinService upbitCoinService;

}