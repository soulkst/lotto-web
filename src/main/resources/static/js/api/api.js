define(["StringUtils", "json!api/api.json"], function(StringUtils, ApiData) {
    const methods = ApiData.methods;
    const apiVersion = ApiData.version;
    const baseUri = ApiData.baseUri;

    return (function(host) {
        const call = function(uri, method, contentType, body, successCallback, errorCallback) {
            const url = host + baseUri + "/" + apiVersion + uri;
            console.log("request url", url);
            try {
                $.ajax({
                    url: url,
                    data: body,
                    contentType: contentType,
                    method: method,
                }).done(function(body) {
                    if(successCallback) {
                        successCallback(body);
                    }
                }).fail(function(xhr, status, error) {
                    if(errorCallback) {
                        errorCallback(xhr, status, error);
                    }
                });
            } catch (err) {
                if(errorCallback) {
                    errorCallback(err);
                }
            }
        }

        const functions = {};
        for(var idx in methods) {
            const method = methods[idx];
            functions[method] = function(uri, contentType, data, successCallback, errorCallback) {
                call(uri, method, contentType, data, successCallback, errorCallback);
            }
        }
        return functions;
    });
});