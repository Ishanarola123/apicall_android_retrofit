package cm.aptoide.myapplication;


import cm.aptoide.myapplication.ModelResponse.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApi {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username")String username,
            @Field("email")String email,
            @Field("password")String password
    );



}
