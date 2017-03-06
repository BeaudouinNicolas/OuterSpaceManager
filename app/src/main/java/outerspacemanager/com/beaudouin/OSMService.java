package outerspacemanager.com.beaudouin;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nico on 06/03/17.
 */

public interface OSMService {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    @POST("auth/create")
    Call<User> creatUser(@Body User createUser);

    @POST("auth/login")
    Call<User> loginUser(@Body User loginUser);




}
