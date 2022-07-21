define(["StringUtils", "json!view/index/index.json"], function(StringUtils, IndexData) {
    const RoundNumber = (function() {
        RoundNumber.prototype.setNumber = function(selector, numbers, callback) {
            const $numbersElement = $(selector);
            $(numbers).each(function(idx, number) {
                $numbersElement.find(StringUtils.format(IndexData.common.selector.last_round_number, idx))
                    .addClass(StringUtils.format(IndexData.common.css.round_number_color, Math.trunc(number/10)))
                    .html(number);
            }).promise().done(function() {
                console.log("Set numbers to ", selector);
                if(callback) {
                    callback();
                }
            });
        }

        return RoundNumber.prototype;
    })
    return new RoundNumber();
})