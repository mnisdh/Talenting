package a.talenting.com.talenting.domain;

/**
 * Created by user on 2017-12-07.
 */

public interface IDomainCallback<T> {
    void onError(Throwable t);
    void onSuccess(int code, T receivedData);
    void onFailure(int code, T receivedData);
}
