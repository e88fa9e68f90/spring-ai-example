package org.springframework.ai.mcp.sample.server.entity;

import java.util.List;

public class PolicyResponse {
    private boolean success;
    private int code;
    private String errorMessage;
    private String apiErrorCode;
    private String apiErrorMsg;
    private String traceId;
    private List<PolicyData> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getApiErrorCode() {
        return apiErrorCode;
    }

    public void setApiErrorCode(String apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    public String getApiErrorMsg() {
        return apiErrorMsg;
    }

    public void setApiErrorMsg(String apiErrorMsg) {
        this.apiErrorMsg = apiErrorMsg;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<PolicyData> getData() {
        return data;
    }

    public void setData(List<PolicyData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Response [success=");
        builder.append(success);
        builder.append(", code=");
        builder.append(code);
        builder.append(", errorMessage=");
        builder.append(errorMessage);
        builder.append(", apiErrorCode=");
        builder.append(apiErrorCode);
        builder.append(", apiErrorMsg=");
        builder.append(apiErrorMsg);
        builder.append(", traceId=");
        builder.append(traceId);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
