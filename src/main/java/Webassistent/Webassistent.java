package Webassistent;

import Webassistent.services.BundesligaService;
import Webassistent.services.IService;
import Webassistent.services.NewsService;
import Webassistent.services.WeatherService;
import Webassistent.utils.HtmlCreatorUtils;
import Webassistent.utils.Transcriber;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.inject.Named;
import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Api(name = "webassistent", version = "v1", scopes = {Constants.EMAIL_SCOPE}, clientIds = {Constants.WEB_CLIENT_ID,
        Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID}, audiences = {Constants.ANDROID_AUDIENCE})
public class Webassistent {

    private List<IService> services = new ArrayList<>();
    public static ArrayList<ServerResponse> suggestions = new ArrayList<>();
    Transcriber transcriber = null;

    public Webassistent() {
        this.services.add(new BundesligaService());
        this.services.add(new WeatherService());
        this.services.add(new NewsService());

        for (IService service : services) {
            for (String command : service.getCommands())
                suggestions.add(new ServerResponse(command));
        }
        transcriber = new Transcriber();
    }

    /**
     * The main method to get the corresponding message to given command.
     *
     * @param commandId the id of the command which will be executed from ui.
     * @return the response value of the corresponding command given from the ui.
     * If the command does not exist, it responds with message.
     * @throws NotFoundException
     */
    public ServerResponse getServerresponse(@Named("id") String commandId) throws NotFoundException {
        List<String> tempList = new LinkedList<String>();
        List<String> parameter = new LinkedList<String>();
        try {
            String rVal = "";
            for (IService service : services) {
                String[] splitCommand = null;
                for (String command : service.getCommands()) {
                    if (commandId.startsWith(command)) {
                        splitCommand = commandId.split(command);
                        if (splitCommand.length > 0) {
                            tempList = Arrays.asList(splitCommand[splitCommand.length - 1].split(" "));
                        }
                        for (int i = 0; i < tempList.size(); i++) {
                            if (!(tempList.get(i).isEmpty())) {
                                parameter.add(tempList.get(i));
                            }
                        }
                        rVal = service.getServiceResponse(command, parameter).toString();
                    }
                }

            }
            return isEmptyOrNull(rVal) ? noCommandFoundResponse() : new ServerResponse(rVal);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Error in Service");
        }
    }

    private boolean isEmptyOrNull(String value) {
        if (value == null) {
            return true;
        } else if (value.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    @ApiMethod(name = "getResponce", path = "get_responce", httpMethod = ApiMethod.HttpMethod.POST)
    public ServerResponse getResponce(Object file) throws NotFoundException {

        String testing = "";
        File audio = new File("");
        String s = file.getClass().toString();
        try {

            String base = ((LinkedHashMap) file).get("encoded").toString();
            int comma = base.indexOf(",");
            base = base.substring(++comma);
            byte[] audioDataBytes = Base64.decodeBase64(base);
            testing = transcriber.testing(audioDataBytes);

        } catch (Exception e) {
            return new ServerResponse("failed");
        }


//        File audio = new File("");
//        for (Object c : ((LinkedHashMap) file).values()) {
//            if (c.toString().length() <= 1) {
//                s += c;
//            }
//        }
//        try {
//           FileUtils.copyURLToFile(new URL(s), audio);
//
//        } catch (Exception e) {
//            return new ServerResponse("failed");
//        }

//        String testing = Transcriber.testing(audio);



        return new ServerResponse(testing+ " here");
    }

    /**
     * Response if the command was not found.
     *
     * @return a new message with possible commands
     */
    public ServerResponse noCommandFoundResponse() {
        List<String> allSuggestions = new LinkedList<>();
        for (ServerResponse command : getSuggestions()) {
            allSuggestions.add(command.getMessage());
        }
        return new ServerResponse(HtmlCreatorUtils.createPanelWithListOfPoints(
                "No command found !",
                "The given does not exist or is typed wrong. Use on of this: ",
                allSuggestions).toString());
    }


    /**
     * Gets all sugestions (commands).
     *
     * @return arraylist with all sugestions (commands)
     */
    public ArrayList<ServerResponse> getSuggestions() {
        return suggestions;
    }

}
