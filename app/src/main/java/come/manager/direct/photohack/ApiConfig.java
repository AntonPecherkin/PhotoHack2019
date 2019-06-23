package come.manager.direct.photohack;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface ApiConfig {
        @Multipart
    @POST("upload")
        Call uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name);
}
