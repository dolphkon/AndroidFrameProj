package com.example.frameproj.Rxjava2.threadcontrol;
import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.frameproj.R;
import com.example.frameproj.base.BaseActivity;
import com.yechaoa.yutils.LogUtil;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.internal.schedulers.NewThreadWorker;


/**
 * Rxjava2的线程调度
 * */
public class RxjavaThreadControlActivity extends BaseActivity {

    private static final String TAG = "RxjavaThreadControlActivity";
    @BindView(R.id.observerRxjavaDefaultWorkThread)
    Button observerRxjavaDefaultWorkThread;
    @BindView(R.id.schedulers_once)
    Button schedulersOnce;
    @BindView(R.id.schedulers_twice_subscribeOn)
    Button schedulersTwiceSubscribeOn;
    @BindView(R.id.schedulers_twice_observeOn)
    Button schedulersTwiceObserveOn;
    @BindView(R.id.schedulers_trampoline)
    Button schedulersTrampoline;
    @BindView(R.id.schedulers_single)
    Button schedulersSingle;
    @BindView(R.id.schedulers_demo)
    Button schedulersDemo;
    @BindView(R.id.schedulers_retrofit)
    Button schedulersRetrofit;
    @BindView(R.id.schedulers_deeplearn)
    Button schedulersDeeplearn;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getResourceId() {
        return R.layout.activity_rxjava_thread_control;
    }

    @OnClick({R.id.observerRxjavaDefaultWorkThread, R.id.schedulers_once, R.id.schedulers_twice_subscribeOn, R.id.schedulers_twice_observeOn, R.id.schedulers_trampoline, R.id.schedulers_single, R.id.schedulers_demo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.observerRxjavaDefaultWorkThread:
                RxjavaDefaultWorkThread();     //观察者和被观察者默认的工作线程
                break;
            case R.id.schedulers_once:
                schedulers_once();   //使用一次subscribeOn()和observeOn()

                break;
            case R.id.schedulers_twice_subscribeOn:
                schedulers_twice_subscribeOn(schedulersTwiceSubscribeOn);  //调用一次subscribeOn()和两次observeOn()
                break;
            case R.id.schedulers_twice_observeOn:
                schedulers_twice_observeOn();


                break;
            case R.id.schedulers_trampoline:
                schedulers_trampoline(schedulersTwiceSubscribeOn);

                break;
            case R.id.schedulers_single:

                break;
            case R.id.schedulers_demo:

                break;
        }
    }
/**
 * 调用一次subscribeOn()和两次observeOn()
 * */
    private void schedulers_twice_observeOn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                LogUtil.d("上游被观察者所在IO线程为:"+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        LogUtil.d("下游观察者线程为"+Thread.currentThread().getName());
                        return 2*integer+"";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.d("onNext():下游观察者所在线程为"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {

                    }
                });








    }

    /**：当其它排队的任务完成后，在当前线程排队开始执行，FIFO。
 * */
    public void schedulers_trampoline(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @SuppressLint("LongLogTag")
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 4; i++) {
                    Log.e(TAG, "上游被观察者Observable在" + Thread.currentThread().getName() + "线程, 生产一个事件: " + i );
                    SystemClock.sleep(500);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.trampoline())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onNext(Integer integer) {
                        SystemClock.sleep(1000);
                        Log.d(TAG, "下游观察者Observer在" + Thread.currentThread().getName() + "线程接收响应到了事件: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void schedulers_twice_subscribeOn(View view){
        //使用两次subscribeOn() 和 一次observeOn()的线程调度
        //通过两次设置subscribeOn()发射和处理数据在不同的线程，但是最终起作用的是第一次设置的工作线程
        //由此可以得出多次设置subscribeOn()设置被观察者Observable工作的线程最终起作用的是第一次设置的线程
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @SuppressLint("LongLogTag")
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 4; i++) {
                    Log.d(TAG, "上游被观察者Observable在" + Thread.currentThread().getName() + "线程, 生产一个事件: " + i );
                    SystemClock.sleep(1000);
                    e.onNext(i);
                }
                e.onComplete();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<Integer, Integer>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "map操作符在" + Thread.currentThread().getName() + "线程处理了事件: " + integer);
                        return integer * 2;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "下游观察者Observer在IO线程" + Thread.currentThread().getName() + "接收响应到了事件: " + integer);
                    }
                });
    }


    /**
     * 使用一次SubscribeOn
     * */
    private void schedulers_once() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @SuppressLint("LongLogTag")
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 4; i++) {
                    Log.e(TAG, "上游被观察者Observable在子线程为" + Thread.currentThread().getName() + "生产一个事件: " + i );
                    emitter.onNext(i);
                    SystemClock.sleep(3000);
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Integer>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @SuppressLint("LongLogTag")
              @Override
              public void onNext(Integer integer) {
                  Log.d(TAG, "下游观察者Observer在主线程:" + Thread.currentThread().getName()+"onNext事件中接收了："+integer);

              }

              @Override
              public void onError(Throwable e) {

              }

              @SuppressLint("LongLogTag")
              @Override
              public void onComplete() {
                  Log.d(TAG, "onComplete: 下游观察者接收完毕");
              }
          });
    }
    /**
 * 默认情况下观察者和被观察者都是工作在主线程
 * */
    @SuppressLint("LongLogTag")
    private void RxjavaDefaultWorkThread() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }
}
