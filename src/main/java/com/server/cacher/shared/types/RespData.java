package com.server.cacher.shared.types;

import com.server.cacher.shared.enums.RespDataEnum;

public abstract class RespData {

    abstract public RespDataEnum getType();

    abstract public String getFormattedValue();

    abstract public Object getRawValue();

    public String toString() {
        return (getType() + "[" + getFormattedValue() + "]").replaceAll("\\r\\n", " ");
    }
}
