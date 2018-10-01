package com.yavin.afficheca.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yavin.afficheca.data.entity.EventEntity;
import com.yavin.afficheca.data.entity.mapper.EventEntityXMLMapper;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventsRoot;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.operators.single.SingleDoOnError;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RestApiImpl implements RestApi {

    private final Context context;
    private final EventEntityXMLMapper eventEntityXMLMapper;

    private XmlApi xmlApi;

    public RestApiImpl(Context context, EventEntityXMLMapper eventEntityXMLMapper) {
        if (context == null || eventEntityXMLMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.eventEntityXMLMapper = eventEntityXMLMapper;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        xmlApi = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(XmlApi.API_BASE_URL)
                .client(httpClient.build())
                .build()
                .create(XmlApi.class);
    }

    @Override
    public Observable<List<EventEntity>> eventEntityList() {

            return xmlApi.getEventsVersionRX()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .map(eventsVersionRoot -> eventsVersionRoot.getDataFileName())
                    .flatMap(fileName -> getEventsRootRxS2(fileName))
                    .map(eventsRoot -> new EventEntityXMLMapper()
                            .transformEventEntityCollection(eventsRoot));

    }

    private Observable<EventsRoot> getEventsRootRxS2(String fileName) {
        return xmlApi.getEventsRootRx(fileName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
