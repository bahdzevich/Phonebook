package com.bogdevich.profile.security;

import java.util.List;

/**
 * Custom realization of security security holder.
 *
 * @author Eugene Bogdevich
 */
public class SecurityContextHolder {

    private static final ThreadLocal<Long> threadLocalPrincipalId = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalPrincipalName = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> threadLocalAuthorityList = new ThreadLocal<>();

    public static Long getThreadLocalPrincipalId() {
        return threadLocalPrincipalId.get();
    }

    public static String getThreadLocalPrincipalName() {
        return threadLocalPrincipalName.get();
    }

    public static List<String> getThreadLocalAuthorityList() {
        return threadLocalAuthorityList.get();
    }

    public static void setThreadLocalPrincipalId(Long principalId) {
        threadLocalPrincipalId.set(principalId);
    }

    public static void setThreadLocalPrincipalName(String principalName) {
        threadLocalPrincipalName.set(principalName);
    }

    public static void setThreadLocalAuthorityList(List<String> principalAuthority) {
        threadLocalAuthorityList.set(principalAuthority);
    }
}
