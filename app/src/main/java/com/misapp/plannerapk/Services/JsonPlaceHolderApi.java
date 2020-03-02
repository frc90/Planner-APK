package com.misapp.plannerapk.Services;

import com.misapp.plannerapk.Model.Post;
import com.misapp.plannerapk.Routes.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    // ->https://jsonplaceholder.typicode.com/posts
    @GET(Routes.POSTS)
    Call<List<Post>> getPosts();
}
