package com.skillplugins.json;

import io.swagger.model.UserData;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserFileLoader {

    private final JsonConfigAPI jsonConfigAPI = new JsonConfigAPI(true);

    @Getter
    private Optional<UserFile> optionalUserFile = Optional.empty();

    public void loadFile() {
        jsonConfigAPI.registerConfig(new UserFile(), "", "users.json");
        optionalUserFile = Optional.ofNullable(jsonConfigAPI.getConfig(UserFile.class));
    }

    public HttpStatus addUserData(final UserData userData) {
        if (optionalUserFile.isPresent()) {
            final UserFile userFile = optionalUserFile.get();
            final Map<String, Map<String, UserData>> data = userFile.getData();

            //user is not registered
            if (!userFile.getData().containsKey(userData.getUserId())) {
                final Map<String, UserData> tempMap = new HashMap<>();
                tempMap.put(userData.getUserId(), userData);
                userFile.getData().put(userData.getUserId(), tempMap);
                jsonConfigAPI.saveConfig(userFile);
                loadFile();
                return HttpStatus.OK;
            }

            final Map<String, UserData> map = data.get(userData.getUserId());

            //product is not registered
            if (!map.containsKey(userData.getProduct())) {
                map.put(userData.getProduct(), userData);
                jsonConfigAPI.saveConfig(userFile);
                loadFile();
                return HttpStatus.OK;
            }

            //same discord id
            if (map.get(userData.getProduct()).getDiscordId().equals(userData.getDiscordId())) {
                return HttpStatus.OK;
            }

            //different discord id
            return HttpStatus.CONFLICT;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
