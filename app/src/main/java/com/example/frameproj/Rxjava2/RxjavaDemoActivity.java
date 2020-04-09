package com.example.frameproj.Rxjava2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.frameproj.R;
import com.example.frameproj.Rxjava2.threadcontrol.RxjavaThreadControlActivity;
import com.example.frameproj.base.BaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxjavaDemoActivity extends BaseActivity {


    private static final String TAG = "RxjavaDemoActivity";
    @BindView(R.id.btn_basic)
    Button btnBasic;
    @BindView(R.id.chain)
    Button chain;
    @BindView(R.id.break_connect)
    Button breakConnect;
    @BindView(R.id.rxjava_threadcontrol)
    Button rxjavaThreadcontrol;
    @BindView(R.id.rxjava_backpressure)
    Button rxjavaBackpressure;
    @BindView(R.id.rxjava_simpleobservable)
    Button rxjavaSimpleobservable;
    @BindView(R.id.rxjava_compose)
    Button rxjavaCompose;
    @BindView(R.id.rxjava_hotandcoldObservable)
    Button rxjavaHotandcoldObservable;
    @BindView(R.id.rxjava_subjectandprocessor)
    Button rxjavaSubjectandprocessor;
    @BindView(R.id.rxjava_usedemo)
    Button rxjavaUsedemo;
    @BindView(R.id.rxbus_demo)
    Button rxbusDemo;
    @BindView(R.id.create)
    Button create;
    @BindView(R.id.quick_create_just)
    Button quickCreateJust;
    @BindView(R.id.quick_create_from)
    Button quickCreateFrom;
    @BindView(R.id.quick_create_extra)
    Button quickCreateExtra;
    @BindView(R.id.quick_create_defer)
    Button quickCreateDefer;
    @BindView(R.id.quick_create_timer)
    Button quickCreateTimer;
    @BindView(R.id.quick_create_interval)
    Button quickCreateInterval;
    @BindView(R.id.quick_create_intervalrange)
    Button quickCreateIntervalrange;
    @BindView(R.id.quick_create_range)
    Button quickCreateRange;
    @BindView(R.id.tranformer_map)
    Button tranformerMap;
    @BindView(R.id.tranformer_flatMap)
    Button tranformerFlatMap;
    @BindView(R.id.tranformer_concatMap)
    Button tranformerConcatMap;
    @BindView(R.id.tranformer_buffer)
    Button tranformerBuffer;
    @BindView(R.id.tranformer_switchmap)
    Button tranformerSwitchmap;
    @BindView(R.id.tranformer_flatmapiterable)
    Button tranformerFlatmapiterable;
    @BindView(R.id.tranformer_concatmapeager)
    Button tranformerConcatmapeager;
    @BindView(R.id.tranformer_mapdifferent)
    Button tranformerMapdifferent;
    @BindView(R.id.tranformer_scan)
    Button tranformerScan;
    @BindView(R.id.tranformer_groupby)
    Button tranformerGroupby;
    @BindView(R.id.tranformer_window)
    Button tranformerWindow;
    @BindView(R.id.conbine_concat)
    Button conbineConcat;
    @BindView(R.id.conbine_merge)
    Button conbineMerge;
    @BindView(R.id.conbine_delayError)
    Button conbineDelayError;
    @BindView(R.id.conbine_zip)
    Button conbineZip;
    @BindView(R.id.conbine_combineLatest)
    Button conbineCombineLatest;
    @BindView(R.id.conbine_combineLatestDelayError)
    Button conbineCombineLatestDelayError;
    @BindView(R.id.conbine_reduce)
    Button conbineReduce;
    @BindView(R.id.conbine_collect)
    Button conbineCollect;
    @BindView(R.id.conbine_startwith_startwitharray)
    Button conbineStartwithStartwitharray;
    @BindView(R.id.conbine_count)
    Button conbineCount;
    @BindView(R.id.func_subscribe)
    Button funcSubscribe;
    @BindView(R.id.func_delay)
    Button funcDelay;
    @BindView(R.id.func_do)
    Button funcDo;
    @BindView(R.id.func_errorreturn)
    Button funcErrorreturn;
    @BindView(R.id.func_errorResumeNext)
    Button funcErrorResumeNext;
    @BindView(R.id.func_retry)
    Button funcRetry;
    @BindView(R.id.func_retryUntil)
    Button funcRetryUntil;
    @BindView(R.id.func_retryWhen)
    Button funcRetryWhen;
    @BindView(R.id.func_repeat)
    Button funcRepeat;
    @BindView(R.id.func_repeatWhen)
    Button funcRepeatWhen;
    @BindView(R.id.func_repeatUntil)
    Button funcRepeatUntil;
    @BindView(R.id.filter_filter)
    Button filterFilter;
    @BindView(R.id.filter_ofType)
    Button filterOfType;
    @BindView(R.id.filter_skip_skipLast)
    Button filterSkipSkipLast;
    @BindView(R.id.filter_distinct_distinctUntilChanged)
    Button filterDistinctDistinctUntilChanged;
    @BindView(R.id.filter_take_takeLast)
    Button filterTakeTakeLast;
    @BindView(R.id.filter_throttlefirst_throttlelast)
    Button filterThrottlefirstThrottlelast;
    @BindView(R.id.filter_sample)
    Button filterSample;
    @BindView(R.id.filter_throttleWithtimeout_debounce)
    Button filterThrottleWithtimeoutDebounce;
    @BindView(R.id.filter_firstElement_lastElement)
    Button filterFirstElementLastElement;
    @BindView(R.id.filter_elementAt)
    Button filterElementAt;
    @BindView(R.id.filter_elementAtError)
    Button filterElementAtError;
    @BindView(R.id.filter_ignoreelements)
    Button filterIgnoreelements;
    @BindView(R.id.condition_all)
    Button conditionAll;
    @BindView(R.id.condition_takeWhile)
    Button conditionTakeWhile;
    @BindView(R.id.condition_sikpWhile)
    Button conditionSikpWhile;
    @BindView(R.id.condition_takeUntil)
    Button conditionTakeUntil;
    @BindView(R.id.condition_skipUntil)
    Button conditionSkipUntil;
    @BindView(R.id.condition_sequenceEqual)
    Button conditionSequenceEqual;
    @BindView(R.id.condition_contains)
    Button conditionContains;
    @BindView(R.id.condition_isEmpty)
    Button conditionIsEmpty;
    @BindView(R.id.condition_amb)
    Button conditionAmb;
    @BindView(R.id.condition_defaultIfEmpty)
    Button conditionDefaultIfEmpty;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getResourceId() {
        return R.layout.activity_rxjava_demo;
    }
    @OnClick({R.id.btn_basic, R.id.chain, R.id.break_connect, R.id.rxjava_threadcontrol, R.id.rxjava_backpressure, R.id.rxjava_simpleobservable, R.id.rxjava_compose, R.id.rxjava_hotandcoldObservable, R.id.rxjava_subjectandprocessor, R.id.rxjava_usedemo, R.id.rxbus_demo, R.id.create, R.id.quick_create_just, R.id.quick_create_from, R.id.quick_create_extra, R.id.quick_create_defer, R.id.quick_create_timer, R.id.quick_create_interval, R.id.quick_create_intervalrange, R.id.quick_create_range, R.id.tranformer_map, R.id.tranformer_flatMap, R.id.tranformer_concatMap, R.id.tranformer_buffer, R.id.tranformer_switchmap, R.id.tranformer_flatmapiterable, R.id.tranformer_concatmapeager, R.id.tranformer_mapdifferent, R.id.tranformer_scan, R.id.tranformer_groupby, R.id.tranformer_window, R.id.conbine_concat, R.id.conbine_merge, R.id.conbine_delayError, R.id.conbine_zip, R.id.conbine_combineLatest, R.id.conbine_combineLatestDelayError, R.id.conbine_reduce, R.id.conbine_collect, R.id.conbine_startwith_startwitharray, R.id.conbine_count, R.id.func_subscribe, R.id.func_delay, R.id.func_do, R.id.func_errorreturn, R.id.func_errorResumeNext, R.id.func_retry, R.id.func_retryUntil, R.id.func_retryWhen, R.id.func_repeat, R.id.func_repeatWhen, R.id.func_repeatUntil, R.id.filter_filter, R.id.filter_ofType, R.id.filter_skip_skipLast, R.id.filter_distinct_distinctUntilChanged, R.id.filter_take_takeLast, R.id.filter_throttlefirst_throttlelast, R.id.filter_sample, R.id.filter_throttleWithtimeout_debounce, R.id.filter_firstElement_lastElement, R.id.filter_elementAt, R.id.filter_elementAtError, R.id.filter_ignoreelements, R.id.condition_all, R.id.condition_takeWhile, R.id.condition_sikpWhile, R.id.condition_takeUntil, R.id.condition_skipUntil, R.id.condition_sequenceEqual, R.id.condition_contains, R.id.condition_isEmpty, R.id.condition_amb, R.id.condition_defaultIfEmpty, R.id.scrollView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_basic:
                rxbasicUse();
                break;
            case R.id.chain:
                rxchainUse();
                break;
            case R.id.break_connect:
                disConnect();
                break;
            case R.id.rxjava_threadcontrol:
                startActivity(new Intent(this, RxjavaThreadControlActivity.class));
                break;
            case R.id.rxjava_backpressure:
                break;
            case R.id.rxjava_simpleobservable:
                break;
            case R.id.rxjava_compose:
                break;
            case R.id.rxjava_hotandcoldObservable:
                break;
            case R.id.rxjava_subjectandprocessor:
                break;
            case R.id.rxjava_usedemo:
                break;
            case R.id.rxbus_demo:
                break;
            case R.id.create:
                break;
            case R.id.quick_create_just:
                break;
            case R.id.quick_create_from:
                break;
            case R.id.quick_create_extra:
                break;
            case R.id.quick_create_defer:
                break;
            case R.id.quick_create_timer:
                break;
            case R.id.quick_create_interval:
                break;
            case R.id.quick_create_intervalrange:
                break;
            case R.id.quick_create_range:
                break;
            case R.id.tranformer_map:
                break;
            case R.id.tranformer_flatMap:
                break;
            case R.id.tranformer_concatMap:
                break;
            case R.id.tranformer_buffer:
                break;
            case R.id.tranformer_switchmap:
                break;
            case R.id.tranformer_flatmapiterable:
                break;
            case R.id.tranformer_concatmapeager:
                break;
            case R.id.tranformer_mapdifferent:
                break;
            case R.id.tranformer_scan:
                break;
            case R.id.tranformer_groupby:
                break;
            case R.id.tranformer_window:
                break;
            case R.id.conbine_concat:
                break;
            case R.id.conbine_merge:
                break;
            case R.id.conbine_delayError:
                break;
            case R.id.conbine_zip:
                break;
            case R.id.conbine_combineLatest:
                break;
            case R.id.conbine_combineLatestDelayError:
                break;
            case R.id.conbine_reduce:
                break;
            case R.id.conbine_collect:
                break;
            case R.id.conbine_startwith_startwitharray:
                break;
            case R.id.conbine_count:
                break;
            case R.id.func_subscribe:
                break;
            case R.id.func_delay:
                break;
            case R.id.func_do:
                break;
            case R.id.func_errorreturn:
                break;
            case R.id.func_errorResumeNext:
                break;
            case R.id.func_retry:
                break;
            case R.id.func_retryUntil:
                break;
            case R.id.func_retryWhen:
                break;
            case R.id.func_repeat:
                break;
            case R.id.func_repeatWhen:
                break;
            case R.id.func_repeatUntil:
                break;
            case R.id.filter_filter:
                break;
            case R.id.filter_ofType:
                break;
            case R.id.filter_skip_skipLast:
                break;
            case R.id.filter_distinct_distinctUntilChanged:
                break;
            case R.id.filter_take_takeLast:
                break;
            case R.id.filter_throttlefirst_throttlelast:
                break;
            case R.id.filter_sample:
                break;
            case R.id.filter_throttleWithtimeout_debounce:
                break;
            case R.id.filter_firstElement_lastElement:
                break;
            case R.id.filter_elementAt:
                break;
            case R.id.filter_elementAtError:
                break;
            case R.id.filter_ignoreelements:
                break;
            case R.id.condition_all:
                break;
            case R.id.condition_takeWhile:
                break;
            case R.id.condition_sikpWhile:
                break;
            case R.id.condition_takeUntil:
                break;
            case R.id.condition_skipUntil:
                break;
            case R.id.condition_sequenceEqual:
                break;
            case R.id.condition_contains:
                break;
            case R.id.condition_isEmpty:
                break;
            case R.id.condition_amb:
                break;
            case R.id.condition_defaultIfEmpty:
                break;
            case R.id.scrollView:
                break;
        }
    }

/** 通过Disposable断开被观察者和观察者之间
 * 的连接
 *
 * 1.Disposable 并不影响上游继续发射事件
 * 2.当 Disposabl.dispose()触发后，下游将不再继续接收事件
 * */
    private void disConnect() {
        Observable.just(1,2,3,4,5,6).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
               mDisposable=d;
                Log.d(TAG, "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext: 接收到的数据是:"+s);
                if (s>3){
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    // rxjava的基本流程使用
    private void rxbasicUse() {

        Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {        // 1.创建 Observable对象
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(" 2020-04-03，rxjava学习之旅开启啦");
                emitter.onNext(" 1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onNext("4");
                emitter.onComplete();
                emitter.onNext(" 1");
                emitter.onNext(" 2");
            }
        });
        Observer<String> mObserver = new Observer<String>() { // 2.创建observer
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
        mObservable.subscribe(mObserver);// 3.开始调用



     //创建被观察者的变种
        /** 1.通过just(T...)直接将传入的参数依次发送出来
         * 特点:发送的事件不可以超过10个以上。
         *
         * */
   Observable<String>observable=Observable.just("JAVA","Kotlin","C++","Python");

        /**
         * 2.通过FromArray(T...)/  拆分成具体对象后依次发射出来
         *特点：和 just() 类似，只不过 fromArray 可以传入多于10个的变量，并且可以传入一个数组。
         * */
        String[] strArray  = {"Hello", "Rxjava","我来了from!"};
        Observable<String>observable1=Observable.fromArray(strArray);

        /**
         * 3.fromIterable(Iterable i) 发送集合
         * */
        List<String> list = new ArrayList<>();
        list.add("kotlin");
        list.add("java");
        list.add("C++");
        list.add("python");
       Observable.fromIterable(list);

    }

    // 链式调用
    private void rxchainUse() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "onNext:我方已成功发送 "+1);
                emitter.onNext(1);
                Log.d(TAG, "onNext:我方已成功发送 "+2);
                emitter.onNext(2);
                Log.d(TAG, "onNext:我方已成功发送 "+3);
                emitter.onNext(3);
                Log.d(TAG, "onNext:我方已成功发送 "+4);
                emitter.onNext(4);
                Log.d(TAG, "onNext:我方已成功发送 "+5);
                emitter.onNext(5);
                Log.d(TAG, "发送完毕! ");
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "我方已成功接收:"+integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: "+s);
                Log.d(TAG, "........................... ");
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {
                Log.d(TAG, "接收完毕! ");
                Log.d(TAG, "........................... ");
            }
        });
    }
}
