package chessgameapi.dto;

public abstract class BaseResultDTO {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
