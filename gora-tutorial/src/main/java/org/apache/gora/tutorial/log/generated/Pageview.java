/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.apache.gora.tutorial.log.generated;  
@SuppressWarnings("all")
public class Pageview extends org.apache.gora.persistency.impl.PersistentBase implements org.apache.avro.specific.SpecificRecord, org.apache.gora.persistency.Persistent {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Pageview\",\"namespace\":\"org.apache.gora.tutorial.log.generated\",\"fields\":[{\"name\":\"__g__dirty\",\"type\":\"bytes\",\"doc\":\"Bytes used to represent weather or not a field is dirty.\",\"default\":\"AAA=\"},{\"name\":\"url\",\"type\":\"string\"},{\"name\":\"timestamp\",\"type\":\"long\"},{\"name\":\"ip\",\"type\":\"string\"},{\"name\":\"httpMethod\",\"type\":\"string\"},{\"name\":\"httpStatusCode\",\"type\":\"int\"},{\"name\":\"responseSize\",\"type\":\"int\"},{\"name\":\"referrer\",\"type\":\"string\"},{\"name\":\"userAgent\",\"type\":\"string\"}]}");
  /** Bytes used to represent weather or not a field is dirty. */
  @Deprecated public java.nio.ByteBuffer __g__dirty = java.nio.ByteBuffer.wrap(new byte[2]);
  @Deprecated public java.lang.CharSequence url;
  @Deprecated public long timestamp;
  @Deprecated public java.lang.CharSequence ip;
  @Deprecated public java.lang.CharSequence httpMethod;
  @Deprecated public int httpStatusCode;
  @Deprecated public int responseSize;
  @Deprecated public java.lang.CharSequence referrer;
  @Deprecated public java.lang.CharSequence userAgent;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return __g__dirty;
    case 1: return url;
    case 2: return timestamp;
    case 3: return ip;
    case 4: return httpMethod;
    case 5: return httpStatusCode;
    case 6: return responseSize;
    case 7: return referrer;
    case 8: return userAgent;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: __g__dirty = (java.nio.ByteBuffer)value$; break;
    case 1: url = (java.lang.CharSequence)value$; break;
    case 2: timestamp = (java.lang.Long)value$; break;
    case 3: ip = (java.lang.CharSequence)value$; break;
    case 4: httpMethod = (java.lang.CharSequence)value$; break;
    case 5: httpStatusCode = (java.lang.Integer)value$; break;
    case 6: responseSize = (java.lang.Integer)value$; break;
    case 7: referrer = (java.lang.CharSequence)value$; break;
    case 8: userAgent = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'url' field.
   */
  public java.lang.CharSequence getUrl() {
    return url;
  }

  /**
   * Sets the value of the 'url' field.
   * @param value the value to set.
   */
  public void setUrl(java.lang.CharSequence value) {
    this.url = value;
    setDirty(1);
  }
  
  /**
   * Checks the dirty status of the 'url' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isUrlDirty(java.lang.CharSequence value) {
    return isDirty(1);
  }

  /**
   * Gets the value of the 'timestamp' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
    setDirty(2);
  }
  
  /**
   * Checks the dirty status of the 'timestamp' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isTimestampDirty(java.lang.Long value) {
    return isDirty(2);
  }

  /**
   * Gets the value of the 'ip' field.
   */
  public java.lang.CharSequence getIp() {
    return ip;
  }

  /**
   * Sets the value of the 'ip' field.
   * @param value the value to set.
   */
  public void setIp(java.lang.CharSequence value) {
    this.ip = value;
    setDirty(3);
  }
  
  /**
   * Checks the dirty status of the 'ip' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isIpDirty(java.lang.CharSequence value) {
    return isDirty(3);
  }

  /**
   * Gets the value of the 'httpMethod' field.
   */
  public java.lang.CharSequence getHttpMethod() {
    return httpMethod;
  }

  /**
   * Sets the value of the 'httpMethod' field.
   * @param value the value to set.
   */
  public void setHttpMethod(java.lang.CharSequence value) {
    this.httpMethod = value;
    setDirty(4);
  }
  
  /**
   * Checks the dirty status of the 'httpMethod' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isHttpMethodDirty(java.lang.CharSequence value) {
    return isDirty(4);
  }

  /**
   * Gets the value of the 'httpStatusCode' field.
   */
  public java.lang.Integer getHttpStatusCode() {
    return httpStatusCode;
  }

  /**
   * Sets the value of the 'httpStatusCode' field.
   * @param value the value to set.
   */
  public void setHttpStatusCode(java.lang.Integer value) {
    this.httpStatusCode = value;
    setDirty(5);
  }
  
  /**
   * Checks the dirty status of the 'httpStatusCode' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isHttpStatusCodeDirty(java.lang.Integer value) {
    return isDirty(5);
  }

  /**
   * Gets the value of the 'responseSize' field.
   */
  public java.lang.Integer getResponseSize() {
    return responseSize;
  }

  /**
   * Sets the value of the 'responseSize' field.
   * @param value the value to set.
   */
  public void setResponseSize(java.lang.Integer value) {
    this.responseSize = value;
    setDirty(6);
  }
  
  /**
   * Checks the dirty status of the 'responseSize' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isResponseSizeDirty(java.lang.Integer value) {
    return isDirty(6);
  }

  /**
   * Gets the value of the 'referrer' field.
   */
  public java.lang.CharSequence getReferrer() {
    return referrer;
  }

  /**
   * Sets the value of the 'referrer' field.
   * @param value the value to set.
   */
  public void setReferrer(java.lang.CharSequence value) {
    this.referrer = value;
    setDirty(7);
  }
  
  /**
   * Checks the dirty status of the 'referrer' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isReferrerDirty(java.lang.CharSequence value) {
    return isDirty(7);
  }

  /**
   * Gets the value of the 'userAgent' field.
   */
  public java.lang.CharSequence getUserAgent() {
    return userAgent;
  }

  /**
   * Sets the value of the 'userAgent' field.
   * @param value the value to set.
   */
  public void setUserAgent(java.lang.CharSequence value) {
    this.userAgent = value;
    setDirty(8);
  }
  
  /**
   * Checks the dirty status of the 'userAgent' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isUserAgentDirty(java.lang.CharSequence value) {
    return isDirty(8);
  }

  /** Creates a new Pageview RecordBuilder */
  public static org.apache.gora.tutorial.log.generated.Pageview.Builder newBuilder() {
    return new org.apache.gora.tutorial.log.generated.Pageview.Builder();
  }
  
  /** Creates a new Pageview RecordBuilder by copying an existing Builder */
  public static org.apache.gora.tutorial.log.generated.Pageview.Builder newBuilder(org.apache.gora.tutorial.log.generated.Pageview.Builder other) {
    return new org.apache.gora.tutorial.log.generated.Pageview.Builder(other);
  }
  
  /** Creates a new Pageview RecordBuilder by copying an existing Pageview instance */
  public static org.apache.gora.tutorial.log.generated.Pageview.Builder newBuilder(org.apache.gora.tutorial.log.generated.Pageview other) {
    return new org.apache.gora.tutorial.log.generated.Pageview.Builder(other);
  }
  
  /**
   * RecordBuilder for Pageview instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Pageview>
    implements org.apache.avro.data.RecordBuilder<Pageview> {

    private java.nio.ByteBuffer __g__dirty;
    private java.lang.CharSequence url;
    private long timestamp;
    private java.lang.CharSequence ip;
    private java.lang.CharSequence httpMethod;
    private int httpStatusCode;
    private int responseSize;
    private java.lang.CharSequence referrer;
    private java.lang.CharSequence userAgent;

    /** Creates a new Builder */
    private Builder() {
      super(org.apache.gora.tutorial.log.generated.Pageview.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.apache.gora.tutorial.log.generated.Pageview.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Pageview instance */
    private Builder(org.apache.gora.tutorial.log.generated.Pageview other) {
            super(org.apache.gora.tutorial.log.generated.Pageview.SCHEMA$);
      if (isValidValue(fields()[0], other.__g__dirty)) {
        this.__g__dirty = (java.nio.ByteBuffer) data().deepCopy(fields()[0].schema(), other.__g__dirty);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.url)) {
        this.url = (java.lang.CharSequence) data().deepCopy(fields()[1].schema(), other.url);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = (java.lang.Long) data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ip)) {
        this.ip = (java.lang.CharSequence) data().deepCopy(fields()[3].schema(), other.ip);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.httpMethod)) {
        this.httpMethod = (java.lang.CharSequence) data().deepCopy(fields()[4].schema(), other.httpMethod);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.httpStatusCode)) {
        this.httpStatusCode = (java.lang.Integer) data().deepCopy(fields()[5].schema(), other.httpStatusCode);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.responseSize)) {
        this.responseSize = (java.lang.Integer) data().deepCopy(fields()[6].schema(), other.responseSize);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.referrer)) {
        this.referrer = (java.lang.CharSequence) data().deepCopy(fields()[7].schema(), other.referrer);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.userAgent)) {
        this.userAgent = (java.lang.CharSequence) data().deepCopy(fields()[8].schema(), other.userAgent);
        fieldSetFlags()[8] = true;
      }
    }

    /** Gets the value of the 'url' field */
    public java.lang.CharSequence getUrl() {
      return url;
    }
    
    /** Sets the value of the 'url' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setUrl(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.url = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'url' field has been set */
    public boolean hasUrl() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'url' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearUrl() {
      url = null;
      fieldSetFlags()[1] = false;
      return this;
    }
    
    /** Gets the value of the 'timestamp' field */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }
    
    /** Sets the value of the 'timestamp' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'timestamp' field has been set */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'timestamp' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }
    
    /** Gets the value of the 'ip' field */
    public java.lang.CharSequence getIp() {
      return ip;
    }
    
    /** Sets the value of the 'ip' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setIp(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.ip = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'ip' field has been set */
    public boolean hasIp() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'ip' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearIp() {
      ip = null;
      fieldSetFlags()[3] = false;
      return this;
    }
    
    /** Gets the value of the 'httpMethod' field */
    public java.lang.CharSequence getHttpMethod() {
      return httpMethod;
    }
    
    /** Sets the value of the 'httpMethod' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setHttpMethod(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.httpMethod = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'httpMethod' field has been set */
    public boolean hasHttpMethod() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'httpMethod' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearHttpMethod() {
      httpMethod = null;
      fieldSetFlags()[4] = false;
      return this;
    }
    
    /** Gets the value of the 'httpStatusCode' field */
    public java.lang.Integer getHttpStatusCode() {
      return httpStatusCode;
    }
    
    /** Sets the value of the 'httpStatusCode' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setHttpStatusCode(int value) {
      validate(fields()[5], value);
      this.httpStatusCode = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'httpStatusCode' field has been set */
    public boolean hasHttpStatusCode() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'httpStatusCode' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearHttpStatusCode() {
      fieldSetFlags()[5] = false;
      return this;
    }
    
    /** Gets the value of the 'responseSize' field */
    public java.lang.Integer getResponseSize() {
      return responseSize;
    }
    
    /** Sets the value of the 'responseSize' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setResponseSize(int value) {
      validate(fields()[6], value);
      this.responseSize = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'responseSize' field has been set */
    public boolean hasResponseSize() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'responseSize' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearResponseSize() {
      fieldSetFlags()[6] = false;
      return this;
    }
    
    /** Gets the value of the 'referrer' field */
    public java.lang.CharSequence getReferrer() {
      return referrer;
    }
    
    /** Sets the value of the 'referrer' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setReferrer(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.referrer = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'referrer' field has been set */
    public boolean hasReferrer() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'referrer' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearReferrer() {
      referrer = null;
      fieldSetFlags()[7] = false;
      return this;
    }
    
    /** Gets the value of the 'userAgent' field */
    public java.lang.CharSequence getUserAgent() {
      return userAgent;
    }
    
    /** Sets the value of the 'userAgent' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder setUserAgent(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.userAgent = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'userAgent' field has been set */
    public boolean hasUserAgent() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'userAgent' field */
    public org.apache.gora.tutorial.log.generated.Pageview.Builder clearUserAgent() {
      userAgent = null;
      fieldSetFlags()[8] = false;
      return this;
    }
    
    @Override
    public Pageview build() {
      try {
        Pageview record = new Pageview();
        record.__g__dirty = fieldSetFlags()[0] ? this.__g__dirty : (java.nio.ByteBuffer) defaultValue(fields()[0]);
        record.url = fieldSetFlags()[1] ? this.url : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.Long) defaultValue(fields()[2]);
        record.ip = fieldSetFlags()[3] ? this.ip : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.httpMethod = fieldSetFlags()[4] ? this.httpMethod : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.httpStatusCode = fieldSetFlags()[5] ? this.httpStatusCode : (java.lang.Integer) defaultValue(fields()[5]);
        record.responseSize = fieldSetFlags()[6] ? this.responseSize : (java.lang.Integer) defaultValue(fields()[6]);
        record.referrer = fieldSetFlags()[7] ? this.referrer : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.userAgent = fieldSetFlags()[8] ? this.userAgent : (java.lang.CharSequence) defaultValue(fields()[8]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}