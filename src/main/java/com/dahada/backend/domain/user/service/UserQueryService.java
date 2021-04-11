package com.dahada.backend.domain.user.service;

import com.dahada.backend.domain.user.service.dto.CheckUserExistenceRequest;

/**
 * @author hyeyoom
 */
public interface UserQueryService {
    boolean exist(CheckUserExistenceRequest request);
}
