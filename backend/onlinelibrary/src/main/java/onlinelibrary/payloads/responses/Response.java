package onlinelibrary.payloads.responses;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Integer status;
    private String message;
    private Object data;
    private boolean success; // New field for success indication
    private Map<String, String> additionalData;

    // Constructors
    public Response() {
        this.additionalData = new HashMap<>();
    }

    public Response(Integer status, String message, Object data, boolean success ) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.success = success;
        this.additionalData = new HashMap<>();
    }

    // Getters and Setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Additional Data methods
    public void addAdditionalData(String key, String value) {
        additionalData.put(key, value);
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }
}
