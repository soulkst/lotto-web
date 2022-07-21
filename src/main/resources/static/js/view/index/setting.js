define(["Notification", "json!message/msg.json", "api/batch"]
    , function(Notification, MessageData, BatchApi) {
    const notification = new Notification();
    const batchApi = new BatchApi("");

    return (function () {
        return {
            execBatch: function(callback) {
                notification.info(MessageData.setting.start_scrap)
                batchApi.scrap(function() {
                    notification.success(MessageData.setting.success_scrap);
                    if(callback) {
                        callback();
                    }
                }, function(xhr, status, error) {
                    notification.error(MessageData.setting.error_scrap);
                })
            }
        }
    });
})