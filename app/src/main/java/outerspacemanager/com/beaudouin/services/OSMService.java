package outerspacemanager.com.beaudouin.services;

import outerspacemanager.com.beaudouin.models.Building;
import outerspacemanager.com.beaudouin.models.Buildings;
import outerspacemanager.com.beaudouin.models.Ships;
import outerspacemanager.com.beaudouin.models.User;
import outerspacemanager.com.beaudouin.models.Users;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by nico on 06/03/17.
 */

public interface OSMService {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    // create/login user
    @POST("auth/create")
    Call<User> creatUser(@Body User createUser);
    @POST("auth/login")
    Call<User> loginUser(@Body User loginUser);

    // current user information
    @GET("users/get")
    Call<User> getCurrentUser(@Header("x-access-token") String token);

    // get/create buildings of current user
    @GET("buildings/list")
    Call<Buildings> getBuildings(@Header("x-access-token") String token);
    @POST("buildings/create/{id}")
    Call<Building> postBuilding(@Path("id") Integer buildingId, @Header("x-access-token") String token);

    @GET("ships")
    Call<Ships> getShips(@Header("x-access-token") String token);

    // users list in the galaxy
    @GET("users/0/20")
    Call<Users> getUsers(@Header("x-access-token") String token);

}
