define(["StringUtils", "api/api", "json!api/api.json"], function(StringUtils, Api, ApiData) {
    return (function(host) {
        const api = new Api(host);
        return {
            getPrediction: function(successCallback, errorCallback) {
                const uri = ApiData.api.prediction.getAllPredictions;
                console.log("request uri", uri);
                api.get(uri, null, null, successCallback, errorCallback);
            },

            getLastPredictionResult: function(roundNo, successCallback, errorCallback) {
                const uri = StringUtils.format(ApiData.api.prediction.getLastPredictions, roundNo);
                console.log("request uri", uri);
                api.get(uri, null, null, successCallback, errorCallback);
            },

        }
    });
});