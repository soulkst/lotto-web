define(["StringUtils", "Notification"
    , "json!view/index/index.json"
    , "view/index/setting", "view/index/round", "view/index/prediction"
], function(StringUtils, Notification
    , IndexData
    , Setting, Round, Prediction) {

    const setting = new Setting();
    const round = new Round();
    const prediction = new Prediction();

    $(document).ready(function() {
        round.getLast();
        prediction.getPrediction();

        $(IndexData.setting.selector.exec_scrap_batch).click(function(e) {
            setting.execBatch(function() {
                round.getLast();
                prediction.getPrediction();
            })
        });
    });
});