package outerspacemanager.com.beaudouin.services;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;
import outerspacemanager.com.beaudouin.models.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("users/get")
    Call<User> getCurrentUser(@Header("x-access-token") String token);

    @GET("buildings/list")
    Call<Buildings> getBuilding(@Header("x-access-token") String token);




}
