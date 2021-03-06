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
package org.apache.dolphinscheduler.dao.mapper;

import org.apache.dolphinscheduler.dao.entity.ProcessLineage;
import org.apache.dolphinscheduler.dao.entity.WorkFlowLineage;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface WorkFlowLineageMapper {

    /**
     * queryByName
     *
     * @param searchVal searchVal
     * @param projectCode projectCode
     * @return WorkFlowLineage list
     */
    List<WorkFlowLineage> queryByName(@Param("searchVal") String searchVal, @Param("projectCode") Long projectCode);

    /**
     * queryCodeRelation
     *
     * @param taskCode taskCode
     * @param taskVersion taskVersion
     * @param processDefinitionCode processDefinitionCode
     * @return ProcessLineage
     */
    List<ProcessLineage> queryCodeRelation(
            @Param("taskCode") Long taskCode, @Param("taskVersion") int taskVersion,
            @Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode);

    /**
     * queryRelationByIds
     *
     * @param ids ids
     * @param projectCode projectCode
     * @return ProcessLineage
     */
    List<ProcessLineage> queryRelationByIds(@Param("ids") Set<Integer> ids, @Param("projectCode") Long projectCode);

    /**
     * queryWorkFlowLineageByCode
     *
     * @param processDefinitionCode processDefinitionCode
     * @param projectCode projectCode
     * @return WorkFlowLineage
     */
    WorkFlowLineage queryWorkFlowLineageByCode(@Param("processDefinitionCode") Long processDefinitionCode, @Param("projectCode") Long projectCode);

}
