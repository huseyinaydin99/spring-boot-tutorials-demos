(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('@angular/core'), require('@angular/common'), require('@angular/common/http'), require('rxjs'), require('rxjs/operators')) :
    typeof define === 'function' && define.amd ? define('angular-oauth2-oidc', ['exports', '@angular/core', '@angular/common', '@angular/common/http', 'rxjs', 'rxjs/operators'], factory) :
    (global = global || self, factory(global['angular-oauth2-oidc'] = {}, global.ng.core, global.ng.common, global.ng.common.http, global.rxjs, global.rxjs.operators));
}(this, (function (exports, core, common, http, rxjs, operators) { 'use strict';

    /**
     * Additional options that can be passed to tryLogin.
     */
    var LoginOptions = /** @class */ (function () {
        function LoginOptions() {
            /**
             * Normally, you want to clear your hash fragment after
             * the lib read the token(s) so that they are not displayed
             * anymore in the url. If not, set this to true. For code flow
             * this controls removing query string values.
             */
            this.preventClearHashAfterLogin = false;
        }
        return LoginOptions;
    }());
    /**
     * Defines the logging interface the OAuthService uses
     * internally. Is compatible with the `console` object,
     * but you can provide your own implementation as well
     * through dependency injection.
     */
    var OAuthLogger = /** @class */ (function () {
        function OAuthLogger() {
        }
        return OAuthLogger;
    }());
    /**
     * Defines a simple storage that can be used for
     * storing the tokens at client side.
     * Is compatible to localStorage and sessionStorage,
     * but you can also create your own implementations.
     */
    var OAuthStorage = /** @class */ (function () {
        function OAuthStorage() {
        }
        return OAuthStorage;
    }());
    var MemoryStorage = /** @class */ (function () {
        function MemoryStorage() {
            this.data = new Map();
        }
        MemoryStorage.prototype.getItem = function (key) {
            return this.data.get(key);
        };
        MemoryStorage.prototype.removeItem = function (key) {
            this.data.delete(key);
        };
        MemoryStorage.prototype.setItem = function (key, data) {
            this.data.set(key, data);
        };
        return MemoryStorage;
    }());
    MemoryStorage.decorators = [
        { type: core.Injectable }
    ];
    /**
     * Represents the received tokens, the received state
     * and the parsed claims from the id-token.
     */
    var ReceivedTokens = /** @class */ (function () {
        function ReceivedTokens() {
        }
        return ReceivedTokens;
    }());

    /*! *****************************************************************************
    Copyright (c) Microsoft Corporation.

    Permission to use, copy, modify, and/or distribute this software for any
    purpose with or without fee is hereby granted.

    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
    REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
    AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
    INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
    LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
    OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
    PERFORMANCE OF THIS SOFTWARE.
    ***************************************************************************** */
    /* global Reflect, Promise */
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b)
                if (b.hasOwnProperty(p))
                    d[p] = b[p]; };
        return extendStatics(d, b);
    };
    function __extends(d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    }
    var __assign = function () {
        __assign = Object.assign || function __assign(t) {
            for (var s, i = 1, n = arguments.length; i < n; i++) {
                s = arguments[i];
                for (var p in s)
                    if (Object.prototype.hasOwnProperty.call(s, p))
                        t[p] = s[p];
            }
            return t;
        };
        return __assign.apply(this, arguments);
    };
    function __rest(s, e) {
        var t = {};
        for (var p in s)
            if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
                t[p] = s[p];
        if (s != null && typeof Object.getOwnPropertySymbols === "function")
            for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
                if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                    t[p[i]] = s[p[i]];
            }
        return t;
    }
    function __decorate(decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function")
            r = Reflect.decorate(decorators, target, key, desc);
        else
            for (var i = decorators.length - 1; i >= 0; i--)
                if (d = decorators[i])
                    r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    }
    function __param(paramIndex, decorator) {
        return function (target, key) { decorator(target, key, paramIndex); };
    }
    function __metadata(metadataKey, metadataValue) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function")
            return Reflect.metadata(metadataKey, metadataValue);
    }
    function __awaiter(thisArg, _arguments, P, generator) {
        function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
        return new (P || (P = Promise))(function (resolve, reject) {
            function fulfilled(value) { try {
                step(generator.next(value));
            }
            catch (e) {
                reject(e);
            } }
            function rejected(value) { try {
                step(generator["throw"](value));
            }
            catch (e) {
                reject(e);
            } }
            function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
            step((generator = generator.apply(thisArg, _arguments || [])).next());
        });
    }
    function __generator(thisArg, body) {
        var _ = { label: 0, sent: function () { if (t[0] & 1)
                throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
        return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function () { return this; }), g;
        function verb(n) { return function (v) { return step([n, v]); }; }
        function step(op) {
            if (f)
                throw new TypeError("Generator is already executing.");
            while (_)
                try {
                    if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done)
                        return t;
                    if (y = 0, t)
                        op = [op[0] & 2, t.value];
                    switch (op[0]) {
                        case 0:
                        case 1:
                            t = op;
                            break;
                        case 4:
                            _.label++;
                            return { value: op[1], done: false };
                        case 5:
                            _.label++;
                            y = op[1];
                            op = [0];
                            continue;
                        case 7:
                            op = _.ops.pop();
                            _.trys.pop();
                            continue;
                        default:
                            if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) {
                                _ = 0;
                                continue;
                            }
                            if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) {
                                _.label = op[1];
                                break;
                            }
                            if (op[0] === 6 && _.label < t[1]) {
                                _.label = t[1];
                                t = op;
                                break;
                            }
                            if (t && _.label < t[2]) {
                                _.label = t[2];
                                _.ops.push(op);
                                break;
                            }
                            if (t[2])
                                _.ops.pop();
                            _.trys.pop();
                            continue;
                    }
                    op = body.call(thisArg, _);
                }
                catch (e) {
                    op = [6, e];
                    y = 0;
                }
                finally {
                    f = t = 0;
                }
            if (op[0] & 5)
                throw op[1];
            return { value: op[0] ? op[1] : void 0, done: true };
        }
    }
    var __createBinding = Object.create ? (function (o, m, k, k2) {
        if (k2 === undefined)
            k2 = k;
        Object.defineProperty(o, k2, { enumerable: true, get: function () { return m[k]; } });
    }) : (function (o, m, k, k2) {
        if (k2 === undefined)
            k2 = k;
        o[k2] = m[k];
    });
    function __exportStar(m, exports) {
        for (var p in m)
            if (p !== "default" && !exports.hasOwnProperty(p))
                __createBinding(exports, m, p);
    }
    function __values(o) {
        var s = typeof Symbol === "function" && Symbol.iterator, m = s && o[s], i = 0;
        if (m)
            return m.call(o);
        if (o && typeof o.length === "number")
            return {
                next: function () {
                    if (o && i >= o.length)
                        o = void 0;
                    return { value: o && o[i++], done: !o };
                }
            };
        throw new TypeError(s ? "Object is not iterable." : "Symbol.iterator is not defined.");
    }
    function __read(o, n) {
        var m = typeof Symbol === "function" && o[Symbol.iterator];
        if (!m)
            return o;
        var i = m.call(o), r, ar = [], e;
        try {
            while ((n === void 0 || n-- > 0) && !(r = i.next()).done)
                ar.push(r.value);
        }
        catch (error) {
            e = { error: error };
        }
        finally {
            try {
                if (r && !r.done && (m = i["return"]))
                    m.call(i);
            }
            finally {
                if (e)
                    throw e.error;
            }
        }
        return ar;
    }
    function __spread() {
        for (var ar = [], i = 0; i < arguments.length; i++)
            ar = ar.concat(__read(arguments[i]));
        return ar;
    }
    function __spreadArrays() {
        for (var s = 0, i = 0, il = arguments.length; i < il; i++)
            s += arguments[i].length;
        for (var r = Array(s), k = 0, i = 0; i < il; i++)
            for (var a = arguments[i], j = 0, jl = a.length; j < jl; j++, k++)
                r[k] = a[j];
        return r;
    }
    ;
    function __await(v) {
        return this instanceof __await ? (this.v = v, this) : new __await(v);
    }
    function __asyncGenerator(thisArg, _arguments, generator) {
        if (!Symbol.asyncIterator)
            throw new TypeError("Symbol.asyncIterator is not defined.");
        var g = generator.apply(thisArg, _arguments || []), i, q = [];
        return i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i;
        function verb(n) { if (g[n])
            i[n] = function (v) { return new Promise(function (a, b) { q.push([n, v, a, b]) > 1 || resume(n, v); }); }; }
        function resume(n, v) { try {
            step(g[n](v));
        }
        catch (e) {
            settle(q[0][3], e);
        } }
        function step(r) { r.value instanceof __await ? Promise.resolve(r.value.v).then(fulfill, reject) : settle(q[0][2], r); }
        function fulfill(value) { resume("next", value); }
        function reject(value) { resume("throw", value); }
        function settle(f, v) { if (f(v), q.shift(), q.length)
            resume(q[0][0], q[0][1]); }
    }
    function __asyncDelegator(o) {
        var i, p;
        return i = {}, verb("next"), verb("throw", function (e) { throw e; }), verb("return"), i[Symbol.iterator] = function () { return this; }, i;
        function verb(n, f) { i[n] = o[n] ? function (v) { return (p = !p) ? { value: __await(o[n](v)), done: n === "return" } : f ? f(v) : v; } : f; }
    }
    function __asyncValues(o) {
        if (!Symbol.asyncIterator)
            throw new TypeError("Symbol.asyncIterator is not defined.");
        var m = o[Symbol.asyncIterator], i;
        return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
        function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
        function settle(resolve, reject, d, v) { Promise.resolve(v).then(function (v) { resolve({ value: v, done: d }); }, reject); }
    }
    function __makeTemplateObject(cooked, raw) {
        if (Object.defineProperty) {
            Object.defineProperty(cooked, "raw", { value: raw });
        }
        else {
            cooked.raw = raw;
        }
        return cooked;
    }
    ;
    var __setModuleDefault = Object.create ? (function (o, v) {
        Object.defineProperty(o, "default", { enumerable: true, value: v });
    }) : function (o, v) {
        o["default"] = v;
    };
    function __importStar(mod) {
        if (mod && mod.__esModule)
            return mod;
        var result = {};
        if (mod != null)
            for (var k in mod)
                if (Object.hasOwnProperty.call(mod, k))
                    __createBinding(result, mod, k);
        __setModuleDefault(result, mod);
        return result;
    }
    function __importDefault(mod) {
        return (mod && mod.__esModule) ? mod : { default: mod };
    }
    function __classPrivateFieldGet(receiver, privateMap) {
        if (!privateMap.has(receiver)) {
            throw new TypeError("attempted to get private field on non-instance");
        }
        return privateMap.get(receiver);
    }
    function __classPrivateFieldSet(receiver, privateMap, value) {
        if (!privateMap.has(receiver)) {
            throw new TypeError("attempted to set private field on non-instance");
        }
        privateMap.set(receiver, value);
        return value;
    }

    // see: https://developer.mozilla.org/en-US/docs/Web/API/WindowBase64/Base64_encoding_and_decoding#The_.22Unicode_Problem.22
    function b64DecodeUnicode(str) {
        var base64 = str.replace(/\-/g, '+').replace(/\_/g, '/');
        return decodeURIComponent(atob(base64)
            .split('')
            .map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
            .join(''));
    }
    function base64UrlEncode(str) {
        var base64 = btoa(str);
        return base64
            .replace(/\+/g, '-')
            .replace(/\//g, '_')
            .replace(/=/g, '');
    }

    /**
     * Interface for Handlers that are hooked in to
     * validate tokens.
     */
    var ValidationHandler = /** @class */ (function () {
        function ValidationHandler() {
        }
        return ValidationHandler;
    }());
    /**
     * This abstract implementation of ValidationHandler already implements
     * the method validateAtHash. However, to make use of it,
     * you have to override the method calcHash.
     */
    var AbstractValidationHandler = /** @class */ (function () {
        function AbstractValidationHandler() {
        }
        /**
         * Validates the at_hash in an id_token against the received access_token.
         */
        AbstractValidationHandler.prototype.validateAtHash = function (params) {
            return __awaiter(this, void 0, void 0, function () {
                var hashAlg, tokenHash, leftMostHalf, atHash, claimsAtHash;
                return __generator(this, function (_a) {
                    switch (_a.label) {
                        case 0:
                            hashAlg = this.inferHashAlgorithm(params.idTokenHeader);
                            return [4 /*yield*/, this.calcHash(params.accessToken, hashAlg)];
                        case 1:
                            tokenHash = _a.sent();
                            leftMostHalf = tokenHash.substr(0, tokenHash.length / 2);
                            atHash = base64UrlEncode(leftMostHalf);
                            claimsAtHash = params.idTokenClaims['at_hash'].replace(/=/g, '');
                            if (atHash !== claimsAtHash) {
                                console.error('exptected at_hash: ' + atHash);
                                console.error('actual at_hash: ' + claimsAtHash);
                            }
                            return [2 /*return*/, atHash === claimsAtHash];
                    }
                });
            });
        };
        /**
         * Infers the name of the hash algorithm to use
         * from the alg field of an id_token.
         *
         * @param jwtHeader the id_token's parsed header
         */
        AbstractValidationHandler.prototype.inferHashAlgorithm = function (jwtHeader) {
            var alg = jwtHeader['alg'];
            if (!alg.match(/^.S[0-9]{3}$/)) {
                throw new Error('Algorithm not supported: ' + alg);
            }
            return 'sha-' + alg.substr(2);
        };
        return AbstractValidationHandler;
    }());

    var UrlHelperService = /** @class */ (function () {
        function UrlHelperService() {
        }
        UrlHelperService.prototype.getHashFragmentParams = function (customHashFragment) {
            var hash = customHashFragment || window.location.hash;
            hash = decodeURIComponent(hash);
            if (hash.indexOf('#') !== 0) {
                return {};
            }
            var questionMarkPosition = hash.indexOf('?');
            if (questionMarkPosition > -1) {
                hash = hash.substr(questionMarkPosition + 1);
            }
            else {
                hash = hash.substr(1);
            }
            return this.parseQueryString(hash);
        };
        UrlHelperService.prototype.parseQueryString = function (queryString) {
            var data = {};
            var pairs, pair, separatorIndex, escapedKey, escapedValue, key, value;
            if (queryString === null) {
                return data;
            }
            pairs = queryString.split('&');
            for (var i = 0; i < pairs.length; i++) {
                pair = pairs[i];
                separatorIndex = pair.indexOf('=');
                if (separatorIndex === -1) {
                    escapedKey = pair;
                    escapedValue = null;
                }
                else {
                    escapedKey = pair.substr(0, separatorIndex);
                    escapedValue = pair.substr(separatorIndex + 1);
                }
                key = decodeURIComponent(escapedKey);
                value = decodeURIComponent(escapedValue);
                if (key.substr(0, 1) === '/') {
                    key = key.substr(1);
                }
                data[key] = value;
            }
            return data;
        };
        return UrlHelperService;
    }());
    UrlHelperService.decorators = [
        { type: core.Injectable }
    ];

    var OAuthEvent = /** @class */ (function () {
        function OAuthEvent(type) {
            this.type = type;
        }
        return OAuthEvent;
    }());
    var OAuthSuccessEvent = /** @class */ (function (_super) {
        __extends(OAuthSuccessEvent, _super);
        function OAuthSuccessEvent(type, info) {
            if (info === void 0) { info = null; }
            var _this = _super.call(this, type) || this;
            _this.info = info;
            return _this;
        }
        return OAuthSuccessEvent;
    }(OAuthEvent));
    var OAuthInfoEvent = /** @class */ (function (_super) {
        __extends(OAuthInfoEvent, _super);
        function OAuthInfoEvent(type, info) {
            if (info === void 0) { info = null; }
            var _this = _super.call(this, type) || this;
            _this.info = info;
            return _this;
        }
        return OAuthInfoEvent;
    }(OAuthEvent));
    var OAuthErrorEvent = /** @class */ (function (_super) {
        __extends(OAuthErrorEvent, _super);
        function OAuthErrorEvent(type, reason, params) {
            if (params === void 0) { params = null; }
            var _this = _super.call(this, type) || this;
            _this.reason = reason;
            _this.params = params;
            return _this;
        }
        return OAuthErrorEvent;
    }(OAuthEvent));

    var AuthConfig = /** @class */ (function () {
        function AuthConfig(json) {
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
            this.openUri = function (uri) {
                location.href = uri;
            };
            if (json) {
                Object.assign(this, json);
            }
        }
        return AuthConfig;
    }());

    /**
     * This custom encoder allows charactes like +, % and / to be used in passwords
     */
    var WebHttpUrlEncodingCodec = /** @class */ (function () {
        function WebHttpUrlEncodingCodec() {
        }
        WebHttpUrlEncodingCodec.prototype.encodeKey = function (k) {
            return encodeURIComponent(k);
        };
        WebHttpUrlEncodingCodec.prototype.encodeValue = function (v) {
            return encodeURIComponent(v);
        };
        WebHttpUrlEncodingCodec.prototype.decodeKey = function (k) {
            return decodeURIComponent(k);
        };
        WebHttpUrlEncodingCodec.prototype.decodeValue = function (v) {
            return decodeURIComponent(v);
        };
        return WebHttpUrlEncodingCodec;
    }());

    /**
     * [js-sha256]{@link https://github.com/emn178/js-sha256}
     *
     * @version 0.9.0
     * @author Chen, Yi-Cyuan [emn178@gmail.com]
     * @copyright Chen, Yi-Cyuan 2014-2017
     * @license MIT
     */
    /*jslint bitwise: true */
    var ERROR = 'input is invalid type';
    var WINDOW = typeof window === 'object';
    var root = WINDOW ? window : {};
    if (root.JS_SHA256_NO_WINDOW) {
        WINDOW = false;
    }
    var WEB_WORKER = !WINDOW && typeof self === 'object';
    var NODE_JS = !root.JS_SHA256_NO_NODE_JS && typeof process === 'object' && process.versions && process.versions.node;
    if (NODE_JS) {
        root = global;
    }
    else if (WEB_WORKER) {
        root = self;
    }
    var COMMON_JS = !root.JS_SHA256_NO_COMMON_JS && typeof module === 'object' && module.exports;
    var AMD = typeof define === 'function' && define.amd;
    var ARRAY_BUFFER = !root.JS_SHA256_NO_ARRAY_BUFFER && typeof ArrayBuffer !== 'undefined';
    var HEX_CHARS = '0123456789abcdef'.split('');
    var EXTRA = [-2147483648, 8388608, 32768, 128];
    var SHIFT = [24, 16, 8, 0];
    var K = [
        0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    ];
    var OUTPUT_TYPES = ['hex', 'array', 'digest', 'arrayBuffer'];
    var blocks = [];
    if (root.JS_SHA256_NO_NODE_JS || !Array.isArray) {
        Array.isArray = function (obj) {
            return Object.prototype.toString.call(obj) === '[object Array]';
        };
    }
    if (ARRAY_BUFFER && (root.JS_SHA256_NO_ARRAY_BUFFER_IS_VIEW || !ArrayBuffer.isView)) {
        ArrayBuffer.isView = function (obj) {
            return typeof obj === 'object' && obj.buffer && obj.buffer.constructor === ArrayBuffer;
        };
    }
    var createOutputMethod = function (outputType, is224) {
        return function (message) {
            return new Sha256(is224, true).update(message)[outputType]();
        };
    };
    var ɵ0 = createOutputMethod;
    var createMethod = function (is224) {
        var method = createOutputMethod('hex', is224);
        if (NODE_JS) {
            method = nodeWrap(method, is224);
        }
        method.create = function () {
            return new Sha256(is224);
        };
        method.update = function (message) {
            return method.create().update(message);
        };
        for (var i = 0; i < OUTPUT_TYPES.length; ++i) {
            var type = OUTPUT_TYPES[i];
            method[type] = createOutputMethod(type, is224);
        }
        return method;
    };
    var ɵ1 = createMethod;
    var nodeWrap = function (method, is224) {
        var crypto = eval("require('crypto')");
        var Buffer = eval("require('buffer').Buffer");
        var algorithm = is224 ? 'sha224' : 'sha256';
        var nodeMethod = function (message) {
            if (typeof message === 'string') {
                return crypto.createHash(algorithm).update(message, 'utf8').digest('hex');
            }
            else {
                if (message === null || message === undefined) {
                    throw new Error(ERROR);
                }
                else if (message.constructor === ArrayBuffer) {
                    message = new Uint8Array(message);
                }
            }
            if (Array.isArray(message) || ArrayBuffer.isView(message) ||
                message.constructor === Buffer) {
                return crypto.createHash(algorithm).update(new Buffer(message)).digest('hex');
            }
            else {
                return method(message);
            }
        };
        return nodeMethod;
    };
    var ɵ2 = nodeWrap;
    var createHmacOutputMethod = function (outputType, is224) {
        return function (key, message) {
            return new HmacSha256(key, is224, true).update(message)[outputType]();
        };
    };
    var ɵ3 = createHmacOutputMethod;
    var createHmacMethod = function (is224) {
        var method = createHmacOutputMethod('hex', is224);
        method.create = function (key) {
            return new HmacSha256(key, is224);
        };
        method.update = function (key, message) {
            return method.create(key).update(message);
        };
        for (var i = 0; i < OUTPUT_TYPES.length; ++i) {
            var type = OUTPUT_TYPES[i];
            method[type] = createHmacOutputMethod(type, is224);
        }
        return method;
    };
    var ɵ4 = createHmacMethod;
    function Sha256(is224, sharedMemory) {
        if (sharedMemory) {
            blocks[0] = blocks[16] = blocks[1] = blocks[2] = blocks[3] =
                blocks[4] = blocks[5] = blocks[6] = blocks[7] =
                    blocks[8] = blocks[9] = blocks[10] = blocks[11] =
                        blocks[12] = blocks[13] = blocks[14] = blocks[15] = 0;
            this.blocks = blocks;
        }
        else {
            this.blocks = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        }
        if (is224) {
            this.h0 = 0xc1059ed8;
            this.h1 = 0x367cd507;
            this.h2 = 0x3070dd17;
            this.h3 = 0xf70e5939;
            this.h4 = 0xffc00b31;
            this.h5 = 0x68581511;
            this.h6 = 0x64f98fa7;
            this.h7 = 0xbefa4fa4;
        }
        else { // 256
            this.h0 = 0x6a09e667;
            this.h1 = 0xbb67ae85;
            this.h2 = 0x3c6ef372;
            this.h3 = 0xa54ff53a;
            this.h4 = 0x510e527f;
            this.h5 = 0x9b05688c;
            this.h6 = 0x1f83d9ab;
            this.h7 = 0x5be0cd19;
        }
        this.block = this.start = this.bytes = this.hBytes = 0;
        this.finalized = this.hashed = false;
        this.first = true;
        this.is224 = is224;
    }
    Sha256.prototype.update = function (message) {
        if (this.finalized) {
            return;
        }
        var notString, type = typeof message;
        if (type !== 'string') {
            if (type === 'object') {
                if (message === null) {
                    throw new Error(ERROR);
                }
                else if (ARRAY_BUFFER && message.constructor === ArrayBuffer) {
                    message = new Uint8Array(message);
                }
                else if (!Array.isArray(message)) {
                    if (!ARRAY_BUFFER || !ArrayBuffer.isView(message)) {
                        throw new Error(ERROR);
                    }
                }
            }
            else {
                throw new Error(ERROR);
            }
            notString = true;
        }
        var code, index = 0, i, length = message.length, blocks = this.blocks;
        while (index < length) {
            if (this.hashed) {
                this.hashed = false;
                blocks[0] = this.block;
                blocks[16] = blocks[1] = blocks[2] = blocks[3] =
                    blocks[4] = blocks[5] = blocks[6] = blocks[7] =
                        blocks[8] = blocks[9] = blocks[10] = blocks[11] =
                            blocks[12] = blocks[13] = blocks[14] = blocks[15] = 0;
            }
            if (notString) {
                for (i = this.start; index < length && i < 64; ++index) {
                    blocks[i >> 2] |= message[index] << SHIFT[i++ & 3];
                }
            }
            else {
                for (i = this.start; index < length && i < 64; ++index) {
                    code = message.charCodeAt(index);
                    if (code < 0x80) {
                        blocks[i >> 2] |= code << SHIFT[i++ & 3];
                    }
                    else if (code < 0x800) {
                        blocks[i >> 2] |= (0xc0 | (code >> 6)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | (code & 0x3f)) << SHIFT[i++ & 3];
                    }
                    else if (code < 0xd800 || code >= 0xe000) {
                        blocks[i >> 2] |= (0xe0 | (code >> 12)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | ((code >> 6) & 0x3f)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | (code & 0x3f)) << SHIFT[i++ & 3];
                    }
                    else {
                        code = 0x10000 + (((code & 0x3ff) << 10) | (message.charCodeAt(++index) & 0x3ff));
                        blocks[i >> 2] |= (0xf0 | (code >> 18)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | ((code >> 12) & 0x3f)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | ((code >> 6) & 0x3f)) << SHIFT[i++ & 3];
                        blocks[i >> 2] |= (0x80 | (code & 0x3f)) << SHIFT[i++ & 3];
                    }
                }
            }
            this.lastByteIndex = i;
            this.bytes += i - this.start;
            if (i >= 64) {
                this.block = blocks[16];
                this.start = i - 64;
                this.hash();
                this.hashed = true;
            }
            else {
                this.start = i;
            }
        }
        if (this.bytes > 4294967295) {
            this.hBytes += this.bytes / 4294967296 << 0;
            this.bytes = this.bytes % 4294967296;
        }
        return this;
    };
    Sha256.prototype.finalize = function () {
        if (this.finalized) {
            return;
        }
        this.finalized = true;
        var blocks = this.blocks, i = this.lastByteIndex;
        blocks[16] = this.block;
        blocks[i >> 2] |= EXTRA[i & 3];
        this.block = blocks[16];
        if (i >= 56) {
            if (!this.hashed) {
                this.hash();
            }
            blocks[0] = this.block;
            blocks[16] = blocks[1] = blocks[2] = blocks[3] =
                blocks[4] = blocks[5] = blocks[6] = blocks[7] =
                    blocks[8] = blocks[9] = blocks[10] = blocks[11] =
                        blocks[12] = blocks[13] = blocks[14] = blocks[15] = 0;
        }
        blocks[14] = this.hBytes << 3 | this.bytes >>> 29;
        blocks[15] = this.bytes << 3;
        this.hash();
    };
    Sha256.prototype.hash = function () {
        var a = this.h0, b = this.h1, c = this.h2, d = this.h3, e = this.h4, f = this.h5, g = this.h6, h = this.h7, blocks = this.blocks, j, s0, s1, maj, t1, t2, ch, ab, da, cd, bc;
        for (j = 16; j < 64; ++j) {
            // rightrotate
            t1 = blocks[j - 15];
            s0 = ((t1 >>> 7) | (t1 << 25)) ^ ((t1 >>> 18) | (t1 << 14)) ^ (t1 >>> 3);
            t1 = blocks[j - 2];
            s1 = ((t1 >>> 17) | (t1 << 15)) ^ ((t1 >>> 19) | (t1 << 13)) ^ (t1 >>> 10);
            blocks[j] = blocks[j - 16] + s0 + blocks[j - 7] + s1 << 0;
        }
        bc = b & c;
        for (j = 0; j < 64; j += 4) {
            if (this.first) {
                if (this.is224) {
                    ab = 300032;
                    t1 = blocks[0] - 1413257819;
                    h = t1 - 150054599 << 0;
                    d = t1 + 24177077 << 0;
                }
                else {
                    ab = 704751109;
                    t1 = blocks[0] - 210244248;
                    h = t1 - 1521486534 << 0;
                    d = t1 + 143694565 << 0;
                }
                this.first = false;
            }
            else {
                s0 = ((a >>> 2) | (a << 30)) ^ ((a >>> 13) | (a << 19)) ^ ((a >>> 22) | (a << 10));
                s1 = ((e >>> 6) | (e << 26)) ^ ((e >>> 11) | (e << 21)) ^ ((e >>> 25) | (e << 7));
                ab = a & b;
                maj = ab ^ (a & c) ^ bc;
                ch = (e & f) ^ (~e & g);
                t1 = h + s1 + ch + K[j] + blocks[j];
                t2 = s0 + maj;
                h = d + t1 << 0;
                d = t1 + t2 << 0;
            }
            s0 = ((d >>> 2) | (d << 30)) ^ ((d >>> 13) | (d << 19)) ^ ((d >>> 22) | (d << 10));
            s1 = ((h >>> 6) | (h << 26)) ^ ((h >>> 11) | (h << 21)) ^ ((h >>> 25) | (h << 7));
            da = d & a;
            maj = da ^ (d & b) ^ ab;
            ch = (h & e) ^ (~h & f);
            t1 = g + s1 + ch + K[j + 1] + blocks[j + 1];
            t2 = s0 + maj;
            g = c + t1 << 0;
            c = t1 + t2 << 0;
            s0 = ((c >>> 2) | (c << 30)) ^ ((c >>> 13) | (c << 19)) ^ ((c >>> 22) | (c << 10));
            s1 = ((g >>> 6) | (g << 26)) ^ ((g >>> 11) | (g << 21)) ^ ((g >>> 25) | (g << 7));
            cd = c & d;
            maj = cd ^ (c & a) ^ da;
            ch = (g & h) ^ (~g & e);
            t1 = f + s1 + ch + K[j + 2] + blocks[j + 2];
            t2 = s0 + maj;
            f = b + t1 << 0;
            b = t1 + t2 << 0;
            s0 = ((b >>> 2) | (b << 30)) ^ ((b >>> 13) | (b << 19)) ^ ((b >>> 22) | (b << 10));
            s1 = ((f >>> 6) | (f << 26)) ^ ((f >>> 11) | (f << 21)) ^ ((f >>> 25) | (f << 7));
            bc = b & c;
            maj = bc ^ (b & d) ^ cd;
            ch = (f & g) ^ (~f & h);
            t1 = e + s1 + ch + K[j + 3] + blocks[j + 3];
            t2 = s0 + maj;
            e = a + t1 << 0;
            a = t1 + t2 << 0;
        }
        this.h0 = this.h0 + a << 0;
        this.h1 = this.h1 + b << 0;
        this.h2 = this.h2 + c << 0;
        this.h3 = this.h3 + d << 0;
        this.h4 = this.h4 + e << 0;
        this.h5 = this.h5 + f << 0;
        this.h6 = this.h6 + g << 0;
        this.h7 = this.h7 + h << 0;
    };
    Sha256.prototype.hex = function () {
        this.finalize();
        var h0 = this.h0, h1 = this.h1, h2 = this.h2, h3 = this.h3, h4 = this.h4, h5 = this.h5, h6 = this.h6, h7 = this.h7;
        var hex = HEX_CHARS[(h0 >> 28) & 0x0F] + HEX_CHARS[(h0 >> 24) & 0x0F] +
            HEX_CHARS[(h0 >> 20) & 0x0F] + HEX_CHARS[(h0 >> 16) & 0x0F] +
            HEX_CHARS[(h0 >> 12) & 0x0F] + HEX_CHARS[(h0 >> 8) & 0x0F] +
            HEX_CHARS[(h0 >> 4) & 0x0F] + HEX_CHARS[h0 & 0x0F] +
            HEX_CHARS[(h1 >> 28) & 0x0F] + HEX_CHARS[(h1 >> 24) & 0x0F] +
            HEX_CHARS[(h1 >> 20) & 0x0F] + HEX_CHARS[(h1 >> 16) & 0x0F] +
            HEX_CHARS[(h1 >> 12) & 0x0F] + HEX_CHARS[(h1 >> 8) & 0x0F] +
            HEX_CHARS[(h1 >> 4) & 0x0F] + HEX_CHARS[h1 & 0x0F] +
            HEX_CHARS[(h2 >> 28) & 0x0F] + HEX_CHARS[(h2 >> 24) & 0x0F] +
            HEX_CHARS[(h2 >> 20) & 0x0F] + HEX_CHARS[(h2 >> 16) & 0x0F] +
            HEX_CHARS[(h2 >> 12) & 0x0F] + HEX_CHARS[(h2 >> 8) & 0x0F] +
            HEX_CHARS[(h2 >> 4) & 0x0F] + HEX_CHARS[h2 & 0x0F] +
            HEX_CHARS[(h3 >> 28) & 0x0F] + HEX_CHARS[(h3 >> 24) & 0x0F] +
            HEX_CHARS[(h3 >> 20) & 0x0F] + HEX_CHARS[(h3 >> 16) & 0x0F] +
            HEX_CHARS[(h3 >> 12) & 0x0F] + HEX_CHARS[(h3 >> 8) & 0x0F] +
            HEX_CHARS[(h3 >> 4) & 0x0F] + HEX_CHARS[h3 & 0x0F] +
            HEX_CHARS[(h4 >> 28) & 0x0F] + HEX_CHARS[(h4 >> 24) & 0x0F] +
            HEX_CHARS[(h4 >> 20) & 0x0F] + HEX_CHARS[(h4 >> 16) & 0x0F] +
            HEX_CHARS[(h4 >> 12) & 0x0F] + HEX_CHARS[(h4 >> 8) & 0x0F] +
            HEX_CHARS[(h4 >> 4) & 0x0F] + HEX_CHARS[h4 & 0x0F] +
            HEX_CHARS[(h5 >> 28) & 0x0F] + HEX_CHARS[(h5 >> 24) & 0x0F] +
            HEX_CHARS[(h5 >> 20) & 0x0F] + HEX_CHARS[(h5 >> 16) & 0x0F] +
            HEX_CHARS[(h5 >> 12) & 0x0F] + HEX_CHARS[(h5 >> 8) & 0x0F] +
            HEX_CHARS[(h5 >> 4) & 0x0F] + HEX_CHARS[h5 & 0x0F] +
            HEX_CHARS[(h6 >> 28) & 0x0F] + HEX_CHARS[(h6 >> 24) & 0x0F] +
            HEX_CHARS[(h6 >> 20) & 0x0F] + HEX_CHARS[(h6 >> 16) & 0x0F] +
            HEX_CHARS[(h6 >> 12) & 0x0F] + HEX_CHARS[(h6 >> 8) & 0x0F] +
            HEX_CHARS[(h6 >> 4) & 0x0F] + HEX_CHARS[h6 & 0x0F];
        if (!this.is224) {
            hex += HEX_CHARS[(h7 >> 28) & 0x0F] + HEX_CHARS[(h7 >> 24) & 0x0F] +
                HEX_CHARS[(h7 >> 20) & 0x0F] + HEX_CHARS[(h7 >> 16) & 0x0F] +
                HEX_CHARS[(h7 >> 12) & 0x0F] + HEX_CHARS[(h7 >> 8) & 0x0F] +
                HEX_CHARS[(h7 >> 4) & 0x0F] + HEX_CHARS[h7 & 0x0F];
        }
        return hex;
    };
    Sha256.prototype.toString = Sha256.prototype.hex;
    Sha256.prototype.digest = function () {
        this.finalize();
        var h0 = this.h0, h1 = this.h1, h2 = this.h2, h3 = this.h3, h4 = this.h4, h5 = this.h5, h6 = this.h6, h7 = this.h7;
        var arr = [
            (h0 >> 24) & 0xFF, (h0 >> 16) & 0xFF, (h0 >> 8) & 0xFF, h0 & 0xFF,
            (h1 >> 24) & 0xFF, (h1 >> 16) & 0xFF, (h1 >> 8) & 0xFF, h1 & 0xFF,
            (h2 >> 24) & 0xFF, (h2 >> 16) & 0xFF, (h2 >> 8) & 0xFF, h2 & 0xFF,
            (h3 >> 24) & 0xFF, (h3 >> 16) & 0xFF, (h3 >> 8) & 0xFF, h3 & 0xFF,
            (h4 >> 24) & 0xFF, (h4 >> 16) & 0xFF, (h4 >> 8) & 0xFF, h4 & 0xFF,
            (h5 >> 24) & 0xFF, (h5 >> 16) & 0xFF, (h5 >> 8) & 0xFF, h5 & 0xFF,
            (h6 >> 24) & 0xFF, (h6 >> 16) & 0xFF, (h6 >> 8) & 0xFF, h6 & 0xFF
        ];
        if (!this.is224) {
            arr.push((h7 >> 24) & 0xFF, (h7 >> 16) & 0xFF, (h7 >> 8) & 0xFF, h7 & 0xFF);
        }
        return arr;
    };
    Sha256.prototype.array = Sha256.prototype.digest;
    Sha256.prototype.arrayBuffer = function () {
        this.finalize();
        var buffer = new ArrayBuffer(this.is224 ? 28 : 32);
        var dataView = new DataView(buffer);
        dataView.setUint32(0, this.h0);
        dataView.setUint32(4, this.h1);
        dataView.setUint32(8, this.h2);
        dataView.setUint32(12, this.h3);
        dataView.setUint32(16, this.h4);
        dataView.setUint32(20, this.h5);
        dataView.setUint32(24, this.h6);
        if (!this.is224) {
            dataView.setUint32(28, this.h7);
        }
        return buffer;
    };
    function HmacSha256(key, is224, sharedMemory) {
        var i, type = typeof key;
        if (type === 'string') {
            var bytes = [], length = key.length, index = 0, code;
            for (i = 0; i < length; ++i) {
                code = key.charCodeAt(i);
                if (code < 0x80) {
                    bytes[index++] = code;
                }
                else if (code < 0x800) {
                    bytes[index++] = (0xc0 | (code >> 6));
                    bytes[index++] = (0x80 | (code & 0x3f));
                }
                else if (code < 0xd800 || code >= 0xe000) {
                    bytes[index++] = (0xe0 | (code >> 12));
                    bytes[index++] = (0x80 | ((code >> 6) & 0x3f));
                    bytes[index++] = (0x80 | (code & 0x3f));
                }
                else {
                    code = 0x10000 + (((code & 0x3ff) << 10) | (key.charCodeAt(++i) & 0x3ff));
                    bytes[index++] = (0xf0 | (code >> 18));
                    bytes[index++] = (0x80 | ((code >> 12) & 0x3f));
                    bytes[index++] = (0x80 | ((code >> 6) & 0x3f));
                    bytes[index++] = (0x80 | (code & 0x3f));
                }
            }
            key = bytes;
        }
        else {
            if (type === 'object') {
                if (key === null) {
                    throw new Error(ERROR);
                }
                else if (ARRAY_BUFFER && key.constructor === ArrayBuffer) {
                    key = new Uint8Array(key);
                }
                else if (!Array.isArray(key)) {
                    if (!ARRAY_BUFFER || !ArrayBuffer.isView(key)) {
                        throw new Error(ERROR);
                    }
                }
            }
            else {
                throw new Error(ERROR);
            }
        }
        if (key.length > 64) {
            key = (new Sha256(is224, true)).update(key).array();
        }
        var oKeyPad = [], iKeyPad = [];
        for (i = 0; i < 64; ++i) {
            var b = key[i] || 0;
            oKeyPad[i] = 0x5c ^ b;
            iKeyPad[i] = 0x36 ^ b;
        }
        Sha256.call(this, is224, sharedMemory);
        this.update(iKeyPad);
        this.oKeyPad = oKeyPad;
        this.inner = true;
        this.sharedMemory = sharedMemory;
    }
    HmacSha256.prototype = new Sha256();
    HmacSha256.prototype.finalize = function () {
        Sha256.prototype.finalize.call(this);
        if (this.inner) {
            this.inner = false;
            var innerHash = this.array();
            Sha256.call(this, this.is224, this.sharedMemory);
            this.update(this.oKeyPad);
            this.update(innerHash);
            Sha256.prototype.finalize.call(this);
        }
    };
    var exports$1 = createMethod();
    exports$1.sha256 = exports$1;
    exports$1.sha224 = createMethod(true);
    exports$1.sha256.hmac = createHmacMethod();
    exports$1.sha224.hmac = createHmacMethod(true);

    /**
     * Abstraction for crypto algorithms
     */
    var HashHandler = /** @class */ (function () {
        function HashHandler() {
        }
        return HashHandler;
    }());
    var DefaultHashHandler = /** @class */ (function () {
        function DefaultHashHandler() {
        }
        DefaultHashHandler.prototype.calcHash = function (valueToHash, algorithm) {
            return __awaiter(this, void 0, void 0, function () {
                var hashArray, hashString;
                return __generator(this, function (_a) {
                    hashArray = exports$1.array(valueToHash);
                    hashString = this.toHashString2(hashArray);
                    return [2 /*return*/, hashString];
                });
            });
        };
        DefaultHashHandler.prototype.toHashString2 = function (byteArray) {
            var e_1, _a;
            var result = '';
            try {
                for (var byteArray_1 = __values(byteArray), byteArray_1_1 = byteArray_1.next(); !byteArray_1_1.done; byteArray_1_1 = byteArray_1.next()) {
                    var e = byteArray_1_1.value;
                    result += String.fromCharCode(e);
                }
            }
            catch (e_1_1) { e_1 = { error: e_1_1 }; }
            finally {
                try {
                    if (byteArray_1_1 && !byteArray_1_1.done && (_a = byteArray_1.return)) _a.call(byteArray_1);
                }
                finally { if (e_1) throw e_1.error; }
            }
            return result;
        };
        DefaultHashHandler.prototype.toHashString = function (buffer) {
            var e_2, _a;
            var byteArray = new Uint8Array(buffer);
            var result = '';
            try {
                for (var byteArray_2 = __values(byteArray), byteArray_2_1 = byteArray_2.next(); !byteArray_2_1.done; byteArray_2_1 = byteArray_2.next()) {
                    var e = byteArray_2_1.value;
                    result += String.fromCharCode(e);
                }
            }
            catch (e_2_1) { e_2 = { error: e_2_1 }; }
            finally {
                try {
                    if (byteArray_2_1 && !byteArray_2_1.done && (_a = byteArray_2.return)) _a.call(byteArray_2);
                }
                finally { if (e_2) throw e_2.error; }
            }
            return result;
        };
        return DefaultHashHandler;
    }());
    DefaultHashHandler.decorators = [
        { type: core.Injectable }
    ];

    /**
     * Service for logging in and logging out with
     * OIDC and OAuth2. Supports implicit flow and
     * password flow.
     */
    var OAuthService = /** @class */ (function (_super) {
        __extends(OAuthService, _super);
        function OAuthService(ngZone, http, storage, tokenValidationHandler, config, urlHelper, logger, crypto, document) {
            var _this = this;
            var _a;
            _this = _super.call(this) || this;
            _this.ngZone = ngZone;
            _this.http = http;
            _this.config = config;
            _this.urlHelper = urlHelper;
            _this.logger = logger;
            _this.crypto = crypto;
            /**
             * @internal
             * Deprecated:  use property events instead
             */
            _this.discoveryDocumentLoaded = false;
            /**
             * The received (passed around) state, when logging
             * in with implicit flow.
             */
            _this.state = '';
            _this.eventsSubject = new rxjs.Subject();
            _this.discoveryDocumentLoadedSubject = new rxjs.Subject();
            _this.grantTypesSupported = [];
            _this.inImplicitFlow = false;
            _this.saveNoncesInLocalStorage = false;
            _this.debug('angular-oauth2-oidc v10');
            // See https://github.com/manfredsteyer/angular-oauth2-oidc/issues/773 for why this is needed
            _this.document = document;
            if (!config) {
                config = {};
            }
            _this.discoveryDocumentLoaded$ = _this.discoveryDocumentLoadedSubject.asObservable();
            _this.events = _this.eventsSubject.asObservable();
            if (tokenValidationHandler) {
                _this.tokenValidationHandler = tokenValidationHandler;
            }
            if (config) {
                _this.configure(config);
            }
            try {
                if (storage) {
                    _this.setStorage(storage);
                }
                else if (typeof sessionStorage !== 'undefined') {
                    _this.setStorage(sessionStorage);
                }
            }
            catch (e) {
                console.error('No OAuthStorage provided and cannot access default (sessionStorage).' +
                    'Consider providing a custom OAuthStorage implementation in your module.', e);
            }
            // in IE, sessionStorage does not always survive a redirect
            if (typeof window !== 'undefined' &&
                typeof window['localStorage'] !== 'undefined') {
                var ua = (_a = window === null || window === void 0 ? void 0 : window.navigator) === null || _a === void 0 ? void 0 : _a.userAgent;
                var msie = (ua === null || ua === void 0 ? void 0 : ua.includes('MSIE ')) || (ua === null || ua === void 0 ? void 0 : ua.includes('Trident'));
                if (msie) {
                    _this.saveNoncesInLocalStorage = true;
                }
            }
            _this.setupRefreshTimer();
            return _this;
        }
        /**
         * Use this method to configure the service
         * @param config the configuration
         */
        OAuthService.prototype.configure = function (config) {
            // For the sake of downward compatibility with
            // original configuration API
            Object.assign(this, new AuthConfig(), config);
            this.config = Object.assign({}, new AuthConfig(), config);
            if (this.sessionChecksEnabled) {
                this.setupSessionCheck();
            }
            this.configChanged();
        };
        OAuthService.prototype.configChanged = function () {
            this.setupRefreshTimer();
        };
        OAuthService.prototype.restartSessionChecksIfStillLoggedIn = function () {
            if (this.hasValidIdToken()) {
                this.initSessionCheck();
            }
        };
        OAuthService.prototype.restartRefreshTimerIfStillLoggedIn = function () {
            this.setupExpirationTimers();
        };
        OAuthService.prototype.setupSessionCheck = function () {
            var _this = this;
            this.events.pipe(operators.filter(function (e) { return e.type === 'token_received'; })).subscribe(function (e) {
                _this.initSessionCheck();
            });
        };
        /**
         * Will setup up silent refreshing for when the token is
         * about to expire. When the user is logged out via this.logOut method, the
         * silent refreshing will pause and not refresh the tokens until the user is
         * logged back in via receiving a new token.
         * @param params Additional parameter to pass
         * @param listenTo Setup automatic refresh of a specific token type
         */
        OAuthService.prototype.setupAutomaticSilentRefresh = function (params, listenTo, noPrompt) {
            var _this = this;
            if (params === void 0) { params = {}; }
            if (noPrompt === void 0) { noPrompt = true; }
            var shouldRunSilentRefresh = true;
            this.events
                .pipe(operators.tap(function (e) {
                if (e.type === 'token_received') {
                    shouldRunSilentRefresh = true;
                }
                else if (e.type === 'logout') {
                    shouldRunSilentRefresh = false;
                }
            }), operators.filter(function (e) { return e.type === 'token_expires'; }), operators.debounceTime(1000))
                .subscribe(function (e) {
                var event = e;
                if ((listenTo == null || listenTo === 'any' || event.info === listenTo) &&
                    shouldRunSilentRefresh) {
                    // this.silentRefresh(params, noPrompt).catch(_ => {
                    _this.refreshInternal(params, noPrompt).catch(function (_) {
                        _this.debug('Automatic silent refresh did not work');
                    });
                }
            });
            this.restartRefreshTimerIfStillLoggedIn();
        };
        OAuthService.prototype.refreshInternal = function (params, noPrompt) {
            if (!this.useSilentRefresh && this.responseType === 'code') {
                return this.refreshToken();
            }
            else {
                return this.silentRefresh(params, noPrompt);
            }
        };
        /**
         * Convenience method that first calls `loadDiscoveryDocument(...)` and
         * directly chains using the `then(...)` part of the promise to call
         * the `tryLogin(...)` method.
         *
         * @param options LoginOptions to pass through to `tryLogin(...)`
         */
        OAuthService.prototype.loadDiscoveryDocumentAndTryLogin = function (options) {
            var _this = this;
            if (options === void 0) { options = null; }
            return this.loadDiscoveryDocument().then(function (doc) {
                return _this.tryLogin(options);
            });
        };
        /**
         * Convenience method that first calls `loadDiscoveryDocumentAndTryLogin(...)`
         * and if then chains to `initLoginFlow()`, but only if there is no valid
         * IdToken or no valid AccessToken.
         *
         * @param options LoginOptions to pass through to `tryLogin(...)`
         */
        OAuthService.prototype.loadDiscoveryDocumentAndLogin = function (options) {
            var _this = this;
            if (options === void 0) { options = null; }
            options = options || {};
            return this.loadDiscoveryDocumentAndTryLogin(options).then(function (_) {
                if (!_this.hasValidIdToken() || !_this.hasValidAccessToken()) {
                    var state = typeof options.state === 'string' ? options.state : '';
                    _this.initLoginFlow(state);
                    return false;
                }
                else {
                    return true;
                }
            });
        };
        OAuthService.prototype.debug = function () {
            var args = [];
            for (var _i = 0; _i < arguments.length; _i++) {
                args[_i] = arguments[_i];
            }
            if (this.showDebugInformation) {
                this.logger.debug.apply(this.logger, args);
            }
        };
        OAuthService.prototype.validateUrlFromDiscoveryDocument = function (url) {
            var errors = [];
            var httpsCheck = this.validateUrlForHttps(url);
            var issuerCheck = this.validateUrlAgainstIssuer(url);
            if (!httpsCheck) {
                errors.push('https for all urls required. Also for urls received by discovery.');
            }
            if (!issuerCheck) {
                errors.push('Every url in discovery document has to start with the issuer url.' +
                    'Also see property strictDiscoveryDocumentValidation.');
            }
            return errors;
        };
        OAuthService.prototype.validateUrlForHttps = function (url) {
            if (!url) {
                return true;
            }
            var lcUrl = url.toLowerCase();
            if (this.requireHttps === false) {
                return true;
            }
            if ((lcUrl.match(/^http:\/\/localhost($|[:\/])/) ||
                lcUrl.match(/^http:\/\/localhost($|[:\/])/)) &&
                this.requireHttps === 'remoteOnly') {
                return true;
            }
            return lcUrl.startsWith('https://');
        };
        OAuthService.prototype.assertUrlNotNullAndCorrectProtocol = function (url, description) {
            if (!url) {
                throw new Error("'" + description + "' should not be null");
            }
            if (!this.validateUrlForHttps(url)) {
                throw new Error("'" + description + "' must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
        };
        OAuthService.prototype.validateUrlAgainstIssuer = function (url) {
            if (!this.strictDiscoveryDocumentValidation) {
                return true;
            }
            if (!url) {
                return true;
            }
            return url.toLowerCase().startsWith(this.issuer.toLowerCase());
        };
        OAuthService.prototype.setupRefreshTimer = function () {
            var _this = this;
            if (typeof window === 'undefined') {
                this.debug('timer not supported on this plattform');
                return;
            }
            if (this.hasValidIdToken() || this.hasValidAccessToken()) {
                this.clearAccessTokenTimer();
                this.clearIdTokenTimer();
                this.setupExpirationTimers();
            }
            if (this.tokenReceivedSubscription)
                this.tokenReceivedSubscription.unsubscribe();
            this.tokenReceivedSubscription = this.events
                .pipe(operators.filter(function (e) { return e.type === 'token_received'; }))
                .subscribe(function (_) {
                _this.clearAccessTokenTimer();
                _this.clearIdTokenTimer();
                _this.setupExpirationTimers();
            });
        };
        OAuthService.prototype.setupExpirationTimers = function () {
            if (this.hasValidAccessToken()) {
                this.setupAccessTokenTimer();
            }
            if (this.hasValidIdToken()) {
                this.setupIdTokenTimer();
            }
        };
        OAuthService.prototype.setupAccessTokenTimer = function () {
            var _this = this;
            var expiration = this.getAccessTokenExpiration();
            var storedAt = this.getAccessTokenStoredAt();
            var timeout = this.calcTimeout(storedAt, expiration);
            this.ngZone.runOutsideAngular(function () {
                _this.accessTokenTimeoutSubscription = rxjs.of(new OAuthInfoEvent('token_expires', 'access_token'))
                    .pipe(operators.delay(timeout))
                    .subscribe(function (e) {
                    _this.ngZone.run(function () {
                        _this.eventsSubject.next(e);
                    });
                });
            });
        };
        OAuthService.prototype.setupIdTokenTimer = function () {
            var _this = this;
            var expiration = this.getIdTokenExpiration();
            var storedAt = this.getIdTokenStoredAt();
            var timeout = this.calcTimeout(storedAt, expiration);
            this.ngZone.runOutsideAngular(function () {
                _this.idTokenTimeoutSubscription = rxjs.of(new OAuthInfoEvent('token_expires', 'id_token'))
                    .pipe(operators.delay(timeout))
                    .subscribe(function (e) {
                    _this.ngZone.run(function () {
                        _this.eventsSubject.next(e);
                    });
                });
            });
        };
        /**
         * Stops timers for automatic refresh.
         * To restart it, call setupAutomaticSilentRefresh again.
         */
        OAuthService.prototype.stopAutomaticRefresh = function () {
            this.clearAccessTokenTimer();
            this.clearIdTokenTimer();
        };
        OAuthService.prototype.clearAccessTokenTimer = function () {
            if (this.accessTokenTimeoutSubscription) {
                this.accessTokenTimeoutSubscription.unsubscribe();
            }
        };
        OAuthService.prototype.clearIdTokenTimer = function () {
            if (this.idTokenTimeoutSubscription) {
                this.idTokenTimeoutSubscription.unsubscribe();
            }
        };
        OAuthService.prototype.calcTimeout = function (storedAt, expiration) {
            var now = Date.now();
            var delta = (expiration - storedAt) * this.timeoutFactor - (now - storedAt);
            return Math.max(0, delta);
        };
        /**
         * DEPRECATED. Use a provider for OAuthStorage instead:
         *
         * { provide: OAuthStorage, useFactory: oAuthStorageFactory }
         * export function oAuthStorageFactory(): OAuthStorage { return localStorage; }
         * Sets a custom storage used to store the received
         * tokens on client side. By default, the browser's
         * sessionStorage is used.
         * @ignore
         *
         * @param storage
         */
        OAuthService.prototype.setStorage = function (storage) {
            this._storage = storage;
            this.configChanged();
        };
        /**
         * Loads the discovery document to configure most
         * properties of this service. The url of the discovery
         * document is infered from the issuer's url according
         * to the OpenId Connect spec. To use another url you
         * can pass it to to optional parameter fullUrl.
         *
         * @param fullUrl
         */
        OAuthService.prototype.loadDiscoveryDocument = function (fullUrl) {
            var _this = this;
            if (fullUrl === void 0) { fullUrl = null; }
            return new Promise(function (resolve, reject) {
                if (!fullUrl) {
                    fullUrl = _this.issuer || '';
                    if (!fullUrl.endsWith('/')) {
                        fullUrl += '/';
                    }
                    fullUrl += '.well-known/openid-configuration';
                }
                if (!_this.validateUrlForHttps(fullUrl)) {
                    reject("issuer  must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
                    return;
                }
                _this.http.get(fullUrl).subscribe(function (doc) {
                    if (!_this.validateDiscoveryDocument(doc)) {
                        _this.eventsSubject.next(new OAuthErrorEvent('discovery_document_validation_error', null));
                        reject('discovery_document_validation_error');
                        return;
                    }
                    _this.loginUrl = doc.authorization_endpoint;
                    _this.logoutUrl = doc.end_session_endpoint || _this.logoutUrl;
                    _this.grantTypesSupported = doc.grant_types_supported;
                    _this.issuer = doc.issuer;
                    _this.tokenEndpoint = doc.token_endpoint;
                    _this.userinfoEndpoint =
                        doc.userinfo_endpoint || _this.userinfoEndpoint;
                    _this.jwksUri = doc.jwks_uri;
                    _this.sessionCheckIFrameUrl =
                        doc.check_session_iframe || _this.sessionCheckIFrameUrl;
                    _this.discoveryDocumentLoaded = true;
                    _this.discoveryDocumentLoadedSubject.next(doc);
                    _this.revocationEndpoint = doc.revocation_endpoint;
                    if (_this.sessionChecksEnabled) {
                        _this.restartSessionChecksIfStillLoggedIn();
                    }
                    _this.loadJwks()
                        .then(function (jwks) {
                        var result = {
                            discoveryDocument: doc,
                            jwks: jwks
                        };
                        var event = new OAuthSuccessEvent('discovery_document_loaded', result);
                        _this.eventsSubject.next(event);
                        resolve(event);
                        return;
                    })
                        .catch(function (err) {
                        _this.eventsSubject.next(new OAuthErrorEvent('discovery_document_load_error', err));
                        reject(err);
                        return;
                    });
                }, function (err) {
                    _this.logger.error('error loading discovery document', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('discovery_document_load_error', err));
                    reject(err);
                });
            });
        };
        OAuthService.prototype.loadJwks = function () {
            var _this = this;
            return new Promise(function (resolve, reject) {
                if (_this.jwksUri) {
                    _this.http.get(_this.jwksUri).subscribe(function (jwks) {
                        _this.jwks = jwks;
                        _this.eventsSubject.next(new OAuthSuccessEvent('discovery_document_loaded'));
                        resolve(jwks);
                    }, function (err) {
                        _this.logger.error('error loading jwks', err);
                        _this.eventsSubject.next(new OAuthErrorEvent('jwks_load_error', err));
                        reject(err);
                    });
                }
                else {
                    resolve(null);
                }
            });
        };
        OAuthService.prototype.validateDiscoveryDocument = function (doc) {
            var errors;
            if (!this.skipIssuerCheck && doc.issuer !== this.issuer) {
                this.logger.error('invalid issuer in discovery document', 'expected: ' + this.issuer, 'current: ' + doc.issuer);
                return false;
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.authorization_endpoint);
            if (errors.length > 0) {
                this.logger.error('error validating authorization_endpoint in discovery document', errors);
                return false;
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.end_session_endpoint);
            if (errors.length > 0) {
                this.logger.error('error validating end_session_endpoint in discovery document', errors);
                return false;
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.token_endpoint);
            if (errors.length > 0) {
                this.logger.error('error validating token_endpoint in discovery document', errors);
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.revocation_endpoint);
            if (errors.length > 0) {
                this.logger.error('error validating revocation_endpoint in discovery document', errors);
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.userinfo_endpoint);
            if (errors.length > 0) {
                this.logger.error('error validating userinfo_endpoint in discovery document', errors);
                return false;
            }
            errors = this.validateUrlFromDiscoveryDocument(doc.jwks_uri);
            if (errors.length > 0) {
                this.logger.error('error validating jwks_uri in discovery document', errors);
                return false;
            }
            if (this.sessionChecksEnabled && !doc.check_session_iframe) {
                this.logger.warn('sessionChecksEnabled is activated but discovery document' +
                    ' does not contain a check_session_iframe field');
            }
            return true;
        };
        /**
         * Uses password flow to exchange userName and password for an
         * access_token. After receiving the access_token, this method
         * uses it to query the userinfo endpoint in order to get information
         * about the user in question.
         *
         * When using this, make sure that the property oidc is set to false.
         * Otherwise stricter validations take place that make this operation
         * fail.
         *
         * @param userName
         * @param password
         * @param headers Optional additional http-headers.
         */
        OAuthService.prototype.fetchTokenUsingPasswordFlowAndLoadUserProfile = function (userName, password, headers) {
            var _this = this;
            if (headers === void 0) { headers = new http.HttpHeaders(); }
            return this.fetchTokenUsingPasswordFlow(userName, password, headers).then(function () { return _this.loadUserProfile(); });
        };
        /**
         * Loads the user profile by accessing the user info endpoint defined by OpenId Connect.
         *
         * When using this with OAuth2 password flow, make sure that the property oidc is set to false.
         * Otherwise stricter validations take place that make this operation fail.
         */
        OAuthService.prototype.loadUserProfile = function () {
            var _this = this;
            if (!this.hasValidAccessToken()) {
                throw new Error('Can not load User Profile without access_token');
            }
            if (!this.validateUrlForHttps(this.userinfoEndpoint)) {
                throw new Error("userinfoEndpoint must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
            return new Promise(function (resolve, reject) {
                var headers = new http.HttpHeaders().set('Authorization', 'Bearer ' + _this.getAccessToken());
                _this.http
                    .get(_this.userinfoEndpoint, { headers: headers })
                    .subscribe(function (info) {
                    _this.debug('userinfo received', info);
                    var existingClaims = _this.getIdentityClaims() || {};
                    if (!_this.skipSubjectCheck) {
                        if (_this.oidc &&
                            (!existingClaims['sub'] || info.sub !== existingClaims['sub'])) {
                            var err = 'if property oidc is true, the received user-id (sub) has to be the user-id ' +
                                'of the user that has logged in with oidc.\n' +
                                'if you are not using oidc but just oauth2 password flow set oidc to false';
                            reject(err);
                            return;
                        }
                    }
                    info = Object.assign({}, existingClaims, info);
                    _this._storage.setItem('id_token_claims_obj', JSON.stringify(info));
                    _this.eventsSubject.next(new OAuthSuccessEvent('user_profile_loaded'));
                    resolve(info);
                }, function (err) {
                    _this.logger.error('error loading user info', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('user_profile_load_error', err));
                    reject(err);
                });
            });
        };
        /**
         * Uses password flow to exchange userName and password for an access_token.
         * @param userName
         * @param password
         * @param headers Optional additional http-headers.
         */
        OAuthService.prototype.fetchTokenUsingPasswordFlow = function (userName, password, headers) {
            var _this = this;
            if (headers === void 0) { headers = new http.HttpHeaders(); }
            this.assertUrlNotNullAndCorrectProtocol(this.tokenEndpoint, 'tokenEndpoint');
            return new Promise(function (resolve, reject) {
                var e_1, _b;
                /**
                 * A `HttpParameterCodec` that uses `encodeURIComponent` and `decodeURIComponent` to
                 * serialize and parse URL parameter keys and values.
                 *
                 * @stable
                 */
                var params = new http.HttpParams({ encoder: new WebHttpUrlEncodingCodec() })
                    .set('grant_type', 'password')
                    .set('scope', _this.scope)
                    .set('username', userName)
                    .set('password', password);
                if (_this.useHttpBasicAuth) {
                    var header = btoa(_this.clientId + ":" + _this.dummyClientSecret);
                    headers = headers.set('Authorization', 'Basic ' + header);
                }
                if (!_this.useHttpBasicAuth) {
                    params = params.set('client_id', _this.clientId);
                }
                if (!_this.useHttpBasicAuth && _this.dummyClientSecret) {
                    params = params.set('client_secret', _this.dummyClientSecret);
                }
                if (_this.customQueryParams) {
                    try {
                        for (var _c = __values(Object.getOwnPropertyNames(_this.customQueryParams)), _d = _c.next(); !_d.done; _d = _c.next()) {
                            var key = _d.value;
                            params = params.set(key, _this.customQueryParams[key]);
                        }
                    }
                    catch (e_1_1) { e_1 = { error: e_1_1 }; }
                    finally {
                        try {
                            if (_d && !_d.done && (_b = _c.return)) _b.call(_c);
                        }
                        finally { if (e_1) throw e_1.error; }
                    }
                }
                headers = headers.set('Content-Type', 'application/x-www-form-urlencoded');
                _this.http
                    .post(_this.tokenEndpoint, params, { headers: headers })
                    .subscribe(function (tokenResponse) {
                    _this.debug('tokenResponse', tokenResponse);
                    _this.storeAccessTokenResponse(tokenResponse.access_token, tokenResponse.refresh_token, tokenResponse.expires_in ||
                        _this.fallbackAccessTokenExpirationTimeInSec, tokenResponse.scope, _this.extractRecognizedCustomParameters(tokenResponse));
                    _this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                    resolve(tokenResponse);
                }, function (err) {
                    _this.logger.error('Error performing password flow', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('token_error', err));
                    reject(err);
                });
            });
        };
        /**
         * Refreshes the token using a refresh_token.
         * This does not work for implicit flow, b/c
         * there is no refresh_token in this flow.
         * A solution for this is provided by the
         * method silentRefresh.
         */
        OAuthService.prototype.refreshToken = function () {
            var _this = this;
            this.assertUrlNotNullAndCorrectProtocol(this.tokenEndpoint, 'tokenEndpoint');
            return new Promise(function (resolve, reject) {
                var e_2, _b;
                var params = new http.HttpParams()
                    .set('grant_type', 'refresh_token')
                    .set('scope', _this.scope)
                    .set('refresh_token', _this._storage.getItem('refresh_token'));
                var headers = new http.HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
                if (_this.useHttpBasicAuth) {
                    var header = btoa(_this.clientId + ":" + _this.dummyClientSecret);
                    headers = headers.set('Authorization', 'Basic ' + header);
                }
                if (!_this.useHttpBasicAuth) {
                    params = params.set('client_id', _this.clientId);
                }
                if (!_this.useHttpBasicAuth && _this.dummyClientSecret) {
                    params = params.set('client_secret', _this.dummyClientSecret);
                }
                if (_this.customQueryParams) {
                    try {
                        for (var _c = __values(Object.getOwnPropertyNames(_this.customQueryParams)), _d = _c.next(); !_d.done; _d = _c.next()) {
                            var key = _d.value;
                            params = params.set(key, _this.customQueryParams[key]);
                        }
                    }
                    catch (e_2_1) { e_2 = { error: e_2_1 }; }
                    finally {
                        try {
                            if (_d && !_d.done && (_b = _c.return)) _b.call(_c);
                        }
                        finally { if (e_2) throw e_2.error; }
                    }
                }
                _this.http
                    .post(_this.tokenEndpoint, params, { headers: headers })
                    .pipe(operators.switchMap(function (tokenResponse) {
                    if (tokenResponse.id_token) {
                        return rxjs.from(_this.processIdToken(tokenResponse.id_token, tokenResponse.access_token, true)).pipe(operators.tap(function (result) { return _this.storeIdToken(result); }), operators.map(function (_) { return tokenResponse; }));
                    }
                    else {
                        return rxjs.of(tokenResponse);
                    }
                }))
                    .subscribe(function (tokenResponse) {
                    _this.debug('refresh tokenResponse', tokenResponse);
                    _this.storeAccessTokenResponse(tokenResponse.access_token, tokenResponse.refresh_token, tokenResponse.expires_in ||
                        _this.fallbackAccessTokenExpirationTimeInSec, tokenResponse.scope, _this.extractRecognizedCustomParameters(tokenResponse));
                    _this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                    _this.eventsSubject.next(new OAuthSuccessEvent('token_refreshed'));
                    resolve(tokenResponse);
                }, function (err) {
                    _this.logger.error('Error refreshing token', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('token_refresh_error', err));
                    reject(err);
                });
            });
        };
        OAuthService.prototype.removeSilentRefreshEventListener = function () {
            if (this.silentRefreshPostMessageEventListener) {
                window.removeEventListener('message', this.silentRefreshPostMessageEventListener);
                this.silentRefreshPostMessageEventListener = null;
            }
        };
        OAuthService.prototype.setupSilentRefreshEventListener = function () {
            var _this = this;
            this.removeSilentRefreshEventListener();
            this.silentRefreshPostMessageEventListener = function (e) {
                var message = _this.processMessageEventMessage(e);
                _this.tryLogin({
                    customHashFragment: message,
                    preventClearHashAfterLogin: true,
                    customRedirectUri: _this.silentRefreshRedirectUri || _this.redirectUri
                }).catch(function (err) { return _this.debug('tryLogin during silent refresh failed', err); });
            };
            window.addEventListener('message', this.silentRefreshPostMessageEventListener);
        };
        /**
         * Performs a silent refresh for implicit flow.
         * Use this method to get new tokens when/before
         * the existing tokens expire.
         */
        OAuthService.prototype.silentRefresh = function (params, noPrompt) {
            var _this = this;
            if (params === void 0) { params = {}; }
            if (noPrompt === void 0) { noPrompt = true; }
            var claims = this.getIdentityClaims() || {};
            if (this.useIdTokenHintForSilentRefresh && this.hasValidIdToken()) {
                params['id_token_hint'] = this.getIdToken();
            }
            if (!this.validateUrlForHttps(this.loginUrl)) {
                throw new Error("loginUrl  must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
            if (typeof this.document === 'undefined') {
                throw new Error('silent refresh is not supported on this platform');
            }
            var existingIframe = this.document.getElementById(this.silentRefreshIFrameName);
            if (existingIframe) {
                this.document.body.removeChild(existingIframe);
            }
            this.silentRefreshSubject = claims['sub'];
            var iframe = this.document.createElement('iframe');
            iframe.id = this.silentRefreshIFrameName;
            this.setupSilentRefreshEventListener();
            var redirectUri = this.silentRefreshRedirectUri || this.redirectUri;
            this.createLoginUrl(null, null, redirectUri, noPrompt, params).then(function (url) {
                iframe.setAttribute('src', url);
                if (!_this.silentRefreshShowIFrame) {
                    iframe.style['display'] = 'none';
                }
                _this.document.body.appendChild(iframe);
            });
            var errors = this.events.pipe(operators.filter(function (e) { return e instanceof OAuthErrorEvent; }), operators.first());
            var success = this.events.pipe(operators.filter(function (e) { return e.type === 'token_received'; }), operators.first());
            var timeout = rxjs.of(new OAuthErrorEvent('silent_refresh_timeout', null)).pipe(operators.delay(this.silentRefreshTimeout));
            return rxjs.race([errors, success, timeout])
                .pipe(operators.map(function (e) {
                if (e instanceof OAuthErrorEvent) {
                    if (e.type === 'silent_refresh_timeout') {
                        _this.eventsSubject.next(e);
                    }
                    else {
                        e = new OAuthErrorEvent('silent_refresh_error', e);
                        _this.eventsSubject.next(e);
                    }
                    throw e;
                }
                else if (e.type === 'token_received') {
                    e = new OAuthSuccessEvent('silently_refreshed');
                    _this.eventsSubject.next(e);
                }
                return e;
            }))
                .toPromise();
        };
        /**
         * This method exists for backwards compatibility.
         * {@link OAuthService#initLoginFlowInPopup} handles both code
         * and implicit flows.
         */
        OAuthService.prototype.initImplicitFlowInPopup = function (options) {
            return this.initLoginFlowInPopup(options);
        };
        OAuthService.prototype.initLoginFlowInPopup = function (options) {
            var _this = this;
            options = options || {};
            return this.createLoginUrl(null, null, this.silentRefreshRedirectUri, false, {
                display: 'popup'
            }).then(function (url) {
                return new Promise(function (resolve, reject) {
                    /**
                     * Error handling section
                     */
                    var checkForPopupClosedInterval = 500;
                    var windowRef = window.open(url, '_blank', _this.calculatePopupFeatures(options));
                    var checkForPopupClosedTimer;
                    var checkForPopupClosed = function () {
                        if (!windowRef || windowRef.closed) {
                            cleanup();
                            reject(new OAuthErrorEvent('popup_closed', {}));
                        }
                    };
                    if (!windowRef) {
                        reject(new OAuthErrorEvent('popup_blocked', {}));
                    }
                    else {
                        checkForPopupClosedTimer = window.setInterval(checkForPopupClosed, checkForPopupClosedInterval);
                    }
                    var cleanup = function () {
                        window.clearInterval(checkForPopupClosedTimer);
                        window.removeEventListener('message', listener);
                        if (windowRef !== null) {
                            windowRef.close();
                        }
                        windowRef = null;
                    };
                    var listener = function (e) {
                        var message = _this.processMessageEventMessage(e);
                        if (message && message !== null) {
                            _this.tryLogin({
                                customHashFragment: message,
                                preventClearHashAfterLogin: true,
                                customRedirectUri: _this.silentRefreshRedirectUri
                            }).then(function () {
                                cleanup();
                                resolve();
                            }, function (err) {
                                cleanup();
                                reject(err);
                            });
                        }
                        else {
                            console.log('false event firing');
                        }
                    };
                    window.addEventListener('message', listener);
                });
            });
        };
        OAuthService.prototype.calculatePopupFeatures = function (options) {
            // Specify an static height and width and calculate centered position
            var height = options.height || 470;
            var width = options.width || 500;
            var left = window.screenLeft + (window.outerWidth - width) / 2;
            var top = window.screenTop + (window.outerHeight - height) / 2;
            return "location=no,toolbar=no,width=" + width + ",height=" + height + ",top=" + top + ",left=" + left;
        };
        OAuthService.prototype.processMessageEventMessage = function (e) {
            var expectedPrefix = '#';
            if (this.silentRefreshMessagePrefix) {
                expectedPrefix += this.silentRefreshMessagePrefix;
            }
            if (!e || !e.data || typeof e.data !== 'string') {
                return;
            }
            var prefixedMessage = e.data;
            if (!prefixedMessage.startsWith(expectedPrefix)) {
                return;
            }
            return '#' + prefixedMessage.substr(expectedPrefix.length);
        };
        OAuthService.prototype.canPerformSessionCheck = function () {
            if (!this.sessionChecksEnabled) {
                return false;
            }
            if (!this.sessionCheckIFrameUrl) {
                console.warn('sessionChecksEnabled is activated but there is no sessionCheckIFrameUrl');
                return false;
            }
            var sessionState = this.getSessionState();
            if (!sessionState) {
                console.warn('sessionChecksEnabled is activated but there is no session_state');
                return false;
            }
            if (typeof this.document === 'undefined') {
                return false;
            }
            return true;
        };
        OAuthService.prototype.setupSessionCheckEventListener = function () {
            var _this = this;
            this.removeSessionCheckEventListener();
            this.sessionCheckEventListener = function (e) {
                var origin = e.origin.toLowerCase();
                var issuer = _this.issuer.toLowerCase();
                _this.debug('sessionCheckEventListener');
                if (!issuer.startsWith(origin)) {
                    _this.debug('sessionCheckEventListener', 'wrong origin', origin, 'expected', issuer, 'event', e);
                    return;
                }
                // only run in Angular zone if it is 'changed' or 'error'
                switch (e.data) {
                    case 'unchanged':
                        _this.handleSessionUnchanged();
                        break;
                    case 'changed':
                        _this.ngZone.run(function () {
                            _this.handleSessionChange();
                        });
                        break;
                    case 'error':
                        _this.ngZone.run(function () {
                            _this.handleSessionError();
                        });
                        break;
                }
                _this.debug('got info from session check inframe', e);
            };
            // prevent Angular from refreshing the view on every message (runs in intervals)
            this.ngZone.runOutsideAngular(function () {
                window.addEventListener('message', _this.sessionCheckEventListener);
            });
        };
        OAuthService.prototype.handleSessionUnchanged = function () {
            this.debug('session check', 'session unchanged');
        };
        OAuthService.prototype.handleSessionChange = function () {
            var _this = this;
            this.eventsSubject.next(new OAuthInfoEvent('session_changed'));
            this.stopSessionCheckTimer();
            if (!this.useSilentRefresh && this.responseType === 'code') {
                this.refreshToken()
                    .then(function (_) {
                    _this.debug('token refresh after session change worked');
                })
                    .catch(function (_) {
                    _this.debug('token refresh did not work after session changed');
                    _this.eventsSubject.next(new OAuthInfoEvent('session_terminated'));
                    _this.logOut(true);
                });
            }
            else if (this.silentRefreshRedirectUri) {
                this.silentRefresh().catch(function (_) { return _this.debug('silent refresh failed after session changed'); });
                this.waitForSilentRefreshAfterSessionChange();
            }
            else {
                this.eventsSubject.next(new OAuthInfoEvent('session_terminated'));
                this.logOut(true);
            }
        };
        OAuthService.prototype.waitForSilentRefreshAfterSessionChange = function () {
            var _this = this;
            this.events
                .pipe(operators.filter(function (e) { return e.type === 'silently_refreshed' ||
                e.type === 'silent_refresh_timeout' ||
                e.type === 'silent_refresh_error'; }), operators.first())
                .subscribe(function (e) {
                if (e.type !== 'silently_refreshed') {
                    _this.debug('silent refresh did not work after session changed');
                    _this.eventsSubject.next(new OAuthInfoEvent('session_terminated'));
                    _this.logOut(true);
                }
            });
        };
        OAuthService.prototype.handleSessionError = function () {
            this.stopSessionCheckTimer();
            this.eventsSubject.next(new OAuthInfoEvent('session_error'));
        };
        OAuthService.prototype.removeSessionCheckEventListener = function () {
            if (this.sessionCheckEventListener) {
                window.removeEventListener('message', this.sessionCheckEventListener);
                this.sessionCheckEventListener = null;
            }
        };
        OAuthService.prototype.initSessionCheck = function () {
            if (!this.canPerformSessionCheck()) {
                return;
            }
            var existingIframe = this.document.getElementById(this.sessionCheckIFrameName);
            if (existingIframe) {
                this.document.body.removeChild(existingIframe);
            }
            var iframe = this.document.createElement('iframe');
            iframe.id = this.sessionCheckIFrameName;
            this.setupSessionCheckEventListener();
            var url = this.sessionCheckIFrameUrl;
            iframe.setAttribute('src', url);
            iframe.style.display = 'none';
            this.document.body.appendChild(iframe);
            this.startSessionCheckTimer();
        };
        OAuthService.prototype.startSessionCheckTimer = function () {
            var _this = this;
            this.stopSessionCheckTimer();
            this.ngZone.runOutsideAngular(function () {
                _this.sessionCheckTimer = setInterval(_this.checkSession.bind(_this), _this.sessionCheckIntervall);
            });
        };
        OAuthService.prototype.stopSessionCheckTimer = function () {
            if (this.sessionCheckTimer) {
                clearInterval(this.sessionCheckTimer);
                this.sessionCheckTimer = null;
            }
        };
        OAuthService.prototype.checkSession = function () {
            var iframe = this.document.getElementById(this.sessionCheckIFrameName);
            if (!iframe) {
                this.logger.warn('checkSession did not find iframe', this.sessionCheckIFrameName);
            }
            var sessionState = this.getSessionState();
            if (!sessionState) {
                this.stopSessionCheckTimer();
            }
            var message = this.clientId + ' ' + sessionState;
            iframe.contentWindow.postMessage(message, this.issuer);
        };
        OAuthService.prototype.createLoginUrl = function (state, loginHint, customRedirectUri, noPrompt, params) {
            if (state === void 0) { state = ''; }
            if (loginHint === void 0) { loginHint = ''; }
            if (customRedirectUri === void 0) { customRedirectUri = ''; }
            if (noPrompt === void 0) { noPrompt = false; }
            if (params === void 0) { params = {}; }
            return __awaiter(this, void 0, void 0, function () {
                var that, redirectUri, nonce, seperationChar, scope, url, _b, challenge, verifier, _c, _d, key, _e, _f, key;
                var e_3, _g, e_4, _h;
                return __generator(this, function (_j) {
                    switch (_j.label) {
                        case 0:
                            that = this;
                            if (customRedirectUri) {
                                redirectUri = customRedirectUri;
                            }
                            else {
                                redirectUri = this.redirectUri;
                            }
                            return [4 /*yield*/, this.createAndSaveNonce()];
                        case 1:
                            nonce = _j.sent();
                            if (state) {
                                state =
                                    nonce + this.config.nonceStateSeparator + encodeURIComponent(state);
                            }
                            else {
                                state = nonce;
                            }
                            if (!this.requestAccessToken && !this.oidc) {
                                throw new Error('Either requestAccessToken or oidc or both must be true');
                            }
                            if (this.config.responseType) {
                                this.responseType = this.config.responseType;
                            }
                            else {
                                if (this.oidc && this.requestAccessToken) {
                                    this.responseType = 'id_token token';
                                }
                                else if (this.oidc && !this.requestAccessToken) {
                                    this.responseType = 'id_token';
                                }
                                else {
                                    this.responseType = 'token';
                                }
                            }
                            seperationChar = that.loginUrl.indexOf('?') > -1 ? '&' : '?';
                            scope = that.scope;
                            if (this.oidc && !scope.match(/(^|\s)openid($|\s)/)) {
                                scope = 'openid ' + scope;
                            }
                            url = that.loginUrl +
                                seperationChar +
                                'response_type=' +
                                encodeURIComponent(that.responseType) +
                                '&client_id=' +
                                encodeURIComponent(that.clientId) +
                                '&state=' +
                                encodeURIComponent(state) +
                                '&redirect_uri=' +
                                encodeURIComponent(redirectUri) +
                                '&scope=' +
                                encodeURIComponent(scope);
                            if (!(this.responseType.includes('code') && !this.disablePKCE)) return [3 /*break*/, 3];
                            return [4 /*yield*/, this.createChallangeVerifierPairForPKCE()];
                        case 2:
                            _b = __read.apply(void 0, [_j.sent(), 2]), challenge = _b[0], verifier = _b[1];
                            if (this.saveNoncesInLocalStorage &&
                                typeof window['localStorage'] !== 'undefined') {
                                localStorage.setItem('PKCE_verifier', verifier);
                            }
                            else {
                                this._storage.setItem('PKCE_verifier', verifier);
                            }
                            url += '&code_challenge=' + challenge;
                            url += '&code_challenge_method=S256';
                            _j.label = 3;
                        case 3:
                            if (loginHint) {
                                url += '&login_hint=' + encodeURIComponent(loginHint);
                            }
                            if (that.resource) {
                                url += '&resource=' + encodeURIComponent(that.resource);
                            }
                            if (that.oidc) {
                                url += '&nonce=' + encodeURIComponent(nonce);
                            }
                            if (noPrompt) {
                                url += '&prompt=none';
                            }
                            try {
                                for (_c = __values(Object.keys(params)), _d = _c.next(); !_d.done; _d = _c.next()) {
                                    key = _d.value;
                                    url +=
                                        '&' + encodeURIComponent(key) + '=' + encodeURIComponent(params[key]);
                                }
                            }
                            catch (e_3_1) { e_3 = { error: e_3_1 }; }
                            finally {
                                try {
                                    if (_d && !_d.done && (_g = _c.return)) _g.call(_c);
                                }
                                finally { if (e_3) throw e_3.error; }
                            }
                            if (this.customQueryParams) {
                                try {
                                    for (_e = __values(Object.getOwnPropertyNames(this.customQueryParams)), _f = _e.next(); !_f.done; _f = _e.next()) {
                                        key = _f.value;
                                        url +=
                                            '&' + key + '=' + encodeURIComponent(this.customQueryParams[key]);
                                    }
                                }
                                catch (e_4_1) { e_4 = { error: e_4_1 }; }
                                finally {
                                    try {
                                        if (_f && !_f.done && (_h = _e.return)) _h.call(_e);
                                    }
                                    finally { if (e_4) throw e_4.error; }
                                }
                            }
                            return [2 /*return*/, url];
                    }
                });
            });
        };
        OAuthService.prototype.initImplicitFlowInternal = function (additionalState, params) {
            var _this = this;
            if (additionalState === void 0) { additionalState = ''; }
            if (params === void 0) { params = ''; }
            if (this.inImplicitFlow) {
                return;
            }
            this.inImplicitFlow = true;
            if (!this.validateUrlForHttps(this.loginUrl)) {
                throw new Error("loginUrl  must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
            var addParams = {};
            var loginHint = null;
            if (typeof params === 'string') {
                loginHint = params;
            }
            else if (typeof params === 'object') {
                addParams = params;
            }
            this.createLoginUrl(additionalState, loginHint, null, false, addParams)
                .then(this.config.openUri)
                .catch(function (error) {
                console.error('Error in initImplicitFlow', error);
                _this.inImplicitFlow = false;
            });
        };
        /**
         * Starts the implicit flow and redirects to user to
         * the auth servers' login url.
         *
         * @param additionalState Optional state that is passed around.
         *  You'll find this state in the property `state` after `tryLogin` logged in the user.
         * @param params Hash with additional parameter. If it is a string, it is used for the
         *               parameter loginHint (for the sake of compatibility with former versions)
         */
        OAuthService.prototype.initImplicitFlow = function (additionalState, params) {
            var _this = this;
            if (additionalState === void 0) { additionalState = ''; }
            if (params === void 0) { params = ''; }
            if (this.loginUrl !== '') {
                this.initImplicitFlowInternal(additionalState, params);
            }
            else {
                this.events
                    .pipe(operators.filter(function (e) { return e.type === 'discovery_document_loaded'; }))
                    .subscribe(function (_) { return _this.initImplicitFlowInternal(additionalState, params); });
            }
        };
        /**
         * Reset current implicit flow
         *
         * @description This method allows resetting the current implict flow in order to be initialized again.
         */
        OAuthService.prototype.resetImplicitFlow = function () {
            this.inImplicitFlow = false;
        };
        OAuthService.prototype.callOnTokenReceivedIfExists = function (options) {
            var that = this;
            if (options.onTokenReceived) {
                var tokenParams = {
                    idClaims: that.getIdentityClaims(),
                    idToken: that.getIdToken(),
                    accessToken: that.getAccessToken(),
                    state: that.state
                };
                options.onTokenReceived(tokenParams);
            }
        };
        OAuthService.prototype.storeAccessTokenResponse = function (accessToken, refreshToken, expiresIn, grantedScopes, customParameters) {
            var _this = this;
            this._storage.setItem('access_token', accessToken);
            if (grantedScopes && !Array.isArray(grantedScopes)) {
                this._storage.setItem('granted_scopes', JSON.stringify(grantedScopes.split('+')));
            }
            else if (grantedScopes && Array.isArray(grantedScopes)) {
                this._storage.setItem('granted_scopes', JSON.stringify(grantedScopes));
            }
            this._storage.setItem('access_token_stored_at', '' + Date.now());
            if (expiresIn) {
                var expiresInMilliSeconds = expiresIn * 1000;
                var now = new Date();
                var expiresAt = now.getTime() + expiresInMilliSeconds;
                this._storage.setItem('expires_at', '' + expiresAt);
            }
            if (refreshToken) {
                this._storage.setItem('refresh_token', refreshToken);
            }
            if (customParameters) {
                customParameters.forEach(function (value, key) {
                    _this._storage.setItem(key, value);
                });
            }
        };
        /**
         * Delegates to tryLoginImplicitFlow for the sake of competability
         * @param options Optional options.
         */
        OAuthService.prototype.tryLogin = function (options) {
            if (options === void 0) { options = null; }
            if (this.config.responseType === 'code') {
                return this.tryLoginCodeFlow(options).then(function (_) { return true; });
            }
            else {
                return this.tryLoginImplicitFlow(options);
            }
        };
        OAuthService.prototype.parseQueryString = function (queryString) {
            if (!queryString || queryString.length === 0) {
                return {};
            }
            if (queryString.charAt(0) === '?') {
                queryString = queryString.substr(1);
            }
            return this.urlHelper.parseQueryString(queryString);
        };
        OAuthService.prototype.tryLoginCodeFlow = function (options) {
            if (options === void 0) { options = null; }
            options = options || {};
            var querySource = options.customHashFragment
                ? options.customHashFragment.substring(1)
                : window.location.search;
            var parts = this.getCodePartsFromUrl(querySource);
            var code = parts['code'];
            var state = parts['state'];
            var sessionState = parts['session_state'];
            if (!options.preventClearHashAfterLogin) {
                var href = location.href
                    .replace(/[&\?]code=[^&\$]*/, '')
                    .replace(/[&\?]scope=[^&\$]*/, '')
                    .replace(/[&\?]state=[^&\$]*/, '')
                    .replace(/[&\?]session_state=[^&\$]*/, '');
                history.replaceState(null, window.name, href);
            }
            var _b = __read(this.parseState(state), 2), nonceInState = _b[0], userState = _b[1];
            this.state = userState;
            if (parts['error']) {
                this.debug('error trying to login');
                this.handleLoginError({}, parts);
                var err = new OAuthErrorEvent('code_error', {}, parts);
                this.eventsSubject.next(err);
                return Promise.reject(err);
            }
            if (!nonceInState) {
                return Promise.resolve();
            }
            var success = this.validateNonce(nonceInState);
            if (!success) {
                var event = new OAuthErrorEvent('invalid_nonce_in_state', null);
                this.eventsSubject.next(event);
                return Promise.reject(event);
            }
            this.storeSessionState(sessionState);
            if (code) {
                return this.getTokenFromCode(code, options).then(function (_) { return null; });
            }
            else {
                return Promise.resolve();
            }
        };
        /**
         * Retrieve the returned auth code from the redirect uri that has been called.
         * If required also check hash, as we could use hash location strategy.
         */
        OAuthService.prototype.getCodePartsFromUrl = function (queryString) {
            if (!queryString || queryString.length === 0) {
                return this.urlHelper.getHashFragmentParams();
            }
            // normalize query string
            if (queryString.charAt(0) === '?') {
                queryString = queryString.substr(1);
            }
            return this.urlHelper.parseQueryString(queryString);
        };
        /**
         * Get token using an intermediate code. Works for the Authorization Code flow.
         */
        OAuthService.prototype.getTokenFromCode = function (code, options) {
            var params = new http.HttpParams()
                .set('grant_type', 'authorization_code')
                .set('code', code)
                .set('redirect_uri', options.customRedirectUri || this.redirectUri);
            if (!this.disablePKCE) {
                var PKCEVerifier = void 0;
                if (this.saveNoncesInLocalStorage &&
                    typeof window['localStorage'] !== 'undefined') {
                    PKCEVerifier = localStorage.getItem('PKCE_verifier');
                }
                else {
                    PKCEVerifier = this._storage.getItem('PKCE_verifier');
                }
                if (!PKCEVerifier) {
                    console.warn('No PKCE verifier found in oauth storage!');
                }
                else {
                    params = params.set('code_verifier', PKCEVerifier);
                }
            }
            return this.fetchAndProcessToken(params);
        };
        OAuthService.prototype.fetchAndProcessToken = function (params) {
            var _this = this;
            this.assertUrlNotNullAndCorrectProtocol(this.tokenEndpoint, 'tokenEndpoint');
            var headers = new http.HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
            if (this.useHttpBasicAuth) {
                var header = btoa(this.clientId + ":" + this.dummyClientSecret);
                headers = headers.set('Authorization', 'Basic ' + header);
            }
            if (!this.useHttpBasicAuth) {
                params = params.set('client_id', this.clientId);
            }
            if (!this.useHttpBasicAuth && this.dummyClientSecret) {
                params = params.set('client_secret', this.dummyClientSecret);
            }
            return new Promise(function (resolve, reject) {
                var e_5, _b;
                if (_this.customQueryParams) {
                    try {
                        for (var _c = __values(Object.getOwnPropertyNames(_this.customQueryParams)), _d = _c.next(); !_d.done; _d = _c.next()) {
                            var key = _d.value;
                            params = params.set(key, _this.customQueryParams[key]);
                        }
                    }
                    catch (e_5_1) { e_5 = { error: e_5_1 }; }
                    finally {
                        try {
                            if (_d && !_d.done && (_b = _c.return)) _b.call(_c);
                        }
                        finally { if (e_5) throw e_5.error; }
                    }
                }
                _this.http
                    .post(_this.tokenEndpoint, params, { headers: headers })
                    .subscribe(function (tokenResponse) {
                    _this.debug('refresh tokenResponse', tokenResponse);
                    _this.storeAccessTokenResponse(tokenResponse.access_token, tokenResponse.refresh_token, tokenResponse.expires_in ||
                        _this.fallbackAccessTokenExpirationTimeInSec, tokenResponse.scope, _this.extractRecognizedCustomParameters(tokenResponse));
                    if (_this.oidc && tokenResponse.id_token) {
                        _this.processIdToken(tokenResponse.id_token, tokenResponse.access_token)
                            .then(function (result) {
                            _this.storeIdToken(result);
                            _this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                            _this.eventsSubject.next(new OAuthSuccessEvent('token_refreshed'));
                            resolve(tokenResponse);
                        })
                            .catch(function (reason) {
                            _this.eventsSubject.next(new OAuthErrorEvent('token_validation_error', reason));
                            console.error('Error validating tokens');
                            console.error(reason);
                            reject(reason);
                        });
                    }
                    else {
                        _this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                        _this.eventsSubject.next(new OAuthSuccessEvent('token_refreshed'));
                        resolve(tokenResponse);
                    }
                }, function (err) {
                    console.error('Error getting token', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('token_refresh_error', err));
                    reject(err);
                });
            });
        };
        /**
         * Checks whether there are tokens in the hash fragment
         * as a result of the implicit flow. These tokens are
         * parsed, validated and used to sign the user in to the
         * current client.
         *
         * @param options Optional options.
         */
        OAuthService.prototype.tryLoginImplicitFlow = function (options) {
            var _this = this;
            if (options === void 0) { options = null; }
            options = options || {};
            var parts;
            if (options.customHashFragment) {
                parts = this.urlHelper.getHashFragmentParams(options.customHashFragment);
            }
            else {
                parts = this.urlHelper.getHashFragmentParams();
            }
            this.debug('parsed url', parts);
            var state = parts['state'];
            var _b = __read(this.parseState(state), 2), nonceInState = _b[0], userState = _b[1];
            this.state = userState;
            if (parts['error']) {
                this.debug('error trying to login');
                this.handleLoginError(options, parts);
                var err = new OAuthErrorEvent('token_error', {}, parts);
                this.eventsSubject.next(err);
                return Promise.reject(err);
            }
            var accessToken = parts['access_token'];
            var idToken = parts['id_token'];
            var sessionState = parts['session_state'];
            var grantedScopes = parts['scope'];
            if (!this.requestAccessToken && !this.oidc) {
                return Promise.reject('Either requestAccessToken or oidc (or both) must be true.');
            }
            if (this.requestAccessToken && !accessToken) {
                return Promise.resolve(false);
            }
            if (this.requestAccessToken && !options.disableOAuth2StateCheck && !state) {
                return Promise.resolve(false);
            }
            if (this.oidc && !idToken) {
                return Promise.resolve(false);
            }
            if (this.sessionChecksEnabled && !sessionState) {
                this.logger.warn('session checks (Session Status Change Notification) ' +
                    'were activated in the configuration but the id_token ' +
                    'does not contain a session_state claim');
            }
            if (this.requestAccessToken && !options.disableOAuth2StateCheck) {
                var success = this.validateNonce(nonceInState);
                if (!success) {
                    var event = new OAuthErrorEvent('invalid_nonce_in_state', null);
                    this.eventsSubject.next(event);
                    return Promise.reject(event);
                }
            }
            if (this.requestAccessToken) {
                this.storeAccessTokenResponse(accessToken, null, parts['expires_in'] || this.fallbackAccessTokenExpirationTimeInSec, grantedScopes);
            }
            if (!this.oidc) {
                this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                if (this.clearHashAfterLogin && !options.preventClearHashAfterLogin) {
                    location.hash = '';
                }
                this.callOnTokenReceivedIfExists(options);
                return Promise.resolve(true);
            }
            return this.processIdToken(idToken, accessToken)
                .then(function (result) {
                if (options.validationHandler) {
                    return options
                        .validationHandler({
                        accessToken: accessToken,
                        idClaims: result.idTokenClaims,
                        idToken: result.idToken,
                        state: state
                    })
                        .then(function (_) { return result; });
                }
                return result;
            })
                .then(function (result) {
                _this.storeIdToken(result);
                _this.storeSessionState(sessionState);
                if (_this.clearHashAfterLogin && !options.preventClearHashAfterLogin) {
                    location.hash = '';
                }
                _this.eventsSubject.next(new OAuthSuccessEvent('token_received'));
                _this.callOnTokenReceivedIfExists(options);
                _this.inImplicitFlow = false;
                return true;
            })
                .catch(function (reason) {
                _this.eventsSubject.next(new OAuthErrorEvent('token_validation_error', reason));
                _this.logger.error('Error validating tokens');
                _this.logger.error(reason);
                return Promise.reject(reason);
            });
        };
        OAuthService.prototype.parseState = function (state) {
            var nonce = state;
            var userState = '';
            if (state) {
                var idx = state.indexOf(this.config.nonceStateSeparator);
                if (idx > -1) {
                    nonce = state.substr(0, idx);
                    userState = state.substr(idx + this.config.nonceStateSeparator.length);
                }
            }
            return [nonce, userState];
        };
        OAuthService.prototype.validateNonce = function (nonceInState) {
            var savedNonce;
            if (this.saveNoncesInLocalStorage &&
                typeof window['localStorage'] !== 'undefined') {
                savedNonce = localStorage.getItem('nonce');
            }
            else {
                savedNonce = this._storage.getItem('nonce');
            }
            if (savedNonce !== nonceInState) {
                var err = 'Validating access_token failed, wrong state/nonce.';
                console.error(err, savedNonce, nonceInState);
                return false;
            }
            return true;
        };
        OAuthService.prototype.storeIdToken = function (idToken) {
            this._storage.setItem('id_token', idToken.idToken);
            this._storage.setItem('id_token_claims_obj', idToken.idTokenClaimsJson);
            this._storage.setItem('id_token_expires_at', '' + idToken.idTokenExpiresAt);
            this._storage.setItem('id_token_stored_at', '' + Date.now());
        };
        OAuthService.prototype.storeSessionState = function (sessionState) {
            this._storage.setItem('session_state', sessionState);
        };
        OAuthService.prototype.getSessionState = function () {
            return this._storage.getItem('session_state');
        };
        OAuthService.prototype.handleLoginError = function (options, parts) {
            if (options.onLoginError) {
                options.onLoginError(parts);
            }
            if (this.clearHashAfterLogin && !options.preventClearHashAfterLogin) {
                location.hash = '';
            }
        };
        /**
         * @ignore
         */
        OAuthService.prototype.processIdToken = function (idToken, accessToken, skipNonceCheck) {
            var _this = this;
            if (skipNonceCheck === void 0) { skipNonceCheck = false; }
            var tokenParts = idToken.split('.');
            var headerBase64 = this.padBase64(tokenParts[0]);
            var headerJson = b64DecodeUnicode(headerBase64);
            var header = JSON.parse(headerJson);
            var claimsBase64 = this.padBase64(tokenParts[1]);
            var claimsJson = b64DecodeUnicode(claimsBase64);
            var claims = JSON.parse(claimsJson);
            var savedNonce;
            if (this.saveNoncesInLocalStorage &&
                typeof window['localStorage'] !== 'undefined') {
                savedNonce = localStorage.getItem('nonce');
            }
            else {
                savedNonce = this._storage.getItem('nonce');
            }
            if (Array.isArray(claims.aud)) {
                if (claims.aud.every(function (v) { return v !== _this.clientId; })) {
                    var err = 'Wrong audience: ' + claims.aud.join(',');
                    this.logger.warn(err);
                    return Promise.reject(err);
                }
            }
            else {
                if (claims.aud !== this.clientId) {
                    var err = 'Wrong audience: ' + claims.aud;
                    this.logger.warn(err);
                    return Promise.reject(err);
                }
            }
            if (!claims.sub) {
                var err = 'No sub claim in id_token';
                this.logger.warn(err);
                return Promise.reject(err);
            }
            /* For now, we only check whether the sub against
             * silentRefreshSubject when sessionChecksEnabled is on
             * We will reconsider in a later version to do this
             * in every other case too.
             */
            if (this.sessionChecksEnabled &&
                this.silentRefreshSubject &&
                this.silentRefreshSubject !== claims['sub']) {
                var err = 'After refreshing, we got an id_token for another user (sub). ' +
                    ("Expected sub: " + this.silentRefreshSubject + ", received sub: " + claims['sub']);
                this.logger.warn(err);
                return Promise.reject(err);
            }
            if (!claims.iat) {
                var err = 'No iat claim in id_token';
                this.logger.warn(err);
                return Promise.reject(err);
            }
            if (!this.skipIssuerCheck && claims.iss !== this.issuer) {
                var err = 'Wrong issuer: ' + claims.iss;
                this.logger.warn(err);
                return Promise.reject(err);
            }
            if (!skipNonceCheck && claims.nonce !== savedNonce) {
                var err = 'Wrong nonce: ' + claims.nonce;
                this.logger.warn(err);
                return Promise.reject(err);
            }
            // at_hash is not applicable to authorization code flow
            // addressing https://github.com/manfredsteyer/angular-oauth2-oidc/issues/661
            // i.e. Based on spec the at_hash check is only true for implicit code flow on Ping Federate
            // https://www.pingidentity.com/developer/en/resources/openid-connect-developers-guide.html
            if (this.hasOwnProperty('responseType') &&
                (this.responseType === 'code' || this.responseType === 'id_token')) {
                this.disableAtHashCheck = true;
            }
            if (!this.disableAtHashCheck &&
                this.requestAccessToken &&
                !claims['at_hash']) {
                var err = 'An at_hash is needed!';
                this.logger.warn(err);
                return Promise.reject(err);
            }
            var now = Date.now();
            var issuedAtMSec = claims.iat * 1000;
            var expiresAtMSec = claims.exp * 1000;
            var clockSkewInMSec = (this.clockSkewInSec || 600) * 1000;
            if (issuedAtMSec - clockSkewInMSec >= now ||
                expiresAtMSec + clockSkewInMSec <= now) {
                var err = 'Token has expired';
                console.error(err);
                console.error({
                    now: now,
                    issuedAtMSec: issuedAtMSec,
                    expiresAtMSec: expiresAtMSec
                });
                return Promise.reject(err);
            }
            var validationParams = {
                accessToken: accessToken,
                idToken: idToken,
                jwks: this.jwks,
                idTokenClaims: claims,
                idTokenHeader: header,
                loadKeys: function () { return _this.loadJwks(); }
            };
            if (this.disableAtHashCheck) {
                return this.checkSignature(validationParams).then(function (_) {
                    var result = {
                        idToken: idToken,
                        idTokenClaims: claims,
                        idTokenClaimsJson: claimsJson,
                        idTokenHeader: header,
                        idTokenHeaderJson: headerJson,
                        idTokenExpiresAt: expiresAtMSec
                    };
                    return result;
                });
            }
            return this.checkAtHash(validationParams).then(function (atHashValid) {
                if (!_this.disableAtHashCheck && _this.requestAccessToken && !atHashValid) {
                    var err = 'Wrong at_hash';
                    _this.logger.warn(err);
                    return Promise.reject(err);
                }
                return _this.checkSignature(validationParams).then(function (_) {
                    var atHashCheckEnabled = !_this.disableAtHashCheck;
                    var result = {
                        idToken: idToken,
                        idTokenClaims: claims,
                        idTokenClaimsJson: claimsJson,
                        idTokenHeader: header,
                        idTokenHeaderJson: headerJson,
                        idTokenExpiresAt: expiresAtMSec
                    };
                    if (atHashCheckEnabled) {
                        return _this.checkAtHash(validationParams).then(function (atHashValid) {
                            if (_this.requestAccessToken && !atHashValid) {
                                var err = 'Wrong at_hash';
                                _this.logger.warn(err);
                                return Promise.reject(err);
                            }
                            else {
                                return result;
                            }
                        });
                    }
                    else {
                        return result;
                    }
                });
            });
        };
        /**
         * Returns the received claims about the user.
         */
        OAuthService.prototype.getIdentityClaims = function () {
            var claims = this._storage.getItem('id_token_claims_obj');
            if (!claims) {
                return null;
            }
            return JSON.parse(claims);
        };
        /**
         * Returns the granted scopes from the server.
         */
        OAuthService.prototype.getGrantedScopes = function () {
            var scopes = this._storage.getItem('granted_scopes');
            if (!scopes) {
                return null;
            }
            return JSON.parse(scopes);
        };
        /**
         * Returns the current id_token.
         */
        OAuthService.prototype.getIdToken = function () {
            return this._storage ? this._storage.getItem('id_token') : null;
        };
        OAuthService.prototype.padBase64 = function (base64data) {
            while (base64data.length % 4 !== 0) {
                base64data += '=';
            }
            return base64data;
        };
        /**
         * Returns the current access_token.
         */
        OAuthService.prototype.getAccessToken = function () {
            return this._storage ? this._storage.getItem('access_token') : null;
        };
        OAuthService.prototype.getRefreshToken = function () {
            return this._storage ? this._storage.getItem('refresh_token') : null;
        };
        /**
         * Returns the expiration date of the access_token
         * as milliseconds since 1970.
         */
        OAuthService.prototype.getAccessTokenExpiration = function () {
            if (!this._storage.getItem('expires_at')) {
                return null;
            }
            return parseInt(this._storage.getItem('expires_at'), 10);
        };
        OAuthService.prototype.getAccessTokenStoredAt = function () {
            return parseInt(this._storage.getItem('access_token_stored_at'), 10);
        };
        OAuthService.prototype.getIdTokenStoredAt = function () {
            return parseInt(this._storage.getItem('id_token_stored_at'), 10);
        };
        /**
         * Returns the expiration date of the id_token
         * as milliseconds since 1970.
         */
        OAuthService.prototype.getIdTokenExpiration = function () {
            if (!this._storage.getItem('id_token_expires_at')) {
                return null;
            }
            return parseInt(this._storage.getItem('id_token_expires_at'), 10);
        };
        /**
         * Checkes, whether there is a valid access_token.
         */
        OAuthService.prototype.hasValidAccessToken = function () {
            if (this.getAccessToken()) {
                var expiresAt = this._storage.getItem('expires_at');
                var now = new Date();
                if (expiresAt && parseInt(expiresAt, 10) < now.getTime()) {
                    return false;
                }
                return true;
            }
            return false;
        };
        /**
         * Checks whether there is a valid id_token.
         */
        OAuthService.prototype.hasValidIdToken = function () {
            if (this.getIdToken()) {
                var expiresAt = this._storage.getItem('id_token_expires_at');
                var now = new Date();
                if (expiresAt && parseInt(expiresAt, 10) < now.getTime()) {
                    return false;
                }
                return true;
            }
            return false;
        };
        /**
         * Retrieve a saved custom property of the TokenReponse object. Only if predefined in authconfig.
         */
        OAuthService.prototype.getCustomTokenResponseProperty = function (requestedProperty) {
            return this._storage &&
                this.config.customTokenParameters &&
                this.config.customTokenParameters.indexOf(requestedProperty) >= 0 &&
                this._storage.getItem(requestedProperty) !== null
                ? JSON.parse(this._storage.getItem(requestedProperty))
                : null;
        };
        /**
         * Returns the auth-header that can be used
         * to transmit the access_token to a service
         */
        OAuthService.prototype.authorizationHeader = function () {
            return 'Bearer ' + this.getAccessToken();
        };
        OAuthService.prototype.logOut = function (customParameters, state) {
            var _this = this;
            if (customParameters === void 0) { customParameters = {}; }
            if (state === void 0) { state = ''; }
            var noRedirectToLogoutUrl = false;
            if (typeof customParameters === 'boolean') {
                noRedirectToLogoutUrl = customParameters;
                customParameters = {};
            }
            var id_token = this.getIdToken();
            this._storage.removeItem('access_token');
            this._storage.removeItem('id_token');
            this._storage.removeItem('refresh_token');
            if (this.saveNoncesInLocalStorage) {
                localStorage.removeItem('nonce');
                localStorage.removeItem('PKCE_verifier');
            }
            else {
                this._storage.removeItem('nonce');
                this._storage.removeItem('PKCE_verifier');
            }
            this._storage.removeItem('expires_at');
            this._storage.removeItem('id_token_claims_obj');
            this._storage.removeItem('id_token_expires_at');
            this._storage.removeItem('id_token_stored_at');
            this._storage.removeItem('access_token_stored_at');
            this._storage.removeItem('granted_scopes');
            this._storage.removeItem('session_state');
            if (this.config.customTokenParameters) {
                this.config.customTokenParameters.forEach(function (customParam) { return _this._storage.removeItem(customParam); });
            }
            this.silentRefreshSubject = null;
            this.eventsSubject.next(new OAuthInfoEvent('logout'));
            if (!this.logoutUrl) {
                return;
            }
            if (noRedirectToLogoutUrl) {
                return;
            }
            if (!id_token && !this.postLogoutRedirectUri) {
                return;
            }
            var logoutUrl;
            if (!this.validateUrlForHttps(this.logoutUrl)) {
                throw new Error("logoutUrl  must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
            // For backward compatibility
            if (this.logoutUrl.indexOf('{{') > -1) {
                logoutUrl = this.logoutUrl
                    .replace(/\{\{id_token\}\}/, id_token)
                    .replace(/\{\{client_id\}\}/, this.clientId);
            }
            else {
                var params = new http.HttpParams();
                if (id_token) {
                    params = params.set('id_token_hint', id_token);
                }
                var postLogoutUrl = this.postLogoutRedirectUri || this.redirectUri;
                if (postLogoutUrl) {
                    params = params.set('post_logout_redirect_uri', postLogoutUrl);
                    if (state) {
                        params = params.set('state', state);
                    }
                }
                for (var key in customParameters) {
                    params = params.set(key, customParameters[key]);
                }
                logoutUrl =
                    this.logoutUrl +
                        (this.logoutUrl.indexOf('?') > -1 ? '&' : '?') +
                        params.toString();
            }
            this.config.openUri(logoutUrl);
        };
        /**
         * @ignore
         */
        OAuthService.prototype.createAndSaveNonce = function () {
            var that = this;
            return this.createNonce().then(function (nonce) {
                // Use localStorage for nonce if possible
                // localStorage is the only storage who survives a
                // redirect in ALL browsers (also IE)
                // Otherwiese we'd force teams who have to support
                // IE into using localStorage for everything
                if (that.saveNoncesInLocalStorage &&
                    typeof window['localStorage'] !== 'undefined') {
                    localStorage.setItem('nonce', nonce);
                }
                else {
                    that._storage.setItem('nonce', nonce);
                }
                return nonce;
            });
        };
        /**
         * @ignore
         */
        OAuthService.prototype.ngOnDestroy = function () {
            this.clearAccessTokenTimer();
            this.clearIdTokenTimer();
            this.removeSilentRefreshEventListener();
            var silentRefreshFrame = this.document.getElementById(this.silentRefreshIFrameName);
            if (silentRefreshFrame) {
                silentRefreshFrame.remove();
            }
            this.stopSessionCheckTimer();
            this.removeSessionCheckEventListener();
            var sessionCheckFrame = this.document.getElementById(this.sessionCheckIFrameName);
            if (sessionCheckFrame) {
                sessionCheckFrame.remove();
            }
        };
        OAuthService.prototype.createNonce = function () {
            var _this = this;
            return new Promise(function (resolve) {
                if (_this.rngUrl) {
                    throw new Error('createNonce with rng-web-api has not been implemented so far');
                }
                /*
                 * This alphabet is from:
                 * https://tools.ietf.org/html/rfc7636#section-4.1
                 *
                 * [A-Z] / [a-z] / [0-9] / "-" / "." / "_" / "~"
                 */
                var unreserved = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~';
                var size = 45;
                var id = '';
                var crypto = typeof self === 'undefined' ? null : self.crypto || self['msCrypto'];
                if (crypto) {
                    var bytes = new Uint8Array(size);
                    crypto.getRandomValues(bytes);
                    // Needed for IE
                    if (!bytes.map) {
                        bytes.map = Array.prototype.map;
                    }
                    bytes = bytes.map(function (x) { return unreserved.charCodeAt(x % unreserved.length); });
                    id = String.fromCharCode.apply(null, bytes);
                }
                else {
                    while (0 < size--) {
                        id += unreserved[(Math.random() * unreserved.length) | 0];
                    }
                }
                resolve(base64UrlEncode(id));
            });
        };
        OAuthService.prototype.checkAtHash = function (params) {
            return __awaiter(this, void 0, void 0, function () {
                return __generator(this, function (_b) {
                    if (!this.tokenValidationHandler) {
                        this.logger.warn('No tokenValidationHandler configured. Cannot check at_hash.');
                        return [2 /*return*/, true];
                    }
                    return [2 /*return*/, this.tokenValidationHandler.validateAtHash(params)];
                });
            });
        };
        OAuthService.prototype.checkSignature = function (params) {
            if (!this.tokenValidationHandler) {
                this.logger.warn('No tokenValidationHandler configured. Cannot check signature.');
                return Promise.resolve(null);
            }
            return this.tokenValidationHandler.validateSignature(params);
        };
        /**
         * Start the implicit flow or the code flow,
         * depending on your configuration.
         */
        OAuthService.prototype.initLoginFlow = function (additionalState, params) {
            if (additionalState === void 0) { additionalState = ''; }
            if (params === void 0) { params = {}; }
            if (this.responseType === 'code') {
                return this.initCodeFlow(additionalState, params);
            }
            else {
                return this.initImplicitFlow(additionalState, params);
            }
        };
        /**
         * Starts the authorization code flow and redirects to user to
         * the auth servers login url.
         */
        OAuthService.prototype.initCodeFlow = function (additionalState, params) {
            var _this = this;
            if (additionalState === void 0) { additionalState = ''; }
            if (params === void 0) { params = {}; }
            if (this.loginUrl !== '') {
                this.initCodeFlowInternal(additionalState, params);
            }
            else {
                this.events
                    .pipe(operators.filter(function (e) { return e.type === 'discovery_document_loaded'; }))
                    .subscribe(function (_) { return _this.initCodeFlowInternal(additionalState, params); });
            }
        };
        OAuthService.prototype.initCodeFlowInternal = function (additionalState, params) {
            if (additionalState === void 0) { additionalState = ''; }
            if (params === void 0) { params = {}; }
            if (!this.validateUrlForHttps(this.loginUrl)) {
                throw new Error("loginUrl  must use HTTPS (with TLS), or config value for property 'requireHttps' must be set to 'false' and allow HTTP (without TLS).");
            }
            this.createLoginUrl(additionalState, '', null, false, params)
                .then(this.config.openUri)
                .catch(function (error) {
                console.error('Error in initAuthorizationCodeFlow');
                console.error(error);
            });
        };
        OAuthService.prototype.createChallangeVerifierPairForPKCE = function () {
            return __awaiter(this, void 0, void 0, function () {
                var verifier, challengeRaw, challenge;
                return __generator(this, function (_b) {
                    switch (_b.label) {
                        case 0:
                            if (!this.crypto) {
                                throw new Error('PKCE support for code flow needs a CryptoHander. Did you import the OAuthModule using forRoot() ?');
                            }
                            return [4 /*yield*/, this.createNonce()];
                        case 1:
                            verifier = _b.sent();
                            return [4 /*yield*/, this.crypto.calcHash(verifier, 'sha-256')];
                        case 2:
                            challengeRaw = _b.sent();
                            challenge = base64UrlEncode(challengeRaw);
                            return [2 /*return*/, [challenge, verifier]];
                    }
                });
            });
        };
        OAuthService.prototype.extractRecognizedCustomParameters = function (tokenResponse) {
            var foundParameters = new Map();
            if (!this.config.customTokenParameters) {
                return foundParameters;
            }
            this.config.customTokenParameters.forEach(function (recognizedParameter) {
                if (tokenResponse[recognizedParameter]) {
                    foundParameters.set(recognizedParameter, JSON.stringify(tokenResponse[recognizedParameter]));
                }
            });
            return foundParameters;
        };
        /**
         * Revokes the auth token to secure the vulnarability
         * of the token issued allowing the authorization server to clean
         * up any security credentials associated with the authorization
         */
        OAuthService.prototype.revokeTokenAndLogout = function (customParameters, ignoreCorsIssues) {
            var e_6, _b;
            var _this = this;
            if (customParameters === void 0) { customParameters = {}; }
            if (ignoreCorsIssues === void 0) { ignoreCorsIssues = false; }
            var revokeEndpoint = this.revocationEndpoint;
            var accessToken = this.getAccessToken();
            var refreshToken = this.getRefreshToken();
            if (!accessToken) {
                return;
            }
            var params = new http.HttpParams();
            var headers = new http.HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
            if (this.useHttpBasicAuth) {
                var header = btoa(this.clientId + ":" + this.dummyClientSecret);
                headers = headers.set('Authorization', 'Basic ' + header);
            }
            if (!this.useHttpBasicAuth) {
                params = params.set('client_id', this.clientId);
            }
            if (!this.useHttpBasicAuth && this.dummyClientSecret) {
                params = params.set('client_secret', this.dummyClientSecret);
            }
            if (this.customQueryParams) {
                try {
                    for (var _c = __values(Object.getOwnPropertyNames(this.customQueryParams)), _d = _c.next(); !_d.done; _d = _c.next()) {
                        var key = _d.value;
                        params = params.set(key, this.customQueryParams[key]);
                    }
                }
                catch (e_6_1) { e_6 = { error: e_6_1 }; }
                finally {
                    try {
                        if (_d && !_d.done && (_b = _c.return)) _b.call(_c);
                    }
                    finally { if (e_6) throw e_6.error; }
                }
            }
            return new Promise(function (resolve, reject) {
                var revokeAccessToken;
                var revokeRefreshToken;
                if (accessToken) {
                    var revokationParams = params
                        .set('token', accessToken)
                        .set('token_type_hint', 'access_token');
                    revokeAccessToken = _this.http.post(revokeEndpoint, revokationParams, { headers: headers });
                }
                else {
                    revokeAccessToken = rxjs.of(null);
                }
                if (refreshToken) {
                    var revokationParams = params
                        .set('token', refreshToken)
                        .set('token_type_hint', 'refresh_token');
                    revokeRefreshToken = _this.http.post(revokeEndpoint, revokationParams, { headers: headers });
                }
                else {
                    revokeRefreshToken = rxjs.of(null);
                }
                if (ignoreCorsIssues) {
                    revokeAccessToken = revokeAccessToken.pipe(operators.catchError(function (err) {
                        if (err.status === 0) {
                            return rxjs.of(null);
                        }
                        return rxjs.throwError(err);
                    }));
                    revokeRefreshToken = revokeRefreshToken.pipe(operators.catchError(function (err) {
                        if (err.status === 0) {
                            return rxjs.of(null);
                        }
                        return rxjs.throwError(err);
                    }));
                }
                rxjs.combineLatest([revokeAccessToken, revokeRefreshToken]).subscribe(function (res) {
                    _this.logOut(customParameters);
                    resolve(res);
                    _this.logger.info('Token successfully revoked');
                }, function (err) {
                    _this.logger.error('Error revoking token', err);
                    _this.eventsSubject.next(new OAuthErrorEvent('token_revoke_error', err));
                    reject(err);
                });
            });
        };
        return OAuthService;
    }(AuthConfig));
    OAuthService.decorators = [
        { type: core.Injectable }
    ];
    OAuthService.ctorParameters = function () { return [
        { type: core.NgZone },
        { type: http.HttpClient },
        { type: OAuthStorage, decorators: [{ type: core.Optional }] },
        { type: ValidationHandler, decorators: [{ type: core.Optional }] },
        { type: AuthConfig, decorators: [{ type: core.Optional }] },
        { type: UrlHelperService },
        { type: OAuthLogger },
        { type: HashHandler, decorators: [{ type: core.Optional }] },
        { type: undefined, decorators: [{ type: core.Inject, args: [common.DOCUMENT,] }] }
    ]; };

    var OAuthModuleConfig = /** @class */ (function () {
        function OAuthModuleConfig() {
        }
        return OAuthModuleConfig;
    }());
    var OAuthResourceServerConfig = /** @class */ (function () {
        function OAuthResourceServerConfig() {
        }
        return OAuthResourceServerConfig;
    }());

    var OAuthResourceServerErrorHandler = /** @class */ (function () {
        function OAuthResourceServerErrorHandler() {
        }
        return OAuthResourceServerErrorHandler;
    }());
    var OAuthNoopResourceServerErrorHandler = /** @class */ (function () {
        function OAuthNoopResourceServerErrorHandler() {
        }
        OAuthNoopResourceServerErrorHandler.prototype.handleError = function (err) {
            return rxjs.throwError(err);
        };
        return OAuthNoopResourceServerErrorHandler;
    }());

    var DefaultOAuthInterceptor = /** @class */ (function () {
        function DefaultOAuthInterceptor(authStorage, oAuthService, errorHandler, moduleConfig) {
            this.authStorage = authStorage;
            this.oAuthService = oAuthService;
            this.errorHandler = errorHandler;
            this.moduleConfig = moduleConfig;
        }
        DefaultOAuthInterceptor.prototype.checkUrl = function (url) {
            if (this.moduleConfig.resourceServer.customUrlValidation) {
                return this.moduleConfig.resourceServer.customUrlValidation(url);
            }
            if (this.moduleConfig.resourceServer.allowedUrls) {
                return !!this.moduleConfig.resourceServer.allowedUrls.find(function (u) { return url.startsWith(u); });
            }
            return true;
        };
        DefaultOAuthInterceptor.prototype.intercept = function (req, next) {
            var _this = this;
            var url = req.url.toLowerCase();
            if (!this.moduleConfig ||
                !this.moduleConfig.resourceServer ||
                !this.checkUrl(url)) {
                return next.handle(req);
            }
            var sendAccessToken = this.moduleConfig.resourceServer.sendAccessToken;
            if (!sendAccessToken) {
                return next
                    .handle(req)
                    .pipe(operators.catchError(function (err) { return _this.errorHandler.handleError(err); }));
            }
            return rxjs.merge(rxjs.of(this.oAuthService.getAccessToken()).pipe(operators.filter(function (token) { return (token ? true : false); })), this.oAuthService.events.pipe(operators.filter(function (e) { return e.type === 'token_received'; }), operators.timeout(this.oAuthService.waitForTokenInMsec || 0), operators.catchError(function (_) { return rxjs.of(null); }), // timeout is not an error
            operators.map(function (_) { return _this.oAuthService.getAccessToken(); }))).pipe(operators.take(1), operators.mergeMap(function (token) {
                if (token) {
                    var header = 'Bearer ' + token;
                    var headers = req.headers.set('Authorization', header);
                    req = req.clone({ headers: headers });
                }
                return next
                    .handle(req)
                    .pipe(operators.catchError(function (err) { return _this.errorHandler.handleError(err); }));
            }));
        };
        return DefaultOAuthInterceptor;
    }());
    DefaultOAuthInterceptor.decorators = [
        { type: core.Injectable }
    ];
    DefaultOAuthInterceptor.ctorParameters = function () { return [
        { type: OAuthStorage },
        { type: OAuthService },
        { type: OAuthResourceServerErrorHandler },
        { type: OAuthModuleConfig, decorators: [{ type: core.Optional }] }
    ]; };

    /**
     * A validation handler that isn't validating nothing.
     * Can be used to skip validation (at your own risk).
     */
    var NullValidationHandler = /** @class */ (function () {
        function NullValidationHandler() {
        }
        NullValidationHandler.prototype.validateSignature = function (validationParams) {
            return Promise.resolve(null);
        };
        NullValidationHandler.prototype.validateAtHash = function (validationParams) {
            return Promise.resolve(true);
        };
        return NullValidationHandler;
    }());

    function createDefaultLogger() {
        return console;
    }
    function createDefaultStorage() {
        return typeof sessionStorage !== 'undefined'
            ? sessionStorage
            : new MemoryStorage();
    }

    var OAuthModule = /** @class */ (function () {
        function OAuthModule() {
        }
        OAuthModule.forRoot = function (config, validationHandlerClass) {
            if (config === void 0) { config = null; }
            if (validationHandlerClass === void 0) { validationHandlerClass = NullValidationHandler; }
            return {
                ngModule: OAuthModule,
                providers: [
                    OAuthService,
                    UrlHelperService,
                    { provide: OAuthLogger, useFactory: createDefaultLogger },
                    { provide: OAuthStorage, useFactory: createDefaultStorage },
                    { provide: ValidationHandler, useClass: validationHandlerClass },
                    { provide: HashHandler, useClass: DefaultHashHandler },
                    {
                        provide: OAuthResourceServerErrorHandler,
                        useClass: OAuthNoopResourceServerErrorHandler
                    },
                    { provide: OAuthModuleConfig, useValue: config },
                    {
                        provide: http.HTTP_INTERCEPTORS,
                        useClass: DefaultOAuthInterceptor,
                        multi: true
                    }
                ]
            };
        };
        return OAuthModule;
    }());
    OAuthModule.decorators = [
        { type: core.NgModule, args: [{
                    imports: [common.CommonModule],
                    declarations: [],
                    exports: []
                },] }
    ];

    var err = "PLEASE READ THIS CAREFULLY:\n\nBeginning with angular-oauth2-oidc version 9, the JwksValidationHandler\nhas been moved to an library of its own. If you need it for implementing\nOAuth2/OIDC **implicit flow**, please install it using npm:\n\n  npm i angular-oauth2-oidc-jwks --save\n\nAfter that, you can import it into your application:\n\n  import { JwksValidationHandler } from 'angular-oauth2-oidc-jwks';\n\nPlease note, that this dependency is not needed for the **code flow**,\nwhich is nowadays the **recommented** one for single page applications.\nThis also results in smaller bundle sizes.\n";
    /**
     * This is just a dummy of the JwksValidationHandler
     * telling the users that the real one has been moved
     * to an library of its own, namely angular-oauth2-oidc-utils
     */
    var JwksValidationHandler = /** @class */ (function (_super) {
        __extends(JwksValidationHandler, _super);
        function JwksValidationHandler() {
            var _this = _super.call(this) || this;
            console.error(err);
            return _this;
        }
        return JwksValidationHandler;
    }(NullValidationHandler));

    var AUTH_CONFIG = new core.InjectionToken('AUTH_CONFIG');

    /**
     * Generated bundle index. Do not edit.
     */

    exports.AUTH_CONFIG = AUTH_CONFIG;
    exports.AbstractValidationHandler = AbstractValidationHandler;
    exports.AuthConfig = AuthConfig;
    exports.DefaultOAuthInterceptor = DefaultOAuthInterceptor;
    exports.JwksValidationHandler = JwksValidationHandler;
    exports.LoginOptions = LoginOptions;
    exports.MemoryStorage = MemoryStorage;
    exports.NullValidationHandler = NullValidationHandler;
    exports.OAuthErrorEvent = OAuthErrorEvent;
    exports.OAuthEvent = OAuthEvent;
    exports.OAuthInfoEvent = OAuthInfoEvent;
    exports.OAuthLogger = OAuthLogger;
    exports.OAuthModule = OAuthModule;
    exports.OAuthModuleConfig = OAuthModuleConfig;
    exports.OAuthNoopResourceServerErrorHandler = OAuthNoopResourceServerErrorHandler;
    exports.OAuthResourceServerConfig = OAuthResourceServerConfig;
    exports.OAuthResourceServerErrorHandler = OAuthResourceServerErrorHandler;
    exports.OAuthService = OAuthService;
    exports.OAuthStorage = OAuthStorage;
    exports.OAuthSuccessEvent = OAuthSuccessEvent;
    exports.ReceivedTokens = ReceivedTokens;
    exports.UrlHelperService = UrlHelperService;
    exports.ValidationHandler = ValidationHandler;
    exports.ɵa = HashHandler;
    exports.ɵb = DefaultHashHandler;
    exports.ɵc = createDefaultLogger;
    exports.ɵd = createDefaultStorage;

    Object.defineProperty(exports, '__esModule', { value: true });

})));
//# sourceMappingURL=angular-oauth2-oidc.umd.js.map
