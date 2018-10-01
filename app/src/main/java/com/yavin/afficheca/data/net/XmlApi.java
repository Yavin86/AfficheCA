package com.yavin.afficheca.data.net;

import com.yavin.afficheca.data.entity.mapper.xmlModels.EventsRoot;
import com.yavin.afficheca.data.entity.mapper.xmlModels.EventsVersionRoot;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface XmlApi {

    /** Api url for getting all events
     * API_BASE_URL + events-[date].xml from events-version.xml response
     * e.g. API_BASE_URL + /events-2018-09-25-07-02-48.xml*/

    /** Api url for getting all images of events
     * API_BASE_URL + images/event_images_[id].xml from events-[date].xml response
     * e.g. API_BASE_URL + /images/event_images_60_000.jpg*/

    String API_BASE_URL = "kind_a_sicret"; // sorry
    String VERSION_FILE_NAME = "events-version.xml";
    String IMAGE_FILE_NAME = "images/event_images_X_000.jpg";

    @GET(VERSION_FILE_NAME)
    Observable<EventsVersionRoot> getEventsVersionRX();

    @GET("{fileName}")
    Observable<EventsRoot> getEventsRootRx(@Path("fileName") String fileName);
}
