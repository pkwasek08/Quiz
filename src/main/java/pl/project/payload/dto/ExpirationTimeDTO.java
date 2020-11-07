package pl.project.payload.dto;

public class ExpirationTimeDTO {
    private int expirationTime;
    private int refreshExpirationTime;

    public ExpirationTimeDTO(int expirationTime, int refreshExpirationTime) {
        this.expirationTime = expirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getRefreshExpirationTime() {
        return refreshExpirationTime;
    }

    public void setRefreshExpirationTime(int refreshExpirationTime) {
        this.refreshExpirationTime = refreshExpirationTime;
    }
}
