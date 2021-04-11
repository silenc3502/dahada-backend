package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;

/**
 * @author hyeyoom
 */
public interface UserQueryService {

    /**
     * 사용자 존재 유무 검사
     *
     * @param request
     * @return true if exists
     */
    boolean exist(CheckUserExistenceRequest request);
}
