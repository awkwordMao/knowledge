package com.lzw.authority.bean;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class AuthorityObjectInfo {
    private String objectName;
    private String methodName;

    public AuthorityObjectInfo() {
    }

    public AuthorityObjectInfo(String objectName, String methodName) {
        this.objectName = objectName;
        this.methodName = methodName;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.methodName == null ? 0 : this.methodName.hashCode());
        result = 31 * result + (this.objectName == null ? 0 : this.objectName.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            AuthorityObjectInfo other = (AuthorityObjectInfo)obj;
            if (this.methodName == null) {
                if (other.methodName != null) {
                    return false;
                }
            } else if (!this.methodName.equals(other.methodName)) {
                return false;
            }

            if (this.objectName == null) {
                if (other.objectName != null) {
                    return false;
                }
            } else if (!this.objectName.equals(other.objectName)) {
                return false;
            }

            return true;
        }
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
