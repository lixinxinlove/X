package com.lee.x.http.api;


import com.lee.x.http.data.CommentBean;
import com.lee.x.http.data.MovieInfo;
import com.lee.x.http.data.MovieResponse;
import com.lee.x.http.data.RetDataBean;
import com.lee.x.http.data.SearchData;
import com.lee.x.http.data.TypedVideos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 2017/2/28.
 * <p>
 * 电影 API
 */

public interface MovieApi {

    /**
     * 获取首页数据 json
     *
     * @return 首页数据的 Observer
     */
    @GET("homePageApi/homePage.do")
    Observable<MovieResponse<RetDataBean>> getHomePage();

    /**
     * 获取电影详情 json
     *
     * @param mediaId 影片Id
     * @return 影片详情 bserver
     */
    @GET("videoDetailApi/videoDetail.do")
    Observable<MovieResponse<MovieInfo>> getMovieDaily(@Query("mediaId") String mediaId);

    /**
     * 影片搜索
     *
     * @param keyword 搜索关键词
     * @param pnum    页码
     * @return 影片搜索结果
     */
    @GET("searchKeyWordApi/getVideoListByKeyWord.do")
    Observable<MovieResponse<SearchData>> getVideoListByKeyWord(@Query("keyword") String keyword, @Query("pnum") String pnum);

    /**
     * 获取评论列表
     *
     * @param mediaId 影片 ID
     * @param pnum    页码
     * @return 评论结果
     */
    @GET("Commentary/getCommentList.do")
    Observable<MovieResponse<CommentBean>> getCommentList(@Query("mediaId") String mediaId, @Query("pnum") String pnum);

    /**
     * 获取分类视频列表
     */
    @GET("columns/getVideoList.do")
    Observable<MovieResponse<TypedVideos>> getTypedVideos(@Query("catalogId") String catalogId, @Query("pnum") String pnum);

    /**
     * 获取直播列表
     */
    @GET("liveData/getLiveDataByTVcatalogId.do")
    Observable<MovieResponse<TypedVideos>> getLiveVideo(@Query("catalogId") String catalogId, @Query("pnum") String pnum);
    /**
     * 获取专题列表
     */
    @GET("columns/getVideoList.do")
    Observable<MovieResponse<TypedVideos>> getTypedVideos(@Query("catalogId") String catalogId);
}
