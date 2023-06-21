/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.admin.discovery;

import org.apache.shenyu.admin.listener.DataChangedEvent;
import org.apache.shenyu.admin.model.dto.DiscoveryHandlerDTO;
import org.apache.shenyu.admin.model.dto.DiscoveryUpstreamDTO;
import org.apache.shenyu.admin.model.dto.ProxySelectorDTO;
import org.apache.shenyu.admin.model.entity.DiscoveryDO;
import org.apache.shenyu.common.enums.ConfigGroupEnum;
import org.apache.shenyu.common.enums.DataEventTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.Collections;
import java.util.List;

/**
 * LocalDiscoveryProcessor.
 */
public class LocalDiscoveryProcessor implements DiscoveryProcessor, ApplicationEventPublisherAware {

    private static final Logger LOG = LoggerFactory.getLogger(LocalDiscoveryProcessor.class);

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void createDiscovery(final DiscoveryDO discoveryDO) {
        LOG.info("shenyu discovery local mode do nothing in createDiscovery");
    }

    @Override
    public void createProxySelector(final DiscoveryHandlerDTO discoveryHandlerDTO, final ProxySelectorDTO proxySelectorDTO) {
        DataChangedEvent dataChangedEvent = new DataChangedEvent(ConfigGroupEnum.PROXY_SELECTOR, DataEventTypeEnum.CREATE, Collections.singletonList(covert(proxySelectorDTO, null)));
        eventPublisher.publishEvent(dataChangedEvent);
    }

    @Override
    public void removeDiscovery(final DiscoveryDO discoveryDO) {
        LOG.info("shenyu discovery local mode do nothing in removeDiscovery");
    }

    @Override
    public void removeProxySelector(final DiscoveryHandlerDTO discoveryHandlerDTO, final ProxySelectorDTO proxySelectorDTO) {
        DataChangedEvent dataChangedEvent = new DataChangedEvent(ConfigGroupEnum.PROXY_SELECTOR, DataEventTypeEnum.DELETE, Collections.singletonList(covert(proxySelectorDTO, null)));
        eventPublisher.publishEvent(dataChangedEvent);
    }

    @Override
    public void changeUpstream(final DiscoveryHandlerDTO discoveryHandlerDTO, final ProxySelectorDTO proxySelectorDTO, final List<DiscoveryUpstreamDTO> upstreamDTOS) {
        DataChangedEvent dataChangedEvent = new DataChangedEvent(ConfigGroupEnum.PROXY_SELECTOR, DataEventTypeEnum.DELETE, Collections.singletonList(covert(proxySelectorDTO, upstreamDTOS)));
        eventPublisher.publishEvent(dataChangedEvent);
    }

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

}