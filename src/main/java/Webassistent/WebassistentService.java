package Webassistent;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Api(
    name = "webassistent",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class WebassistentService {

  private List<IService> services = new ArrayList<>();
  public WebassistentService(){
    this.services.add(new BundesligaService());
  }

  public Serverresponse getServerresponse(@Named("id") String id) throws NotFoundException {
    try {
      String rVal = "";
      for (IService service:services) {
        if (service.hasCommand(id)){
          rVal =  service.getServiceResponse(id).toString();
        }
      }
      return new Serverresponse(rVal);

    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Greeting not found with an index: " + id);
    }
  }

}
