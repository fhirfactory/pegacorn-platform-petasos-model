/*
 * Copyright (c) 2020 Mark A. Hunter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.pegacorn.petasos.model.topics;

import net.fhirfactory.pegacorn.common.model.FDNToken;

import java.util.Objects;

/**
 *
 * @author Mark A. Hunter
 * @since 2020-08-01
 */
public class TopicToken {
    private FDNToken identifier;
    private String version;

    public TopicToken(FDNToken datasetToken, String datasetVersion) {
        this.identifier = datasetToken;
        this.version = datasetVersion;
    }

    public TopicToken() {
        this.identifier = null;
        this.version = null;
    }

    public FDNToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(FDNToken identifier) {
        this.identifier = identifier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TopicToken{(identifier=" + identifier + "),(version=" + version + ")}";
    }

    public boolean equals(TopicToken otherToken) {
        if (this == otherToken) return true;
        if ((otherToken == null) || (getClass() != otherToken.getClass())) return false;
        if (!this.getIdentifier().equals(otherToken.getIdentifier())) return false;
        if (!this.getVersion().contentEquals(otherToken.getVersion())) return false;
        return(true);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getVersion());
    }
}
