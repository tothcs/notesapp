package com.github.tothcs.mock.interceptors;

import android.net.Uri;

import com.github.tothcs.network.NetworkConfig;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.github.tothcs.mock.interceptors.MockHelper.makeResponse;

public class NoteMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "note") && request.method().equals("POST")) {
            responseString = "";
            responseCode = 200;
		} else if (uri.getPath().contains(NetworkConfig.ENDPOINT_PREFIX + "note/") && request.method().equals("PUT")) {
            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().contains(NetworkConfig.ENDPOINT_PREFIX + "note/") && request.method().equals("DELETE")) {
            responseString = "";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
