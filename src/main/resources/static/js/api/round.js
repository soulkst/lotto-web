define(["StringUtils", "api/api", "json!api/api.json"], function(StringUtils, Api, ApiData) {
    return (function(host) {
        const api = new Api(host);
        return {
            getLast: function(successCallback, errorCallback) {
                api.get(ApiData.api.round.getLastRound, null, null, successCallback, errorCallback);
            }
        }
    });
});