define(["StringUtils", "api/api", "json!api/api.json"], function(StringUtils, Api, ApiData) {
    return (function(host) {
        const api = new Api(host);
        return {
            scrap: function(successCallback, errorCallback) {
                const uri = ApiData.api.batch.execScrap;
                console.log("request uri", uri);
                api.post(uri, null, null, successCallback, errorCallback);
            }
        }
    });
});