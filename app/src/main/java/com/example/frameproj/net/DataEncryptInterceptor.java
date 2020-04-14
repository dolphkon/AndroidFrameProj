package com.example.frameproj.net;


import com.example.frameproj.BuildConfig;
import com.yechaoa.yutils.LogUtil;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


public class DataEncryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        RequestBody oldRequestBody = request.body();

        String oldBodyStr = "";
        if (oldRequestBody != null) {
            Buffer requestBuffer = new Buffer();
            oldRequestBody.writeTo(requestBuffer);
            oldBodyStr = requestBuffer.readUtf8();
            requestBuffer.close();
        }
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        if (BuildConfig.DEBUG) {
            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        if(body.encodedName(i)!=null&&body.encodedName(i).equals("common")){
                            sb.append("\""+body.encodedName(i)+"\"" + ":" + body.encodedValue(i) + ",");
                        }else{
                            sb.append("\""+body.encodedName(i)+"\"" + ":\"" + body.encodedValue(i) + "\",");
                        }
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    LogUtil.e("请求：\n" + request.url() + "\n{" + URLDecoder.decode(sb.toString(), "utf-8") + "}");
                } else {
                    LogUtil.e("请求：\n" + request.url()+","+oldBodyStr);
                }
            } else {
                LogUtil.e(String.format("请求：\n%s\n%s",
                        request.url(), request.headers()));
            }
        }

        //响应
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        LogUtil.e("响应：\n" + responseBody.string() + "");
        return response;
    }
}
