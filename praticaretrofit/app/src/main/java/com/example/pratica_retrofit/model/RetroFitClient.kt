import com.example.pratica_retrofit.model.EnderecoServiceIF
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://viacep.com.br/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val enderecoService: EnderecoServiceIF by lazy {
        retrofit.create(EnderecoServiceIF::class.java)
    }
}
