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

--  add t_ds_resources_un
delimiter d//
CREATE OR REPLACE FUNCTION uc_dolphin_T_t_ds_resources_un() RETURNS void AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.KEY_COLUMN_USAGE
        WHERE  TABLE_NAME = 't_ds_resources'
        AND CONSTRAINT_NAME = 't_ds_resources_un')
    THEN
        ALTER TABLE t_ds_resources ADD CONSTRAINT t_ds_resources_un UNIQUE (full_name,"type");
    END IF;
END;
$$ LANGUAGE plpgsql;
d//

delimiter ;
SELECT uc_dolphin_T_t_ds_resources_un();
DROP FUNCTION IF EXISTS uc_dolphin_T_t_ds_resources_un();
