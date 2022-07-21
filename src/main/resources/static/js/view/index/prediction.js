define(["StringUtils", "Notification"
    , "api/prediction", "chart/bar_chart"
    , "view/index/progress", "view/index/round_number"
    , "json!view/index/index.json", "json!message/msg.json"]
, function(StringUtils, Notification
           , PredictionApi, BarChart
           , Progress, RoundNumber
           , IndexData, MessageData) {

    const notification = new Notification();
    const predictionApi = new PredictionApi("");
    const chart = new BarChart();

    return (function() {
        return {
            getPrediction: function() {
                Progress.show(IndexData.prediction.selector.chart_progress);
                Progress.show(IndexData.prediction.selector.last_prediction_progress);

                predictionApi.getPrediction(function(data) {
                    Progress.hide(IndexData.prediction.selector.chart_progress);
                    const labels = [];
                    const appearRate = [];

                    let totalCount = 0;
                    for(let number in data.numberMap) {
                        labels.push(number);
                        totalCount += data.numberMap[number];
                        appearRate.push(data.numberMap[number] * 100 / data.roundNo);
                    }

                    chart.draw(IndexData.prediction.selector.chart
                        , labels, {color: IndexData.prediction.value.chart_color, data: appearRate});

                    Progress.hide(IndexData.prediction.selector.last_prediction_progress);
                    predictionApi.getLastPredictionResult(data.roundNo + 1, function(resultData) {
                        $(resultData).each(function(idx, dataItem) {
                            const selector = StringUtils.format(IndexData.prediction.selector.last_prediction_numbers
                                , dataItem.predictionType.toLowerCase());
                            const sortedNumbers = dataItem.numbers.sort(function(a, b) {
                                return a-b;
                            })
                            RoundNumber.setNumber(selector, sortedNumbers, function() {
                                console.log("Loaded last prediction. type", dataItem.predictionType);
                            });
                        }).promise().done(function() {
                            console.log("Loaded last prediction numbers");
                        })
                    }, function(xhr, status, error) {
                        notification.error(MessageData.prediction.error_get_last_predictions);
                        Progress.disable(IndexData.prediction.selector.last_prediction_progress);
                        console.error("Cannot get last prediction", xhr.data);
                    })

                }, function(xhr, status, error) {
                    notification.error(MessageData.prediction.error_get_predictions);
                    Progress.disable(IndexData.prediction.selector.chart_progress);
                    Progress.disable(IndexData.prediction.selector.last_prediction_progress);
                    console.error("Cannot get prediction numbers", xhr.data);
                })
            }
        }
    });
});