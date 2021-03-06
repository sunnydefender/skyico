package com.sky.ico.service.service;

import com.sky.framework.common.exception.BusinessException;
import com.sky.framework.common.utils.JsonUtil;
import com.sky.ico.service.data.dao.PlatformEmailMapper;
import com.sky.ico.service.data.entity.PlatformEmail;
import com.sky.ico.service.enums.BusinessMode;
import com.sky.ico.service.enums.PlatformEmailGroup;
import com.sky.ico.service.errorcode.CommonErrorCode;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private PlatformEmailMapper platformEmailMapper;

    private Random random = new Random();

    public PlatformEmail getPlatformEmail(BusinessMode businessMode) {
        List<PlatformEmail> platformEmails = platformEmailMapper.selectAvailableListByEmailGroup(PlatformEmailGroup.buildPlatformEmailGroup(businessMode));
        if (CollectionUtils.isEmpty(platformEmails)) {
            throw new BusinessException(CommonErrorCode.Email.PLATFORM_EMAL_GROUP_NOT_AVAILABLE);
        }

        int index = random.nextInt(10000) % platformEmails.size();
        return platformEmails.get(index);
    }

}
