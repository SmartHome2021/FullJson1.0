package com.example.fulljson10.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//1. Создаем object, и называем его RetrofitClient
object RetrofitClient {

//    2. Создаем переменную retrofit типа Retrofit
    private var retrofit: Retrofit? = null

//    3. Создаем функцию и называем ее getCleint(baseUrl: String) и тип возвращаемого значения Retrofit
    fun getClient(baseUrl: String): Retrofit {
//    4. выполняем проверку retrofit'a на null, и так как выше мы его обнулили, то:
        if (retrofit == null) {
//            5. К ретрофиту присваиваем Retrofit.Builder()
            retrofit = Retrofit.Builder()
//               6. присоединяем baseUrl с параметром baseUrl(Начальная часть нашей ссылки дял парсинга)
                .baseUrl(baseUrl)
//            7. Присоединяем метод addconverterFactory c параметром GsonConverterFactory.create()
//            (Преобразование Json в java обьект)
                .addConverterFactory(GsonConverterFactory.create())
//             8. Билдим с помощью метода build()
                .build()
        }
//     возвращаем ретрофит в ненулевой тип
        return retrofit!!
    }
}