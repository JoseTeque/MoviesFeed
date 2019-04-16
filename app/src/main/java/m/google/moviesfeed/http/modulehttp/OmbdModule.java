package m.google.moviesfeed.http.modulehttp;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import m.google.moviesfeed.http.interfaces.OmdbInterfaces;
import m.google.moviesfeed.http.interfaces.TmdbInterfaces;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class OmbdModule {

    public final String BASE_URL= "http://www.omdbapi.com/";
    public final String APY_KEY= "eb212a6b";

    @Provides
    public OkHttpClient client(){

        HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request= chain.request();
                HttpUrl url= request.url().newBuilder().addQueryParameter("apikey", APY_KEY).build();
                request= request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();

    }

    @Provides
    public Retrofit providesretrofit(String base_url, OkHttpClient okHttpClient ){

        return new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }

    @Provides
    public OmdbInterfaces tmdbInterfaces(){
        return providesretrofit(BASE_URL,client()).create(OmdbInterfaces.class);
    }
}
