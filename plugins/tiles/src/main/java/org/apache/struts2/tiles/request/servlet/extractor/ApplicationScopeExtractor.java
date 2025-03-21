/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.tiles.request.servlet.extractor;

import jakarta.servlet.ServletContext;
import java.util.Enumeration;

import org.apache.tiles.request.attribute.AttributeExtractor;


public class ApplicationScopeExtractor implements AttributeExtractor {
    private ServletContext context;

    public ApplicationScopeExtractor(ServletContext context) {
        this.context = context;
    }

    public void setValue(String name, Object value) {
        this.context.setAttribute(name, value);
    }

    public void removeValue(String name) {
        this.context.removeAttribute(name);
    }

    public Enumeration<String> getKeys() {
        return this.context.getAttributeNames();
    }

    public Object getValue(String key) {
        return this.context.getAttribute(key);
    }
}
