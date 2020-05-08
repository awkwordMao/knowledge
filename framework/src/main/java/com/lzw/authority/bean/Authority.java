package com.lzw.authority.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class Authority {
    private String id;
    private String serviceId;
    private String name;
    private String code;
    private String memo;
    private String type;
    private String path;
    private String controller;
    private String method;
    private String version;

    public Map<String, String> contrast(Authority authority) {
        Map<String, String> map = new LinkedHashMap();
        if (authority == null) {
            return map;
        } else if (!this.code.equals(authority.getCode())) {
            throw new RuntimeException("不是同一操作码错误\r\ncode1=" + this.code + ",code2=" + authority.getCode() + ",code1!=code2");
        } else if (!this.serviceId.equals(authority.getServiceId())) {
            throw new RuntimeException("操作码所属项目发生变化!\r\nservice_id1=" + this.serviceId + ",service_id2=" + authority.getServiceId() + ",service_id1!=service_id2");
        } else {
            if (this.type == null) {
                if (null != authority.getType()) {
                    map.put("type", "type");
                }
            } else if (!this.type.equals(authority.getType())) {
                map.put("type", "type");
            }

            if (this.name == null) {
                if (null != authority.getName()) {
                    map.put("name", "name");
                }
            } else if (!this.name.equals(authority.getName())) {
                map.put("name", "name");
            }

            if (this.memo == null) {
                if (null != authority.getMemo()) {
                    map.put("memo", "memo");
                }
            } else if (!this.memo.equals(authority.getMemo())) {
                map.put("memo", "memo");
            }

            if (this.path == null) {
                if (null != authority.getPath()) {
                    map.put("path", "path");
                }
            } else if (!this.path.equals(authority.getPath())) {
                map.put("path", "path");
            }

            if (this.controller == null) {
                if (null != authority.getController()) {
                    map.put("controller", "controller");
                }
            } else if (!this.controller.equals(authority.getController())) {
                map.put("controller", "controller");
            }

            if (this.method == null) {
                if (null != authority.getMethod()) {
                    map.put("method", "method");
                }
            } else if (!this.method.equals(authority.getMethod())) {
                map.put("method", "method");
            }
            if (this.version == null) {
                if (null != authority.getMethod()) {
                    map.put("version", "version");
                }
            } else if (!this.version.equals(authority.getVersion())) {
                map.put("version", "version");
            }

            return map;
        }
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.code == null ? 0 : this.code.hashCode());
        result = 31 * result + (this.controller == null ? 0 : this.controller.hashCode());
        result = 31 * result + (this.memo == null ? 0 : this.memo.hashCode());
        result = 31 * result + (this.method == null ? 0 : this.method.hashCode());
        result = 31 * result + (this.version == null ? 0 : this.version.hashCode());
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
        result = 31 * result + (this.serviceId == null ? 0 : this.serviceId.hashCode());
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
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
            Authority other = (Authority)obj;
            if (this.code == null) {
                if (other.code != null) {
                    return false;
                }
            } else if (!this.code.equals(other.code)) {
                return false;
            }

            if (this.controller == null) {
                if (other.controller != null) {
                    return false;
                }
            } else if (!this.controller.equals(other.controller)) {
                return false;
            }

            if (this.memo == null) {
                if (other.memo != null) {
                    return false;
                }
            } else if (!this.memo.equals(other.memo)) {
                return false;
            }

            if (this.method == null) {
                if (other.method != null) {
                    return false;
                }
            } else if (!this.method.equals(other.method)) {
                return false;
            }

            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }

            if (this.path == null) {
                if (other.path != null) {
                    return false;
                }
            } else if (!this.path.equals(other.path)) {
                return false;
            }
            if (this.serviceId == null) {
                if (other.serviceId != null) {
                    return false;
                }
            } else if (!this.serviceId.equals(other.serviceId)) {
                return false;
            }
            if (this.type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!this.type.equals(other.type)) {
                return false;
            }
            if (this.version == null) {
                if (other.version != null) {
                    return false;
                }
            } else if (!this.version.equals(other.version)) {
                return false;
            }
            return true;
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getController() {
        return this.controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
