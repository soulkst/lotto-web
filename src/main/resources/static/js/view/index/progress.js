define(["json!view/index/index.json"], function(IndexData) {
    const Progress = (function() {
        Progress.prototype.hide =function(selector) {
            $(selector).hide();
        };
        Progress.prototype.show = function(selector) {
            $(selector + "> i").attr("class", "").addClass(IndexData.common.css.in_progress);
            $(selector).show();
        };
        Progress.prototype.disable = function(selector) {
            $(selector + "> i").attr("class", "").addClass(IndexData.common.css.disable_view);
            $(selector).show();
        }
    })

    return new Progress();
});