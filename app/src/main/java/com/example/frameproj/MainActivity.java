package com.example.frameproj;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameproj.base.BaseActivity;
import com.example.frameproj.bean.LoginResponse;
import com.example.frameproj.bean.RegisterResp;
import com.example.frameproj.utils.ApiService;
import com.yechaoa.yutils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    private String username;
    private String password;
    private String repassword;


    @Override
    public void initView() {

    }

    @Override
    public void initData() {


    }

    @Override
    public int getResourceId() {
        return R.layout.activity_main;
    }

    private void login() {
        //1.创建retrofit对象注册
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建请求接口对象
        ApiService api = retrofit.create(ApiService.class);

        //使用接口请求对象创建call对象
        Observable<RegisterResp> call = api.register(username, password, repassword);
         call.subscribeOn(Schedulers.io())
             .flatMap(new Function<RegisterResp, ObservableSource<LoginResponse>>() {
            @Override
            public ObservableSource<LoginResponse> apply(RegisterResp registerResp) throws Exception {
              if (-1==registerResp.getErrorCode()){
                  return Observable.error(new Throwable("用户名已经注册"));
              }else if ( -1001==registerResp.getErrorCode()){
                  return Observable.error(new Throwable("注册失败"));
              }

                return api.login(username, password);
            }
        }).observeOn(AndroidSchedulers.mainThread())
         .subscribe(new Observer<LoginResponse>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(LoginResponse loginResponse) {
                 Toast.makeText(MainActivity.this, "恭喜你登录成功", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onError(Throwable e) {
                 if ("注册失败".equals(e.getMessage())){
                     Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                 }else if (("用户名已经注册".equals(e.getMessage()))){
                     Toast.makeText(MainActivity.this, "用户名已经注册", Toast.LENGTH_SHORT).show();
                     }
                 }

             @Override
             public void onComplete() {

             }
         });



    }


    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                repassword = etRepassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                    Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(repassword)) {
                        login();
                    } else {
                        Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

}
