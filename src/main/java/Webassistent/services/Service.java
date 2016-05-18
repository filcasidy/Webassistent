package Webassistent.services;

import java.util.List;

public class Service implements IService {

    public Service(){}

    @Override
    public List<String> getCommands() {
        return null;
    }

    @Override
    public boolean hasCommand(String userCommand) {
        return false;
    }

    @Override
    public Object getServiceResponse(String userCommand) {
        return null;
    }
}
