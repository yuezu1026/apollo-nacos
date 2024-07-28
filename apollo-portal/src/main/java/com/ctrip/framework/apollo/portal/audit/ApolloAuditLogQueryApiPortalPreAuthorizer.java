/*
 * Copyright 2024 Apollo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ctrip.framework.apollo.portal.audit;

import com.ctrip.framework.apollo.audit.spi.ApolloAuditLogQueryApiPreAuthorizer;
import com.ctrip.framework.apollo.portal.component.PermissionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("apolloAuditLogQueryApiPreAuthorizer")
@ConditionalOnProperty(prefix = "apollo.audit.log", name = "enabled", havingValue = "true")
public class ApolloAuditLogQueryApiPortalPreAuthorizer implements
    ApolloAuditLogQueryApiPreAuthorizer {
  private final PermissionValidator permissionValidator;

  public ApolloAuditLogQueryApiPortalPreAuthorizer(PermissionValidator permissionValidator) {
    this.permissionValidator = permissionValidator;
  }

  @Override
  public boolean hasQueryPermission() {
    return permissionValidator.isSuperAdmin();
  }
}
