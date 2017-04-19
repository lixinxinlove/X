package com.lee.x.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lee.x.R;
import com.lee.x.activity.EventFeedbackActivity;
import com.lee.x.adapter.MovieAdapter;
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
public class MovieFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private TextView tv;

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

        refreshLayout.setOnRefreshListener(this);

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
                            for (int i = 0; i < value.getData().getList().size(); i++) {
                                data.add(value.getData().getList().get(i).getMoreURL());
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

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
}
