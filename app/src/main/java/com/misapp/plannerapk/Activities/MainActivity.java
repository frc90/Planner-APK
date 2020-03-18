package com.misapp.plannerapk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.misapp.plannerapk.Model.Post;
import com.misapp.plannerapk.Model.User;
import com.misapp.plannerapk.R;
import com.misapp.plannerapk.Routes.Routes;
import com.misapp.plannerapk.Services.JsonPlaceHolderApi;
import com.misapp.plannerapk.Services.LaraBlogTestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private TextView tv_result;
    private Button btn_placeHolderApi;
    private Button btn_larablogTest;

    // retrofit
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    LaraBlogTestApi laraBlogTestApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_planner_logo_foreground);


        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);


        tv_result = findViewById(R.id.tv_result);
        btn_placeHolderApi = findViewById(R.id.btn_placeHolderApi);
        btn_larablogTest = findViewById(R.id.btn_larablogTest);
    }

    // intancia del menu (inflandolo)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // acciones del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                logOut();
                return true;
            case R.id.menu_forget_logout:
                removeSharedPreferences();
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    // desconectarce
    private void logOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // borrar los datos del sharedpreferences
    private void removeSharedPreferences() {
        prefs.edit().clear().apply();
    }


    // get posts
    public void getPosts(View v) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Routes.BASE_HTTPS + Routes.BASE_JSONPLACEHOLDER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tv_result.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

    // get users
    public void getUsers(View v) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Routes.BASE_HTTP + Routes.LARABLOG_LOCALHOST_TEST) // http://localhost:8080/larablog/public/users
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        laraBlogTestApi = retrofit.create(LaraBlogTestApi.class);

        Call<List<User>> call = laraBlogTestApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code: " + response.code());
                    return;
                }

                List<User> users = response.body();
                for (User user : users) {
                    String content = "";
                    content += "ID: " + user.getId() + "\n";
                    content += "Name: " + user.getName() + "\n";
                    content += "Title: " + user.getEmail() + "\n";
                    content += "Created: " + user.getCreated_at() + "\n";
                    content += "Updated: " + user.getUpdated_at() + "\n\n";

                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tv_result.setText(t.getMessage());
            }
        });
    }

    // change of activities
    public void change(View v) {
        Intent intent = new Intent(MainActivity.this, ActivitiesActivity.class);
        startActivity(intent);
        finish();
    }
}
