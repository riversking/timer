package com.rivers.configjdbcclient.service;

import com.rivers.configjdbcclient.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service
 *
 * @author rivers
 * @Date 2020-01-21 14:28
 */
@Service
public class ConfigService {

    @Autowired
    private ConfigDao configDao;


    public void saveConfig() {

    }

}
