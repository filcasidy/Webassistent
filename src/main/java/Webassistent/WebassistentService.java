package Webassistent;

import Webassistent.services.BundesligaService;
import Webassistent.services.IService;
import Webassistent.services.NewsService;

import Webassistent.services.WeatherService;
import Webassistent.utils.HtmlCreatorUtils;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Api(name = "webassistent", version = "v1", scopes = {Constants.EMAIL_SCOPE}, clientIds = {Constants.WEB_CLIENT_ID,
        Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID}, audiences = {Constants.ANDROID_AUDIENCE})
public class WebassistentService {

    private List<IService> services = new ArrayList<>();
    public static ArrayList<Serverresponse> suggestions = new ArrayList<>();

    public WebassistentService() {
        this.services.add(new BundesligaService());
        this.services.add(new WeatherService());
        this.services.add(new NewsService());

        for (IService service : services) {
            for (String command : service.getCommands())
                suggestions.add(new Serverresponse(command));
        }
    }

    /**
     * The main method to get the corresponding message to given command.
     *
     * @param commandId the id of the command which will be executed from ui.
     * @return the response value of the corresponding command given from the ui.
     * If the command does not exist, it responds with message.
     * @throws NotFoundException
     */
    public Serverresponse getServerresponse(@Named("id") String commandId) throws NotFoundException {
        String[] arr = commandId.split(",");
        List<String> para = new LinkedList<String>();
        String command = arr[0];
        for (int i = 1; i < arr.length; i++) {
            para.add(arr[i].replace(" ", ""));
        }
        try {
            String rVal = "";
            for (IService service : services) {
                if (service.hasCommand(command)) {
                    rVal = service.getServiceResponse(command, para).toString();
                }
            }
            return rVal.isEmpty() ? noCommandFoundResponse() : new Serverresponse(rVal);

        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Error in Service");
        }
    }

    private Serverresponse noCommandFoundResponse() {
        List<String> allSugestions = new LinkedList<>();
        for (Serverresponse command : getSuggestions()) {
            allSugestions.add(command.getMessage().toString());
        }

        return new Serverresponse(HtmlCreatorUtils.createPanelWithListOfPoints(
                "No command found !",
                "The given does not exist or is typed wrong. Use on of this: ",
                allSugestions).toString());
    }


    /**
     * Gets all sugestions (commands).
     *
     * @return arraylist with all sugestions (commands)
     */
    public ArrayList<Serverresponse> getSuggestions() {
        return suggestions;
    }

}
