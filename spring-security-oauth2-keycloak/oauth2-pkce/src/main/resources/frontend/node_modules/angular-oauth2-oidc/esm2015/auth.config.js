export class AuthConfig {
    constructor(json) {
        /**
         * The client's id as registered with the auth server
         */
        this.clientId = '';
        /**
         * The client's redirectUri as registered with the auth server
         */
        this.redirectUri = '';
        /**
         * An optional second redirectUri where the auth server
         * redirects the user to after logging out.
         */
        this.postLogoutRedirectUri = '';
        /**
         * The auth server's endpoint that allows to log
         * the user in when using implicit flow.
         */
        this.loginUrl = '';
        /**
         * The requested scopes
         */
        this.scope = 'openid profile';
        this.resource = '';
        this.rngUrl = '';
        /**
         * Defines whether to use OpenId Connect during
         * implicit flow.
         */
        this.oidc = true;
        /**
         * Defines whether to request an access token during
         * implicit flow.
         */
        this.requestAccessToken = true;
        this.options = null;
        /**
         * The issuer's uri.
         */
        this.issuer = '';
        /**
         * The logout url.
         */
        this.logoutUrl = '';
        /**
         * Defines whether to clear the hash fragment after logging in.
         */
        this.clearHashAfterLogin = true;
        /**
         * Url of the token endpoint as defined by OpenId Connect and OAuth 2.
         */
        this.tokenEndpoint = null;
        /**
         * Url of the revocation endpoint as defined by OpenId Connect and OAuth 2.
         */
        this.revocationEndpoint = null;
        /**
         * Names of known parameters sent out in the TokenResponse. https://tools.ietf.org/html/rfc6749#section-5.1
         */
        this.customTokenParameters = [];
        /**
         * Url of the userinfo endpoint as defined by OpenId Connect.
         */
        this.userinfoEndpoint = null;
        this.responseType = '';
        /**
         * Defines whether additional debug information should
         * be shown at the console. Note that in certain browsers
         * the verbosity of the console needs to be explicitly set
         * to include Debug level messages.
         */
        this.showDebugInformation = false;
        /**
         * The redirect uri used when doing silent refresh.
         */
        this.silentRefreshRedirectUri = '';
        this.silentRefreshMessagePrefix = '';
        /**
         * Set this to true to display the iframe used for
         * silent refresh for debugging.
         */
        this.silentRefreshShowIFrame = false;
        /**
         * Timeout for silent refresh.
         * @internal
         * depreacted b/c of typo, see silentRefreshTimeout
         */
        this.siletRefreshTimeout = 1000 * 20;
        /**
         * Timeout for silent refresh.
         */
        this.silentRefreshTimeout = 1000 * 20;
        /**
         * Some auth servers don't allow using password flow
         * w/o a client secret while the standards do not
         * demand for it. In this case, you can set a password
         * here. As this password is exposed to the public
         * it does not bring additional security and is therefore
         * as good as using no password.
         */
        this.dummyClientSecret = null;
        /**
         * Defines whether https is required.
         * The default value is remoteOnly which only allows
         * http for localhost, while every other domains need
         * to be used with https.
         */
        this.requireHttps = 'remoteOnly';
        /**
         * Defines whether every url provided by the discovery
         * document has to start with the issuer's url.
         */
        this.strictDiscoveryDocumentValidation = true;
        /**
         * JSON Web Key Set (https://tools.ietf.org/html/rfc7517)
         * with keys used to validate received id_tokens.
         * This is taken out of the disovery document. Can be set manually too.
         */
        this.jwks = null;
        /**
         * Map with additional query parameter that are appended to
         * the request when initializing implicit flow.
         */
        this.customQueryParams = null;
        this.silentRefreshIFrameName = 'angular-oauth-oidc-silent-refresh-iframe';
        /**
         * Defines when the token_timeout event should be raised.
         * If you set this to the default value 0.75, the event
         * is triggered after 75% of the token's life time.
         */
        this.timeoutFactor = 0.75;
        /**
         * If true, the lib will try to check whether the user
         * is still logged in on a regular basis as described
         * in http://openid.net/specs/openid-connect-session-1_0.html#ChangeNotification
         */
        this.sessionChecksEnabled = false;
        /**
         * Interval in msec for checking the session
         * according to http://openid.net/specs/openid-connect-session-1_0.html#ChangeNotification
         */
        this.sessionCheckIntervall = 3 * 1000;
        /**
         * Url for the iframe used for session checks
         */
        this.sessionCheckIFrameUrl = null;
        /**
         * Name of the iframe to use for session checks
         */
        this.sessionCheckIFrameName = 'angular-oauth-oidc-check-session-iframe';
        /**
         * This property has been introduced to disable at_hash checks
         * and is indented for Identity Provider that does not deliver
         * an at_hash EVEN THOUGH its recommended by the OIDC specs.
         * Of course, when disabling these checks the we are bypassing
         * a security check which means we are more vulnerable.
         */
        this.disableAtHashCheck = false;
        /**
         * Defines wether to check the subject of a refreshed token after silent refresh.
         * Normally, it should be the same as before.
         */
        this.skipSubjectCheck = false;
        this.useIdTokenHintForSilentRefresh = false;
        /**
         * Defined whether to skip the validation of the issuer in the discovery document.
         * Normally, the discovey document's url starts with the url of the issuer.
         */
        this.skipIssuerCheck = false;
        /**
         * final state sent to issuer is built as follows:
         * state = nonce + nonceStateSeparator + additional state
         * Default separator is ';' (encoded %3B).
         * In rare cases, this character might be forbidden or inconvenient to use by the issuer so it can be customized.
         */
        this.nonceStateSeparator = ';';
        /**
         * Set this to true to use HTTP BASIC auth for AJAX calls
         */
        this.useHttpBasicAuth = false;
        /**
         * The interceptors waits this time span if there is no token
         */
        this.waitForTokenInMsec = 0;
        /**
         * Code Flow is by defauld used together with PKCI which is also higly recommented.
         * You can disbale it here by setting this flag to true.
         * https://tools.ietf.org/html/rfc7636#section-1.1
         */
        this.disablePKCE = false;
        /**
         * This property allows you to override the method that is used to open the login url,
         * allowing a way for implementations to specify their own method of routing to new
         * urls.
         */
        this.openUri = uri => {
            location.href = uri;
        };
        if (json) {
            Object.assign(this, json);
        }
    }
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXV0aC5jb25maWcuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyIuLi8uLi8uLi9wcm9qZWN0cy9saWIvc3JjL2F1dGguY29uZmlnLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBLE1BQU0sT0FBTyxVQUFVO0lBNlByQixZQUFZLElBQTBCO1FBNVB0Qzs7V0FFRztRQUNJLGFBQVEsR0FBSSxFQUFFLENBQUM7UUFFdEI7O1dBRUc7UUFDSSxnQkFBVyxHQUFJLEVBQUUsQ0FBQztRQUV6Qjs7O1dBR0c7UUFDSSwwQkFBcUIsR0FBSSxFQUFFLENBQUM7UUFFbkM7OztXQUdHO1FBQ0ksYUFBUSxHQUFJLEVBQUUsQ0FBQztRQUV0Qjs7V0FFRztRQUNJLFVBQUssR0FBSSxnQkFBZ0IsQ0FBQztRQUUxQixhQUFRLEdBQUksRUFBRSxDQUFDO1FBRWYsV0FBTSxHQUFJLEVBQUUsQ0FBQztRQUVwQjs7O1dBR0c7UUFDSSxTQUFJLEdBQUksSUFBSSxDQUFDO1FBRXBCOzs7V0FHRztRQUNJLHVCQUFrQixHQUFJLElBQUksQ0FBQztRQUUzQixZQUFPLEdBQVMsSUFBSSxDQUFDO1FBRTVCOztXQUVHO1FBQ0ksV0FBTSxHQUFJLEVBQUUsQ0FBQztRQUVwQjs7V0FFRztRQUNJLGNBQVMsR0FBSSxFQUFFLENBQUM7UUFFdkI7O1dBRUc7UUFDSSx3QkFBbUIsR0FBSSxJQUFJLENBQUM7UUFFbkM7O1dBRUc7UUFDSSxrQkFBYSxHQUFZLElBQUksQ0FBQztRQUVyQzs7V0FFRztRQUNJLHVCQUFrQixHQUFZLElBQUksQ0FBQztRQUUxQzs7V0FFRztRQUNJLDBCQUFxQixHQUFjLEVBQUUsQ0FBQztRQUU3Qzs7V0FFRztRQUNJLHFCQUFnQixHQUFZLElBQUksQ0FBQztRQUVqQyxpQkFBWSxHQUFJLEVBQUUsQ0FBQztRQUUxQjs7Ozs7V0FLRztRQUNJLHlCQUFvQixHQUFJLEtBQUssQ0FBQztRQUVyQzs7V0FFRztRQUNJLDZCQUF3QixHQUFJLEVBQUUsQ0FBQztRQUUvQiwrQkFBMEIsR0FBSSxFQUFFLENBQUM7UUFFeEM7OztXQUdHO1FBQ0ksNEJBQXVCLEdBQUksS0FBSyxDQUFDO1FBRXhDOzs7O1dBSUc7UUFDSSx3QkFBbUIsR0FBWSxJQUFJLEdBQUcsRUFBRSxDQUFDO1FBRWhEOztXQUVHO1FBQ0kseUJBQW9CLEdBQVksSUFBSSxHQUFHLEVBQUUsQ0FBQztRQUVqRDs7Ozs7OztXQU9HO1FBQ0ksc0JBQWlCLEdBQVksSUFBSSxDQUFDO1FBRXpDOzs7OztXQUtHO1FBQ0ksaUJBQVksR0FBNEIsWUFBWSxDQUFDO1FBRTVEOzs7V0FHRztRQUNJLHNDQUFpQyxHQUFJLElBQUksQ0FBQztRQUVqRDs7OztXQUlHO1FBQ0ksU0FBSSxHQUFZLElBQUksQ0FBQztRQUU1Qjs7O1dBR0c7UUFDSSxzQkFBaUIsR0FBWSxJQUFJLENBQUM7UUFFbEMsNEJBQXVCLEdBQUksMENBQTBDLENBQUM7UUFFN0U7Ozs7V0FJRztRQUNJLGtCQUFhLEdBQUksSUFBSSxDQUFDO1FBRTdCOzs7O1dBSUc7UUFDSSx5QkFBb0IsR0FBSSxLQUFLLENBQUM7UUFFckM7OztXQUdHO1FBQ0ksMEJBQXFCLEdBQUksQ0FBQyxHQUFHLElBQUksQ0FBQztRQUV6Qzs7V0FFRztRQUNJLDBCQUFxQixHQUFZLElBQUksQ0FBQztRQUU3Qzs7V0FFRztRQUNJLDJCQUFzQixHQUFJLHlDQUF5QyxDQUFDO1FBRTNFOzs7Ozs7V0FNRztRQUNJLHVCQUFrQixHQUFJLEtBQUssQ0FBQztRQUVuQzs7O1dBR0c7UUFDSSxxQkFBZ0IsR0FBSSxLQUFLLENBQUM7UUFFMUIsbUNBQThCLEdBQUksS0FBSyxDQUFDO1FBRS9DOzs7V0FHRztRQUNJLG9CQUFlLEdBQUksS0FBSyxDQUFDO1FBU2hDOzs7OztXQUtHO1FBQ0ksd0JBQW1CLEdBQUksR0FBRyxDQUFDO1FBRWxDOztXQUVHO1FBQ0kscUJBQWdCLEdBQUksS0FBSyxDQUFDO1FBT2pDOztXQUVHO1FBQ0ksdUJBQWtCLEdBQUksQ0FBQyxDQUFDO1FBVS9COzs7O1dBSUc7UUFDSSxnQkFBVyxHQUFJLEtBQUssQ0FBQztRQVE1Qjs7OztXQUlHO1FBQ0ksWUFBTyxHQUEyQixHQUFHLENBQUMsRUFBRTtZQUM3QyxRQUFRLENBQUMsSUFBSSxHQUFHLEdBQUcsQ0FBQztRQUN0QixDQUFDLENBQUM7UUFaQSxJQUFJLElBQUksRUFBRTtZQUNSLE1BQU0sQ0FBQyxNQUFNLENBQUMsSUFBSSxFQUFFLElBQUksQ0FBQyxDQUFDO1NBQzNCO0lBQ0gsQ0FBQztDQVVGIiwic291cmNlc0NvbnRlbnQiOlsiZXhwb3J0IGNsYXNzIEF1dGhDb25maWcge1xyXG4gIC8qKlxyXG4gICAqIFRoZSBjbGllbnQncyBpZCBhcyByZWdpc3RlcmVkIHdpdGggdGhlIGF1dGggc2VydmVyXHJcbiAgICovXHJcbiAgcHVibGljIGNsaWVudElkPyA9ICcnO1xyXG5cclxuICAvKipcclxuICAgKiBUaGUgY2xpZW50J3MgcmVkaXJlY3RVcmkgYXMgcmVnaXN0ZXJlZCB3aXRoIHRoZSBhdXRoIHNlcnZlclxyXG4gICAqL1xyXG4gIHB1YmxpYyByZWRpcmVjdFVyaT8gPSAnJztcclxuXHJcbiAgLyoqXHJcbiAgICogQW4gb3B0aW9uYWwgc2Vjb25kIHJlZGlyZWN0VXJpIHdoZXJlIHRoZSBhdXRoIHNlcnZlclxyXG4gICAqIHJlZGlyZWN0cyB0aGUgdXNlciB0byBhZnRlciBsb2dnaW5nIG91dC5cclxuICAgKi9cclxuICBwdWJsaWMgcG9zdExvZ291dFJlZGlyZWN0VXJpPyA9ICcnO1xyXG5cclxuICAvKipcclxuICAgKiBUaGUgYXV0aCBzZXJ2ZXIncyBlbmRwb2ludCB0aGF0IGFsbG93cyB0byBsb2dcclxuICAgKiB0aGUgdXNlciBpbiB3aGVuIHVzaW5nIGltcGxpY2l0IGZsb3cuXHJcbiAgICovXHJcbiAgcHVibGljIGxvZ2luVXJsPyA9ICcnO1xyXG5cclxuICAvKipcclxuICAgKiBUaGUgcmVxdWVzdGVkIHNjb3Blc1xyXG4gICAqL1xyXG4gIHB1YmxpYyBzY29wZT8gPSAnb3BlbmlkIHByb2ZpbGUnO1xyXG5cclxuICBwdWJsaWMgcmVzb3VyY2U/ID0gJyc7XHJcblxyXG4gIHB1YmxpYyBybmdVcmw/ID0gJyc7XHJcblxyXG4gIC8qKlxyXG4gICAqIERlZmluZXMgd2hldGhlciB0byB1c2UgT3BlbklkIENvbm5lY3QgZHVyaW5nXHJcbiAgICogaW1wbGljaXQgZmxvdy5cclxuICAgKi9cclxuICBwdWJsaWMgb2lkYz8gPSB0cnVlO1xyXG5cclxuICAvKipcclxuICAgKiBEZWZpbmVzIHdoZXRoZXIgdG8gcmVxdWVzdCBhbiBhY2Nlc3MgdG9rZW4gZHVyaW5nXHJcbiAgICogaW1wbGljaXQgZmxvdy5cclxuICAgKi9cclxuICBwdWJsaWMgcmVxdWVzdEFjY2Vzc1Rva2VuPyA9IHRydWU7XHJcblxyXG4gIHB1YmxpYyBvcHRpb25zPzogYW55ID0gbnVsbDtcclxuXHJcbiAgLyoqXHJcbiAgICogVGhlIGlzc3VlcidzIHVyaS5cclxuICAgKi9cclxuICBwdWJsaWMgaXNzdWVyPyA9ICcnO1xyXG5cclxuICAvKipcclxuICAgKiBUaGUgbG9nb3V0IHVybC5cclxuICAgKi9cclxuICBwdWJsaWMgbG9nb3V0VXJsPyA9ICcnO1xyXG5cclxuICAvKipcclxuICAgKiBEZWZpbmVzIHdoZXRoZXIgdG8gY2xlYXIgdGhlIGhhc2ggZnJhZ21lbnQgYWZ0ZXIgbG9nZ2luZyBpbi5cclxuICAgKi9cclxuICBwdWJsaWMgY2xlYXJIYXNoQWZ0ZXJMb2dpbj8gPSB0cnVlO1xyXG5cclxuICAvKipcclxuICAgKiBVcmwgb2YgdGhlIHRva2VuIGVuZHBvaW50IGFzIGRlZmluZWQgYnkgT3BlbklkIENvbm5lY3QgYW5kIE9BdXRoIDIuXHJcbiAgICovXHJcbiAgcHVibGljIHRva2VuRW5kcG9pbnQ/OiBzdHJpbmcgPSBudWxsO1xyXG5cclxuICAvKipcclxuICAgKiBVcmwgb2YgdGhlIHJldm9jYXRpb24gZW5kcG9pbnQgYXMgZGVmaW5lZCBieSBPcGVuSWQgQ29ubmVjdCBhbmQgT0F1dGggMi5cclxuICAgKi9cclxuICBwdWJsaWMgcmV2b2NhdGlvbkVuZHBvaW50Pzogc3RyaW5nID0gbnVsbDtcclxuXHJcbiAgLyoqXHJcbiAgICogTmFtZXMgb2Yga25vd24gcGFyYW1ldGVycyBzZW50IG91dCBpbiB0aGUgVG9rZW5SZXNwb25zZS4gaHR0cHM6Ly90b29scy5pZXRmLm9yZy9odG1sL3JmYzY3NDkjc2VjdGlvbi01LjFcclxuICAgKi9cclxuICBwdWJsaWMgY3VzdG9tVG9rZW5QYXJhbWV0ZXJzPzogc3RyaW5nW10gPSBbXTtcclxuXHJcbiAgLyoqXHJcbiAgICogVXJsIG9mIHRoZSB1c2VyaW5mbyBlbmRwb2ludCBhcyBkZWZpbmVkIGJ5IE9wZW5JZCBDb25uZWN0LlxyXG4gICAqL1xyXG4gIHB1YmxpYyB1c2VyaW5mb0VuZHBvaW50Pzogc3RyaW5nID0gbnVsbDtcclxuXHJcbiAgcHVibGljIHJlc3BvbnNlVHlwZT8gPSAnJztcclxuXHJcbiAgLyoqXHJcbiAgICogRGVmaW5lcyB3aGV0aGVyIGFkZGl0aW9uYWwgZGVidWcgaW5mb3JtYXRpb24gc2hvdWxkXHJcbiAgICogYmUgc2hvd24gYXQgdGhlIGNvbnNvbGUuIE5vdGUgdGhhdCBpbiBjZXJ0YWluIGJyb3dzZXJzXHJcbiAgICogdGhlIHZlcmJvc2l0eSBvZiB0aGUgY29uc29sZSBuZWVkcyB0byBiZSBleHBsaWNpdGx5IHNldFxyXG4gICAqIHRvIGluY2x1ZGUgRGVidWcgbGV2ZWwgbWVzc2FnZXMuXHJcbiAgICovXHJcbiAgcHVibGljIHNob3dEZWJ1Z0luZm9ybWF0aW9uPyA9IGZhbHNlO1xyXG5cclxuICAvKipcclxuICAgKiBUaGUgcmVkaXJlY3QgdXJpIHVzZWQgd2hlbiBkb2luZyBzaWxlbnQgcmVmcmVzaC5cclxuICAgKi9cclxuICBwdWJsaWMgc2lsZW50UmVmcmVzaFJlZGlyZWN0VXJpPyA9ICcnO1xyXG5cclxuICBwdWJsaWMgc2lsZW50UmVmcmVzaE1lc3NhZ2VQcmVmaXg/ID0gJyc7XHJcblxyXG4gIC8qKlxyXG4gICAqIFNldCB0aGlzIHRvIHRydWUgdG8gZGlzcGxheSB0aGUgaWZyYW1lIHVzZWQgZm9yXHJcbiAgICogc2lsZW50IHJlZnJlc2ggZm9yIGRlYnVnZ2luZy5cclxuICAgKi9cclxuICBwdWJsaWMgc2lsZW50UmVmcmVzaFNob3dJRnJhbWU/ID0gZmFsc2U7XHJcblxyXG4gIC8qKlxyXG4gICAqIFRpbWVvdXQgZm9yIHNpbGVudCByZWZyZXNoLlxyXG4gICAqIEBpbnRlcm5hbFxyXG4gICAqIGRlcHJlYWN0ZWQgYi9jIG9mIHR5cG8sIHNlZSBzaWxlbnRSZWZyZXNoVGltZW91dFxyXG4gICAqL1xyXG4gIHB1YmxpYyBzaWxldFJlZnJlc2hUaW1lb3V0PzogbnVtYmVyID0gMTAwMCAqIDIwO1xyXG5cclxuICAvKipcclxuICAgKiBUaW1lb3V0IGZvciBzaWxlbnQgcmVmcmVzaC5cclxuICAgKi9cclxuICBwdWJsaWMgc2lsZW50UmVmcmVzaFRpbWVvdXQ/OiBudW1iZXIgPSAxMDAwICogMjA7XHJcblxyXG4gIC8qKlxyXG4gICAqIFNvbWUgYXV0aCBzZXJ2ZXJzIGRvbid0IGFsbG93IHVzaW5nIHBhc3N3b3JkIGZsb3dcclxuICAgKiB3L28gYSBjbGllbnQgc2VjcmV0IHdoaWxlIHRoZSBzdGFuZGFyZHMgZG8gbm90XHJcbiAgICogZGVtYW5kIGZvciBpdC4gSW4gdGhpcyBjYXNlLCB5b3UgY2FuIHNldCBhIHBhc3N3b3JkXHJcbiAgICogaGVyZS4gQXMgdGhpcyBwYXNzd29yZCBpcyBleHBvc2VkIHRvIHRoZSBwdWJsaWNcclxuICAgKiBpdCBkb2VzIG5vdCBicmluZyBhZGRpdGlvbmFsIHNlY3VyaXR5IGFuZCBpcyB0aGVyZWZvcmVcclxuICAgKiBhcyBnb29kIGFzIHVzaW5nIG5vIHBhc3N3b3JkLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBkdW1teUNsaWVudFNlY3JldD86IHN0cmluZyA9IG51bGw7XHJcblxyXG4gIC8qKlxyXG4gICAqIERlZmluZXMgd2hldGhlciBodHRwcyBpcyByZXF1aXJlZC5cclxuICAgKiBUaGUgZGVmYXVsdCB2YWx1ZSBpcyByZW1vdGVPbmx5IHdoaWNoIG9ubHkgYWxsb3dzXHJcbiAgICogaHR0cCBmb3IgbG9jYWxob3N0LCB3aGlsZSBldmVyeSBvdGhlciBkb21haW5zIG5lZWRcclxuICAgKiB0byBiZSB1c2VkIHdpdGggaHR0cHMuXHJcbiAgICovXHJcbiAgcHVibGljIHJlcXVpcmVIdHRwcz86IGJvb2xlYW4gfCAncmVtb3RlT25seScgPSAncmVtb3RlT25seSc7XHJcblxyXG4gIC8qKlxyXG4gICAqIERlZmluZXMgd2hldGhlciBldmVyeSB1cmwgcHJvdmlkZWQgYnkgdGhlIGRpc2NvdmVyeVxyXG4gICAqIGRvY3VtZW50IGhhcyB0byBzdGFydCB3aXRoIHRoZSBpc3N1ZXIncyB1cmwuXHJcbiAgICovXHJcbiAgcHVibGljIHN0cmljdERpc2NvdmVyeURvY3VtZW50VmFsaWRhdGlvbj8gPSB0cnVlO1xyXG5cclxuICAvKipcclxuICAgKiBKU09OIFdlYiBLZXkgU2V0IChodHRwczovL3Rvb2xzLmlldGYub3JnL2h0bWwvcmZjNzUxNylcclxuICAgKiB3aXRoIGtleXMgdXNlZCB0byB2YWxpZGF0ZSByZWNlaXZlZCBpZF90b2tlbnMuXHJcbiAgICogVGhpcyBpcyB0YWtlbiBvdXQgb2YgdGhlIGRpc292ZXJ5IGRvY3VtZW50LiBDYW4gYmUgc2V0IG1hbnVhbGx5IHRvby5cclxuICAgKi9cclxuICBwdWJsaWMgandrcz86IG9iamVjdCA9IG51bGw7XHJcblxyXG4gIC8qKlxyXG4gICAqIE1hcCB3aXRoIGFkZGl0aW9uYWwgcXVlcnkgcGFyYW1ldGVyIHRoYXQgYXJlIGFwcGVuZGVkIHRvXHJcbiAgICogdGhlIHJlcXVlc3Qgd2hlbiBpbml0aWFsaXppbmcgaW1wbGljaXQgZmxvdy5cclxuICAgKi9cclxuICBwdWJsaWMgY3VzdG9tUXVlcnlQYXJhbXM/OiBvYmplY3QgPSBudWxsO1xyXG5cclxuICBwdWJsaWMgc2lsZW50UmVmcmVzaElGcmFtZU5hbWU/ID0gJ2FuZ3VsYXItb2F1dGgtb2lkYy1zaWxlbnQtcmVmcmVzaC1pZnJhbWUnO1xyXG5cclxuICAvKipcclxuICAgKiBEZWZpbmVzIHdoZW4gdGhlIHRva2VuX3RpbWVvdXQgZXZlbnQgc2hvdWxkIGJlIHJhaXNlZC5cclxuICAgKiBJZiB5b3Ugc2V0IHRoaXMgdG8gdGhlIGRlZmF1bHQgdmFsdWUgMC43NSwgdGhlIGV2ZW50XHJcbiAgICogaXMgdHJpZ2dlcmVkIGFmdGVyIDc1JSBvZiB0aGUgdG9rZW4ncyBsaWZlIHRpbWUuXHJcbiAgICovXHJcbiAgcHVibGljIHRpbWVvdXRGYWN0b3I/ID0gMC43NTtcclxuXHJcbiAgLyoqXHJcbiAgICogSWYgdHJ1ZSwgdGhlIGxpYiB3aWxsIHRyeSB0byBjaGVjayB3aGV0aGVyIHRoZSB1c2VyXHJcbiAgICogaXMgc3RpbGwgbG9nZ2VkIGluIG9uIGEgcmVndWxhciBiYXNpcyBhcyBkZXNjcmliZWRcclxuICAgKiBpbiBodHRwOi8vb3BlbmlkLm5ldC9zcGVjcy9vcGVuaWQtY29ubmVjdC1zZXNzaW9uLTFfMC5odG1sI0NoYW5nZU5vdGlmaWNhdGlvblxyXG4gICAqL1xyXG4gIHB1YmxpYyBzZXNzaW9uQ2hlY2tzRW5hYmxlZD8gPSBmYWxzZTtcclxuXHJcbiAgLyoqXHJcbiAgICogSW50ZXJ2YWwgaW4gbXNlYyBmb3IgY2hlY2tpbmcgdGhlIHNlc3Npb25cclxuICAgKiBhY2NvcmRpbmcgdG8gaHR0cDovL29wZW5pZC5uZXQvc3BlY3Mvb3BlbmlkLWNvbm5lY3Qtc2Vzc2lvbi0xXzAuaHRtbCNDaGFuZ2VOb3RpZmljYXRpb25cclxuICAgKi9cclxuICBwdWJsaWMgc2Vzc2lvbkNoZWNrSW50ZXJ2YWxsPyA9IDMgKiAxMDAwO1xyXG5cclxuICAvKipcclxuICAgKiBVcmwgZm9yIHRoZSBpZnJhbWUgdXNlZCBmb3Igc2Vzc2lvbiBjaGVja3NcclxuICAgKi9cclxuICBwdWJsaWMgc2Vzc2lvbkNoZWNrSUZyYW1lVXJsPzogc3RyaW5nID0gbnVsbDtcclxuXHJcbiAgLyoqXHJcbiAgICogTmFtZSBvZiB0aGUgaWZyYW1lIHRvIHVzZSBmb3Igc2Vzc2lvbiBjaGVja3NcclxuICAgKi9cclxuICBwdWJsaWMgc2Vzc2lvbkNoZWNrSUZyYW1lTmFtZT8gPSAnYW5ndWxhci1vYXV0aC1vaWRjLWNoZWNrLXNlc3Npb24taWZyYW1lJztcclxuXHJcbiAgLyoqXHJcbiAgICogVGhpcyBwcm9wZXJ0eSBoYXMgYmVlbiBpbnRyb2R1Y2VkIHRvIGRpc2FibGUgYXRfaGFzaCBjaGVja3NcclxuICAgKiBhbmQgaXMgaW5kZW50ZWQgZm9yIElkZW50aXR5IFByb3ZpZGVyIHRoYXQgZG9lcyBub3QgZGVsaXZlclxyXG4gICAqIGFuIGF0X2hhc2ggRVZFTiBUSE9VR0ggaXRzIHJlY29tbWVuZGVkIGJ5IHRoZSBPSURDIHNwZWNzLlxyXG4gICAqIE9mIGNvdXJzZSwgd2hlbiBkaXNhYmxpbmcgdGhlc2UgY2hlY2tzIHRoZSB3ZSBhcmUgYnlwYXNzaW5nXHJcbiAgICogYSBzZWN1cml0eSBjaGVjayB3aGljaCBtZWFucyB3ZSBhcmUgbW9yZSB2dWxuZXJhYmxlLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBkaXNhYmxlQXRIYXNoQ2hlY2s/ID0gZmFsc2U7XHJcblxyXG4gIC8qKlxyXG4gICAqIERlZmluZXMgd2V0aGVyIHRvIGNoZWNrIHRoZSBzdWJqZWN0IG9mIGEgcmVmcmVzaGVkIHRva2VuIGFmdGVyIHNpbGVudCByZWZyZXNoLlxyXG4gICAqIE5vcm1hbGx5LCBpdCBzaG91bGQgYmUgdGhlIHNhbWUgYXMgYmVmb3JlLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBza2lwU3ViamVjdENoZWNrPyA9IGZhbHNlO1xyXG5cclxuICBwdWJsaWMgdXNlSWRUb2tlbkhpbnRGb3JTaWxlbnRSZWZyZXNoPyA9IGZhbHNlO1xyXG5cclxuICAvKipcclxuICAgKiBEZWZpbmVkIHdoZXRoZXIgdG8gc2tpcCB0aGUgdmFsaWRhdGlvbiBvZiB0aGUgaXNzdWVyIGluIHRoZSBkaXNjb3ZlcnkgZG9jdW1lbnQuXHJcbiAgICogTm9ybWFsbHksIHRoZSBkaXNjb3ZleSBkb2N1bWVudCdzIHVybCBzdGFydHMgd2l0aCB0aGUgdXJsIG9mIHRoZSBpc3N1ZXIuXHJcbiAgICovXHJcbiAgcHVibGljIHNraXBJc3N1ZXJDaGVjaz8gPSBmYWxzZTtcclxuXHJcbiAgLyoqXHJcbiAgICogQWNjb3JkaW5nIHRvIHJmYzY3NDkgaXQgaXMgcmVjb21tZW5kZWQgKGJ1dCBub3QgcmVxdWlyZWQpIHRoYXQgdGhlIGF1dGhcclxuICAgKiBzZXJ2ZXIgZXhwb3NlcyB0aGUgYWNjZXNzX3Rva2VuJ3MgbGlmZSB0aW1lIGluIHNlY29uZHMuXHJcbiAgICogVGhpcyBpcyBhIGZhbGxiYWNrIHZhbHVlIGZvciB0aGUgY2FzZSB0aGlzIHZhbHVlIGlzIG5vdCBleHBvc2VkLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBmYWxsYmFja0FjY2Vzc1Rva2VuRXhwaXJhdGlvblRpbWVJblNlYz86IG51bWJlcjtcclxuXHJcbiAgLyoqXHJcbiAgICogZmluYWwgc3RhdGUgc2VudCB0byBpc3N1ZXIgaXMgYnVpbHQgYXMgZm9sbG93czpcclxuICAgKiBzdGF0ZSA9IG5vbmNlICsgbm9uY2VTdGF0ZVNlcGFyYXRvciArIGFkZGl0aW9uYWwgc3RhdGVcclxuICAgKiBEZWZhdWx0IHNlcGFyYXRvciBpcyAnOycgKGVuY29kZWQgJTNCKS5cclxuICAgKiBJbiByYXJlIGNhc2VzLCB0aGlzIGNoYXJhY3RlciBtaWdodCBiZSBmb3JiaWRkZW4gb3IgaW5jb252ZW5pZW50IHRvIHVzZSBieSB0aGUgaXNzdWVyIHNvIGl0IGNhbiBiZSBjdXN0b21pemVkLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBub25jZVN0YXRlU2VwYXJhdG9yPyA9ICc7JztcclxuXHJcbiAgLyoqXHJcbiAgICogU2V0IHRoaXMgdG8gdHJ1ZSB0byB1c2UgSFRUUCBCQVNJQyBhdXRoIGZvciBBSkFYIGNhbGxzXHJcbiAgICovXHJcbiAgcHVibGljIHVzZUh0dHBCYXNpY0F1dGg/ID0gZmFsc2U7XHJcblxyXG4gIC8qKlxyXG4gICAqIFRoZSB3aW5kb3cgb2YgdGltZSAoaW4gc2Vjb25kcykgdG8gYWxsb3cgdGhlIGN1cnJlbnQgdGltZSB0byBkZXZpYXRlIHdoZW4gdmFsaWRhdGluZyBpZF90b2tlbidzIGlhdCBhbmQgZXhwIHZhbHVlcy5cclxuICAgKi9cclxuICBwdWJsaWMgY2xvY2tTa2V3SW5TZWM/OiBudW1iZXI7XHJcblxyXG4gIC8qKlxyXG4gICAqIFRoZSBpbnRlcmNlcHRvcnMgd2FpdHMgdGhpcyB0aW1lIHNwYW4gaWYgdGhlcmUgaXMgbm8gdG9rZW5cclxuICAgKi9cclxuICBwdWJsaWMgd2FpdEZvclRva2VuSW5Nc2VjPyA9IDA7XHJcblxyXG4gIC8qKlxyXG4gICAqIFNldCB0aGlzIHRvIHRydWUgaWYgeW91IHdhbnQgdG8gdXNlIHNpbGVudCByZWZyZXNoIHRvZ2V0aGVyIHdpdGhcclxuICAgKiBjb2RlIGZsb3cuIEFzIHNpbGVudCByZWZyZXNoIGlzIHRoZSBvbmx5IG9wdGlvbiBmb3IgcmVmcmVzaGluZ1xyXG4gICAqIHdpdGggaW1wbGljaXQgZmxvdywgeW91IGRvbid0IG5lZWQgdG8gZXhwbGljaXRseSB0dXJuIGl0IG9uIGluXHJcbiAgICogdGhpcyBjYXNlLlxyXG4gICAqL1xyXG4gIHB1YmxpYyB1c2VTaWxlbnRSZWZyZXNoPztcclxuXHJcbiAgLyoqXHJcbiAgICogQ29kZSBGbG93IGlzIGJ5IGRlZmF1bGQgdXNlZCB0b2dldGhlciB3aXRoIFBLQ0kgd2hpY2ggaXMgYWxzbyBoaWdseSByZWNvbW1lbnRlZC5cclxuICAgKiBZb3UgY2FuIGRpc2JhbGUgaXQgaGVyZSBieSBzZXR0aW5nIHRoaXMgZmxhZyB0byB0cnVlLlxyXG4gICAqIGh0dHBzOi8vdG9vbHMuaWV0Zi5vcmcvaHRtbC9yZmM3NjM2I3NlY3Rpb24tMS4xXHJcbiAgICovXHJcbiAgcHVibGljIGRpc2FibGVQS0NFPyA9IGZhbHNlO1xyXG5cclxuICBjb25zdHJ1Y3Rvcihqc29uPzogUGFydGlhbDxBdXRoQ29uZmlnPikge1xyXG4gICAgaWYgKGpzb24pIHtcclxuICAgICAgT2JqZWN0LmFzc2lnbih0aGlzLCBqc29uKTtcclxuICAgIH1cclxuICB9XHJcblxyXG4gIC8qKlxyXG4gICAqIFRoaXMgcHJvcGVydHkgYWxsb3dzIHlvdSB0byBvdmVycmlkZSB0aGUgbWV0aG9kIHRoYXQgaXMgdXNlZCB0byBvcGVuIHRoZSBsb2dpbiB1cmwsXHJcbiAgICogYWxsb3dpbmcgYSB3YXkgZm9yIGltcGxlbWVudGF0aW9ucyB0byBzcGVjaWZ5IHRoZWlyIG93biBtZXRob2Qgb2Ygcm91dGluZyB0byBuZXdcclxuICAgKiB1cmxzLlxyXG4gICAqL1xyXG4gIHB1YmxpYyBvcGVuVXJpPzogKHVyaTogc3RyaW5nKSA9PiB2b2lkID0gdXJpID0+IHtcclxuICAgIGxvY2F0aW9uLmhyZWYgPSB1cmk7XHJcbiAgfTtcclxufVxyXG4iXX0=