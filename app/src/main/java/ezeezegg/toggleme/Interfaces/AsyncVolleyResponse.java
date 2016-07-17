package ezeezegg.toggleme.Interfaces;

/**
 * Created by egarcia on 7/5/16.
 */
public interface AsyncVolleyResponse {
    void AsyncVolleyFinish(String response);
    void AsyncVolleyError(String error);
    void AsyncVolleyEntriesResponse(String response);
    void AsyncVolleyEntriesError(String error);
}
