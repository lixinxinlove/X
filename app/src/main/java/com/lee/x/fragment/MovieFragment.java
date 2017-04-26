package com.lee.x.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.activity.EventFeedbackActivity;
import com.lee.x.adapter.MovieAdapter;
import com.lee.x.demo.Main1Activity;
import com.lee.x.http.ApiManager;
import com.lee.x.http.data.MovieResponse;
import com.lee.x.http.data.RetDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by android on 2017/4/12.
 */
public class MovieFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private TextView tv;
    private TextView tv1;
    private TextView tv2;


    private Button btnSocket;


    private boolean isUserVisible = false;


    private RecyclerView recyclerView;

    private MovieAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    private List<String> data = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            isUserVisible = true;
        } else {
            isVisibleToUser = false;
        }
    }

    @Override
    protected int getResLayout() {
        return R.layout.movie_fragment;
    }

    @Override
    protected void findView() {
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter(data);
        recyclerView.setAdapter(adapter);
        tv = (TextView) rootView.findViewById(R.id.tv);
        tv1 = (TextView) rootView.findViewById(R.id.tv1);
        tv2 = (TextView) rootView.findViewById(R.id.tv2);
        refreshLayout.setOnRefreshListener(this);


        btnSocket = (Button) rootView.findViewById(R.id.btn_socket);

        btnSocket.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        if (isUserVisible) {

            ApiManager.getInstence().getMovieService()
                    .getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MovieResponse<RetDataBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MovieResponse<RetDataBean> value) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.chimelong_welcome_bg);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                Palette.Swatch s = palette.getVibrantSwatch();

                tv.setTextColor(s.getRgb());
                tv.setTextColor(palette.getDominantSwatch().getRgb());
                tv.setTextColor(palette.getLightMutedSwatch().getRgb());


            }
        });


    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);

                startActivity(new Intent(getActivity(), EventFeedbackActivity.class));

            }
        }, 3000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ApiManager.getInstence();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_socket:
                startActivity(new Intent(getActivity(), Main1Activity.class));
                break;
            default:
                break;
        }
    }
}
