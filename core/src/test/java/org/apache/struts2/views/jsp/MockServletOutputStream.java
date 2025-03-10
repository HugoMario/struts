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
package org.apache.struts2.views.jsp;

import com.mockobjects.ExpectationCounter;
import com.mockobjects.ExpectationValue;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockServletOutputStream  extends ServletOutputStream {
    private ExpectationValue myWriteCalled = new ExpectationValue("MockServletOutputStream.write()");
    private boolean myThrowException = false;
    private ExpectationCounter myCloseCallCount = new ExpectationCounter("MockServletOutputstream.close()");
    private ByteArrayOutputStream myBuffer;

    public MockServletOutputStream() {
        this.setupClearContents();
    }

    /**
     * @return
     */
    @Override
    public boolean isReady() {
        return false;
    }

    /**
     * @param writeListener
     */
    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    public void setExpectedCloseCalls(int closeCall) {
        this.myCloseCallCount.setExpected(closeCall);
    }

    public void setExpectingWriteCalls(boolean expectingWriteCall) {
        this.myWriteCalled.setExpected(expectingWriteCall);
    }

    public void setThrowIOException(boolean throwException) {
        this.myThrowException = throwException;
    }

    public void close() throws IOException {
        this.myCloseCallCount.inc();
    }

    public String toString() {
        return this.getContents();
    }

    public void write(int b) throws IOException {
        this.myWriteCalled.setActual(true);
        if (this.myThrowException) {
            throw new IOException("Test IOException generated by request");
        } else {
            this.myBuffer.write(b);
        }
    }

    public void setupClearContents() {
        this.myBuffer = new ByteArrayOutputStream();
    }

    public String getContents() {
        return this.myBuffer.toString();
    }

    public void verify() {
        this.myWriteCalled.verify();
        this.myCloseCallCount.verify();
    }
}
