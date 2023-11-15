package ch.zli.m223.http.response;

public class AsyncResponseModel<T> {
    public String status;
    public T data;

    private AsyncResponseModel(T data, String status) {
        this.data = data;
        this.status = status;
    }

    public static <U> AsyncResponseModel<U> createResponse(U data, boolean success) {
        return new AsyncResponseModel<U>(data, success ? "success" : "failure");
    }
}
