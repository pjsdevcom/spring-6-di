package com.pjsdev.spring6di.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class DatasourceServiceQA implements DatasourceService {

    @Override
    public String getDatasource() {
        return "QA";
    }
}
