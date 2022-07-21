define([], function() {
    const V_LEVELS = ["success", "info", "error", "warning"];

    const Notification = (function() {
        const show = function(level, message) {
            toastr[level](message);
        }

        for(var idx in V_LEVELS) {
            const level = V_LEVELS[idx];
            Notification.prototype[level] = function(message, isToggle) {
                show(level, message, isToggle)
            }
        }

        return Notification.prototype;
    });
    return Notification;
});