package Webassistent;

public class Serverresponse {

    public String response;

    public Serverresponse() {
    }

    public Serverresponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return response;
    }

    public void setMessage(String response) {
        this.response = response;
    }
}

