/*
 * Copyright 2003-2012 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.cafebabe.samurai.util;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
class I18nizedResourceTest {


    @Test
    void testGetMessage() {
//        resource = GUIResourceBundle.getInstance("samurai.util.messages");
        GUIResourceBundle resource = GUIResourceBundle.getInstance();
        assertTrue("\u65e5\u672c\u8a9e".equals(resource.getMessage("test")) || "test".equals(resource.getMessage("test")));
        assertTrue("\u65e5\u672c\u8a9efoo\u3064\u304b\u3048\u307e\u3059\u304b\uff1f".equals(resource.getMessage("test2", "foo")) || "foofoobar".equals(resource.getMessage("test2", "foo")));
    }
}
