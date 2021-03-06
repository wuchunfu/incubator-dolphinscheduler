/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.plugin.registry.zookeeper;

import org.apache.dolphinscheduler.spi.register.RegistryConnectListener;
import org.apache.dolphinscheduler.spi.register.RegistryConnectState;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperConnectionStateListener implements ConnectionStateListener {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperConnectionStateListener.class);

    private RegistryConnectListener registryConnectListener;

    public ZookeeperConnectionStateListener(RegistryConnectListener registryConnectListener) {
        this.registryConnectListener = registryConnectListener;
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {

        if (newState == ConnectionState.LOST) {
            logger.error("connection lost from zookeeper");
            registryConnectListener.notify(RegistryConnectState.LOST);
        } else if (newState == ConnectionState.RECONNECTED) {
            logger.info("reconnected to zookeeper");
            registryConnectListener.notify(RegistryConnectState.RECONNECTED);
        } else if (newState == ConnectionState.SUSPENDED) {
            logger.warn("zookeeper connection SUSPENDED");
            registryConnectListener.notify(RegistryConnectState.SUSPENDED);
        }

    }

}
