package com.server.cacher.shared.types;

import com.server.cacher.shared.enuns.RespDataType;

public abstract class RespData {

    abstract public RespDataType getType();

    abstract public String getFormattedValue();

    abstract public Object getRawValue();

    public String toString() {
        return (getType() + "[" + getFormattedValue() + "]").replaceAll("\\r\\n", " ");
    }
}
