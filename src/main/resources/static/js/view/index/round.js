define(["Notification", "StringUtils"
    , "api/round"
    , "view/index/progress", "view/index/round_number"
    , "json!view/index/index.json", "json!message/msg.json"]
, function(Notification, StringUtils
           , RoundApi
           , Progress, RoundNumber
           , IndexData, MessageData) {

    const roundApi = new RoundApi("");
    const notification = new Notification();

    const setView = function(success, data) {
        Progress.hide(IndexData.round.selector.last_round_progress);
        if(success && data.roundNo) {
            const $drawDate = $(IndexData.round.selector.last_round_draw_date);

            $drawDate.text(StringUtils.format(MessageData.round.last_round_title, data.drawDate, data.roundNo));

            const numbers = data.numbers;
            if(!numbers || numbers.length < 1) {
                notification.warning(MessageData.round.empty_last_round);
                return;
            }
            numbers.push(data.bonus);

            RoundNumber.setNumber(IndexData.round.selector.last_round_numbers, numbers, function() {
                console.log("Loaded last win round");
            })
        } else {
            notification.error(MessageData.round.error_get_last_round);
            Progress.disable(IndexData.round.selector.last_round_progress);
        }
    }
    return (function() {
        return {
            getLast: function() {
                Progress.show(IndexData.round.selector.last_round_progress);
                roundApi.getLast(function(data) {
                    setView(true, data)
                }, function(xhr, status, error) {
                    setView(false, xhr, error);
                });
            }
        }
    })
})