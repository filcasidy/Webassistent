package Webassistent;

/**
 * The main response class.
 */
public class ServerResponse {

    public String response;

    public ServerResponse() {
    }

    public ServerResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return response;
    }

    public void setMessage(String response) {
        this.response = response;
    }
}

