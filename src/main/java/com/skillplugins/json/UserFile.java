package com.skillplugins.json;

import io.swagger.model.UserData;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class UserFile implements JsonConfig {

    //<user id, <product name, data>>
    private Map<String, Map<String, UserData>> data;

}
