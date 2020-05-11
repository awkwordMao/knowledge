package com.lzw.framework;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 14
 * @Description:
 */
@ConfigurationProperties(prefix = "microService", ignoreInvalidFields = true)
public class MicroServiceProperties {
    private final Cookie cookie = new Cookie();

    private final Async async = new Async();

    private final Http http = new Http();

    private final Swagger swagger = new Swagger();

    private final Metrics metrics = new Metrics();

    private final CorsConfiguration cors = new CorsConfiguration();

    private final Gateway gateway = new Gateway();

    private final Ribbon ribbon = new Ribbon();

    private final Registry registry = new Registry();

    private final UndertowOptions undertowOptions = new UndertowOptions();

    private final Logging logging = new Logging();

    private final ResourceServer resourceServer = new ResourceServer();

    public ResourceServer getResourceServer() {
        return resourceServer;
    }

    public UndertowOptions getUndertowOptions() {
        return undertowOptions;
    }

    public Async getAsync() {
        return async;
    }

    public Http getHttp() {
        return http;
    }

    public Registry getRegistry() {
        return registry;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Ribbon getRibbon() {
        return ribbon;
    }

    public Logging getLogging() {
        return logging;
    }

    public static class ResourceServer {

        private Boolean local;

        private String resourceId;

        private List<String> urls;

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public Boolean getLocal() {
            return local;
        }

        public void setLocal(Boolean local) {
            this.local = local;
        }

    }

    public static class UndertowOptions {
        private static final int DEFAULT_MAX_PARAMETERS = 10000;
        private Integer maxParameters = DEFAULT_MAX_PARAMETERS;
        private Boolean enableHttp2 = true;
        private Integer maxListParameters = 1000;

        public Integer getMaxListParameters() {
            return maxListParameters;
        }

        public void setMaxListParameters(Integer maxListParameters) {
            this.maxListParameters = maxListParameters;
        }

        public Integer getMaxParameters() {
            return maxParameters;
        }

        public void setMaxParameters(Integer maxParameters) {
            this.maxParameters = maxParameters;
        }

        public Boolean getEnableHttp2() {
            return enableHttp2;
        }

        public void setEnableHttp2(Boolean enableHttp2) {
            this.enableHttp2 = enableHttp2;
        }
    }

    public static class Cookie {

        private Name name = new Name();
        private String domain = "localhost";
        private String path = "/";
        private Integer maxAge = 60 * 60 * 24 * 365 * 3;
        private Boolean secure = false;
        private Boolean httpOnly = false;

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Integer maxAge) {
            this.maxAge = maxAge;
        }

        public Boolean getSecure() {
            return secure;
        }

        public void setSecure(Boolean secure) {
            this.secure = secure;
        }

        public Boolean getHttpOnly() {
            return httpOnly;
        }

        public void setHttpOnly(Boolean httpOnly) {
            this.httpOnly = httpOnly;
        }

        public static class Name {
            private String visitorId = "visitor_id";
            private String userId = "user_id";
            private String unitId = "unit_id";
            private String accessToken = "access_token";
            private String refreshToken = "refresh_token";
            private String loginName = "login_name";

            public String getVisitorId() {
                return visitorId;
            }

            public void setVisitorId(String visitorId) {
                this.visitorId = visitorId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUnitId() {
                return unitId;
            }

            public void setUnitId(String unitId) {
                this.unitId = unitId;
            }

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public String getRefreshToken() {
                return refreshToken;
            }

            public void setRefreshToken(String refreshToken) {
                this.refreshToken = refreshToken;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }
        }
    }

    public static class Async {

        private int corePoolSize = 2;

        private int maxPoolSize = 50;

        private int queueCapacity = 10000;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Http {

        private final Cache cache = new Cache();
        /**
         * HTTP version, must be "V_1_1" (for HTTP/1.1) or V_2_0 (for (HTTP/2)
         */
        public Version version = Version.V_1_1;

        public Cache getCache() {
            return cache;
        }

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }

        public enum Version {
            /**
             *
             */
            V_1_1,
            /**
             *
             */
            V_2_0
        }

        public static class Cache {

            private int timeToLiveInDays = 1461;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Cache {

        private final Hazelcast hazelcast = new Hazelcast();

        private final Ehcache ehcache = new Ehcache();

        public Hazelcast getHazelcast() {
            return hazelcast;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public static class Hazelcast {

            private int timeToLiveSeconds = 3600;

            private int backupCount = 1;

            public int getTimeToLiveSeconds() {
                return timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public int getBackupCount() {
                return backupCount;
            }

            public void setBackupCount(int backupCount) {
                this.backupCount = backupCount;
            }
        }

        public static class Ehcache {

            private int timeToLiveSeconds = 3600;

            private long maxEntries = 100;

            public int getTimeToLiveSeconds() {
                return timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries() {
                return maxEntries;
            }

            public void setMaxEntries(long maxEntries) {
                this.maxEntries = maxEntries;
            }
        }
    }

    public static class Mail {

        private String from = "";

        private String baseUrl = "";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    public static class Swagger {

        private String title = "API";

        private String description = "API";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName = "wegge";

        private String contactUrl = "mailto:232871232@qq.com";

        private String contactEmail = "mailto:232871232@qq.com";

        private String license = "";

        private String licenseUrl = "";

        private String defaultIncludePattern = ".*";

        private String defaultIncludePackage = "com.shiyuesoft.microservice";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }

        public String getDefaultIncludePackage() {
            return defaultIncludePackage;
        }

        public void setDefaultIncludePackage(String defaultIncludePackage) {
            this.defaultIncludePackage = defaultIncludePackage;
        }
    }

    public static class Metrics {

        private final Jmx jmx = new Jmx();

        private final Graphite graphite = new Graphite();

        private final Prometheus prometheus = new Prometheus();

        private final Logs logs = new Logs();

        public Jmx getJmx() {
            return jmx;
        }

        public Graphite getGraphite() {
            return graphite;
        }

        public Prometheus getPrometheus() {
            return prometheus;
        }

        public Logs getLogs() {
            return logs;
        }

        public static class Jmx {

            private boolean enabled = true;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Graphite {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 2003;

            private String prefix = "EcloudApplication";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }

        public static class Prometheus {

            private boolean enabled = false;

            private String endpoint = "/prometheusMetrics";

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getEndpoint() {
                return endpoint;
            }

            public void setEndpoint(String endpoint) {
                this.endpoint = endpoint;
            }
        }

        public static class Logs {

            private boolean enabled = false;

            private long reportFrequency = 60;

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(int reportFrequency) {
                this.reportFrequency = reportFrequency;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    public static class Logging {

        private final Logstash logstash = new Logstash();
        private final SpectatorMetrics spectatorMetrics = new SpectatorMetrics();

        public Logstash getLogstash() {
            return logstash;
        }

        public SpectatorMetrics getSpectatorMetrics() {
            return spectatorMetrics;
        }

        public static class Logstash {

            private boolean enabled = false;

            private String host = "localhost";

            private int port = 5000;

            private int queueSize = 512;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getQueueSize() {
                return queueSize;
            }

            public void setQueueSize(int queueSize) {
                this.queueSize = queueSize;
            }
        }

        public static class SpectatorMetrics {

            private boolean enabled = false;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    public static class Social {

        private String redirectAfterSignIn = "/#/home";

        public String getRedirectAfterSignIn() {
            return redirectAfterSignIn;
        }

        public void setRedirectAfterSignIn(String redirectAfterSignIn) {
            this.redirectAfterSignIn = redirectAfterSignIn;
        }
    }

    public static class Gateway {

        private final RateLimiting rateLimiting = new RateLimiting();
        private Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap<>();

        public RateLimiting getRateLimiting() {
            return rateLimiting;
        }

        public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
            return authorizedMicroservicesEndpoints;
        }

        public void setAuthorizedMicroservicesEndpoints(
                Map<String, List<String>> authorizedMicroservicesEndpoints) {
            this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
        }

        public static class RateLimiting {

            private boolean enabled = false;

            private long limit = 100_000L;

            private int durationInSeconds = 3_600;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getLimit() {
                return this.limit;
            }

            public void setLimit(long limit) {
                this.limit = limit;
            }

            public int getDurationInSeconds() {
                return durationInSeconds;
            }

            public void setDurationInSeconds(int durationInSeconds) {
                this.durationInSeconds = durationInSeconds;
            }
        }
    }

    public static class Ribbon {

        private String[] displayOnActiveProfiles;

        public String[] getDisplayOnActiveProfiles() {
            return displayOnActiveProfiles;
        }

        public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles) {
            this.displayOnActiveProfiles = displayOnActiveProfiles;
        }
    }

    private static class Registry {

        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
