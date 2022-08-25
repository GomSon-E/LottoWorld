package org.techtown.lottoworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RequestQueue requestQueue;
    JsonObject jsonObject;
    String num; // 로또 회차번호
    String[] nums = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6", "bnusNo"}; // 당첨번호 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest zzzzzz;
        zzzzzz = requestNums();
        if(zzzzzz != null) {
            Log.d("태그","리퀘스트가 널은 아님");
            zzzzzz.setShouldCache(false);
            requestQueue.add(zzzzzz);
        }
    }

    public StringRequest requestNums() {
        num = "1029";

        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + num; // api 링크를 가져와서 url 변수에 할당

        // 문자열을 주고 받기위해 사용하는 요청 객체
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonObject = (JsonObject) JsonParser.parseString(response);
                String data = num + "회차 당첨번호 : ";
                for (int i = 0; i < nums.length - 1; i++) {
                    data += jsonObject.get(nums[i]) + ", ";
                }
                data += " 보너스 : " + jsonObject.get(nums[nums.length - 1]);
                tv.setText(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };
        return request;
    }
}